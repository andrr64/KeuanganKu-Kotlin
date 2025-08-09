package com.andreas.keuangankuplus.presentation.ui.component

import androidx.compose.foundation.background
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andreas.keuangankuplus.domain.model.GoalModel

@Composable
fun GoalItem(
    goal: GoalModel,
    isDarkTheme: Boolean,
    ketikaChecklist: (Boolean) -> Unit
) {
    val progressColor = if (goal.achieved) Color.Green else Color.Red

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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Lingkaran status
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(end = 8.dp)
                        .background(progressColor, shape = CircleShape)
                )

                // Judul goal
                Text(
                    text = goal.name,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDarkTheme) Color.White else Color.Black,
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
                    onCheckedChange = ketikaChecklist
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        // Baris 2: Nominal
        Text(
            "Rp ${goal.collected} / Rp ${goal.target}",
            style = MaterialTheme.typography.bodySmall,
            color = if (isDarkTheme) Color.Gray else Color.DarkGray
        )
    }
}
