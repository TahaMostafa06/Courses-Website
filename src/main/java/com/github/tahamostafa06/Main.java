package com.github.tahamostafa06;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.github.tahamostafa06.gui.panels.MainWindowFrame;

public class Main {
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        @SuppressWarnings("unused")
        MainWindowFrame mainWindow = new MainWindowFrame();
    }
}