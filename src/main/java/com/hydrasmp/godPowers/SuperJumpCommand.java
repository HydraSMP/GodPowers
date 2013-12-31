package com.hydrasmp.godPowers;

//import org.bukkit.World;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuperJumpCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public SuperJumpCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.superjump")) {
                if (plugin.superJumper.contains(player.getName())) {
                    plugin.superJumper.remove(player.getName());
                    player.sendMessage(ChatColor.BLUE + "You can no longer leap great heights.");
                } else {
                    player.sendMessage(ChatColor.BLUE + "The gods suddenly grant you the ability to leap great heights");
                    plugin.superJumper.add(player.getName());
                }
                return true;
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
                return true;
            }
        }
        return false;
    }
}
