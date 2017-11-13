package com.GradedWarrior.chat.app;

import com.mrcrayfish.device.api.app.Dialog;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.*;

import java.awt.*;

public class MineChatSettings extends Dialog {
    private Layout changeColorLayout;
    private CheckBox checkBlue, checkRed, checkGreen, checkDarkBlue, checkDarkRed, checkDarkGreen, checkYellow, checkPurple;

    @Override
    public void init() {
        super.init();
        changeColorLayout = new Layout();
        RadioGroup colorChoices = new RadioGroup();
        //Blue
        {
            checkBlue = new CheckBox("Blue", 35, 15);
            checkBlue.setCheckedColour(Color.DARK_GRAY);
            checkBlue.setEnabled(true);
            checkBlue.setRadioGroup(colorChoices);
            checkBlue.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(Color.BLUE);
            });
        }
        //Red
        {
            checkRed = new CheckBox("Red", 35, 45);
            checkRed.setCheckedColour(Color.DARK_GRAY);
            checkRed.setEnabled(true);
            checkRed.setRadioGroup(colorChoices);
            checkRed.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(Color.RED);
            });
        }
        //Green
        {
            checkGreen = new CheckBox("Green", 35, 75);
            checkGreen.setCheckedColour(Color.DARK_GRAY);
            checkGreen.setEnabled(true);
            checkGreen.setRadioGroup(colorChoices);
            checkGreen.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(Color.GREEN);
            });
        }
        //Dark Blue
        {
            checkDarkBlue = new CheckBox("Dark Blue", 35, 75);
            checkDarkBlue.setCheckedColour(Color.DARK_GRAY);
            checkDarkBlue.setEnabled(true);
            checkDarkBlue.setRadioGroup(colorChoices);
            checkDarkBlue.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(ColorHelper.DARKBLUE);
            });
        }

        {
            checkDarkRed = new CheckBox("Dark Red", 35, 75);
            checkDarkRed.setCheckedColour(Color.DARK_GRAY);
            checkDarkRed.setEnabled(true);
            checkDarkRed.setRadioGroup(colorChoices);
            checkDarkRed.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(ColorHelper.DARKRED);
            });
        }

        {
            checkDarkGreen = new CheckBox("Dark Green", 35, 75);
            checkDarkGreen.setCheckedColour(Color.DARK_GRAY);
            checkDarkGreen.setEnabled(true);
            checkDarkGreen.setRadioGroup(colorChoices);
            checkDarkGreen.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(ColorHelper.DARKGREEN);
            });
        }

        {
            checkYellow = new CheckBox("Yellow", 35, 75);
            checkYellow.setCheckedColour(Color.DARK_GRAY);
            checkYellow.setEnabled(true);
            checkYellow.setRadioGroup(colorChoices);
            checkYellow.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(ColorHelper.YELLOW);
            });
        }

        {
            checkPurple = new CheckBox("Purple", 35, 75);
            checkPurple.setCheckedColour(Color.DARK_GRAY);
            checkPurple.setEnabled(true);
            checkPurple.setRadioGroup(colorChoices);
            checkPurple.setClickListener((success, response) -> {
                ColorHelper.setUsernameColor(ColorHelper.PURPLE);
            });
        }
        changeColorLayout.addComponent(checkBlue);
        changeColorLayout.addComponent(checkRed);
        changeColorLayout.addComponent(checkGreen);
        setLayout(changeColorLayout);
    }
}
