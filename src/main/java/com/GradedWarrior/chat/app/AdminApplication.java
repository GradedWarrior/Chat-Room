package com.GradedWarrior.chat.app;

import com.GradedWarrior.chat.ChatApp;
import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Icon;
import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest.ResponseHandler;
import com.mrcrayfish.device.api.app.component.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Author: GradedWarrior
 */
public class AdminApplication extends Application {

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
		this.setDefaultWidth(250);
		chat = new ItemList<>(5, 5, 240, 7);

		addComponent(chat);
		ChatBar = new TextField(6, 112, 191);

		addComponent(ChatBar);
		
		Button sendImage = new Button(198, 112, 16, 15, Icon.PICTURE);
		Send = new Button(215, 112, 30, 15, "Send");
        Send.setClickListener((arg0, arg1)->
                OnlineRequest.getInstance().make(
                        "http://centralos.x10host.com/chat/messageAdmin.php?username="+Minecraft.getMinecraft().player.getName()+"&message="+getFormattedStringData(ChatBar.getText()),
                        (success, response)-> {
                            if(currentData != null) {
                                ChatBar.setText("");
                            }
                        })

        );
		addComponent(Send);
		addComponent(sendImage);
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
                        oldData = currentData;
                    }
                   
                    if (!currentData.equals(oldData)) {
                     //   oldData = currentData;
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
	    return toFormat.replace(" ", "%20");
    }

	@Override
	public void load(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public void save(NBTTagCompound nbtTagCompound) {

	}
}