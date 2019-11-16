package net.civex4.nobility.development.behaviors;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.civex4.nobility.Nobility;
import net.civex4.nobility.development.Development;
import net.civex4.nobility.estate.Estate;
import net.civex4.nobility.gui.ButtonLibrary;
import vg.civcraft.mc.civmodcore.api.ItemAPI;
import vg.civcraft.mc.civmodcore.inventorygui.Clickable;
import vg.civcraft.mc.civmodcore.inventorygui.DecorationStack;

public class Collector implements DevelopmentBehavior, Upgradable {
	
	private Estate estate; 
	private Development development;
	private int level = 1;
	private double productivity = 1;
	private int collectionPower; //starting level
	private final int startingPower = 4;
	private final int powerPerLevel = 2;
	
	private static CollectorManager manager = Nobility.getCollectorManager();
	
	public Collector(Estate estate, Development development) {
		this.estate = estate;
		this.development = development;
		collectionPower = startingPower;
	}

	@Override
	public void build() {
		
	}

	@Override
	public void tick() {
		estate.getInventory().addItem(manager.getResourceAmount(estate, development));		
	}
	
	@Override
	public List<Clickable> getClickables() {
		List<Clickable> clickables = new ArrayList<>();
		
		// INFO
		ItemStack info = ButtonLibrary.createIcon(Material.PAPER, "Info");
		ItemAPI.addLore(info, "Collection Power: " + this.getCollectionPower() * this.getProductivity(),
				"Region Total: " + estate.getRegion().getResource(development.getType().getResource().toUpperCase()));
		//addLore(icon, "Percent: " + TODO: actualYield / regionTotal);
		//TODO: Actual Yield, Food Usage, if (foodUsage != maximum) "Click to increase food usage"
		
		Clickable infoItem = new DecorationStack(info);
		clickables.add(infoItem);

				
		return clickables; 
	}
	
	// Upgrade methods
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void upgrade() {
		level++;
		collectionPower += powerPerLevel;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		collectionPower = (level * powerPerLevel) + startingPower;
	}
	
	public double getProductivity() {
		return productivity;
	}

	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public int getCollectionPower() {
		return collectionPower;
	}

}