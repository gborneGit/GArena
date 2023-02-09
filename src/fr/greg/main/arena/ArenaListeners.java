package fr.greg.main.arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.greg.main.Main;
import fr.greg.main.utils.SafeInventory;
import fr.greg.main.utils.Utils;

public class ArenaListeners implements Listener {
	
	private Main _main;
	
	public ArenaListeners(Main main) { _main = main; }
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			
			Player victim = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			
			Arena arene = _main.arenaManager.getArenaByPlayer(victim);
			
			if (arene != null && !arene.getPlayers().contains(damager)) {
				event.setCancelled(true);
			}
			
		}
		
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent event) {

		Player victim = (Player) event.getEntity();
		
		Arena arena = _main.arenaManager.getArenaByPlayer(victim);
		
		if (arena != null) {
			if (arena.isStarted()) {
				if (!arena.isLoseStuff()) {
					event.getDrops().clear();
					SafeInventory.storeAndClearInventory(victim);
				}
				arena.eliminate(victim);
			}
			else {
				arena.getPlayers().remove(victim);
				victim.sendMessage("§6[⚔] §fVous êtes §cdésinscrit §eà §6" + arena.getName());
			}
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if (SafeInventory.isSafe(event.getPlayer())) {
			SafeInventory.restoreInventory(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player leaver = event.getPlayer();
		
		Arena arena = _main.arenaManager.getArenaByPlayer(leaver);
		
		if (arena != null) {
			if (arena.isStarted()) {
				arena.eliminate(leaver);
				leaver.teleport(Utils.parseStringToLoc("world_safe", "2.5,107,56.5,180,0"));
			}
			else {
				arena.getPlayers().remove(leaver);
			}
		}
	}
	
}
