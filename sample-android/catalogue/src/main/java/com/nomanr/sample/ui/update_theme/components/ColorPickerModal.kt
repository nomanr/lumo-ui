package com.nomanr.sample.ui.update_theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.configs.LocalAppConfigState
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Text

@Composable
fun ColorPickerModal(isVisible: Boolean, onSelectColor: (Color) -> Unit, onDismiss: () -> Unit) {
    val appConfigState = LocalAppConfigState.current

//    ModalBottomSheet(
//        modifier = Modifier.padding(top = 36.dp), isVisible = isVisible, onDismissRequest = onDismiss
//    ) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
//        ) {
//
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                Text("Color Palette", style = AppTheme.typography.h2)
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            ColorPalettes.entries.forEach { entry ->
//                ColorSection(title = entry.key, entry.value, onSelectColor)
//            }
//        }
//    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSection(title: String, colors: List<Color>, onSelectColor: (Color) -> Unit) {

    Spacer(modifier = Modifier.height(16.dp))

    Text(title, style = AppTheme.typography.h4)

    Spacer(modifier = Modifier.height(8.dp))

    BoxWithConstraints {
        val itemWith = ((maxWidth - 8.dp * 4) / 5)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            colors.forEach { color ->
                Box(modifier = Modifier
                    .size(width = itemWith, height = 56.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
                    .clickable { onSelectColor(color) })
            }
        }
    }
}


val ColorPalettes: Map<String, List<Color>> = mapOf(
    "Neutral" to listOf(
        Color(0xFF0E121B),
        Color(0xFF181B25),
        Color(0xFF222530),
        Color(0xFF2B303B),
        Color(0xFF525866),
        Color(0xFF717784),
        Color(0xFF99A0AE),
        Color(0xFFCACFD8),
        Color(0xFFE1E4EA),
        Color(0xFFF2F5F8),
        Color(0xFFF5F7FA),
        Color(0xFFFFFFFF),
    ), "Blue" to listOf(
        Color(0xFF122368),
        Color(0xFF182F8B),
        Color(0xFF1F3BAD),
        Color(0xFF2547D0),
        Color(0xFF3559E9),
        Color(0xFF335CFF),
        Color(0xFF6895FF),
        Color(0xFF97BAFF),
        Color(0xFFC0D5FF),
        Color(0xFFD5E2FF),
        Color(0xFFEBF1FF),
    ), "Orange" to listOf(
        Color(0xFF683412),
        Color(0xFF8B4618),
        Color(0xFFAD581F),
        Color(0xFFD06925),
        Color(0xFFE97D35),
        Color(0xFFFF9147),
        Color(0xFFFFA468),
        Color(0xFFFFC197),
        Color(0xFFFFD9C0),
        Color(0xFFFFE6D5),
        Color(0xFFFFF3EB),
    ), "Red" to listOf(
        Color(0xFF681219),
        Color(0xFF8B1822),
        Color(0xFFAD1F2B),
        Color(0xFFD02533),
        Color(0xFFE93544),
        Color(0xFFFB3748),
        Color(0xFFFF6875),
        Color(0xFFFF97A0),
        Color(0xFFFFC0C5),
        Color(0xFFFFD5D8),
        Color(0xFFFFEBEC),
    ), "Green" to listOf(
        Color(0xFF0B4627),
        Color(0xFF16643B),
        Color(0xFF1A7544),
        Color(0xFF178C4E),
        Color(0xFF1DAF61),
        Color(0xFF1FC16B),
        Color(0xFF3EE089),
        Color(0xFF84EBB4),
        Color(0xFFC2F5DA),
        Color(0xFFD0FBE9),
        Color(0xFFE0FAEC),
    ), "Yellow" to listOf(
        Color(0xFF624C18),
        Color(0xFF86661D),
        Color(0xFFA78025),
        Color(0xFFC99A2C),
        Color(0xFFE6A819),
        Color(0xFFF6B51E),
        Color(0xFFFFD268),
        Color(0xFFFFE097),
        Color(0xFFFFECC0),
        Color(0xFFFFEFCC),
        Color(0xFFFFF4D6),
    ), "Purple" to listOf(
        Color(0xFF351A75),
        Color(0xFF3D1D86),
        Color(0xFF4C25A7),
        Color(0xFF5B2CC9),
        Color(0xFF693EE0),
        Color(0xFF7D52F4),
        Color(0xFF8C71F6),
        Color(0xFFA897FF),
        Color(0xFFCAC0FF),
        Color(0xFFDCD5FF),
        Color(0xFFEFEBFF),
    ), "Sky" to listOf(
        Color(0xFF124B68),
        Color(0xFF18658B),
        Color(0xFF1F7EAD),
        Color(0xFF2597D0),
        Color(0xFF35ADE9),
        Color(0xFF47C2FF),
        Color(0xFF68CDFF),
        Color(0xFF97DCFF),
        Color(0xFFC0EAFF),
        Color(0xFFD5F1FF),
        Color(0xFFEBF8FF),
    ), "Pink" to listOf(
        Color(0xFF68123D),
        Color(0xFF8B1852),
        Color(0xFFAD1F66),
        Color(0xFFD0257A),
        Color(0xFFE9358F),
        Color(0xFFFB4BA3),
        Color(0xFFFF68B3),
        Color(0xFFFF97CB),
        Color(0xFFFFC0DF),
        Color(0xFFFFD5EA),
        Color(0xFFFFEBF4),
    ), "Teal" to listOf(
        Color(0xFF0B463E),
        Color(0xFF16645A),
        Color(0xFF1A7569),
        Color(0xFF178C7D),
        Color(0xFF1DAF9C),
        Color(0xFF22D3BB),
    )
)