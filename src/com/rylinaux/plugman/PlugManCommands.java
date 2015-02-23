package com.rylinaux.plugman;

import com.rylinaux.plugman.command.AbstractCommand;
import com.rylinaux.plugman.command.DisableCommand;
import com.rylinaux.plugman.command.DumpCommand;
import com.rylinaux.plugman.command.EnableCommand;
import com.rylinaux.plugman.command.HelpCommand;
import com.rylinaux.plugman.command.InfoCommand;
import com.rylinaux.plugman.command.ListCommand;
import com.rylinaux.plugman.command.LoadCommand;
import com.rylinaux.plugman.command.ReloadCommand;
import com.rylinaux.plugman.command.RestartCommand;
import com.rylinaux.plugman.command.UnloadCommand;
import com.rylinaux.plugman.command.UsageCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Listen for commands and execute them.
 *
 * @author rylinaux
 */
public class PlugManCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        AbstractCommand cmd = new HelpCommand(sender);

        // No args, show help.
        if (args.length == 0) {
            cmd.execute(sender, command, label, args);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "help":
                cmd = new HelpCommand(sender);
                break;
            case "list":
                cmd = new ListCommand(sender);
                break;
            case "dump":
                cmd = new DumpCommand(sender);
                break;
            case "info":
                cmd = new InfoCommand(sender);
                break;
            case "usage":
                cmd = new UsageCommand(sender);
                break;
            case "enable":
                cmd = new EnableCommand(sender);
                break;
            case "disable":
                cmd = new DisableCommand(sender);
                break;
            case "restart":
                cmd = new RestartCommand(sender);
                break;
            case "load":
                cmd = new LoadCommand(sender);
                break;
            case "reload":
                cmd = new ReloadCommand(sender);
                break;
            case "unload":
                cmd = new UnloadCommand(sender);
                break;
        }

        cmd.execute(sender, command, label, args);
        return true;

    }

}
