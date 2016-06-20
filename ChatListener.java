package me.swduell;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.swduell.swduell;

@SuppressWarnings("deprecation")
public class ChatListener implements Listener {
	private swduell plugin;

	public ChatListener(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
   @EventHandler
   public void onChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
    if ((plugin.ingame == true) && (plugin.tot.contains(p))) {
       e.setCancelled(true);
       for (Player all : Bukkit.getOnlinePlayers()) {
         if (plugin.tot.contains(all))
	          all.sendMessage("§8[§cSpec§8] §r" + e.getPlayer().getDisplayName() + "§8: §f" + e.getMessage());
	      }
	     }
     else
	    {
		      e.setFormat(e.getPlayer().getDisplayName() + "§8: §f" + e.getMessage());
	      e.setCancelled(false);
	     }
			
		

	/*    */   }
	
	
	
}
