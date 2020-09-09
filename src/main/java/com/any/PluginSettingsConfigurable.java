package com.any;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PluginSettingsConfigurable implements Configurable {
    private PluginSettingsComponent pluginSettingsComponent;
    private final Project project;

    public PluginSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Any Plugin Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        pluginSettingsComponent = new PluginSettingsComponent(project);
        return pluginSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        PluginSettingsState pluginSettingsState = PluginSettingsState.getInstance();
        return !pluginSettingsState.baseClass.equals(pluginSettingsComponent.getBaseClassText());
    }

    @Override
    public void apply() throws ConfigurationException {
        PluginSettingsState pluginSettingsState = PluginSettingsState.getInstance();
        pluginSettingsState.baseClass = pluginSettingsComponent.getBaseClassText();
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return pluginSettingsComponent.getPreferredFocusedComponent();
    }

    @Override
    public void reset() {
        PluginSettingsState pluginSettingsState = PluginSettingsState.getInstance();
        pluginSettingsComponent.setBaseClassText(pluginSettingsState.baseClass);
    }

    @Override
    public void disposeUIResources() {
        pluginSettingsComponent = null;
    }
}
