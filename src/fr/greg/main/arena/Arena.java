package fr.greg.main.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.greg.main.Main;
import fr.greg.main.utils.Utils;

public class Arena {
	
	private Main			_main;

	private String			_name;
	private Location		_loc1;
	private Location		_loc2;
	private List<Location>	_spawns;
	private List<Player>	_players;
	private int				_players_nb;
	private boolean			_is_started;
	private	boolean			_lose_stuff;
	
	public Arena(Main main, String name, Location loc1, Location loc2, List<Location> spawns, int players_nb, boolean lose_stuff) {
		_main = main;
		_name = name;
		_loc1 = loc1;
		_loc2 = loc2;
		_spawns = spawns;
		_players_nb = players_nb;
		_lose_stuff = lose_stuff;
		restart();
	}
	
	public void go() {
		setStarted();
		for (int i = 0; i < _players.size(); i++) {
			Player player = _players.get(i);
			player.sendMessage("§6[⚔] §aAdversaire trouvé !");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "menu " + player.getName() + " tp " + _spawns.get(i).getWorld().getName() + " " + Utils.parseLocToString(_spawns.get(i)) + " 30 &6" + _name + " null false");
		}
	}
	
	public String getName() {
		return _name;
	}
	
	public Location getLoc1() {
		return _loc1;
	}
	
	public Location getLoc2() {
		return _loc2;
	}
	
	public List<Player> getPlayers() {
		return _players;
	}
	
	public boolean isStarted() {
		return _is_started;
	}
	
	public boolean isLoseStuff() {
		return _lose_stuff;
	}
	
	public int getPlayerNb() {
		return _players_nb;
	}
	
	public List<Location> getSpawns() {
		return _spawns;
	}
	
	public void setStarted() {
		_is_started = true;
	}

	public void eliminate(Player victim) {
		
		_players.remove(victim);
		checkWin();
	}

	private void checkWin() {
		if (_players.size() == 1) {

			Player winner = _players.get(0);
			if (_lose_stuff)
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "menu " + winner.getName() + " spawn 30 false");
			else
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "menu " + winner.getName() + " spawn");
			_main.score.addPoint(winner.getName());
			Bukkit.broadcastMessage("§6[⚔] §e" + winner.getName() + "§f gagne le duel ! §6+1 point");
			restart();
		}
	}

	private void restart() {
		_players = new ArrayList<>();
		_is_started = false;
		
	}
	
}
