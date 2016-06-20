package me.swduell;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.swduell.swduell;

public class BlockListener implements Listener {
	private swduell plugin;

	public BlockListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
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
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (plugin.ingame == true) {
			e.setCancelled(false);
		}
		if (plugin.tot.contains(p)) {
			e.setCancelled(true);
		}
		if ((plugin.ingame == true) && (plugin.lebend.contains(p))) {
			e.setCancelled(false);
		}
		
	}
	
	@EventHandler
	public void onSpecChestOpen(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() != Material.CHEST) && (e.getClickedBlock().getType() != Material.ENDER_CHEST)) {
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
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.CHEST)) {
			e.setCancelled(true);
		}
		
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.ENDER_CHEST)) {
			e.setCancelled(true);
		}
		
		
	}
	
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e) {
		e.setCancelled(true);
	}
	
	
}
