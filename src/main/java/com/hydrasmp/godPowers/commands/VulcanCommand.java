package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class VulcanCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public VulcanCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.vulcan")) {
                if (split.length == 0) {
                    /*
                    Location loc = player.getTargetBlock(null, 100).getLocation();
					loc.setY(player.getTargetBlock(null, 100).getLocation().getY()+1);
					Location playerLoc = player.getLocation().add(loc);
					*/
                    if (plugin.isVulcan.contains(player.getName())) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.VULCAN_REMOVE);
                        plugin.isVulcan.remove(player.getName());
                        return true;
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.VULCAN_ADD);
                        plugin.isVulcan.add(player.getName());
                        return true;
                    }
                    //world.spawn(player.getTargetBlock(null, 100).getLocation(), Fireball.class);
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.VULCAN_SYNTAX);
                }
                return true;
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return false;
    }
}
