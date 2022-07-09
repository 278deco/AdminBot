package fr.o278deco.adminbot.discord;

import apbiot.core.MainInitializer;
import apbiot.core.event.EventProgramStopping;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.gateway.intent.IntentSet;
import fr.o278deco.adminbot.AdminBot;
import fr.o278deco.adminbot.config.DiscordClientConfigReferences;
import fr.o278deco.adminbot.discord.handlers.CommandHandler;
import fr.o278deco.adminbot.discord.handlers.JSONFilesHandler;

public class DiscordClientInstance extends MainInitializer {

	private DiscordClientConfigReferences references;
	private ClientPresence clientPresence;
	
	private JSONFilesHandler jsonHandler;
	
	public DiscordClientInstance() {
		references = new DiscordClientConfigReferences(AdminBot.CONFIG_PATH);
		setClientPresence(ClientPresenceType.LOADING);
		
		jsonHandler = new JSONFilesHandler();
		
		init(new CommandHandler(), null);
		addRequiredHandler(jsonHandler);
		
		launch(references.getClientToken(), clientPresence, IntentSet.all());
	}
	
	@Override
	protected void onShutdown() {
		eventDispatcher.dispatchEvent(new EventProgramStopping(0));
	}
	
	public DiscordClientConfigReferences getReferences() {
		return references;
	}
	
	public JSONFilesHandler getJsonHandler() {
		return jsonHandler;
	}
	
	public ClientPresence getDefaultPresence() {
		return clientPresence;
	}
	
	private void setClientPresence(ClientPresenceType status) {
		switch(status) {
			case LOADING:
				clientPresence = ClientPresence.idle(ClientActivity.listening("Chargement..."));
				break;
			case LOADED:
				clientPresence = ClientPresence.online(ClientActivity.listening("/help | Version "+references.getVersion()));
				break;
		}
	}
	
	public void setDefaultClientPresence(ClientPresenceType status) {
		setClientPresence(status);
		clientInstance.getClientBuilder().setGameText(clientPresence);
	}
	
	public enum ClientPresenceType {
		LOADING,
		LOADED;
	}

}
