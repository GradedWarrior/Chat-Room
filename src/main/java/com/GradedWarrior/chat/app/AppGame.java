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
import com.mrcrayfish.device.api.utils.OnlineRequest;
import com.mrcrayfish.device.api.utils.OnlineRequest.ResponseHandler;
import net.minecraft.nbt.NBTTagCompound;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.Layout;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.ButtonArrow;
import com.mrcrayfish.device.api.app.component.CheckBox;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.object.Game;
import com.mrcrayfish.device.object.TileGrid;
import com.mrcrayfish.device.object.tiles.Tile;


import java.net.*;
import java.io.*;
import com.google.gson.*;
import com.mrcrayfish.device.api.app.component.*;

/**
 * Author: GradedWarrior
 */
public class AppGame extends Application
{
	private Layout layoutLevelEditor;
	private Game game;
	private TileGrid tileGrid;
	private Label labelLayer;
	private Button btnNextLayer;
	private Button btnPrevLayer;
	private CheckBox checkBoxForeground;
	private CheckBox checkBoxBackground;
	private CheckBox checkBoxPlayer;

	public void init() 
	{
		layoutLevelEditor = new Layout(364, 178);
		
		try 
		{
			game = new Game(4, 4, 256, 144);
			game.setEditorMode(true);
			game.setRenderPlayer(false);
			game.fill(Tile.grass);
			layoutLevelEditor.addComponent(game);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		tileGrid = new TileGrid(266, 3, game);
		layoutLevelEditor.addComponent(tileGrid);
		
		labelLayer = new Label("1", 280, 108);
		layoutLevelEditor.addComponent(labelLayer);
		
		btnNextLayer = new ButtonArrow(266, 106, ButtonArrow.Type.RIGHT);
		btnNextLayer.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.nextLayer();
				labelLayer.setText(Integer.toString(game.getCurrentLayer().layer + 1));
			}
		});
		layoutLevelEditor.addComponent(btnNextLayer);
		
		btnPrevLayer = new ButtonArrow(314, 106, ButtonArrow.Type.LEFT);
		btnPrevLayer.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.prevLayer();
				labelLayer.setText(Integer.toString(game.getCurrentLayer().layer + 1));
			}
		});
		layoutLevelEditor.addComponent(btnPrevLayer);
		
		checkBoxBackground = new CheckBox("Background", 3, 151);
		checkBoxBackground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderBackground(checkBoxBackground.isSelected());
			}
		});
		checkBoxBackground.setSelected(true);
		layoutLevelEditor.addComponent(checkBoxBackground);
		
		checkBoxForeground = new CheckBox("Foreground", 80, 151);
		checkBoxForeground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderForeground(checkBoxForeground.isSelected());
			}
		});
		checkBoxForeground.setSelected(true);
		layoutLevelEditor.addComponent(checkBoxForeground);
		
		checkBoxPlayer = new CheckBox("Player", 160, 151);
		checkBoxPlayer.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderPlayer(checkBoxPlayer.isSelected());
			}
		});
		layoutLevelEditor.addComponent(checkBoxPlayer);
		
		setCurrentLayout(layoutLevelEditor);
	}

	@Override
	public void load(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	
}