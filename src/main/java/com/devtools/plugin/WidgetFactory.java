package com.devtools.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.widget.StatusBarEditorBasedWidgetFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WidgetFactory extends StatusBarEditorBasedWidgetFactory {

    @Override
    public @NonNls @NotNull String getId() {
        return "TimerWidget";
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return "TimerWidget";
    }

    @Override
    public @NotNull StatusBarWidget createWidget(@NotNull Project project) {
        return new CustomWidget(project);
    }

    @Override
    public void disposeWidget(@NotNull StatusBarWidget statusBarWidget){
        Disposer.dispose(statusBarWidget);
    }
}
