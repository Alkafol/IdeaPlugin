package com.devtools.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import org.jetbrains.annotations.NotNull;

public class TimerWindow extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        if (CustomTimer.getRemainingTime() > 0){
            Messages.showMessageDialog("Remaining time: " + CustomTimer.getRemainingTime() + " seconds",
                    "Remaining Time",
                    Messages.getInformationIcon());
            return;
        }
        TimerDialog timerDialog = new TimerDialog();
        timerDialog.showAndGet();
        if (timerDialog.isOK()) {
            try {
                int time = Integer.parseInt(timerDialog.getInput());
                Project currentProject = anActionEvent.getProject();
                CustomTimer.startTimer(time, currentProject);
            } catch (NumberFormatException e) {
                showExceptionMessage(anActionEvent);
                actionPerformed(anActionEvent);
            }
        }
    }

    private void showExceptionMessage(AnActionEvent anActionEvent){
        Project currentProject = anActionEvent.getProject();
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(currentProject);
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("Enter valid integer number", MessageType.ERROR, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getNorthEastOf(statusBar.getComponent()),
                        Balloon.Position.atLeft);
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
}
