package me.swduell;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.swduell.swduell;

public class JoinListener implements Listener {
	private swduell plugin;
	int shed;

	public JoinListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		File location = new File("plugins/SkyWarsDuell/config.yml");
		YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
		Player p = e.getPlayer();
		if (plugin.ingame ==  true) {
			p.setGameMode(GameMode.SPECTATOR);
			e.setJoinMessage(null);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.hidePlayer(p);
			plugin.lebend.remove(p);
			plugin.tot.add(p);
			p.sendTitle("§cDu bist Spectator", "");
			
			Double x = locs.getDouble("SkyWars.Spectate.x");
			Double y = locs.getDouble("SkyWars.Spectate.y");
			Double z = locs.getDouble("SkyWars.Spectate.z");
			Float yaw = (float) locs.getDouble("SkyWars.Spectate.yaw");
			Float pitch = (float) locs.getDouble("SkyWars.Spectate.pitch");
			World w = Bukkit.getWorld(locs.getString("SkyWars.Spectate.world"));
			Location Spectate = new Location(w, x, y, z, yaw, pitch);
			p.teleport(Spectate);
			
			for (Player all : Bukkit.getOnlinePlayers()) {
				plugin.sendIngameBoard(all);
			}
			
			
			
		}
		if (plugin.ingame == false) {
			ItemStack kitmenu = new ItemStack(Material.GHAST_TEAR);
			ItemStack leaveitem = new ItemStack(Material.BARRIER);
			ItemMeta im = kitmenu.getItemMeta();
			im.setDisplayName("§eKit wählen");
			im.setLore(Arrays.asList("§7Suche dir ein Kit aus mit dem du Spielen wirst."));
			kitmenu.setItemMeta(im);
			e.setJoinMessage("§eSkyWars§8: §a" + e.getPlayer().getDisplayName() + " §7hat den Server betreten.");
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			plugin.tot.remove(p);
			plugin.lebend.add(p);
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setGameMode(GameMode.SURVIVAL);
			p.setLevel(0);
			p.setExp(0);
			p.showPlayer(p);
			p.setFireTicks(0);
			
			ItemMeta ki = leaveitem.getItemMeta();
			ki.setDisplayName("§cRunde Verlassen");
			ki.setLore(Arrays.asList("§cVerlasse die Runde und gehe zurück zur Lobby"));
			leaveitem.setItemMeta(ki);
			
			p.getInventory().setItem(0, kitmenu);
			p.getInventory().setItem(8, leaveitem);
			
			
			Double x = locs.getDouble("SkyWars.Lobby.x");
			Double y = locs.getDouble("SkyWars.Lobby.y");
			Double z = locs.getDouble("SkyWars.Lobby.z");
			Float yaw = (float) locs.getDouble("SkyWars.Lobby.yaw");
			Float pitch = (float) locs.getDouble("SkyWars.Lobby.pitch");
			World w = Bukkit.getWorld(locs.getString("SkyWars.Lobby.world"));
			Location Lobby = new Location(w, x, y, z, yaw, pitch);
			p.teleport(Lobby);
			
			
			if (plugin.spawn1.size() < 1) {
				plugin.spawn1.add(p);
				
			}else if (plugin.spawn1.size() >= 1) {
				plugin.spawn2.add(p); 
				
			}
			
			for (Player all : Bukkit.getOnlinePlayers()) {
				plugin.sendLobbyBoard(all);
			}
			
			
			
			
			}else{
				p.kickPlayer("§eSkyWars§8: §cEin Fehler ist aufgetreten!");
			}

			
		}

	@EventHandler
	public void Kickitem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getMaterial().equals(Material.BARRIER)) {
				p.kickPlayer("§eSkyWars§8: §cDu hast die Runde verlassen!");
			}
		}
		
		
	}
	
		
	}
