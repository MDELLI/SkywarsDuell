package me.swduell;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

	private swduell plugin;

	public DamageListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (plugin.ingame == false) {
			e.setCancelled(true);
		}
		else{
			e.setCancelled(false);
		}
		
		
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (plugin.ingame == false) {
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	
	}
}
	
	

