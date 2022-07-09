package fr.o278deco.adminbot.config;

import java.util.Locale;

import apbiot.core.file.json.JSONConfiguration;

public class DiscordClientConfigReferences extends JSONConfiguration {

	/*
	 * Some statics properties that don't need to be changed
	 */
	public final long DEVELOPPER_ID = 207473022158766080L;
	public final String BOT_ID = "994742343695614025";
	
	public DiscordClientConfigReferences(String path) {
		super(path, "discordclient.json");
	}

	@Override
	public void controlRegistredProperties() {
		setPropertyIfAbsent("client token", "none");
		setPropertyIfAbsent("main language", "fr");
		setPropertyIfAbsent("secondary language", "fr");
		setPropertyIfAbsent("version", "0.0.0");	
	}
	
	public String getClientToken() {
		return getStringProperty("client token");
	}
	
	public Locale getMainLanguage() {
		return new Locale(getStringProperty("main language"));
	}
	
	public Locale getSecondaryLanguage() {
		return new Locale(getStringProperty("secondary language"));
	}
	
	public String getVersion() {
		return getStringProperty("version");
	}
	
}
