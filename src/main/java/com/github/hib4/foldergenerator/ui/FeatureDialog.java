package com.github.hib4.foldergenerator.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FeatureDialog extends DialogWrapper {
    private JPanel contentPanel;
    private JTextField nameTextField;

    public FeatureDialog(Project project) {
        super(project);
        this.init();
        this.setTitle("Folder Generator");
    }

    public String getName() {
        return nameTextField != null ? nameTextField.getText() : null;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return contentPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return nameTextField;
    }
}
