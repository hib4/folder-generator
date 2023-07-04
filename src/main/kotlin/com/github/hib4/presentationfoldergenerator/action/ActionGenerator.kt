package com.github.hib4.presentationfoldergenerator.action

import com.github.hib4.presentationfoldergenerator.generator.Generator
import com.github.hib4.presentationfoldergenerator.ui.FeatureDialog
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction

class ActionGenerator : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val dialog = FeatureDialog(actionEvent.project)
        if (dialog.showAndGet()) {
            generate(actionEvent.dataContext, dialog.name)
        }
    }

    private fun generate(dataContext: DataContext, root: String?) {
        val project = CommonDataKeys.PROJECT.getData(dataContext) ?: return
        val selected = PlatformDataKeys.VIRTUAL_FILE.getData(dataContext) ?: return

        var folder = if (selected.isDirectory) selected else selected.parent
        WriteCommandAction.runWriteCommandAction(project) {
            if (!root.isNullOrBlank()) {
                val result = Generator.createFolder(
                        project, folder, root
                ) ?: return@runWriteCommandAction
                folder = result[root]
            }
            Generator.createFolder(project, folder, "bloc")
            Generator.createFolder(project, folder, "pages")
            Generator.createFolder(project, folder, "widgets")
        }
    }
}