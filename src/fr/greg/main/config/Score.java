package fr.greg.main.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.greg.main.Main;

public class Score {
	
	public class PlayerScore {
		
		private String	_name;
		private int		_score;
		
		public PlayerScore(String name, int score) {
			_name = name;
			_score = score;
		}
		
		public String getName() {
			return _name;
		}
		
		public int getScore() {
			return _score;
		}
		
		public void upScore() {
			_score++;
		}
	
	}

	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	private List<PlayerScore>	_score = new ArrayList<PlayerScore>();
	
	public Score(Main main) {
		_main = main;
		_yaml = loadConfig("score.yml");
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
	
	private void readConfig() {
		
		ConfigurationSection section;
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("" + i)) != null) {
			_score.add(new PlayerScore(section.getString("name"), section.getInt("score")));
			i++;
		}
	}
	
	private void save() {
		try {
			_yaml.save(_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<PlayerScore> getScore() {
		return _score;
	}
	
	private void setScore(String player_name, int point) {
		
		ConfigurationSection section;
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("" + i)) != null) {
			if (section.getString("name").equals(player_name)) {
				section.set("score", point);
				return ;
			}
			i++;
		}
		_yaml.set(i + ".name", player_name);
		_yaml.set(i + ".score", point);
		save();
	}
	
	public void reload() {
		_score.clear();
		_yaml = loadConfig("score.yml");
		readConfig();
	}
	
	public void addPoint(String player_name) {
		
		for (int i = 0; i < _score.size(); i++) {
			if (_score.get(i).getName().equals(player_name)) {
				_score.get(i).upScore();
				setScore(player_name, _score.get(i).getScore());
				return ;
			}
		}
		_score.add(new PlayerScore(player_name, 1));
		setScore(player_name, 1);
	}
}
