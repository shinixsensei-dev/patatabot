package core;

import commands.*;
import config.settings;
import listeners.commandlistener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import okhttp3.Cookie;
import worker.CommandHandler;

import javax.security.auth.login.LoginException;


public class Main {

    public static JDABuilder builder;

    public static void main(String[] Args) throws LoginException, InterruptedException {
        builder = new JDABuilder(AccountType.BOT);

        //Important
        builder.setToken(settings.TOKEN);
        builder.setAutoReconnect(true);

        //Status
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGame(Game.playing("in #panties"));

        //Listeners
        builder.addEventListener(new commandlistener());
        addCommands();


        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            builder.setStatus(OnlineStatus.IDLE);
            builder.setGame(Game.playing("low processing mode!"));
        }

    }

    public static void addCommands() {

        CommandHandler.commands.put("ping", new ping());
        CommandHandler.commands.put("membercount", new membercount());

        CommandHandler.commands.put("rainbowrole", new rainbowrole());

        CommandHandler.commands.put("voicestate", new voicestate());
        
        CommandHandler.commands.put("spam", new spam());

    }
}
