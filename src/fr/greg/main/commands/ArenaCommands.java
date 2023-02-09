package fr.greg.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.greg.main.Main;
import fr.greg.main.arena.Arena;

public class ArenaCommands implements CommandExecutor {
	
	private Main	_main;
	
	public ArenaCommands(Main main) {
		_main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (label.equalsIgnoreCase("duel") && sender instanceof Player) {
			
			Player player = (Player)sender;
			
			if (args.length == 0) {
				
				Arena arena = _main.arenaManager.getArenaByPlayer(player);
				if (arena != null && arena.isStarted())
					player.sendMessage("§6[⚔] §cVotre combat va commencer");
				else
					_main.guy.open(player);
				return true;
			}
			else if (args.length == 1 && args[0].equals("reload") && player.hasPermission("garena.use")) {
				_main.config.reload();
				_main.score.reload();
				player.sendMessage("§a[GArena] Reload success");
				System.out.println("§a[GArena] Reload success");
			}
		}
		
		return false;
	}
}
