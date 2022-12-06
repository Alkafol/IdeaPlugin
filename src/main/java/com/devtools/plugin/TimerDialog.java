package com.devtools.plugin;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TimerDialog extends DialogWrapper {
    private final JPanel panel;

    public TimerDialog() {
        super(true); // use current window as parent
        panel = new JPanel();
        setTitle("Start Timer");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JLabel description = new JLabel("Enter how many seconds do you want to work:");
        JTextField field = new JTextField();

        panel.add(description);
        panel.add(field);

        return panel;
    }

    String getInput(){
        return ((JTextField) panel.getComponent(1)).getText();
    }
}
