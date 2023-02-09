package fr.greg.main;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import fr.greg.main.arena.ArenaListeners;
import fr.greg.main.arena.ArenaManager;
import fr.greg.main.commands.ArenaCommands;
import fr.greg.main.config.Config;
import fr.greg.main.config.Score;
import fr.greg.main.guy.Guy;
import fr.greg.main.guy.GuyListener;

public class Main extends JavaPlugin {
	
	public ArenaManager			arenaManager;
	public Config				config;
	public Score				score;
	public Guy					guy;
	
	@Override
	public void onEnable() {
		
		this.arenaManager = new ArenaManager();
		this.score = new Score(this);
		this.config = new Config(this);
		this.guy = new Guy(this);
		
		getCommand("duel").setExecutor(new ArenaCommands(this));
		getServer().getPluginManager().registerEvents(new ArenaListeners(this), this);
		getServer().getPluginManager().registerEvents(new GuyListener(this), this);
		
	}
	
	public File getDirectory() {
		return getDataFolder();
	}
}
