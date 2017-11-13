package com.GradedWarrior.chat.app;

import com.GradedWarrior.chat.ChatApp;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Icon;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.ItemList;
import com.mrcrayfish.device.api.app.component.TextArea;
import com.mrcrayfish.device.api.app.component.TextField;
import com.mrcrayfish.device.api.utils.OnlineRequest;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;

public class MineChatApplication extends Application{

    private String currentData, oldData;

    private Button send, settings;
    private TextArea write;
    private ItemList<String> chat;
    private String colorStr;

    @Override
    public void init() {
        this.setDefaultHeight(130);
        this.setDefaultWidth(250);
        chat = new ItemList<>(5, 5, 240, 7);

        addComponent(chat);
        write = new TextField(6, 112, 191);

        addComponent(write);

        send = new Button(215, 112, 30, 15, "Send");
        send.setClickListener((arg0, arg1)->
                OnlineRequest.getInstance().make(
                        "http://centralos.x10host.com/chat/messageAdmin.php?username="+ getColoredString(Minecraft.getMinecraft().player.getName())+"&message="+getFormattedStringData(write.getText()),
                        (success, response)-> {
                            if(currentData != null) {
                                write.setText("");
                            }
                        })

        );
        addComponent(send);

        settings = new Button(send.left - 30, send.top, 15, 15, Icon.WRENCH);
        settings.setClickListener((success, response)->{
            MineChatSettings settingsWindow = new MineChatSettings();
            openDialog(settingsWindow);
        });
        addComponent(settings);
    }

    private int counter = 0;
    @Override
    public void onTick() {
        super.onTick();

        counter++;

        if ((counter % 20) == 0) { //Every second (tick / 20 -> 0 remainders)

            OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/chat.html", new OnlineRequest.ResponseHandler() {
                @Override
                public void handle(boolean success, String response) {

                    if (currentData == null) {
                        currentData = response;
                        oldData = currentData;
                    }

                    if (!currentData.equals(oldData)) {
                        currentData = response;

                        chat.addItem(currentData);
                        ChatApp.LOGGER.info(currentData);
                    } else {
                        oldData = response;
                    }
                }

            });

        }
    }

    private String getFormattedStringData(String toFormat){
        TextComponentString format = new TextComponentString(TextFormatting.WHITE.toString() + toFormat);
        return format.getText().replace(" ", "%20");
    }

    private String getColoredString(String toFormat){

        if(ColorHelper.getUsernameColor() == Color.RED){
            this.colorStr = TextFormatting.RED.toString();
        }else if(ColorHelper.getUsernameColor() == Color.BLUE){
            this.colorStr = TextFormatting.BLUE.toString();
        }else if(ColorHelper.getUsernameColor() == Color.GREEN){
            this.colorStr = TextFormatting.GREEN.toString();
        }else if(ColorHelper.getUsernameColor() == ColorHelper.DARKBLUE){
            this.colorStr = TextFormatting.DARK_BLUE.toString();
        }else if(ColorHelper.getUsernameColor() == ColorHelper.DARKGREEN){
            this.colorStr = TextFormatting.DARK_GREEN.toString();
        }else if(ColorHelper.getUsernameColor() == ColorHelper.DARKRED){
            this.colorStr = TextFormatting.DARK_RED.toString();
        }else if(ColorHelper.getUsernameColor() == ColorHelper.YELLOW){
            this.colorStr = TextFormatting.YELLOW.toString();
        }else if(ColorHelper.getUsernameColor() == ColorHelper.PURPLE){
            this.colorStr = TextFormatting.DARK_PURPLE.toString();
        }
        TextComponentString tcs = new TextComponentString(this.colorStr + toFormat);
        return tcs.getText().replace(" ", "%20");
    }

    @Override
    public void load(NBTTagCompound tagCompound) {
    }

    @Override
    public void save(NBTTagCompound tagCompound) {
    }
}
