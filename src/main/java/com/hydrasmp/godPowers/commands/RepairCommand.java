package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RepairCommand implements CommandExecutor {
    private final godPowers plugin;

    public RepairCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("godpowers.repair")) {
                if (args.length == 0) {
                    try {
                        if (player.getItemInHand().getDurability() == 0) {
                            player.sendMessage(ChatColor.BLUE + "The gods cannot repair that which is not broken!");
                        } else if (player.getItemInHand().getType() == Material.AIR) {
                            player.sendMessage(ChatColor.BLUE + "The gods cannot repair nothing!");
                        } else {
                            player.getItemInHand().setDurability((short) 0);
                            player.sendMessage(ChatColor.BLUE + "The gods have repaired the item in your hand!");
                        }
                    } catch (Exception i) {
                        player.sendMessage(ChatColor.RED + "Not even the gods can repair this item!");
                    }
                } else {
                    player.sendMessage("Incorrect syntax. Correct usage: '/repair'");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
                return true;
            }
        }
        return true;
    }
}
