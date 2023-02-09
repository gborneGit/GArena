package fr.greg.main.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.greg.main.Main;
import fr.greg.main.arena.Arena;
import fr.greg.main.utils.Utils;

public class Config {
	
	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	
	private String				_world_name;
	
	public Config(Main main) {
		_main = main;
		_yaml = loadConfig("config.yml");
		readConfig();
	}
	
	private YamlConfiguration loadConfig(String file_name) {
		
		if(!_main.getDirectory().exists()) {
			_main.getDirectory().mkdir();
		}
		
		_file = new File(_main.getDataFolder(), file_name);
		
		if (!_file.exists()) {
			_main.saveResource(file_name, false);
		}
		
		return YamlConfiguration.loadConfiguration(_file);
	}
	
	public String	getWorldName() {
		return _world_name;
	}
	
	public void reload() {
		_main.arenaManager.getArenas().clear();
		_yaml = loadConfig("config.yml");
		readConfig();
	}
	
	private void readConfig() {
		
		ConfigurationSection section;
		
		_world_name = _yaml.getString("world");
		
		System.out.println(_world_name);
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("arenas." + i)) != null) {
			
			System.out.println("arene" + i);
			
			String		name = section.getString("name");
			Location	loc1 = Utils.parseStringToLoc(_world_name, section.getString("loc1"));
			Location	loc2 = Utils.parseStringToLoc(_world_name, section.getString("loc2"));
			int			player_nb = section.getInt("players");
			boolean		loss_stuff = section.getBoolean("loss_stuff");
			List<Location> spawns = new ArrayList<Location>();
			
			List<String> spawns_string = section.getStringList("spawns");
			for (int o = 0; o < spawns_string.size(); o++) {
				spawns.add(Utils.parseStringToLoc(_world_name, spawns_string.get(o)));
			}
			Arena arena = new Arena(_main, name, loc1, loc2, spawns, player_nb, loss_stuff);
			_main.arenaManager.addArena(arena);
			i++;
		}
	}
}
