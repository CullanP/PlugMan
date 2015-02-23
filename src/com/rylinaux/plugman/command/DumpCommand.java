package com.rylinaux.plugman.command;

import com.rylinaux.plugman.PlugMan;
import com.rylinaux.plugman.util.PluginUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Command that dumps plugin names and versions to file.
 *
 * @author rylinaux
 */
public class DumpCommand extends AbstractCommand {

    /**
     * The name of the command.
     */
    public static final String NAME = "Dump";

    /**
     * The description of the command.
     */
    public static final String DESCRIPTION = "Dump plugins and versions to file.";

    /**
     * The main permission of the command.
     */
    public static final String PERMISSION = "plugman.dump";

    /**
     * The proper usage of the command.
     */
    public static final String USAGE = "/plugman dump";

    /**
     * The sub permissions of the command.
     */
    public static final String[] SUB_PERMISSIONS = {""};

    /**
     * Construct out object.
     *
     * @param sender the command sender
     */
    public DumpCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    /**
     * Executes the command.
     *
     * @param sender  the sender of the command
     * @param command the command being done
     * @param label   the name of the command
     * @param args    the arguments supplied
     */
    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        if (!hasPermission()) {
            sender.sendMessage(PlugMan.getInstance().getMessenger().format("error.no-permission"));
            return;
        }

        File dumpFile = new File(PlugMan.getInstance().getDataFolder(), "dump.txt");

        // Delete old file if exists.
        if (dumpFile.exists())
            dumpFile.delete();

        try {
            dumpFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;

        List<String> plugins = PluginUtil.getPluginNames(true);
        Collections.sort(plugins, String.CASE_INSENSITIVE_ORDER);

        try {
            writer = new PrintWriter(dumpFile);
            for (String plugin : plugins)
                writer.println(plugin);
            sender.sendMessage(PlugMan.getInstance().getMessenger().format("dump.dumped", dumpFile.getName()));
        } catch (IOException e) {
            sender.sendMessage(PlugMan.getInstance().getMessenger().format("dump.error"));
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }

    }

}
