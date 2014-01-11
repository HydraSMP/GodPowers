package com.hydrasmp.godPowers.commands;

//import org.bukkit.World;

import com.hydrasmp.godPowers.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class godModeCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public godModeCommand(godPowers instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.godmode")) {
                if (split.length == 0) {
                    if (plugin.godmodeEnabled.contains(player.getName())) {
                        if (command.getName().equalsIgnoreCase("godmodeon")) {
                            player.sendMessage(ChatColor.BLUE + "You are already invincible!");
                            return true;
                        }
                        plugin.godmodeEnabled.remove(player.getName());
                        player.sendMessage(ChatColor.BLUE + "The gods return you to your mortal state.");
                        player.setDisplayName(player.getName());
                        return true;
                    } else {
                        if (command.getName().equalsIgnoreCase("godmodeoff")) {
                            player.sendMessage(ChatColor.BLUE + "You aren't invincible!");
                            return true;
                        }
                        player.sendMessage(ChatColor.BLUE + "The gods have given you the gift of invincibility!");
                        player.setDisplayName(plugin.title + player.getDisplayName());
                        plugin.godmodeEnabled.add(player.getName());
                        player.setHealth(20);
                        return true;
                    }
                } else {
                    Player targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + "The user " + split[0] + " does not exist or is not currently logged in.");
                    } else if (targetPlayer == player) {
                        player.sendMessage(ChatColor.RED + "Please use '/godmode' to godmode yourself.");

                    } else {
                        if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                            if (command.getName().equalsIgnoreCase("godmodeon")) {
                                player.sendMessage(ChatColor.RED + "They're already invincible!");
                                return true;
                            }
                            plugin.godmodeEnabled.remove(targetPlayer.getName());
                            targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has returned your mortal state.");
                            targetPlayer.setDisplayName(targetPlayer.getName());
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been returned to their mortal state.");
                        } else {
                            if (command.getName().equalsIgnoreCase("godmodeoff")) {
                                player.sendMessage("They aren't invincible!");
                                return true;
                            }
                            targetPlayer.sendMessage(ChatColor.BLUE + "By the power of " + player.getName() + " you are now invincible!");
                            targetPlayer.setDisplayName(plugin.title + targetPlayer.getName());
                            plugin.godmodeEnabled.add(targetPlayer.getName());
                            targetPlayer.setHealth(20);
                            player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been given the gift of invincibility.");
                        }
                    }
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
                return true;
            }
        }
        return false;
    }
}
