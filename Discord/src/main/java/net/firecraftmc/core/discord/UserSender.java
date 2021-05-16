package net.firecraftmc.core.discord;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.firecraftmc.core.api.framework.command.CommandSender;

public class UserSender implements CommandSender {
    
    private Member member;
    private TextChannel channel;

    public UserSender(Member member, TextChannel channel) {
        this.member = member;
        this.channel = channel;
    }

    public void sendMessage(String message) {
        channel.sendMessage(message).queue();
    }

    public String getName() {
        return member.getEffectiveName();
    }

    public Member getMember() {
        return member;
    }

    public TextChannel getChannel() {
        return channel;
    }
}
