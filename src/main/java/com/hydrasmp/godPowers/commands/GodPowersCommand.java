package com.hydrasmp.godPowers.commands;

import com.hydrasmp.godPowers.godPowers;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodPowersCommand implements CommandExecutor {
    private Player player;
    private final godPowers plugin;

    public GodPowersCommand(godPowers instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;

            if (args.length == 0) {
                ;
                player.sendMessage(ChatColor.GREEN + "Use " + ChatColor.RED + "/godpowers commands" + ChatColor.GREEN + " to see all commands");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("commands")) {
                    if (player.hasPermission("godpowers.commands") | player.isOp()) {
                        player.sendMessage(ChatColor.BLUE + StringHandler.GODPOWERS_COMMAND_LIST_HEADER);
                        if (player.hasPermission("godpowers.bless") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/bless " + ChatColor.GREEN + StringHandler.LIST_BLESS_DESCRIPTION);
                        if (player.hasPermission("godpowers.demigod") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/demigod " + ChatColor.GREEN + StringHandler.LIST_DEMIGOD_DESCRIPTION);
                        if (player.hasPermission("godpowers.die") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/die " + ChatColor.GREEN + StringHandler.LIST_DIE_DESCRIPTION);
                        if (player.hasPermission("godpowers.dupe") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/dupe " + ChatColor.GREEN + StringHandler.LIST_DUPE_DESCRIPTION);
                        if (player.hasPermission("godpowers.fusrodah") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/fusrodah " + ChatColor.GREEN + StringHandler.LIST_FUSRODAH_DESCRIPTION);
                        if (player.hasPermission("godpowers.gaia") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/gaia " + ChatColor.GREEN + StringHandler.LIST_GAIA_DESCRIPTION);
                        if (player.hasPermission("godpowers.godmode") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/godmode " + ChatColor.GREEN + StringHandler.LIST_GODMODE_DESCRIPTION);
                        player.sendMessage(ChatColor.RED + "/godpowers credits " + ChatColor.GREEN + "Show the plugin developers!");
                        if (player.hasPermission("godpowers.hades") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hades " + ChatColor.GREEN + StringHandler.LIST_HADES_DESCRIPTION);
                        if (player.hasPermission("godpowers.heal") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/heal " + ChatColor.GREEN + StringHandler.LIST_HEAL_DESCRIPTION);
                        if (player.hasPermission("godpowers.hermes") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/hermes " + ChatColor.GREEN + StringHandler.LIST_HERMES_DESCRIPTION);
                        if (player.hasPermission("godpowers.inferno") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/inferno " + ChatColor.GREEN + StringHandler.LIST_INFERNO_DESCRIPTION);
                        if (player.hasPermission("godpowers.jesus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/jesus " + ChatColor.GREEN + StringHandler.LIST_JESUS_DESCRIPTION);
                        if (player.hasPermission("godpowers.maim") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/maim " + ChatColor.GREEN + StringHandler.LIST_MAIM_DESCRIPTION);
                        if (player.hasPermission("godpowers.medusa") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/medusa " + ChatColor.GREEN + StringHandler.LIST_MEDUSA_DESCRIPTION);
                        if (player.hasPermission("godpowers.plutus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/plutus " + ChatColor.GREEN + StringHandler.LIST_PLUTUS_DESCRIPTION);
                        if (player.hasPermission("godpowers.poseidon") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/poseidon " + ChatColor.GREEN + StringHandler.LIST_POSEIDON_DESCRIPTION);
                        if (player.hasPermission("godpowers.repair") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/repair " + ChatColor.GREEN + StringHandler.LIST_REPAIR_DESCRIPTION);
                        if (player.hasPermission("godpowers.slay") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/slay " + ChatColor.GREEN + StringHandler.LIST_SLAY_DESCRIPTION);
                        if (player.hasPermission("godpowers.superjump") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/superjump " + ChatColor.GREEN + StringHandler.LIST_SUPERJUMP_DESCRIPTION);
                        if (player.hasPermission("godpowers.vulcan") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/vulcan " + ChatColor.GREEN + StringHandler.LIST_VULCAN_DESCRIPTION);
                        if (player.hasPermission("godpowers.zeus") | player.isOp())
                            player.sendMessage(ChatColor.RED + "/zeus " + ChatColor.GREEN + StringHandler.LIST_ZEUS_DESCRIPTION);
                    } else {
                        player.sendMessage(StringHandler.GODPOWERS_NOPERMISSION);
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("credits")) {
                    player.sendMessage(ChatColor.DARK_AQUA + "Credits:");
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "Hydra SMP" + ChatColor.DARK_AQUA + " (Current Developer)");
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "SwiftDev" + ChatColor.DARK_AQUA + " (Upstream Developer)");
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "FriedTaco" + ChatColor.DARK_AQUA + " (Original Developer)");
                    player.sendMessage(ChatColor.GOLD + "- " + ChatColor.GREEN + "UnceCrafter" + ChatColor.DARK_AQUA + " (/poseidon)");
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "Incorrect syntax. Use " + ChatColor.RED + "/godpowers [commands]");
            }
            return true;
        }
        return false;
    }
}
