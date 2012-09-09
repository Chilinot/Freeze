/**
 *  Name: PlayerListener.java
 *  Date: 17:17:14 - 9 sep 2012
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

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
	
	private Main plugin;

	public PlayerListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onBlockPlace(BlockPlaceEvent event) {
		if(plugin.isBlocked(event.getPlayer().getName())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Du har blivit fryst!");
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onBlockBreak(BlockBreakEvent event) {
		if(plugin.isBlocked(event.getPlayer().getName())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Du har blivit fryst!");
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		
		String playername = event.getPlayer().getName();
		if(plugin.isBlocked(playername)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Du har blivit fryst!");
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {
		
		Player player = event.getPlayer();
		if(plugin.isBlocked(player.getName())) {
			
			Location from = event.getFrom();
			double fromX = from.getX();
			double fromZ = from.getZ();
			double fromY = from.getY();

			Location to = event.getTo();
			double toX = to.getX();
			double toZ = to.getZ();
			double toY = to.getY();

			if(fromX != toX || fromZ != toZ || fromY != toY) {

				player.teleport(from);

				player.sendMessage(ChatColor.RED + "Du har blivit fryst!");
			}
		}
	}
}