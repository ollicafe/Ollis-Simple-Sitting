package com.ollicafe.ollissimplesitting;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Seats {
	
	//stairs
//	private Material[] allowedStairs = {
//			Material.ACACIA_STAIRS, 
//			Material.ANDESITE_STAIRS,
//			Material.BAMBOO_MOSAIC_STAIRS,
//			Material.BAMBOO_STAIRS,
//			Material.BIRCH_STAIRS,
//			Material.BLACKSTONE_STAIRS,
//			Material.BRICK_STAIRS,
//			Material.CHERRY_STAIRS,
//			Material.COBBLED_DEEPSLATE_STAIRS,
//			Material.COBBLESTONE_STAIRS,
//			Material.CRIMSON_STAIRS,
//			Material.CUT_COPPER_STAIRS,
//			Material.DARK_OAK_STAIRS,
//			Material.DARK_PRISMARINE_STAIRS,
//			Material.DEEPSLATE_BRICK_STAIRS,
//			Material.DEEPSLATE_TILE_STAIRS,
//			Material.DIORITE_STAIRS,
//			Material.END_STONE_BRICK_STAIRS,
//			Material.EXPOSED_CUT_COPPER_STAIRS,
//			Material.GRANITE_STAIRS,
//			Material.JUNGLE_STAIRS,
//			Material.MANGROVE_STAIRS,
//			Material.MOSSY_COBBLESTONE_STAIRS,
//			Material.MOSSY_STONE_BRICK_STAIRS,
//			Material.MUD_BRICK_STAIRS,
//			Material.NETHER_BRICK_STAIRS,
//			Material.OAK_STAIRS,
//			Material.OXIDIZED_CUT_COPPER_STAIRS,
//			Material.POLISHED_ANDESITE_STAIRS,
//			Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
//			Material.POLISHED_BLACKSTONE_STAIRS,
//			Material.POLISHED_DEEPSLATE_STAIRS,
//			Material.POLISHED_DIORITE_STAIRS,
//			Material.POLISHED_GRANITE_STAIRS,
//			Material.PRISMARINE_BRICK_STAIRS,
//			Material.PRISMARINE_STAIRS,
//			Material.PURPUR_STAIRS,
//			Material.QUARTZ_STAIRS,
//			Material.RED_NETHER_BRICK_STAIRS,
//			Material.RED_SANDSTONE_STAIRS,
//			Material.SANDSTONE_STAIRS,
//			Material.SMOOTH_QUARTZ_STAIRS,
//			Material.SMOOTH_RED_SANDSTONE_STAIRS,
//			Material.SMOOTH_SANDSTONE_STAIRS,
//			Material.SPRUCE_STAIRS,
//			Material.STONE_BRICK_STAIRS,
//			Material.STONE_STAIRS,
//			Material.WARPED_STAIRS,
//			Material.WAXED_CUT_COPPER_STAIRS,
//			Material.WAXED_EXPOSED_CUT_COPPER_STAIRS,
//			Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
//			Material.WAXED_WEATHERED_CUT_COPPER_STAIRS,
//			Material.WEATHERED_CUT_COPPER_STAIRS};
	
	//array of seats
	private ArrayList<Entity> seats = new ArrayList<Entity>();//seat entity, player entity
	
	public void createSeat(Player player, Block block) {
		//loc depends on block
		Location loc = block.getLocation().add(0.5, 0.3, 0.5);
		if(block.getType().toString().toLowerCase().contains("stairs")) {
			Stairs stair = (Stairs) block.getBlockData();
			double offset = 0.3;
			switch(stair.getFacing()) {
			case WEST:
				loc.add(offset, 0, 0);
				break;
			case NORTH:
				loc.add(0, 0, offset);
				break;
			case EAST:
				loc.add(-offset, 0, 0);
				break;
			case SOUTH:
				loc.add(0, 0, -offset);
				break;
			default:
				break;
			}
		}
		Entity seatEntity = loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		seatEntity.setCustomName("seat");
		ArmorStand seat = (ArmorStand) seatEntity;
		seat.setInvisible(true);
		seat.setGravity(false);
		seat.setMarker(true);
		seat.setInvulnerable(true);
		seat.setSmall(true);
		seat.setBasePlate(false);
		seatEntity.addPassenger(player);
		seats.add(seatEntity);
		
	}
	
	public void createSeat(Player player) {
		//this is to create a seat where the player is, not where the block is

		Location loc = player.getLocation().add(0, -0.2, 0);
		Entity seatEntity = loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		seatEntity.setCustomName("seat");
		ArmorStand seat = (ArmorStand) seatEntity;
		seat.setInvisible(true);
		seat.setGravity(false);
		seat.setMarker(true);
		seat.setInvulnerable(true);
		seat.setSmall(true);
		seat.setBasePlate(false);
		seatEntity.addPassenger(player);
		seats.add(seatEntity);
		
		
		
	}
	
	public void removeSeat(Entity seat) {
		seat.remove();
	}
	
	public void cleanup() {
		for(Entity seat: seats) {
			removeSeat(seat);
		}
		
	}

}
