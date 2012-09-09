/**
 *  Name: Commands.java
 *  Date: 17:18:58 - 9 sep 2012
 * 
 *  Author: LucasEmanuel @ bukkit forums
 *  
 *  
 *  Description:
 *  
 *  
 *  
 * 
 * 
 */

package me.lucasemanuel.freeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private Main plugin;
	
	public Commands(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("freezelist")) {
			
			sender.sendMessage(ChatColor.BLUE + " --- Frysta spelare --- ");
			
			for(String name : plugin.getBlocklist()) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + name);
			}
			
			return true;
		}
		
		if(args.length != 1) {
			sender.sendMessage(ChatColor.RED + "För få argument!");
			return false;
		}
		
		Player player = Bukkit.getPlayer(args[0]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "Spelaren hittades inte!");
			return true;
		}
		
		if(player.hasPermission("freeze.override")) {
			sender.sendMessage(ChatColor.RED + "Du kan inte frysa den här spelaren! :'(");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("freeze")) {
			this.plugin.blockPlayer(player.getName());
			sender.sendMessage(ChatColor.GREEN + "Spelaren " + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.GREEN + " har blivit fryst!");
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Du har blivit fryst!");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("unfreeze")) {
			this.plugin.unblockPlayer(player.getName());
			sender.sendMessage(ChatColor.GREEN + "Spelaren " + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.GREEN + " har blivit tinad!");
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Du har blivit tinad!");
			return true;
		}
		
		return false;
	}
}
