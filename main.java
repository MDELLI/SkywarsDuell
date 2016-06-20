package me.swduell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class swduell extends JavaPlugin implements Listener{
	public ArrayList<Player> tot = new ArrayList<Player>();
	public ArrayList<Player> lebend = new ArrayList<Player>();
	public ArrayList<Player> kitstandard = new ArrayList<Player>();
	public ArrayList<Player> kitmlg = new ArrayList<Player>();
	public ArrayList<Player> kittank = new ArrayList<Player>();
	public ArrayList<Player> kitminer = new ArrayList<Player>();
	public ArrayList<Player> kitarcher = new ArrayList<Player>();
	public HashMap<Location, Inventory> sgchest = new HashMap<Location, Inventory>();
	public HashMap<String, ItemStack[]> Inventory = new HashMap<>();
	public boolean ingame;
	public int high = 31;
	public int LobbyCountdown;
	public int restart = 12;
	public int RestartCountdown;
	public int sbl;
	public static File Locations;
	public static FileConfiguration locs;
	
	public ArrayList<Player> spawn1 = new ArrayList<Player>();
	public ArrayList<Player> spawn2 = new ArrayList<Player>();
	
	
	
	public void onEnable() {
		System.out.println("[SWDuell] Plugin Aktiviert");
		this.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new ChestManager(this), this);
		registerListener();
		PlayerSize();
		LobbyCounter();
		ingame = false;
		high = 31;
		restart = 12;
		loadConfig();
		
	}
	
	
	
	public void onDisable() {
		System.out.println("[SWDuell] Plugin Deaktiviert");
	}
	
	
	
	public void registerListener() {
		new BlockListener(this);
		new ChatListener(this);
		new ChestManager(this);
		new DeathListener(this);
		new ItemListener(this);
		new JoinListener(this);
		new PingListener(this);
		new QuitListener(this);
		new DamageListener(this);
		new kitmenu(this);
		
		}
	
	  public void LobbyCounter() {
			if (ingame == false) {
				
				high = 31;
				LobbyCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						if (Bukkit.getOnlinePlayers().size() > 1) {
						
		                for(Player p : Bukkit.getOnlinePlayers()){
		                    p.setLevel(high-1);
		                }
						
						if (high != 0) {
							//rueckwaerts countdown
							high--;
							//Level bar
							//countdown nachrichten und Ton
							
					         for(Player p : Bukkit.getOnlinePlayers()){
				                   if (high == 3) {
				                	   p.sendTitle("§33", "");
				                	   
				                   }
				                   if (high == 2) {
				                	   p.sendTitle("§e2", "");
				                	   
				                   }
				                   if (high == 1) {
				                	   p.sendTitle("§c1", "");
				                	   
				                   }
				                   if (high == 0) {
				                	   p.sendTitle("§aGo!", "");
				                	   
				                   }
					         }
							
							
							
							
					if ((high == 30) || (high == 20) || (high == 15) || (high == 10) || (high == 5) || (high == 4) || (high == 3) || (high == 2) || (high == 1)) {
		          
						for (Player all : Bukkit.getOnlinePlayers()) {
		            all.playSound(all.getLocation(), Sound.NOTE_PLING, 5.0F, 5.0F);
						}
						Bukkit.broadcastMessage("§eSkyWars§8: §7Die Runde startet in §6" + high + " §7Sekunden.");
						
						
							}
						}
						else{
							//nach dem countdown:
							if (high == 0) {
								Bukkit.broadcastMessage("§eSkyWars§8: §aAlle Spieler werden Teleportiert!");
								for (Player all : Bukkit.getOnlinePlayers()) {
					                all.playSound(all.getLocation(), Sound.LEVEL_UP, 5.0F, 5.0F);
					                all.getInventory().clear();
					                all.getInventory().setArmorContents(null);
					                sendIngameBoard(all);
										}
								onTeleport();
								ingame = true;
								StandartItems();
								Bukkit.getScheduler().cancelTask(LobbyCountdown);
								StandartItems();
							}
							
							//was soll nach dem Countdown passieren?
							
						}
						
						}
						
						if (Bukkit.getOnlinePlayers().size() < 2) {
							high = 31;
							for (Player all : Bukkit.getOnlinePlayers()) {
								all.setLevel(30);
								all.setExp(0);
							}
							
						}
					}
					
					
				}, 0, 20);
				
					
				}
				

		}
	
	
	  public void PlayerSize() {

		  
			RestartCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

				@Override
				public void run() {
					if ((ingame == true) && (lebend.size() < 2)){
					
	                for(Player p : Bukkit.getOnlinePlayers()){
	                    p.setLevel(restart-1);
	                }
					
					if (restart != 0) {
						restart--;			
				if ((restart == 10) || (restart == 5) || (restart == 4) || (restart == 3) || (restart == 2) || (restart == 1)) {
					Bukkit.broadcastMessage("§eSkyWars§8: §7Der Server startet in §c" + restart + " §7Sekunden neu.");
						}	
					}
					else{

							
							for (Player all : Bukkit.getOnlinePlayers()) {
							all.kickPlayer("§eSkyWars§8: §7Der Server startet §cJetzt §7neu.");
							Bukkit.shutdown();
						}
						
							
					}	
				
				}
					if ((Bukkit.getOnlinePlayers().size() < 1) && (ingame == true)) {
						Bukkit.shutdown();
					}
				}	
				
				}, 0, 20);
		  
		  
	
		  

			
			}
	
	
	
		public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
			Player p = (Player) sender;
			Location loc = p.getLocation();
			
			Locations = new File("plugins/SkyWarsDuell", "config.yml");
			locs = YamlConfiguration.loadConfiguration(Locations);
			
			if (cmd.getName().equalsIgnoreCase("start")) {
				if ((ingame == false) && (Bukkit.getOnlinePlayers().size() > 1) && (p.hasPermission("SkyWars.start"))) {
					if (high > 3) {
					p.sendMessage("§eSkyWars§8: §aDu hast die Runde gestartet!");
					high = 4;
					}
				}else{
					p.sendMessage("§eSkyWars§8: §cDu kannst die Runde gerade nicht starten!");
				}
			}
			
			if (cmd.getName().equalsIgnoreCase("skywars")) {
				p.sendMessage("§eSkyWars Duell v1.3 §cDeveloped by §bElli_");
			}
			
			
			if (cmd.getName().equalsIgnoreCase("setspawn1")) {
				if (!p.hasPermission("SkyWars.setup")) {
					p.sendMessage("§eSkyWars§8: §cDu hast keine Rechte auf diesen Befehl!");
				}else{
				locs.set("SkyWars.Spawns.1.x", loc.getX());
				locs.set("SkyWars.Spawns.1.y", loc.getY());
				locs.set("SkyWars.Spawns.1.z", loc.getZ());
				locs.set("SkyWars.Spawns.1.yaw", loc.getYaw());
				locs.set("SkyWars.Spawns.1.pitch", loc.getPitch());
				locs.set("SkyWars.Spawns.1.world", loc.getWorld().getName());
				p.sendMessage("§eSkyWars§8: §aDu hast den 1. Spawn gesetzt!");
				
				try {
					locs.save(Locations);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			}
			
			if (cmd.getName().equalsIgnoreCase("setspawn2")) {
				if (!p.hasPermission("SkyWars.setup")) {
					p.sendMessage("§eSkyWars§8: §cDu hast keine Rechte auf diesen Befehl!");
				}else{
				locs.set("SkyWars.Spawns.2.x", loc.getX());
				locs.set("SkyWars.Spawns.2.y", loc.getY());
				locs.set("SkyWars.Spawns.2.z", loc.getZ());
				locs.set("SkyWars.Spawns.2.yaw", loc.getYaw());
				locs.set("SkyWars.Spawns.2.pitch", loc.getPitch());
				locs.set("SkyWars.Spawns.2.world", loc.getWorld().getName());
				p.sendMessage("§eSkyWars§8: §aDu hast den 2. Spawn gesetzt!");
				
				try {
					locs.save(Locations);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			}
			
			if (cmd.getName().equalsIgnoreCase("setlobby")) {
				if (!p.hasPermission("SkyWars.setup")) {
					p.sendMessage("§eSkyWars§8: §cDu hast keine Rechte auf diesen Befehl!");
				}else{
				locs.set("SkyWars.Lobby.x", loc.getX());
				locs.set("SkyWars.Lobby.y", loc.getY());
				locs.set("SkyWars.Lobby.z", loc.getZ());
				locs.set("SkyWars.Lobby.yaw", loc.getYaw());
				locs.set("SkyWars.Lobby.pitch", loc.getPitch());
				locs.set("SkyWars.Lobby.world", loc.getWorld().getName());
				p.sendMessage("§eSkyWars§8: §aDu hast den Lobby Spawn gesetzt!");
				
				try {
					locs.save(Locations);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			}
			
			if (cmd.getName().equalsIgnoreCase("setspectator")) {
				if (!p.hasPermission("SkyWars.setup")) {
					p.sendMessage("§eSkyWars§8: §cDu hast keine Rechte auf diesen Befehl!");
				}else{
				locs.set("SkyWars.Spectator.x", loc.getX());
				locs.set("SkyWars.Spectator.y", loc.getY());
				locs.set("SkyWars.Spectator.z", loc.getZ());
				locs.set("SkyWars.Spectator.yaw", loc.getYaw());
				locs.set("SkyWars.Spectator.pitch", loc.getPitch());
				locs.set("SkyWars.Spectator.world", loc.getWorld().getName());
				p.sendMessage("§eSkyWars§8: §aDu hast den Spectatorspawn gesetzt!");
				
				try {
					locs.save(Locations);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			}
			
			return true;
		}			
		
		
		
		public void StandartItems() {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (kitstandard.contains(p)) {
					ItemStack stone = new ItemStack(Material.STONE_SWORD);
					ItemStack sttwo = new ItemStack(Material.STONE_AXE);
					ItemStack stthree = new ItemStack(Material.STONE_PICKAXE);
					
					p.getInventory().setItem(0, stone);
					p.getInventory().setItem(1, sttwo);
					p.getInventory().setItem(2, stthree);
				
				}else if (kitmlg.contains(p)) {
					ItemStack stone = new ItemStack(Material.TNT);
					ItemStack sttwo = new ItemStack(Material.BOAT);
					ItemStack stthree = new ItemStack(Material.WEB);
					ItemStack stfour = new ItemStack(Material.WATER_BUCKET);
					
					p.getInventory().setItem(0, stone);
					p.getInventory().setItem(1, sttwo);
					p.getInventory().setItem(2, stthree);
					p.getInventory().setItem(3, stfour);
				}
				else if (kittank.contains(p)) {
					ItemStack stone = new ItemStack(Material.DIAMOND_CHESTPLATE);
					ItemStack sttwo = new ItemStack(Material.DIAMOND_BOOTS);
					ItemStack stthree = new ItemStack(Material.DIAMOND_LEGGINGS);
					ItemStack stfour = new ItemStack(Material.DIAMOND_HELMET);
					
					p.getInventory().setItem(0, stone);
					p.getInventory().setItem(1, sttwo);
					p.getInventory().setItem(2, stthree);
					p.getInventory().setItem(3, stfour);
				}
				else if (kitarcher.contains(p)) {
					ItemStack stone = new ItemStack(Material.BOW);
					ItemStack sttwo = new ItemStack(Material.ARROW, 2);
					
					p.getInventory().setItem(0, stone);
					p.getInventory().setItem(1, sttwo);
				}
				else if (kitminer.contains(p)) {
					ItemStack stone = new ItemStack(Material.DIAMOND_PICKAXE);
					
					p.getInventory().setItem(0, stone);
				}
				
			}
		}
		
		public void onTeleport() {
			File location = new File("plugins/SkyWarsDuell/config.yml");
			YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (spawn1.contains(p)) {
					Double x = locs.getDouble("SkyWars.Spawns.1.x");
					Double y = locs.getDouble("SkyWars.Spawns.1.y");
					Double z = locs.getDouble("SkyWars.Spawns.1.z");
					Float yaw = (float) locs.getDouble("SkyWars.Spawns.1.yaw");
					Float pitch = (float) locs.getDouble("SkyWars.Spawns.1.pitch");
					World w = Bukkit.getWorld(locs.getString("SkyWars.Spawns.1.world"));
					Location spawn1 = new Location(w, x, y, z, yaw, pitch);
					p.teleport(spawn1);
				}else if (spawn2.contains(p)) {
					
					Double x = locs.getDouble("SkyWars.Spawns.2.x");
					Double y = locs.getDouble("SkyWars.Spawns.2.y");
					Double z = locs.getDouble("SkyWars.Spawns.2.z");
					Float yaw = (float) locs.getDouble("SkyWars.Spawns.2.yaw");
					Float pitch = (float) locs.getDouble("SkyWars.Spawns.2.pitch");
					World w = Bukkit.getWorld(locs.getString("SkyWars.Spawns.2.world"));
					Location spawn2 = new Location(w, x, y, z, yaw, pitch);
					p.teleport(spawn2);
					
					
					
				}
				
			}
			
		}
				
		public void onIngameScoreBoard() {
		sbl = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {

			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendIngameBoard(all);
				}
				
			}
		}, 0, 40);
		
		}
		
		
		
		
		
		
		  public void sendLobbyBoard(Player p) {
				File location = new File("plugins/SkyWarsDuell/config.yml");
				YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
			    ScoreboardManager manager = Bukkit.getScoreboardManager();

			    Scoreboard board = manager.getNewScoreboard();
			    Objective objective = board.registerNewObjective("test", "dummy");
			    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

			    objective.setDisplayName("§r" + locs.getString("SkyWars.Scoreboard.Title"));
			    Score Score9 = objective.getScore("§e§l ");
			    Score9.setScore(6);
			    Score Score8 = objective.getScore("§fSpielmodus");
			    Score8.setScore(5);

			    Score Score7 = objective.getScore("§eSkyWars Duell");
			    Score7.setScore(4);

			    Score Score6 = objective.getScore("§4§l ");
			    Score6.setScore(3);
			    Score Score5 = objective.getScore("§fMap");
			    Score5.setScore(2);

			    Score Score4 = objective.getScore("§c" + locs.getString("SkyWars.Scoreboard.Mapname"));
			    Score4.setScore(1);
			    
			    p.setScoreboard(board);
			  }
		
		
		  public void sendIngameBoard(Player p) {
				File location = new File("plugins/SkyWarsDuell/config.yml");
				YamlConfiguration locs = YamlConfiguration.loadConfiguration(location);
			    ScoreboardManager manager = Bukkit.getScoreboardManager();

			    Scoreboard board = manager.getNewScoreboard();
			    Objective objective = board.registerNewObjective("test", "dummy");
			    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

			    objective.setDisplayName("§r" + locs.getString("SkyWars.Scoreboard.Title"));
			    Score Score9 = objective.getScore("§e§l ");
			    Score9.setScore(14);
			    Score Score8 = objective.getScore("§fTeams");
			    Score8.setScore(13);

			    Score Score7 = objective.getScore("§cVerboten");
			    Score7.setScore(12);

			    Score Score6 = objective.getScore("§4§l ");
			    Score6.setScore(11);
			    Score Score5 = objective.getScore("§fMap");
			    Score5.setScore(10);

			    Score Score4 = objective.getScore("§e" + locs.getString("SkyWars.Scoreboard.Mapname"));
			    Score4.setScore(9);
			    
			    Score Score51 = objective.getScore("§5 ");
			    Score51.setScore(8);
			    
			    Score Score61 = objective.getScore("§fKills");
			    Score61.setScore(7);
			    
			    Score Score71 = objective.getScore("§c" + p.getStatistic(Statistic.PLAYER_KILLS));
			    Score71.setScore(6);
			    
			    Score Score81 = objective.getScore("§6");
			    Score81.setScore(5);
			    
			    Score Score91 = objective.getScore("§fSpieler");
			    Score91.setScore(4);
			    
			    Score Score10 = objective.getScore("§b" + lebend.size());
			    Score10.setScore(3);
			    
			    Score Score11 = objective.getScore("§7");
			    Score11.setScore(2);
			    

			    p.setScoreboard(board);
			  }
		  
		  
		  @EventHandler (ignoreCancelled = true, priority = EventPriority.LOW)
		    public void onWeatherChange(WeatherChangeEvent event) {
		        if (event.toWeatherState()) {
		            event.setCancelled(true);
		        }

		  }
		  
		  private void loadConfig() {
				reloadConfig();
				getConfig().options().header("Dies ist die Konfiguration zu SkyWars Duell. Trage hier die Spawns der Spieler ein.");
				getConfig().addDefault("SkyWars.Spawns.1.x", "1");
				getConfig().addDefault("SkyWars.Spawns.1.y", "20");
				getConfig().addDefault("SkyWars.Spawns.1.z", "1");
				getConfig().addDefault("SkyWars.Spawns.1.yaw", "1");
				getConfig().addDefault("SkyWars.Spawns.1.pitch", "-1");
				getConfig().addDefault("SkyWars.Spawns.1.world", "world");
				
				getConfig().addDefault("SkyWars.Spawns.2.x", "1");
				getConfig().addDefault("SkyWars.Spawns.2.y", "20");
				getConfig().addDefault("SkyWars.Spawns.2.z", "1");
				getConfig().addDefault("SkyWars.Spawns.2.yaw", "1");
				getConfig().addDefault("SkyWars.Spawns.2.pitch", "-1");
				getConfig().addDefault("SkyWars.Spawns.2.world", "world");
				
				getConfig().addDefault("SkyWars.Lobby.x", "1");
				getConfig().addDefault("SkyWars.Lobby.y", "20");
				getConfig().addDefault("SkyWars.Lobby.z", "1");
				getConfig().addDefault("SkyWars.Lobby.yaw", "1");
				getConfig().addDefault("SkyWars.Lobby.pitch", "-1");
				getConfig().addDefault("SkyWars.Lobby.world", "world");
				
				getConfig().addDefault("SkyWars.Spectate.x", "1");
				getConfig().addDefault("SkyWars.Spectate.y", "20");
				getConfig().addDefault("SkyWars.Spectate.z", "1");
				getConfig().addDefault("SkyWars.Spectate.yaw", "1");
				getConfig().addDefault("SkyWars.Spectate.pitch", "-1");
				getConfig().addDefault("SkyWars.Spectate.world", "world");
				
				getConfig().addDefault("SkyWars.Scoreboard.Title", "DeinServer.de");
				getConfig().addDefault("SkyWars.Scoreboard.Mapname", "Mapname");
				getConfig().options().copyDefaults(true);
				saveConfig();
				System.out.println("Config wurde geladen!");
		  }
		  
}
