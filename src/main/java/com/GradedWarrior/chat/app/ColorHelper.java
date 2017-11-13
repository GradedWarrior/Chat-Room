package com.GradedWarrior.chat.app;

import com.GradedWarrior.chat.ChatApp;

import java.awt.*;

public class ColorHelper {
    private static Color _color = Color.WHITE;

    public static final Color DARKBLUE = new Color(0, 0, 120);
    public static final Color DARKRED = new Color(100, 0, 0);
    public static final Color DARKGREEN = new Color(0, 80, 0);
    public static final Color YELLOW = new Color(200, 190, 0);
    public static final Color PURPLE = new Color(100, 75, 200);

    public static void setUsernameColor(Color color){
        printColorValues(color);
        _color = color;
    }

    public static Color getUsernameColor(){
        printColorValues(_color);
        return _color;
    }

    private static void printColorValues(Color color){
        ChatApp.LOGGER.info("Red: " + String.valueOf(color.getRed()));
        ChatApp.LOGGER.info("Blue: " + String.valueOf(color.getBlue()));
        ChatApp.LOGGER.info("Green: " + String.valueOf(color.getGreen()));
    }
}
