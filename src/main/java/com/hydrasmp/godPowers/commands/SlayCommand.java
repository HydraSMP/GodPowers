package com.hydrasmp.godPowers.commands;

//import org.bukkit.World;

import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlayCommand implements CommandExecutor {
    private Player player, targetPlayer;
    private final godPowers plugin;

    public SlayCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = sender instanceof Player ? ((Player) sender).getWorld() : plugin.getServer().getWorlds().get(0);
        String[] split = args;
        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.hasPermission("godpowers.slay")) {
                if (split.length == 1) {
                    targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.SLAY_DOESNTEXIST);
                    } else if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                        player.sendMessage(ChatColor.RED + StringHandler.SLAY_GOD);
                    } else {
                        targetPlayer.setHealth(0);
                        plugin.dropDeadItems(targetPlayer);
                        player.sendMessage(ChatColor.BLUE + StringHandler.SLAY_SLAINOTHER + " " + targetPlayer.getName() + ".");
                        targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.SLAY_WILLOF + " " + player.getName() + ", " + StringHandler.SLAY_BEENSLAIN);
                    }

                    return true;
                } else if (split.length == 2) {
                    targetPlayer = plugin.getServer().getPlayer(split[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.RED + StringHandler.SLAY_DOESNTEXIST);
                        return true;
                    } else if (plugin.godmodeEnabled.contains(targetPlayer.getName())) {
                        player.sendMessage(ChatColor.DARK_RED + StringHandler.SLAY_GOD);
                        return true;
                    }
                    if (split[1].equalsIgnoreCase("a") || split[1].equalsIgnoreCase("arrows")) {
                        int x = -4;
                        Location arrows = new Location(world, targetPlayer.getLocation().getX() + x, targetPlayer.getLocation().getY() + 1, targetPlayer.getLocation().getZ() + x);
                        while (arrows.getBlock().getType() != Material.AIR) {
                            arrows = new Location(world, targetPlayer.getLocation().getX() + x, targetPlayer.getLocation().getY() + 1, targetPlayer.getLocation().getZ() + x);
                            if (arrows.getBlock().getType() == Material.AIR)
                                break;
                            else if (x > 4)
                                break;
                        }
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " +StringHandler.SLAY_SLAINARROWS);
                        plugin.arrowKill.add(targetPlayer.getName());
                    } else if (split[1].equalsIgnoreCase("f") || split[1].equalsIgnoreCase("fire")) {
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.SLAY_SLAINIGNITE);
                        targetPlayer.setFireTicks(500);
                        targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.SLAY_SLAINIGNITEYOU);
                        plugin.burn.add(targetPlayer.getName());
                    } else if (split[1].equalsIgnoreCase("d") || split[1].equalsIgnoreCase("drop")) {
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.SLAY_SLAINDROP);
                        targetPlayer.teleport(new Location(world, targetPlayer.getLocation().getX(), 1000, targetPlayer.getLocation().getZ()));
                        targetPlayer.sendMessage(ChatColor.BLUE + StringHandler.SLAY_SLAINDROPYOU);
                    } else if (split[1].equalsIgnoreCase("l") || split[1].equalsIgnoreCase("lightning")) {
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.SLAY_SLAINLIGHTNING);
                        player.getWorld().strikeLightning(targetPlayer.getLocation());
                    } else if (split[1].equalsIgnoreCase("c") || split[1].equalsIgnoreCase("curse")) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.SLAY_SLAINCURSE + " " + targetPlayer.getName());
                        targetPlayer.sendMessage(ChatColor.DARK_PURPLE + StringHandler.SLAY_SLAINCURSEYOU);
                        int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                            public void run() {
                                targetPlayer.damage(4);
                                targetPlayer.sendMessage(ChatColor.DARK_PURPLE + StringHandler.SLAY_SLAINCURSEEFFECT);
                            }
                        }, 30L, 30L);
                        plugin.curse.put(targetPlayer.getName(), Integer.valueOf(id));
                    } else if (split[1].equalsIgnoreCase("v") || split[1].equalsIgnoreCase("void")) {
                        player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " " + StringHandler.SLAY_SLAINVOID);
                        targetPlayer.sendMessage(ChatColor.DARK_GRAY + StringHandler.SLAY_SLAINVOIDYOU);
                        Location voidLoc = targetPlayer.getLocation();
                        voidLoc.setY(0);
                        targetPlayer.teleport(voidLoc);
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + StringHandler.SLAY_SYNTAX);
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
