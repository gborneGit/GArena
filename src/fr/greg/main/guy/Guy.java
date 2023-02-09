package fr.greg.main.guy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.greg.main.Main;
import fr.greg.main.arena.Arena;
import fr.greg.main.utils.Utils;

public class Guy {

	private Main _main;
	
	public Guy(Main main) {
		_main = main;
	}
	
public void open(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, "§8Les Arènes");
	
		if (_main.arenaManager.getArenas().size() > 5)
			player.sendMessage("§6[⚔] §cErreur: Trop d'arènes à afficher)");
		else {
			// SCORES
			List<String> score_lore = new ArrayList<String>();
			for (int i = 0; i < _main.score.getScore().size(); i++) {
				String name = _main.score.getScore().get(i).getName();
				int score = _main.score.getScore().get(i).getScore();
				score_lore.add("§7▪ §f" + name + ": §e" + score + " points");
			}
			inv.setItem(0, Utils.getItem(Material.PLAYER_HEAD, "§6Classement", score_lore));
			// ARENES
			int i = 0;
			
			while (i < _main.arenaManager.getArenas().size()) {
				
				Arena arene = _main.arenaManager.getArenas().get(i);
				List<String> lore = new ArrayList<String>();
				if (!arene.isStarted()) {
					
					lore.add("§7▪ §fJoueurs: §e" + arene.getPlayers().size() + "/" + arene.getPlayerNb());
					
					if (arene.isLoseStuff())
						lore.add("§7▪ §fPerte de stuff: §cOui");
					else
						lore.add("§7▪ §fPerte de stuff: §eNon");
					
					if (arene.getPlayers().contains(player))
						lore.add("§e(●) §cDésinscription");
					else
						lore.add("§e(●) §aInscription");
				}
				else {
					lore.add("§6[⚔] §cCombat en cours!");
				}
				
				inv.setItem(i + 2, Utils.getItem(Material.SHIELD, "§6" + arene.getName(), lore));
				i++;
			}
			
			// RETOUR
			inv.setItem(8, Utils.getItem(Material.BARRIER, "§e(●) §cRetour", null));
			player.openInventory(inv);
		}
	}
}
