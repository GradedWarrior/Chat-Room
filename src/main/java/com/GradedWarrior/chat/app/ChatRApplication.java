package com.GradedWarrior.chat.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarException;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.Dialog;
import com.mrcrayfish.device.api.app.Dialog.Input;
import com.mrcrayfish.device.api.app.Dialog.Message;
import com.mrcrayfish.device.api.app.Dialog.OpenFile;
import com.mrcrayfish.device.api.app.Dialog.SaveFile;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.api.io.File;
import com.mrcrayfish.device.api.utils.BankUtil;

import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest.ResponseHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

import java.net.*;
import java.io.*;
import com.google.gson.*;
import com.mrcrayfish.device.api.app.component.*;

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
	// private static final String USERNAME = ChatBar.getText();

	/*
	* 
	*
	*
	*
	*
	*
	*
	*
	* Type here ^
	* 
	* Note: MC is in debug mode
	*/
	
	
	
	@Override
	public void init() {
		this.setDefaultHeight(130);
		chat = new ItemList<String>(5, 5, 190, 7);

		OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/chat.html", new ResponseHandler() {
			@Override
			public void handle(boolean success, String response) {

				oldData = response;

			}
		});

		addComponent(chat);
		ChatBar = new TextField(6, 112, 155);

		addComponent(ChatBar);

		Send = new Button(165, 112, 30, 15, "Send");
		addComponent(Send);
	}

	@Override
	public void onTick() {
		counter++;
	    if ((counter % 100) == 0) { //Every 5 seconds (tick / 100 -> 0 remainders)

	    OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/chat.html", new ResponseHandler() {
	        @Override
	        public void handle(boolean success, String response) {


	            if(currentData != oldData){
	            	oldData = currentData;
	                currentData = response; 
	                chat.addItem("§9" + Minecraft.getMinecraft().player.getName() + ": " + response);
	            }
	           

	        }


	    });

	}

		Timer timer = new Timer();
		Timer timer2 = new Timer();
		/*
		 * @Override protected void handleTick() { timer++; if(timer % 60 == 0)
		 * { //Do request } }
		 * 
		 */
		// timer.schedule(new SayHello(), 0, 5000);
		// timer2.schedule(new SayHello2(), 0, 5000);

		Send.setClickListener(new ClickListener() {

			public void onClick(Component arg0, int arg1) {

				OnlineRequest.getInstance().make(
						"http://centralos.x10host.com/chat/message.php?message=" + ChatBar.getText() + "",
						new ResponseHandler() {

							public void handle(boolean success, String response) {

								ChatBar.setText("");
								// new SayHello();

							}
						});

			}

		});

	}

	@Override
	public void load(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public void save(NBTTagCompound nbtTagCompound) {

	}
}