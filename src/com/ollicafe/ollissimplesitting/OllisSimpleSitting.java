package com.ollicafe.ollissimplesitting;

import org.bukkit.plugin.java.JavaPlugin;

public class OllisSimpleSitting extends JavaPlugin {
	
	public Seats seats;
	
	@Override
	public void onEnable() {
		//create seats
		seats  = new Seats();
		//implement commands
		this.getCommand("sit").setExecutor(new SitCommand(this, seats));
		//implement listeners
		this.getServer().getPluginManager().registerEvents(new SitListener(this, seats), this);
		//done :)
	}
	
	@Override
	public void onDisable() {
		seats.cleanup();
	}

}
