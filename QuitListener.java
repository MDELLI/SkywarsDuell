package me.swduell;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.swduell.swduell;

public class QuitListener implements Listener {
	private swduell plugin;

	public QuitListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		if (plugin.tot.contains(p)) {
			plugin.tot.remove(p);
			plugin.lebend.remove(p);
			e.setQuitMessage(null);
		}
		if (plugin.ingame == false) {
			e.setQuitMessage("§eSkyWars§8: §7Dein Gegner §c" + e.getPlayer().getDisplayName() + " §7hat die Runde verlassen.");
			plugin.lebend.remove(p);
			plugin.tot.remove(p);
			plugin.kitarcher.remove(p);
			plugin.kitminer.remove(p);
			plugin.kitmlg.remove(p);
			plugin.kitstandard.remove(p);
			plugin.kittank.remove(p);
			plugin.spawn1.remove(p);
			plugin.spawn2.remove(p);
		}else{
			plugin.lebend.remove(p);
			plugin.tot.remove(p);
			for (Player all : Bukkit.getOnlinePlayers()) {
				plugin.sendIngameBoard(all);
			}
			e.setQuitMessage("§eSkyWars§8: §7Dein Gegner §c" + e.getPlayer().getDisplayName() + " §7hat die Runde verlassen.");
		}
		
		
	}
	
	
	
}
