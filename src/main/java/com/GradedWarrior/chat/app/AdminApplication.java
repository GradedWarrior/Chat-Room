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
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.api.app.component.TextField;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.api.io.File;
import com.mrcrayfish.device.api.utils.BankUtil;
import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest.ResponseHandler;
import net.minecraft.nbt.NBTTagCompound;




import java.net.*;
import java.io.*;
import com.google.gson.*;
import com.mrcrayfish.device.api.app.component.*;

/**
 * Author: GradedWarrior
 */
public class AdminApplication extends Application
{

	private Text Messages;
	private Label test;
	private Button Send;
	private Button Clear;
	private static TextField ChatBar;
	private static TextField User;
	
	
	{
    //private static final String USERNAME = ChatBar.getText();

	this.setDefaultWidth(300);
	}
	
	
    @Override
    public void init()
    {
    
    	
    	
    	

    	
    	
    	
    	class SayHello extends TimerTask {
    	    public void run() {

    	    	OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/chat.html", new ResponseHandler() {
    	    	    @Override
    	    	    public void handle(boolean success, String response) {
    
    	    	    	
    	    	    	Messages = new Text(response, 5, 5, 130);
    	    	    	
    	    			
    	    	    	addComponent(Messages);
    	    	    }
    	    	});
    	    }
    	}
    	
    	
    	ChatBar = new TextField(6, 80, 155);
		
		addComponent(ChatBar);
		
		Send = new Button(165, 80, 30, 15, "Send");
		addComponent(Send);
		
		Clear = new Button(220, 80, 70, 15, "Clear Chat");
		addComponent(Clear);
		
		
		
		
		
		
		
		
    	// And From your main() method or any other method
    	Timer timer = new Timer();
    	timer.schedule(new SayHello(), 0, 5000);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		Send.setClickListener(new ClickListener()
		{

			@Override
			public void onClick(Component arg0, int arg1) {
			
    	    	OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/messageAdmin.php?message="+ChatBar.getText()+"", new ResponseHandler() {
    	    	    @Override
    	    	    public void handle(boolean success, String response) {
    
    	    	    	
    	    	    	ChatBar.setText("");
    	    			
    	    			
    	    	    }
    	    	});
				
			}

		});


		Clear.setClickListener(new ClickListener()
		{

			@Override
			public void onClick(Component arg0, int arg1) {
			
    	    	OnlineRequest.getInstance().make("http://centralos.x10host.com/chat/clear.php", new ResponseHandler() {
    	    	    @Override
    	    	    public void handle(boolean success, String response) {
    
    	    	    	
    	    	    	
    	    			
    	    			
    	    	    }
    	    	});
				
			}

		});		
		
		
		
		
		
    }

    @Override
    public void load(NBTTagCompound nbtTagCompound)
    {

    }

    @Override
    public void save(NBTTagCompound nbtTagCompound)
    {

    }
}