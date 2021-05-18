package net.firecraftmc.core.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.firecraftmc.core.api.framework.command.Command;
import net.firecraftmc.core.api.framework.command.CommandManager;
import net.firecraftmc.core.api.module.FirecraftModule;

import javax.security.auth.login.LoginException;

public class FirecraftCoreDiscord implements FirecraftModule {
    
    private CommandManager commandManager = new CommandManager("!");

    public static void main(String[] args) {
    }
    
    public void onEnable() {
        JDABuilder builder = JDABuilder.createDefault("ODQzMzQ3ODA3NTEwMzMxNDIy.YKCi2Q.TsFf-gmDCks86yt4-RHPEFqmE8Q");
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(true);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.watching("FirecraftMC Discords"));
        builder.addEventListeners(new MessageListener(this));
        JDA jda;
        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            return;
        }
        
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        
        commandManager.registerCommand(new Command("announce", null, "Announce a message to all servers!").setExecutor((sender, cmd, args) -> {
            if (sender instanceof UserSender userSender) {
                boolean fctRole = false;
                for (Role role : userSender.getMember().getRoles()) {
                    if (role.getName().equalsIgnoreCase("Firecraft Team")) {
                        fctRole = true;
                        break;
                    }
                }
                
                if (!fctRole) {
                    sender.sendMessage("You cannot use that command!");
                    return;
                }

                for (Guild guild : jda.getGuilds()) {
                    TextChannel announcementsChannel = null;
                    for (TextChannel textChannel : guild.getTextChannels()) {
                        if (textChannel.getName().contains("announcements")) {
                            announcementsChannel = textChannel;
                            break;
                        }
                    }
                    
                    StringBuilder message = new StringBuilder();
                    for (String arg : args) {
                        message.append(arg).append(" ");
                    }
                    
                    if (announcementsChannel == null) {
                        continue;
                    }
                    
                    announcementsChannel.sendMessage(new MessageBuilder(userSender.getName() + " says to @everyone \n").append(message).build()).queue();
                }
            }
        }));
    }

    public void onDisable() {

    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
