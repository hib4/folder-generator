package com.github.hib4.presentationfoldergenerator.generator

import com.github.hib4.presentationfoldergenerator.ui.Notifier
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

interface Generator {
    companion object {
        fun createFolder(
                project: Project,
                folder: VirtualFile,
                parent: String
        ): Map<String, VirtualFile>? {
            try {
                for (child in folder.children) {
                    if (child.name == parent) {
                        Notifier.warning(project, "Directory [$parent] already exists")
                        return null
                    }
                }
                val mapOfFolder = mutableMapOf<String, VirtualFile>()
                mapOfFolder[parent] = folder.createChildDirectory(folder, parent)
                return mapOfFolder
            } catch (e: IOException) {
                Notifier.warning(project, "Couldn't create $parent directory")
                e.printStackTrace()
                return null
            }
        }
    }
}