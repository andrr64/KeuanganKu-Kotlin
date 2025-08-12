package com.andreas.keuanganku.presentation.ui.features.home

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class DonutSegment(
    val label: String,
    val value: Float,
    val color: Color
)

val dummySegments = listOf(
    DonutSegment("Director", 27.3f, Color(0xFF7C3AED)),
    DonutSegment("C-Team", 35f, Color(0xFF8B5CF6)),
    DonutSegment("VP", 13f, Color(0xFFA78BFA)),
    DonutSegment("IC", 17f, Color(0xFFC4B5FD)),
    DonutSegment("Manager", 7.7f, Color(0xFFD6D1FE))
)

@Composable
fun DonutChartLegend(
    segments: List<DonutSegment>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        segments.forEach { segment ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color = segment.color, shape = CircleShape)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = segment.label,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${"%.1f".format(segment.value)}%",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun DonutChart(
    segments: List<DonutSegment>,
    modifier: Modifier = Modifier,
) {
    val total = segments.sumOf { it.value.toDouble() }.toFloat()

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            var startAngle = -90f
            val diameter = size.minDimension
            val strokeWidth = diameter / 5f // Lebih tipis, lebih estetik
            val holeRadius = (diameter - strokeWidth) / 2f // Radius lubang tengah

            segments.forEach { segment ->
                val sweepAngle = (segment.value / total) * 360f
                drawArc(
                    color = segment.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(strokeWidth)
                )
                startAngle += sweepAngle
            }

            // Tambahkan lingkaran putih di tengah (lubang donat)
            drawCircle(
                color = Color(0xff),
                radius = holeRadius,
                center = center
            )
        }
    }
}

@Composable
fun ResponsiveDonutLayout(segments: List<DonutSegment>) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        // Di landscape: chart kiri, legend kanan (Row)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DonutChart(
                segments = segments,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp)
            )
            DonutChartLegend(
                segments = segments,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp)
            )
        }
    } else {
        // Di portrait: chart atas, legend bawah (Column)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DonutChart(
                segments = segments,
                modifier = Modifier
                    .size(200.dp) // Tetap ukuran tetap di portrait
                    .padding(8.dp)
            )
            DonutChartLegend(
                segments = segments,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    Scaffold(
        containerColor = colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Header
                Text(
                    text = "Hello, Andreas!",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Track your finances easily and stay on top of your goals.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isDarkTheme) Color.Gray else Color.DarkGray
                )
            }

            item {
                // Row hanya jika layar cukup lebar, jika tidak → Column
                ResponsiveDonutLayout(segments = dummySegments)
            }
        }
    }
}