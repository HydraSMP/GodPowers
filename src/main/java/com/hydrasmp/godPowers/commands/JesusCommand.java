package com.hydrasmp.godPowers.commands;

//import org.bukkit.World;

import com.hydrasmp.godPowers.Jesus;
import com.hydrasmp.godPowers.Jesus.Raft;
import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JesusCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public JesusCommand(godPowers instance) {
        plugin = instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
            Raft r = (Raft) Jesus.rafts.get(player.getName());
            Jesus j = new Jesus();
            if (player.hasPermission("godpowers.jesus")) {
                if (r == null) {
                    player.sendMessage(ChatColor.BLUE + StringHandler.JESUS_ADD);
                    plugin.isJesus.add(player.getName());
                    Jesus.rafts.put(player.getName(), j.new Raft());
                    return true;
                } else {
                    player.sendMessage(ChatColor.BLUE + StringHandler.JESUS_REMOVE);
                    Jesus.rafts.remove(player.getName());
                    plugin.isJesus.remove(player.getName());
                    r.destroyJesusRaft(player);
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
