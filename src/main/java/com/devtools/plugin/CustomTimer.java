package com.devtools.plugin;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class CustomTimer {
    private static Project project;
    private static int remainingTimeInSeconds;

    private CustomTimer() {
    }

    public static void startTimer(int remainingTimeInSeconds, Project project) {
        CustomTimer.remainingTimeInSeconds = remainingTimeInSeconds;
        CustomTimer.project = project;
        startCounting(remainingTimeInSeconds);
    }

    public static int getRemainingTime() {
        return remainingTimeInSeconds;
    }

    private static void startCounting(int minutes) {
        Timer endTimer = new Timer();
        Timer everySecondTimer = new Timer();
        CustomWidget timerWidget = (CustomWidget) WindowManager.getInstance().getStatusBar(project).getWidget("TimerWidget");

        everySecondTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds <= 0) {

                    everySecondTimer.cancel();
                }
                else if (remainingTimeInSeconds < 60){
                    timerWidget.updateTime(remainingTimeInSeconds + " seconds");
                }
                else{
                    timerWidget.updateTime(remainingTimeInSeconds / 60 + " minutes");
                }

                remainingTimeInSeconds -= 1;
            }
        }, 0, 1000);

        endTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                CustomWidget timerWidget = (CustomWidget) WindowManager.getInstance().getStatusBar(project).getWidget("TimerWidget");
                timerWidget.updateTime("0 minutes");
                invokeEndingWindow(ModalityState.defaultModalityState());
            }
        }, 1000L * minutes);
    }

    private static void invokeEndingWindow(ModalityState modalityState) {
        ApplicationManager.getApplication().invokeLater(
                () -> {
                    Messages.showMessageDialog("You may now take some rest. Go and grab some coffee!", "Time To Rest",
                            Messages.getInformationIcon());
                },
                modalityState);
    }
}
