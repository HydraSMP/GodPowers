package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PoseidonCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public PoseidonCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.poseidon")) {
                if (split.length == 0) {
                    if (plugin.isPoseidon.contains(player.getName())) {
                        plugin.isPoseidon.remove(player.getName());
                        player.sendMessage(ChatColor.BLUE + StringHandler.POSEIDON_REMOVE);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.BLUE + StringHandler.POSEIDON_ADD);
                        plugin.isPoseidon.add(player.getName());
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.POSEIDON_YOURSELF);
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + StringHandler.GODPOWERS_NOPERMISSION);
                return true;
            }
        }
        return false;
    }
}
