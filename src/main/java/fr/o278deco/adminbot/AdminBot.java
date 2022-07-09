package fr.o278deco.adminbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.CommandDispatcher;

import fr.o278deco.adminbot.command.TestCommand;
import fr.o278deco.adminbot.discord.DiscordClientInstance;
import fr.o278deco.adminbot.discord.DiscordClientInstance.ClientPresenceType;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

public class AdminBot implements DedicatedServerModInitializer {
	
	public static final String MOD_ID = "adminbot";
	public static final String CONFIG_PATH = "./config/"+MOD_ID;
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	private volatile DiscordClientInstance clientInstance;
	
	@Override
	public void onInitializeServer() {
		
		ServerLifecycleEvents.SERVER_STARTING.register(this::onServerStarting);
		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted);
		ServerLifecycleEvents.SERVER_STOPPING.register(this::onServerStopping);
		CommandRegistrationCallback.EVENT.register(this::onCommandRegister);
	}
	
	public void onServerStarting(MinecraftServer server) {
		Thread discordClientThread = new Thread(new Runnable() {

			@Override
			public void run() {
				clientInstance = new DiscordClientInstance();
			}
			
		}, "Discord Client Thread");
		
		discordClientThread.start();
	}
	
	public void onServerStarted(MinecraftServer server) {
		clientInstance.setDefaultClientPresence(ClientPresenceType.LOADED);
	}
	
	public void onServerStopping(MinecraftServer server) {
		
	}
	
	public void onCommandRegister(CommandDispatcher<ServerCommandSource> dispatcher,boolean dedicated) {
		TestCommand.register(dispatcher, dedicated);
	}
}
