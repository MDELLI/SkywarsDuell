package me.swduell;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.swduell.swduell;

public class ItemListener implements Listener {
	private swduell plugin;

	public ItemListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (plugin.ingame == false) {
			e.setCancelled(true);
		}
		if (plugin.tot.contains(p)) {
			e.setCancelled(true);
		}
		if ((plugin.ingame == true) && (plugin.lebend.contains(p))) {
			
			e.setCancelled(false);
			
		}
		
		
	}
	
	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if (plugin.ingame == false) {
			e.setCancelled(true);
		}
		if (plugin.tot.contains(p)) {
			e.setCancelled(true);
		}
		if ((plugin.ingame == true) && (plugin.lebend.contains(p))) {
			
			e.setCancelled(false);
			
		}
		
		
	}
	
	@EventHandler
	public void onFoodchange(FoodLevelChangeEvent e) {
		if (plugin.ingame == false) {
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	
	
}
