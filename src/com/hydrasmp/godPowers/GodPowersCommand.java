package com.hydrasmp.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodPowersCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public GodPowersCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;	
    			
				if(args.length == 0)
				{;
				player.sendMessage(ChatColor.GREEN+"Use "+ChatColor.RED+"/godpowers commands"+ChatColor.GREEN+" to see all commands");
				}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("commands")){
		    		if(player.hasPermission("godpowers.commands"))
		    		{
					player.sendMessage(ChatColor.BLUE + "You can use the following commands: (< > = Optional [ ] = Required)");
					for(String s : plugin.list.keySet())
						if(player.hasPermission("godpowers."+plugin.list.get(s)))
								player.sendMessage(ChatColor.AQUA + "/" + s + " " + plugin.list.get(s));
		    		}else
		    		{
		    			player.sendMessage("The gods prevent you from using this command.");
		    			return true;
		    		}
				}
				if(args[0].equalsIgnoreCase("credits")){
					player.sendMessage(ChatColor.DARK_AQUA+"Credits:");
                    player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"Hydra SMP"+ChatColor.DARK_AQUA+" (Current Developer)");
                    player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"SwiftDev"+ChatColor.DARK_AQUA+" (Former Developer)");
                    player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"FriedTaco"+ChatColor.DARK_AQUA+" (Original Developer)");
                    player.sendMessage(ChatColor.GOLD+"- "+ChatColor.GREEN+"UnceCrafter"+ChatColor.DARK_AQUA+" (/poseidon)");
				}
				}
				else
				{
					player.sendMessage("Incorrect syntax. Use '/godpowers [commands]'");
				}
				return true;    		
    	}        
        return false;
    }
}
