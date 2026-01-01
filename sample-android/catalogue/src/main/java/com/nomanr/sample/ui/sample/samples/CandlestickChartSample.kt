package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.ui.components.*
import com.nomanr.sample.ui.AppTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandlestickChartSample() {
    var selectedTimeFrame by remember { mutableStateOf(TimeFrame.DAILY) }
    var selectedCandle by remember { mutableStateOf<CandlestickData?>(null) }
    
    // 生成模拟股票数据
    val stockData = remember {
        generateMockStockData()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        // 顶部标题栏
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "股票K线图",
                    style = AppTheme.typography.h4,
                    color = AppTheme.colors.onBackground
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = AppTheme.colors.background
            )
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 基础K线图示例
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colors.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "基础K线图",
                            style = AppTheme.typography.h5,
                            color = AppTheme.colors.onSurface
                        )
                        
                        Text(
                            text = "支持双指缩放和平移操作",
                            style = AppTheme.typography.body2,
                            color = AppTheme.colors.textSecondary
                        )
                        
                        CandlestickChart(
                            data = stockData.take(30),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            timeFrame = selectedTimeFrame,
                            onTimeFrameChange = { newTimeFrame ->
                                selectedTimeFrame = newTimeFrame
                            }
                        )
                    }
                }
            }
            
            // 不同时间维度的K线图
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colors.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "时间维度切换",
                            style = AppTheme.typography.h5,
                            color = AppTheme.colors.onSurface
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TimeFrame.values().forEach { timeFrame ->
                                FilterChip(
                                    selected = selectedTimeFrame == timeFrame,
                                    onClick = { selectedTimeFrame = timeFrame },
                                    label = {
                                        Text(
                                            text = when (timeFrame) {
                                                TimeFrame.DAILY -> "日K"
                                                TimeFrame.WEEKLY -> "周K"
                                                TimeFrame.MONTHLY -> "月K"
                                                TimeFrame.YEARLY -> "年K"
                                            }
                                        )
                                    }
                                )
                            }
                        }
                        
                        CandlestickChart(
                            data = stockData.take(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            timeFrame = selectedTimeFrame,
                            onTimeFrameChange = { newTimeFrame ->
                                selectedTimeFrame = newTimeFrame
                            }
                        )
                    }
                }
            }
            
            // 交互式K线图
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colors.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "交互式K线图",
                            style = AppTheme.typography.h5,
                            color = AppTheme.colors.onSurface
                        )
                        
                        selectedCandle?.let { candle ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = AppTheme.colors.primary.copy(alpha = 0.1f)
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "选中K线数据",
                                        style = AppTheme.typography.body2,
                                        color = AppTheme.colors.primary
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "开盘: ${candle.open}",
                                            style = AppTheme.typography.body2,
                                            color = AppTheme.colors.onSurface
                                        )
                                        Text(
                                            text = "收盘: ${candle.close}",
                                            style = AppTheme.typography.body2,
                                            color = AppTheme.colors.onSurface
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "最高: ${candle.high}",
                                            style = AppTheme.typography.body2,
                                            color = AppTheme.colors.onSurface
                                        )
                                        Text(
                                            text = "最低: ${candle.low}",
                                            style = AppTheme.typography.body2,
                                            color = AppTheme.colors.onSurface
                                        )
                                    }
                                    Text(
                                        text = "成交量: ${candle.volume}",
                                        style = AppTheme.typography.body2,
                                        color = AppTheme.colors.onSurface
                                    )
                                }
                            }
                        }
                        
                        CandlestickChart(
                            data = stockData.take(40),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            timeFrame = selectedTimeFrame,
                            onCandleClick = { candle ->
                                selectedCandle = candle
                            }
                        )
                    }
                }
            }
            
            // 自定义样式的K线图
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colors.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "自定义样式",
                            style = AppTheme.typography.h5,
                            color = AppTheme.colors.onSurface
                        )
                        
                        val customStyle = CandlestickChartStyle(
                            risingColor = AppTheme.colors.success.copy(alpha = 0.8f),
                            fallingColor = AppTheme.colors.error.copy(alpha = 0.8f),
                            neutralColor = AppTheme.colors.outline,
                            gridColor = AppTheme.colors.outline.copy(alpha = 0.2f),
                            textColor = AppTheme.colors.textSecondary,
                            backgroundColor = AppTheme.colors.background,
                            candleWidth = 6.dp,
                            candleSpacing = 3.dp,
                            gridStrokeWidth = 0.5.dp,
                            axisTextSize = 8.dp,
                            timeAxisHeight = 20.dp
                        )
                        
                        CandlestickChart(
                            data = stockData.take(25),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            timeFrame = selectedTimeFrame,
                            style = customStyle,
                            enableZoom = false,
                            enablePan = false
                        )
                    }
                }
            }
        }
    }
}

// 生成模拟股票数据
private fun generateMockStockData(): List<CandlestickData> {
    val data = mutableListOf<CandlestickData>()
    val currentTime = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()
    
    var basePrice = 100f
    var currentDate = currentTime.toLocalDateTime(timeZone).date
    
    repeat(100) { index ->
        val timestamp = currentDate.atTime(9, 30).toInstant(timeZone).toEpochMilliseconds()
        
        // 模拟价格波动
        val volatility = 0.02f // 2% 波动率
        val trend = if (index % 7 == 0) -0.01f else 0.005f // 每周一次下跌趋势
        
        val open = basePrice * (1 + (Math.random() - 0.5) * volatility * 0.5f)
        val close = open * (1 + (Math.random() - 0.5) * volatility + trend)
        val high = maxOf(open, close) * (1 + Math.random() * volatility * 0.5f)
        val low = minOf(open, close) * (1 - Math.random() * volatility * 0.5f)
        val volume = (1000000 + Math.random() * 2000000).toLong()
        
        data.add(
            CandlestickData(
                timestamp = timestamp,
                open = open,
                high = high.toFloat(),
                low = low.toFloat(),
                close = close,
                volume = volume
            )
        )
        
        basePrice = close
        currentDate = currentDate.minus(1, DateTimeUnit.DAY)
    }
    
    return data.reversed()
}