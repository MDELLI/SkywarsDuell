package me.swduell;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class kitmenu implements Listener {
	private Inventory inv=null;
	private swduell plugin;
	int shed;

	public kitmenu(swduell plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onKitMenu(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		if (p.getItemInHand().getType() == Material.GHAST_TEAR) {
		
		inv = p.getPlayer().getServer().createInventory(null, 9, "§eSkyWars §bKits");
		
		ItemStack istack = new ItemStack(Material.STONE_AXE);
		ItemMeta istackmeta = istack.getItemMeta();
	    istackmeta.setDisplayName("§7Standard Kit");
	    istackmeta.setLore(Arrays.asList("§7Du startest mit: §eStein Schwert, Axt, Spitzhacke"));
	    istack.setItemMeta(istackmeta);
		
	    
	    ItemStack istack2 = new ItemStack(Material.TNT);
		ItemMeta istackmeta2 = istack2.getItemMeta();
	    istackmeta2.setDisplayName("§cMLG Kit");
	    istackmeta2.setLore(Arrays.asList("§7Du startest mit: §cTNT, Spinnenweben, Wassereimer, Druckplatte, Boot"));
	    istack2.setItemMeta(istackmeta2);
	    
	    
	    ItemStack istack3 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta istackmeta3 = istack3.getItemMeta();
	    istackmeta3.setDisplayName("§5Tank Kit");
	    istackmeta3.setLore(Arrays.asList("§7Du startest mit: §eVoller Diamante Rüstung"));
	    istack3.setItemMeta(istackmeta3);
	    
	    ItemStack istack4 = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta istackmeta4 = istack4.getItemMeta();
	    istackmeta4.setDisplayName("§5Miner Kit");
	    istackmeta4.setLore(Arrays.asList("§7Du startest mit einer: §eDiamanten Spitzhacke"));
	    istack4.setItemMeta(istackmeta4);
	    
	    ItemStack istack5 = new ItemStack(Material.BOW);
		ItemMeta istackmeta5 = istack5.getItemMeta();
	    istackmeta5.setDisplayName("§6Premium§8: §5Archer Kit");
	    istackmeta5.setLore(Arrays.asList("§7Du startest mit: §e1 Bogen, 2 Pfeile"));
	    istack5.setItemMeta(istackmeta5);
	
	
	
	
	
	    inv.setItem(0, istack);
	    inv.setItem(1, istack2);
	    inv.setItem(2, istack3);
	    inv.setItem(3, istack4);
	    inv.setItem(4, istack5);
	    
	    
	   p.getPlayer().openInventory(inv);
	    
	    
	   }
		
		
		
		
		
		
		
		}
		}
	
	@EventHandler
	public void Inventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (plugin.ingame == false) {
		if(e.getInventory().getName().equalsIgnoreCase("§eSkyWars §bKits")) {
		
			e.setCancelled(true);
		}
		
		if(e.getCurrentItem().getType() == Material.STONE_AXE) {
			plugin.kitmlg.remove(p);
			plugin.kittank.remove(p);
			plugin.kitminer.remove(p);
			plugin.kitarcher.remove(p);
			plugin.kitstandard.add(p);
			p.sendMessage("§eSkyWars§8: §7Du hast das Kit §6Standard §7gewählt.");
			p.closeInventory();
			p.getInventory().setArmorContents(null);
			
			ItemStack standarditem = new ItemStack(Material.STONE_AXE);
			ItemMeta im = standarditem.getItemMeta();
			im.setDisplayName("§7Kit: §eStandard");
			im.setLore(Arrays.asList("§7Du hast das Kit §eStandard §7gewählt"));
			standarditem.setItemMeta(im);
			p.getInventory().setItem(8, standarditem);
			
			
			
	}else 		if(e.getCurrentItem().getType() == Material.TNT) {
		plugin.kitstandard.remove(p);
		plugin.kittank.remove(p);
		plugin.kitminer.remove(p);
		plugin.kitarcher.remove(p);
		plugin.kitmlg.add(p);
		p.sendMessage("§eSkyWars§8: §7Du hast das Kit §6MLG §7gewählt.");
		p.closeInventory();
		p.getInventory().setArmorContents(null);
		
		ItemStack mlgitem = new ItemStack(Material.TNT);
		ItemMeta im = mlgitem.getItemMeta();
		im.setDisplayName("§7Kit: §eMLG");
		im.setLore(Arrays.asList("§7Du hast das Kit §eMLG §7gewählt"));
		mlgitem.setItemMeta(im);
		p.getInventory().setItem(8, mlgitem);
		
		
		}
	else 	if	(e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
			plugin.kitstandard.remove(p);
			plugin.kitmlg.remove(p);
			plugin.kitminer.remove(p);
			plugin.kitarcher.remove(p);
			plugin.kittank.add(p);
			p.sendMessage("§eSkyWars§8: §7Du hast das Kit §6Tank §7gewählt.");
			p.closeInventory();
			p.getInventory().setArmorContents(null);
			
			ItemStack tankitem = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemMeta im = tankitem.getItemMeta();
			im.setDisplayName("§7Kit: §eTank");
			im.setLore(Arrays.asList("§7Du hast das Kit §eTank §7gewählt"));
			tankitem.setItemMeta(im);
			p.getInventory().setItem(8, tankitem);
			
			}
	else if	(e.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
		plugin.kitstandard.remove(p);
		plugin.kitmlg.remove(p);
		plugin.kittank.remove(p);
		plugin.kitarcher.remove(p);
		plugin.kitminer.add(p);
		p.sendMessage("§eSkyWars§8: §7Du hast das Kit §6Miner §7gewählt.");
		p.closeInventory();
		p.getInventory().setArmorContents(null);
		
		ItemStack mineritem = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta im = mineritem.getItemMeta();
		im.setDisplayName("§7Kit: §eMiner");
		im.setLore(Arrays.asList("§7Du hast das Kit §eMiner §7gewählt"));
		mineritem.setItemMeta(im);
		p.getInventory().setItem(8, mineritem);
		
		}
	else if	(e.getCurrentItem().getType() == Material.BOW) {
		if (p.hasPermission("SkyWars.vipkits")) {
		plugin.kitstandard.remove(p);
		plugin.kitmlg.remove(p);
		plugin.kitminer.remove(p);
		plugin.kittank.remove(p);
		plugin.kitarcher.add(p);
		p.sendMessage("§eSkyWars§8: §7Du hast das Kit §6Archer §7gewählt.");
		p.closeInventory();
		p.getInventory().setArmorContents(null);
		
		ItemStack archeritem = new ItemStack(Material.BOW);
		ItemMeta im = archeritem.getItemMeta();
		im.setDisplayName("§7Kit: §eArcher");
		im.setLore(Arrays.asList("§7Du hast das Kit §eArcher §7gewählt"));
		archeritem.setItemMeta(im);
		p.getInventory().setItem(8, archeritem);
		}
		if (!p.hasPermission("SkyWars.vipkits")) {
			p.sendMessage("§eSkyWars§8: §cDu darfst dieses Kit nur mit dem §6Premium Rang §cbenutzen!");
		}
		}
		}
	}
	
}