package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZeusCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public ZeusCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.zeus")) {
                if (split.length > 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/zeus'");
                    return true;
                }
                if (plugin.isZeus.contains(player.getName())) {
                    player.sendMessage(ChatColor.BLUE + "You feel a sudden loss of your Zeus-like abilities.");
                    plugin.isZeus.remove(player.getName());
                    return true;
                } else {
                    player.sendMessage(ChatColor.BLUE + "The gods suddenly grant you Zeus-like powers!");
                    plugin.isZeus.add(player.getName());
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
            }
        }
        return false;
    }
}