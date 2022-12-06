package com.devtools.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CustomWidget extends EditorBasedWidget implements StatusBarWidget.TextPresentation {
    String myText = "";

    protected CustomWidget(@NotNull Project project) {
        super(project);
    }

    @Override
    public @NonNls @NotNull String ID() {
        return "TimerWidget";
    }

    @Override
    public @NotNull String getText() {
        return myText;
    }

    @Override
    public float getAlignment() {
        return Component.CENTER_ALIGNMENT;
    }

    @Override
    public @Nullable String getTooltipText() {
        return null;
    }

    @Override
    public @Nullable Consumer<MouseEvent> getClickConsumer() {
        return null;
    }

    @Override
    public @Nullable WidgetPresentation getPresentation() {
        return this;
    }

    public void updateTime(String myText) {
        this.myText = myText;
        if (myStatusBar != null) {
            myStatusBar.updateWidget(ID());
        }
    }
}
