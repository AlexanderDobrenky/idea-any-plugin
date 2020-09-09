package com.any;

import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.psi.PsiClass;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class PluginSettingsComponent {
    private final JPanel pluginSettingsPanel;
    private final JBTextField baseClassTextField = new JBTextField();
    private final TreeClassChooser treeClassChooser;

    public PluginSettingsComponent(Project project) {
        treeClassChooser = TreeClassChooserFactory.getInstance(project).createProjectScopeChooser("Choose class ...");

        ComponentWithBrowseButton<JBTextField> classComponent = new ComponentWithBrowseButton<>(baseClassTextField, e -> {
            treeClassChooser.showDialog();
            PsiClass psiClass = treeClassChooser.getSelected();
            if (psiClass != null) {
                baseClassTextField.setText(psiClass.getQualifiedName());
            }
        });

        pluginSettingsPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("Class", classComponent, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return pluginSettingsPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return baseClassTextField;
    }

    @NotNull
    public String getBaseClassText() {
        return baseClassTextField.getText();
    }

    public void setBaseClassText(@NotNull String newText) {
        baseClassTextField.setText(newText);
    }
}
