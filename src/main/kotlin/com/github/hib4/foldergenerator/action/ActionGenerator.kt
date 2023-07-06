package com.github.hib4.foldergenerator.action

import com.github.hib4.foldergenerator.generator.Generator
import com.github.hib4.foldergenerator.ui.FeatureDialog
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.vfs.VirtualFile

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

            val mapOrFalse = Generator.createFolder(
                    project, folder,
                    "data",
                    "repositories"
            ) ?: return@runWriteCommandAction
            mapOrFalse["data"]?.let { data: VirtualFile ->
                Generator.createFolder(
                        project, data,
                        "data_sources",
                        "local", "remote"
                )
            }

            Generator.createFolder(
                    project, folder,
                    "domain",
                    "entities"
            )

            Generator.createFolder(
                    project, folder,
                    "presentation",
                    "bloc", "pages", "widgets"
            )
        }
    }
}