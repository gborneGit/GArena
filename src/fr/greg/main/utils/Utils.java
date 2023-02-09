package fr.greg.main.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

	public static ItemStack getItem(Material material, String custom_name, List<String> lore) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(custom_name);
		if (lore != null && !lore.isEmpty())
			itemM.setLore(lore);
		item.setItemMeta(itemM);;
		return item;
	}
	
	public static Location parseStringToLoc(String world_name, String string) {
		
		String[] parseLoc = string.split(",");
		Location loc = null;
		
		if (parseLoc.length == 3) {
			double x = Double.valueOf(parseLoc[0]);
			double y = Double.valueOf(parseLoc[1]);
			double z = Double.valueOf(parseLoc[2]);
			loc = new Location(Bukkit.getWorld(world_name), x, y, z);
		}
		else if (parseLoc.length == 5) {
			double x = Double.valueOf(parseLoc[0]);
			double y = Double.valueOf(parseLoc[1]);
			double z = Double.valueOf(parseLoc[2]);
			float face_x = Float.valueOf(parseLoc[3]);
			float face_y = Float.valueOf(parseLoc[4]);
			loc = new Location(Bukkit.getWorld(world_name), x, y, z, face_x, face_y);
		}
		return loc;
	}
	
	public static String parseLocToString(Location loc) {
		String string = loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();  
		return string;
	}
}
