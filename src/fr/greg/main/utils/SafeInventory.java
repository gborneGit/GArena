package fr.greg.main.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SafeInventory {

	static List<Player>				players = new ArrayList<Player>();
	static Map<UUID, ItemStack[]>	items = new HashMap<UUID, ItemStack[]>();
	static Map<UUID, ItemStack[]>	armor = new HashMap<UUID, ItemStack[]>();
	
	public static void storeAndClearInventory(Player player){
		
		UUID uuid = player.getUniqueId();

		ItemStack[] cont = player.getInventory().getContents();
	   	ItemStack[] armcont = player.getInventory().getArmorContents();
	
	   	items.put(uuid, cont);
	   	armor.put(uuid, armcont);
	   	players.add(player);

   		player.getInventory().clear();

   		remArmor(player);
	}

   public static void restoreInventory(Player player){
       UUID uuid = player.getUniqueId();

       ItemStack[] contents = items.get(uuid);
       ItemStack[] armorContents = armor.get(uuid);

       if(contents != null){
           player.getInventory().setContents(contents);
           items.remove(uuid);
       }
	   if(armorContents != null){
	       player.getInventory().setArmorContents(armorContents);
	       armor.remove(uuid);
	   }
	   players.remove(player);
   }

    public static void remArmor(Player player){
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
   }
    
    public static boolean isSafe(Player player) {
    	if (players.contains(player))
    		return true;
    	return false;
    }
}
