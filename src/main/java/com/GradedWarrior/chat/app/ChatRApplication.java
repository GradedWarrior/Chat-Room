package com.GradedWarrior.chat.app;

import com.GradedWarrior.chat.ChatApp;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest.ResponseHandler;
import com.mrcrayfish.device.api.app.component.*;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Author: GradedWarrior
 */
public class ChatRApplication extends Application {

	private TextArea Messages;
	private Label test;
	private Button Send;
	private ItemList<String> chat;
	private static TextField ChatBar;
	private static TextField User;
	public String oldData; 
	public String currentData;
	boolean urlData;
	private int counter;

	@Override
	public void init() {
		this.setDefaultHeight(130);
		chat = new ItemList<>(5, 5, 190, 7);

		addComponent(chat);
		ChatBar = new TextField(6, 112, 155);

		addComponent(ChatBar);

		Send = new Button(165, 112, 30, 15, "Send");
        Send.setClickListener((arg0, arg1)->
                OnlineRequest.getInstance().make(
                        "http://centralos.x10host.com/chat/message.php?message=" +
                                getFormattedStringData(ChatBar.getText()),
                        (success, response)-> {
                            if(currentData != null) {
                                ChatBar.setText("");
                            }
                        })

        );
		addComponent(Send);
	}

    @Override
	public void onTick() {
        super.onTick();

        counter++;
        if ((counter % 20) == 0) { //Every second (tick / 20 -> 0 remainders)

            OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/chat.html", new ResponseHandler() {
                @Override
                public void handle(boolean success, String response) {

                    if (currentData == null) {
                        currentData = response;
                    }
                    if (!currentData.equals(oldData)) {
                        oldData = currentData;
                        currentData = response;
                        chat.addItem("§9" + Minecraft.getMinecraft().player.getName() + ": " + currentData);
                        ChatApp.LOGGER.info(currentData);
                    } else {
                        oldData = response;
                    }
                }
            });

        }

	}

	private String getFormattedStringData(String toFormat){
	    return toFormat.replace(" ", "%20");
    }

	@Override
	public void load(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public void save(NBTTagCompound nbtTagCompound) {

	}
}