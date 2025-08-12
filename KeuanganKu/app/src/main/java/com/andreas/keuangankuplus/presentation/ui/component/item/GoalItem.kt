package com.andreas.keuangankuplus.presentation.ui.component.item

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.surfaceColorAtElevation
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
    whenClicked: () -> Unit,
    whenCheckPressed: () -> Unit
) {
    val progressColor = if (goal.achieved) {
        Color(0xFF4CAF50) // hijau lebih soft
    } else {
        Color(0xFFE53935) // merah agak soft
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { whenClicked() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .background(progressColor, shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
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
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                    Spacer(Modifier.width(6.dp))
                    Checkbox(
                        checked = goal.achieved,
                        onCheckedChange = { whenCheckPressed() }
                    )
                }
            }

            if (!goal.achieved) {
                goal.target?.let { targetValue ->
                    Spacer(Modifier.height(12.dp))

                    val progress = if (targetValue > 0) {
                        (goal.collected.toFloat() / targetValue.toFloat()).coerceIn(0f, 1f)
                    } else 0f
                    val percentage = (progress * 100).roundToInt()

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = progressColor,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    )

                    Spacer(Modifier.height(6.dp))

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
}
