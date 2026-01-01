package com.nomanr.lumo.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nomanr.lumo.ui.AppTheme
import kotlin.math.max
import kotlin.math.min

enum class TimeFrame {
    DAILY, WEEKLY, MONTHLY, YEARLY
}

data class CandlestickData(
    val timestamp: Long,
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float,
    val volume: Long = 0
) {
    val isRising: Boolean get() = close > open
    val isFalling: Boolean get() = close < open
    val isNeutral: Boolean get() = close == open
}

@Composable
fun CandlestickChart(
    data: List<CandlestickData>,
    modifier: Modifier = Modifier,
    timeFrame: TimeFrame = TimeFrame.DAILY,
    enableZoom: Boolean = true,
    enablePan: Boolean = true,
    onTimeFrameChange: ((TimeFrame) -> Unit)? = null
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    val textMeasurer = rememberTextMeasurer()
    
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background)
    ) {
        Column {
            // 时间维度选择器
            if (onTimeFrameChange != null) {
                TimeFrameSelector(
                    selectedTimeFrame = timeFrame,
                    onTimeFrameSelected = onTimeFrameChange
                )
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(AppTheme.colors.background)
                    .then(
                        if (enableZoom || enablePan) {
                            Modifier.pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown()
                                    do {
                                        val event = awaitPointerEvent()
                                        val zoom = event.calculateZoom()
                                        val pan = event.calculatePan()
                                        
                                        if (enableZoom && zoom != 1f) {
                                            scale = (scale * zoom).coerceIn(0.5f, 5f)
                                        }
                                        
                                        if (enablePan) {
                                            offsetX += pan.x
                                        }
                                    } while (event.changes.any { it.pressed })
                                }
                            }
                        } else {
                            Modifier
                        }
                    )
            ) {
                if (data.isEmpty()) {
                    EmptyChartState()
                } else {
                    CandlestickChartContent(
                        data = data,
                        scale = scale,
                        offsetX = offsetX,
                        textMeasurer = textMeasurer
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeFrameSelector(
    selectedTimeFrame: TimeFrame,
    onTimeFrameSelected: (TimeFrame) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TimeFrame.values().forEach { timeFrame ->
            val isSelected = timeFrame == selectedTimeFrame
            Button(
                onClick = { onTimeFrameSelected(timeFrame) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) {
                        AppTheme.colors.primary
                    } else {
                        AppTheme.colors.primary.copy(alpha = 0.1f)
                    },
                    contentColor = if (isSelected) {
                        AppTheme.colors.onPrimary
                    } else {
                        AppTheme.colors.primary
                    }
                ),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = when (timeFrame) {
                        TimeFrame.DAILY -> "日K"
                        TimeFrame.WEEKLY -> "周K"
                        TimeFrame.MONTHLY -> "月K"
                        TimeFrame.YEARLY -> "年K"
                    },
                    style = AppTheme.typography.button,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun EmptyChartState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "No data available",
                style = AppTheme.typography.body2,
                color = AppTheme.colors.textSecondary
            )
        }
    }
}

@Composable
private fun CandlestickChartContent(
    data: List<CandlestickData>,
    scale: Float,
    offsetX: Float,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val maxPrice = remember(data) { data.maxOf { it.high } }
    val minPrice = remember(data) { data.minOf { it.low } }
    val priceRange = maxPrice - minPrice
    val pricePadding = priceRange * 0.1f
    
    val adjustedMinPrice = minPrice - pricePadding
    val adjustedMaxPrice = maxPrice + pricePadding
    
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val chartHeight = canvasHeight * 0.85f
        val chartTop = 0f
        val chartBottom = chartHeight
        
        // 绘制网格和价格轴
        drawPriceGrid(
            canvasWidth = canvasWidth,
            chartTop = chartTop,
            chartHeight = chartHeight,
            minPrice = adjustedMinPrice,
            maxPrice = adjustedMaxPrice,
            textMeasurer = textMeasurer
        )
        
        // 绘制K线
        drawCandles(
            data = data,
            canvasWidth = canvasWidth,
            chartTop = chartTop,
            chartBottom = chartBottom,
            minPrice = adjustedMinPrice,
            maxPrice = adjustedMaxPrice,
            scale = scale,
            offsetX = offsetX
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawPriceGrid(
    canvasWidth: Float,
    chartTop: Float,
    chartHeight: Float,
    minPrice: Float,
    maxPrice: Float,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val gridLines = 5
    val gridColor = AppTheme.colors.outline.copy(alpha = 0.3f)
    
    for (i in 0..gridLines) {
        val y = chartTop + (chartHeight / gridLines) * i
        val price = maxPrice - (maxPrice - minPrice) * (i.toFloat() / gridLines)
        
        // 网格线
        drawLine(
            color = gridColor,
            start = Offset(0f, y),
            end = Offset(canvasWidth, y),
            strokeWidth = 1.dp.toPx()
        )
        
        // 价格标签
        val priceText = "%.2f".format(price)
        val textLayoutResult = textMeasurer.measure(
            text = priceText,
            style = TextStyle(
                color = AppTheme.colors.textSecondary,
                fontSize = 10.sp
            )
        )
        
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(8f, y - textLayoutResult.size.height / 2)
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawCandles(
    data: List<CandlestickData>,
    canvasWidth: Float,
    chartTop: Float,
    chartBottom: Float,
    minPrice: Float,
    maxPrice: Float,
    scale: Float,
    offsetX: Float
) {
    if (data.isEmpty()) return
    
    val candleWidth = 8.dp.toPx()
    val candleSpacing = 4.dp.toPx()
    val totalWidth = candleWidth + candleSpacing
    val scaledWidth = totalWidth * scale
    val startX = offsetX.coerceAtLeast(0f)
    
    val risingColor = AppTheme.colors.success
    val fallingColor = AppTheme.colors.error
    val neutralColor = AppTheme.colors.outline
    
    data.forEachIndexed { index, candle ->
        val x = startX + index * scaledWidth + candleSpacing * scale + scaledWidth / 2
        
        if (x + candleWidth * scale < 0 || x > canvasWidth) {
            return@forEachIndexed
        }
        
        val highY = chartBottom - ((candle.high - minPrice) / (maxPrice - minPrice)) * (chartBottom - chartTop)
        val lowY = chartBottom - ((candle.low - minPrice) / (maxPrice - minPrice)) * (chartBottom - chartTop)
        val openY = chartBottom - ((candle.open - minPrice) / (maxPrice - minPrice)) * (chartBottom - chartTop)
        val closeY = chartBottom - ((candle.close - minPrice) / (maxPrice - minPrice)) * (chartBottom - chartTop)
        
        val candleColor = when {
            candle.isRising -> risingColor
            candle.isFalling -> fallingColor
            else -> neutralColor
        }
        
        val scaledCandleWidth = candleWidth * scale
        val candleLeft = x - scaledCandleWidth / 2
        val candleRight = x + scaledCandleWidth / 2
        
        // 绘制上下影线
        drawLine(
            color = candleColor,
            start = Offset(x, highY),
            end = Offset(x, lowY),
            strokeWidth = 1.dp.toPx()
        )
        
        // 绘制实体
        val bodyTop = minOf(openY, closeY)
        val bodyBottom = maxOf(openY, closeY)
        val bodyHeight = bodyBottom - bodyTop
        
        if (candle.isRising) {
            // 阳线 - 空心
            drawRect(
                color = candleColor,
                topLeft = Offset(candleLeft, bodyTop),
                size = androidx.compose.ui.geometry.Size(scaledCandleWidth, bodyHeight),
                style = Stroke(width = 1.dp.toPx())
            )
        } else {
            // 阴线 - 实心
            drawRect(
                color = candleColor,
                topLeft = Offset(candleLeft, bodyTop),
                size = androidx.compose.ui.geometry.Size(scaledCandleWidth, bodyHeight)
            )
        }
    }
}

// 预览函数
@Composable
@androidx.compose.runtime.Composable
fun CandlestickChartPreview() {
    val sampleData = listOf(
        CandlestickData(1, 100f, 105f, 98f, 103f),
        CandlestickData(2, 103f, 104f, 101f, 102f),
        CandlestickData(3, 102f, 108f, 101f, 107f),
        CandlestickData(4, 107f, 109f, 105f, 106f),
        CandlestickData(5, 106f, 110f, 104f, 108f),
        CandlestickData(6, 108f, 105f, 103f, 104f),
        CandlestickData(7, 104f, 107f, 102f, 105f),
        CandlestickData(8, 105f, 103f, 99f, 100f),
        CandlestickData(9, 100f, 102f, 98f, 101f),
        CandlestickData(10, 101f, 106f, 100f, 105f)
    )
    
    var currentTimeFrame by remember { mutableStateOf(TimeFrame.DAILY) }
    
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Stock K-line Chart", style = AppTheme.typography.h4)
            
            CandlestickChart(
                data = sampleData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                timeFrame = currentTimeFrame,
                onTimeFrameChange = { newTimeFrame ->
                    currentTimeFrame = newTimeFrame
                }
            )
        }
    }
}