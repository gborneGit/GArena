package fr.greg.main.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ArenaManager {

	private List<Arena> _arenas = new ArrayList<Arena>();
	
	public void addArena(Arena arena) {
		_arenas.add(arena);
	}
	
	public void join(Arena arene, Player player) {
		if (arene.isStarted())
			player.sendMessage("§6[⚔] §cImpossible de rejoindre une arène en cours!");
		else {
			arene.getPlayers().add(player);
			if (arene.getPlayers().size() >= arene.getPlayerNb()) {
				arene.go();
			}
		}
	}
	
	public void clickArena(Arena arene, Player player) {
		
		Arena sub = getArenaByPlayer(player);
		
		if (sub != null && !sub.isStarted()) {
			if (!sub.getName().equals(arene.getName())) {
				sub.getPlayers().remove(player);
				player.sendMessage("§6[⚔] §fVous êtes §cdésinscrit §fà  §6" + sub.getName());
				join(arene, player);
				player.sendMessage("§6[⚔] §fVous êtes §ainscrit §fà §6" + arene.getName());
				return ;
			}
		}
		if (!arene.isStarted()) {

			if (arene.getPlayers().contains(player)) {
				arene.getPlayers().remove(player);
				player.sendMessage("§6[⚔] §fVous êtes §cdésinscrit §fà  §6" + arene.getName());
			}
			else {
				join(arene, player);
				player.sendMessage("§6[⚔] §fVous êtes §ainscrit §fà §6" + arene.getName());
			}	
		}
		else {
			player.sendMessage("§6[⚔] §cCombat en cours");
		}
	}
	
	public Arena getArenaByPlayer(Player player) {
		
		for(Arena arena : _arenas) {
			if (arena.getPlayers().contains(player)) {
				return arena;
			}
		}
		return null;
	}
	
	public Arena getArenaByName(String name) {
		
		for(Arena arena : _arenas) {
			if (arena.getName().equals(name)) {
				return arena;
			}
		}
		return null;
	}
	
	public List<Arena> getArenas() {
		return _arenas;
	}
}
