package me.swduell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

import me.swduell.swduell;

public class PingListener implements Listener {
	private swduell plugin;
	HashMap<String, Integer> JoinPower = new HashMap();
	
	public PingListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onMotdChange(ServerListPingEvent e) {
		if (plugin.ingame == false) {
			e.setMotd("§aLobby");
		}
		if (plugin.ingame == true) {
			e.setMotd("§cIngame");
		}
		if ((plugin.ingame == false) && (Bukkit.getOnlinePlayers().size() == Bukkit.getMaxPlayers())) {
			e.setMotd("§6Lobby");
		}
		
	}
	
	
	
	   @EventHandler
	   public void onLogin(PlayerLoginEvent e)
	  {
	     Player p = e.getPlayer();
	 
	     if (p.hasPermission("SkyWars.joinfull")) {
	      if (!this.JoinPower.containsKey(p.getName())) {
	         this.JoinPower.put(p.getName(), Integer.valueOf(1));
	      }
	     }
	    else if (!this.JoinPower.containsKey(p.getName())) {
	      this.JoinPower.put(p.getName(), Integer.valueOf(0));
	     }
 
	     if (Bukkit.getOnlinePlayers().size() >= 2) {
	      List canKick = new ArrayList();

	      int plJoinPower = ((Integer)this.JoinPower.get(p.getName())).intValue();
	
	       if (plugin.ingame == false) {
	        if (plJoinPower == 1) {
	          for (Player all : Bukkit.getOnlinePlayers()) {
           if (this.JoinPower.containsKey(all.getName())) {
              int i = ((Integer)this.JoinPower.get(all.getName())).intValue();
              if (i == 0) {
               canKick.add(all);
             }
           }
          }
        
 
        int kickran = new Random().nextInt(canKick.size());
        Player ps = (Player)canKick.get(kickran);
        ps.kickPlayer("§cUm einem §6VIP-Spieler §cPlatz zu machen wirst du aus der Runde gekickt!");
        
       }
    }
   }
	
	}
}
