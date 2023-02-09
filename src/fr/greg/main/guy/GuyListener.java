package fr.greg.main.guy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.greg.main.Main;
import fr.greg.main.arena.Arena;

public class GuyListener implements Listener {
	
	private Main	_main;
	
	public GuyListener(Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if (current != null && current.getType() != null) {
			
			if (event.getView().getTitle().equals("§8Les Arènes")) {
				
				event.setCancelled(true);
				player.closeInventory();
				player.updateInventory();
				
				
				if (current.getItemMeta().getDisplayName().equals("§e(●) §cRetour")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "menu " + player.getName());
					return ;
				}
				else if (current.getItemMeta().getDisplayName().equals("§6Classement")) {
					_main.guy.open(player);
					return ;
				}
				
				Arena arene = _main.arenaManager.getArenaByName(current.getItemMeta().getDisplayName().substring(2));
				
				if (arene != null) {
					_main.arenaManager.clickArena(arene, player);
				}
				else {
					player.sendMessage("§6[⚔] §cCette arène n'existe pas");
				}
			}
		}
		
	}

}
