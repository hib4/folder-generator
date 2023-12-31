package com.github.hib4.foldergenerator.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project

interface Notifier {
    companion object {
        fun warning(project: Project?, content: String) =
                Notifications.Bus.notify(
                        Notification(
                                "FolderGenerator",
                                "Folder Generator Warning",
                                content,
                                NotificationType.WARNING
                        ), project
                )

        fun error(project: Project?, content: String) =
                Notifications.Bus.notify(
                        Notification(
                                "FolderGenerator",
                                "Folder Generator Error",
                                content,
                                NotificationType.ERROR
                        ), project
                )
    }
}