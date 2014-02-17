package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HermesCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public HermesCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.hermes")) {
                if (split.length > 0) {
                    player.sendMessage(ChatColor.RED + StringHandler.HERMES_SYNTAX);
                    return true;
                } else {
                    if (plugin.isHermes.contains(player.getName())) {
                        plugin.isHermes.remove(player.getName());
                        player.sendMessage(ChatColor.AQUA + StringHandler.HERMES_REMOVE);
                        return true;
                    } else {
                        plugin.isHermes.add(player.getName());
                        player.sendMessage(ChatColor.AQUA + StringHandler.HERMES_ADD);
                        return true;
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
            }
        }
        return false;
    }
}