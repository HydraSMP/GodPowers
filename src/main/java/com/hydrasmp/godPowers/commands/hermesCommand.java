package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hermesCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public hermesCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.hermes")) {
                if (split.length > 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/hermes'");
                    return true;
                } else {
                    if (plugin.isHermes.contains(player.getName())) {
                        plugin.isHermes.remove(player.getName());
                        player.sendMessage(ChatColor.AQUA + "You feel your hermes like powers slowly draining");
                        return true;
                    } else {
                        plugin.isHermes.add(player.getName());
                        player.sendMessage(ChatColor.AQUA + "The gods grant you speed like hermes");
                        return true;
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
            }
        }
        return false;
    }
}