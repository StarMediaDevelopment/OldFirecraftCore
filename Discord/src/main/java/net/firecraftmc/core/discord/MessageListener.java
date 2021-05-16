package net.firecraftmc.core.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    public FirecraftCoreDiscord main;

    public MessageListener(FirecraftCoreDiscord main) {
        this.main = main;
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("Message received: " + event.getMessage().getContentRaw());
        main.getCommandManager().handleInput(new UserSender(event.getMember(), event.getTextChannel()), event.getMessage().getContentRaw());
    }
}