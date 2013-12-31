package com.hydrasmp.godPowers;

//import org.bukkit.World;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfernoCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public InfernoCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.inferno")) {
                if (plugin.isInferno.contains(player.getName())) {
                    plugin.isInferno.remove(player.getName());
                    player.sendMessage(ChatColor.BLUE + "You feel your firey rage suddenly subside.");
                } else {
                    plugin.isInferno.add(player.getName());
                    player.sendMessage(ChatColor.DARK_RED + "The gods grant you the firey rage of Hades!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "The gods prevent you from using this command.");
                return true;
            }
            return true;
        }
        return false;
    }
}
