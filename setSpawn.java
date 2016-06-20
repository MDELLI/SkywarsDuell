package me.swduell;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		if (!p.hasPermission("SkyWars.setup")) {
			p.sendMessage("§eSkyWars§8: §cDu hast keine Rechte auf diesen Befehl!");
			return true;
		}
		
		File ordner = new File("plugins//skywarsduell");
		File file = new File("plugins//skywarsduell//config.yml");
		
		if (!ordner.exists()) {
			ordner.mkdir();
		}
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		Location loc = p.getLocation();
		
		double x1 = loc.getX();
		double y1 = loc.getY();
		double z1 = loc.getZ();
		float yaw1 = loc.getYaw();
		float pitch1 = loc.getPitch();
		
		double x2 = loc.getX();
		double y2 = loc.getY();
		double z2 = loc.getZ();
		float yaw2 = loc.getYaw();
		float pitch2 = loc.getPitch();
 		String weltname = loc.getWorld().getName();
 		
 		cfg.set("SkyWars.spawn1.x1", x1);
 		cfg.set("SkyWars.spawn1.y1", y1);
 		cfg.set("SkyWars.spawn1.z1", z1);
 		cfg.set("SkyWars.spawn1.yaw1", yaw1);
 		cfg.set("SkyWars.spawn1.pitch1", pitch1);
 		
 		cfg.set("SkyWars.spawn2.x2", x2);
 		cfg.set("SkyWars.spawn2.y2", y2);
 		cfg.set("SkyWars.spawn2.z2", z2);
 		cfg.set("SkyWars.spawn2.yaw2", yaw2);
 		cfg.set("SkyWars.spawn2.pitch2", pitch2);
 		
 		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendMessage("§eSkyWars§8: §aDu hast den 1. Spawn gesetzt!");
		return false;
	}

}
