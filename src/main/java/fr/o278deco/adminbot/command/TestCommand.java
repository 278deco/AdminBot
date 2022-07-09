package fr.o278deco.adminbot.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class TestCommand {
	
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher,boolean dedicated ) {
		dispatcher.register(CommandManager.literal("testhw")
			.executes(TestCommand::run));
			
	}
	
	private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		context.getSource().sendFeedback(new LiteralText("Hello world !"), false);
		return 1;
	}


}
