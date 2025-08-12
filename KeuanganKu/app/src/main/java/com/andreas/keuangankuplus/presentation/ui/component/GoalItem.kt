package com.andreas.keuangankuplus.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andreas.keuangankuplus.domain.model.GoalModel
import com.andreas.keuangankuplus.util.formatToCurrency
import kotlin.math.roundToInt

@Composable
fun GoalItem(
    goal: GoalModel,
    whenCheckPressed: (Boolean) -> Unit,
) {
    val isDarkTheme = isSystemInDarkTheme()
    val progressColor = if (goal.achieved) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.error
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) MaterialTheme.colorScheme.surfaceVariant else Color.White,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Lingkaran status
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(progressColor, shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    // Judul goal
                    Text(
                        text = goal.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Status + checklist
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (goal.achieved) "Tercapai" else "Belum",
                        color = progressColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.width(8.dp))
                    Checkbox(
                        checked = goal.achieved,
                        onCheckedChange = whenCheckPressed
                    )
                }
            }

            // Jika target tersedia, tampilkan progress
            goal.target?.let { targetValue ->
                Spacer(Modifier.height(8.dp))

                val progress = if (targetValue > 0) {
                    (goal.collected.toFloat() / targetValue.toFloat()).coerceIn(0f, 1f)
                } else 0f
                val percentage = (progress * 100).roundToInt()

                // Progress bar
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = progressColor,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )

                Spacer(Modifier.height(4.dp))

                // Persentase + collected/target
                Text(
                    text = "$percentage% • ${formatToCurrency(goal.collected)} / ${
                        formatToCurrency(
                            goal.target
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
