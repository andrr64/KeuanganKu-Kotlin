package com.andreas.keuanganku.presentation.ui.features.goal

import androidx.compose.runtime.Composable
import com.andreas.keuanganku.domain.model.GoalModel
import com.andreas.keuanganku.presentation.ui.component.ConfirmationDialog
import com.andreas.keuanganku.presentation.ui.modal.ModalAddGoal
import com.andreas.keuanganku.presentation.ui.modal.ModalEditGoal

@Composable
fun GoalDialogs(
    showAddGoalDialog: Boolean,
    onDismissAdd: () -> Unit,
    onSaveAdd: (String, Double, String) -> Unit,

    showConfirmDelete: Boolean,
    onConfirmDelete: () -> Unit,
    onDismissDelete: () -> Unit,

    selectedGoalToEdit: GoalModel?,
    onDismissEdit: () -> Unit,
    onSaveEdit: (GoalModel, String, Double, String) -> Unit,
    onDeleteFromEdit: () -> Unit
) {
    if (showAddGoalDialog) {
        ModalAddGoal(onDismiss = onDismissAdd, onSave = onSaveAdd)
    }
    if (showConfirmDelete) {
        ConfirmationDialog(
            showDialog = true,
            onConfirm = onConfirmDelete,
            onDismiss = onDismissDelete,
            message = "Apakah kamu yakin ingin menghapus data ini?"
        )
    }
    if (selectedGoalToEdit != null) {
        ModalEditGoal(
            goal = selectedGoalToEdit,
            onDismiss = onDismissEdit,
            onSave = onSaveEdit,
            onDelete = onDeleteFromEdit
        )
    }
}
