package me.swduell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.swduell.swduell;

public class ChestManager implements Listener {
	private swduell plugin;

	public ChestManager(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
	@EventHandler
	public void onChestopen(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		 if(e.getAction() == Action.RIGHT_CLICK_BLOCK &&
		 (e.getClickedBlock().getType() == Material.CHEST) && (plugin.ingame == true) && (plugin.lebend.contains(p))) {
				 if(plugin.sgchest.containsKey(e.getClickedBlock().getLocation())) {
					 p.openInventory((Inventory)plugin.sgchest.get(e.getClickedBlock().getLocation()));
				       for (Player all : Bukkit.getOnlinePlayers()) {
				           all.playSound(e.getClickedBlock().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
				        }
						 
				 }else{
					 
					 Random rnd = new Random();
					 int n = 1;
					 n = rnd.nextInt(7);
					 Inventory inv = Bukkit.createInventory(null, 27, "§cTruhe");
					 List<ItemStack> items = new ArrayList<ItemStack>();	
					
					items.add(new ItemStack(Material.BREAD, 3));
					items.add(new ItemStack(Material.WEB, 3));
					items.add(new ItemStack(Material.APPLE, 2));
					items.add(new ItemStack(Material.APPLE, 4));
					items.add(new ItemStack(Material.COAL, 6));
					items.add(new ItemStack(Material.GOLD_BOOTS, 1));
					items.add(new ItemStack(Material.GOLD_CHESTPLATE, 1));
					items.add(new ItemStack(Material.GOLD_HELMET, 1));
					items.add(new ItemStack(Material.GOLD_LEGGINGS, 1));
					items.add(new ItemStack(Material.IRON_BOOTS, 1));
					items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
					items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
					items.add(new ItemStack(Material.IRON_HELMET, 1));
					items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
					items.add(new ItemStack(Material.COBBLESTONE, 32));
					items.add(new ItemStack(Material.RED_SANDSTONE, 12));
					items.add(new ItemStack(Material.RED_SANDSTONE, 32));
					items.add(new ItemStack(Material.RED_SANDSTONE, 64));
					items.add(new ItemStack(Material.RED_SANDSTONE, 19));
					items.add(new ItemStack(Material.RED_SANDSTONE, 13));
					items.add(new ItemStack(Material.RED_SANDSTONE, 9));
					items.add(new ItemStack(Material.RED_SANDSTONE, 7));
					items.add(new ItemStack(Material.RED_SANDSTONE,57));
					items.add(new ItemStack(Material.BRICK, 23));
					items.add(new ItemStack(Material.BRICK, 64));
					items.add(new ItemStack(Material.STICK, 3));
					items.add(new ItemStack(Material.WORKBENCH, 1));
					items.add(new ItemStack(Material.WOOD, 9));
					items.add(new ItemStack(Material.WOOD, 3));
					items.add(new ItemStack(Material.WOOD, 14));
					items.add(new ItemStack(Material.WOOD_PICKAXE, 1));
					items.add(new ItemStack(Material.WOOD_AXE, 1));
					items.add(new ItemStack(Material.GLASS, 29));
					items.add(new ItemStack(Material.RAW_BEEF, 7));
					items.add(new ItemStack(Material.COOKED_BEEF, 7));
					items.add(new ItemStack(Material.COOKED_BEEF, 3));
					items.add(new ItemStack(Material.TNT, 1));
					items.add(new ItemStack(Material.IRON_INGOT, 4));
					items.add(new ItemStack(Material.WEB, 5));
					items.add(new ItemStack(Material.LEATHER_HELMET, 1));
					items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
					items.add(new ItemStack(Material.CAKE_BLOCK, 1));
					items.add(new ItemStack(Material.EXP_BOTTLE, 8));
					items.add(new ItemStack(Material.COOKED_BEEF, 2));
					items.add(new ItemStack(Material.COOKED_BEEF, 4));
					items.add(new ItemStack(Material.COOKED_BEEF, 5));
					
					while (n != 0) {
						n--;
						Random rnd2 = new Random();
						Random rnd3 = new Random();
						
						int n3 = rnd3.nextInt(27);
						
						int n2 = rnd2.nextInt(items.size());
								
								inv.setItem(n3, (ItemStack)items.get(n2));
								
						
					}
					 plugin.sgchest.put(e.getClickedBlock().getLocation(), inv);
					 p.openInventory(inv);
						 return;
						 
					 
					 
					 
				 }
				 return;
			 
			 
			 
			 
		 }
		
		
		
	}
	
	@EventHandler
	public void onBigChestopen(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		 if(e.getAction() == Action.RIGHT_CLICK_BLOCK &&
		 (e.getClickedBlock().getType() == Material.ENDER_CHEST) && (plugin.ingame == true) && (plugin.lebend.contains(p))) {
				 if(plugin.sgchest.containsKey(e.getClickedBlock().getLocation())) {
					 p.openInventory((Inventory)plugin.sgchest.get(e.getClickedBlock().getLocation()));
				       for (Player all : Bukkit.getOnlinePlayers()) {
				           all.playSound(e.getClickedBlock().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
				        }
						 
				 }else{
					 
					 Random rnd = new Random();
					 int n = 1;
					 n = rnd.nextInt(4);
					 Inventory inv = Bukkit.createInventory(null, 27, "§cGrosse Truhe");
					 List<ItemStack> items = new ArrayList<ItemStack>();	
					
					items.add(new ItemStack(Material.DIAMOND_AXE, 1));
					items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
					items.add(new ItemStack(Material.GOLDEN_APPLE, 1));
					items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
					items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
					items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
					items.add(new ItemStack(Material.DIAMOND, 2));
					items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
					items.add(new ItemStack(Material.IRON_AXE, 1));
					items.add(new ItemStack(Material.IRON_BOOTS, 1));
					items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
					items.add(new ItemStack(Material.IRON_HELMET, 1));
					items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
					items.add(new ItemStack(Material.IRON_SWORD, 1));
					items.add(new ItemStack(Material.EXP_BOTTLE, 12));
					items.add(new ItemStack(Material.IRON_INGOT, 7));
					items.add(new ItemStack(Material.TNT, 3));
					items.add(new ItemStack(Material.RED_SANDSTONE, 12));
					items.add(new ItemStack(Material.COOKED_BEEF, 3));
					items.add(new ItemStack(Material.GLASS, 29));
					items.add(new ItemStack(Material.RED_SANDSTONE, 21));
					items.add(new ItemStack(Material.IRON_INGOT, 2));
					items.add(new ItemStack(Material.IRON_INGOT, 4));
					items.add(new ItemStack(Material.STICK, 2));
					items.add(new ItemStack(Material.STICK, 3));
					
					while (n != 0) {
						n--;
						Random rnd2 = new Random();
						Random rnd3 = new Random();
						
						int n3 = rnd3.nextInt(27);
						
						int n2 = rnd2.nextInt(items.size());
								
								inv.setItem(n3, (ItemStack)items.get(n2));
								
						
					}
					 plugin.sgchest.put(e.getClickedBlock().getLocation(), inv);
					 p.openInventory(inv);
						 return;
						 
					 
					 
					 
				 }
				 return;
			 
			 
			 
			 
		 }
		
		
		
	}
	
	
	
}
