package com.ollicafe.ollissimplesitting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SitCommand implements CommandExecutor{
	
	private final OllisSimpleSitting plugin;
	private Seats seats;
	
	public SitCommand(OllisSimpleSitting plugin, Seats seats) {
		this.plugin = plugin;
		this.seats = seats;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("sit")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				LivingEntity check = (LivingEntity) player;
				if(!check.isOnGround()) {
					player.sendMessage("You can't sit in the air!");
					return true;
				}
				seats.createSeat(player);
				player.sendMessage("Sitting");
				return true;
			} else {
				sender.sendMessage("Consoles can't sit silly");
				return true;
			}
		}
		return false;
	}

}
