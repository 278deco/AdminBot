package fr.o278deco.adminbot.discord.handlers;

import java.util.List;
import java.util.Map;

import apbiot.core.command.SlashCommandInstance;
import apbiot.core.handler.ECommandHandler;
import discord4j.core.GatewayDiscordClient;

public class CommandHandler extends ECommandHandler {

	@Override
	public void register(GatewayDiscordClient gateway) {
		
		registerSlashCommand(gateway, 208266634090119169L);
	}

	@Override
	public void init() {
		for(Map.Entry<List<String>, SlashCommandInstance> entry : SLASH_COMMANDS.entrySet()) {
			entry.getValue().initCommand();
		}
	}

}
