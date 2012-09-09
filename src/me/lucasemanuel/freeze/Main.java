/**
 *  Name: Main.java
 *  Date: 18:09:05 - 8 sep 2012
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

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private ArrayList<String> blockedPlayers;
	
	@SuppressWarnings("unchecked")
	public void onEnable() {
		
		this.blockedPlayers = new ArrayList<String>();
		
		if(getConfig().contains("frystaspelare")) {
			for(String playername : (ArrayList<String>) getConfig().getList("frystaspelare")) {
				blockPlayer(playername);
			}
		}
		
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		Commands commands = new Commands(this);
		
		this.getCommand("freeze").setExecutor(commands);
		this.getCommand("unfreeze").setExecutor(commands);
		this.getCommand("freezelist").setExecutor(commands);
	}
	
	public boolean isBlocked(String playername) {
		if(this.blockedPlayers.contains(playername))
			return true;
		else
			return false;
	}
	
	public void blockPlayer(String playername) {
		if(!this.blockedPlayers.contains(playername)) {
			this.blockedPlayers.add(playername);
			getConfig().set("frystaspelare", blockedPlayers);
			saveConfig();
		}
	}
	
	public void unblockPlayer(String playername) {
		if(this.blockedPlayers.contains(playername)) {
			this.blockedPlayers.remove(playername);
			getConfig().set("frystaspelare", blockedPlayers);
			saveConfig();
		}
	}
	
	public ArrayList<String> getBlocklist() {
		return this.blockedPlayers;
	}
}