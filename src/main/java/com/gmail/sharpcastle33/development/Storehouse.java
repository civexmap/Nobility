package com.gmail.sharpcastle33.development;

import org.bukkit.Location;
import org.bukkit.Material;

import com.gmail.sharpcastle33.estate.Estate;

public class Storehouse implements Developable {

	Estate estate;
	Development development;
	
	public Storehouse(Estate estate, Development development) {
		this.estate = estate;
		this.development = development;
	}

	@Override
	public void build() {
		Location loc = estate.getBlock().getLocation().add(1, 0, 0);
		loc.getBlock().setType(Material.CHEST);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	

}
