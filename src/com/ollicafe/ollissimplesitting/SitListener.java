package com.ollicafe.ollissimplesitting;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitListener implements Listener{
	
	private OllisSimpleSitting plugin;
	private Seats seats;
	
	public SitListener(OllisSimpleSitting plugin, Seats seats) {
		this.plugin = plugin;
		this.seats = seats;
	}
	
	@EventHandler
	public void onDismount(EntityDismountEvent e) {
		if(!e.getDismounted().getType().equals(EntityType.ARMOR_STAND)) return;//check if armor stand
		if(!e.getDismounted().getCustomName().equals("seat")) return;
		e.getDismounted().eject();
		e.getEntity().teleport(e.getEntity().getLocation().add(0,1.0,0));
		seats.removeSeat(e.getDismounted());
		//System.out.println("Dismounted: " + e.getDismounted().toString());
		//System.out.println("Entity: " + e.getEntity().toString());
		
	}
	
	@EventHandler
	public void onSitClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if(!(e.getItem() == null)) return;
		Block block = e.getClickedBlock();
		if(block == null) return;
		//player.sendMessage("block: " + block.getType().toString());
		if(!(block.getType().toString().toLowerCase().contains("stairs")
				|| block.getType().toString().toLowerCase().contains("slab"))) return;
		//^ looking back on this code, this was smart lol
		seats.createSeat(player, block);
		player.sendMessage("Sitting");
		
		
	}

}
