package com.GradedWarrior.chat;

import com.mrcrayfish.device.api.ApplicationManager;
import com.GradedWarrior.chat.app.AdminApplication;
import com.GradedWarrior.chat.app.ChatRApplication;
import com.GradedWarrior.chat.app.AppGame;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ChatApp.MODID, version = ChatApp.VERSION)
public class ChatApp
{
    public static final String MODID = "chat";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {


    	ApplicationManager.registerApplication(new ResourceLocation("gca:admin"), AdminApplication.class);
    	ApplicationManager.registerApplication(new ResourceLocation("gca:minechat"), ChatRApplication.class);
   // 	ApplicationManager.registerApplication(new ResourceLocation("gca:game"), AppGame.class);
    }
}
