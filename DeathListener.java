package me.swduell;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.swduell.swduell;

public class DeathListener implements Listener {
	private swduell plugin;

	public DeathListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		File location = new File("plugins/SkyWarsDuell/config.yml");
		YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
		Player p = e.getEntity().getPlayer();
		Player k = null;
		if (plugin.ingame == true) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				plugin.sendIngameBoard(all);
			}
			p.setVelocity(p.getVelocity().setY(2.5));
			p.setAllowFlight(true);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
		    plugin.lebend.remove(p);
		    plugin.tot.add(p);
		    p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 10.0F, 10.0F);
		    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 1));
	
		      if ((e.getEntity().getKiller() instanceof Player)) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						plugin.sendIngameBoard(all);
					}
   k = e.getEntity().getKiller();
   k.playSound(k.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
   
	Double x = locs.getDouble("SkyWars.Spectate.x");
	Double y = locs.getDouble("SkyWars.Spectate.y");
	Double z = locs.getDouble("SkyWars.Spectate.z");
	Float yaw = (float) locs.getDouble("SkyWars.Spectate.yaw");
	Float pitch = (float) locs.getDouble("SkyWars.Spectate.pitch");
	World w = Bukkit.getWorld(locs.getString("SkyWars.Spectate.world"));
	Location Spectate = new Location(w, x, y, z, yaw, pitch);
	p.teleport(Spectate);
   
   
   
   
		      }
		      if ((e.getEntity().getKiller() instanceof Player)){
		    	  Bukkit.broadcastMessage("§eSkyWars§8: §c" + e.getEntity().getDisplayName() + " §7wurde von §a" + e.getEntity().getKiller().getDisplayName() + " §7getötet.");
		    	  e.setDeathMessage(null);
		    	  for (Player all : Bukkit.getOnlinePlayers()) {
		    		  all.sendTitle("§a" + k.getDisplayName(), "hat die Runde gewonnen");
		    	  }
		      }else{
		    	  Bukkit.broadcastMessage("§eSkyWars§8: §c" + e.getEntity().getDisplayName() + " §7ist gestorben.");
		    	  e.setDeathMessage(null);
		    	  for (Player all : Bukkit.getOnlinePlayers()) {
		    	  all.sendTitle("§cRunde beendet", "§c" + e.getEntity().getDisplayName() + "§f ist gestorben");  
		      }
		      
		      }
	}else{
		e.setDeathMessage(null);
		Double x = locs.getDouble("SkyWars.Lobby.x");
		Double y = locs.getDouble("SkyWars.Lobby.y");
		Double z = locs.getDouble("SkyWars.Lobby.z");
		Float yaw = (float) locs.getDouble("SkyWars.Lobby.yaw");
		Float pitch = (float) locs.getDouble("SkyWars.Lobby.pitch");
		World w = Bukkit.getWorld(locs.getString("SkyWars.Lobby.world"));
		Location Lobby = new Location(w, x, y, z, yaw, pitch);
		p.teleport(Lobby);
	}
	
	
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		File location = new File("plugins/SkyWarsDuell/config.yml");
		YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.sendTitle("§cDu bist gestorben!", "");
		p.setGameMode(GameMode.SPECTATOR);
		p.hidePlayer(p);
		Double x = locs.getDouble("SkyWars.Spectate.x");
		Double y = locs.getDouble("SkyWars.Spectate.y");
		Double z = locs.getDouble("SkyWars.Spectate.z");
		Float yaw = (float) locs.getDouble("SkyWars.Spectate.yaw");
		Float pitch = (float) locs.getDouble("SkyWars.Spectate.pitch");
		World w = Bukkit.getWorld(locs.getString("SkyWars.Spectate.world"));
		Location Spectate = new Location(w, x, y, z, yaw, pitch);
		p.teleport(Spectate);
	}
	
	
}
