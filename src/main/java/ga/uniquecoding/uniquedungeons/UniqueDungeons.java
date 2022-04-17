package ga.uniquecoding.uniquedungeons;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import ga.uniquecoding.uniquedungeons.commands.admin.*;
import ga.uniquecoding.uniquedungeons.listeners.BlockBreakListener;
import ga.uniquecoding.uniquedungeons.listeners.DungeonJoinListener;
import ga.uniquecoding.uniquedungeons.listeners.DungeonLeaveListener;
import ga.uniquecoding.uniquedungeons.listeners.PlayerLeaveListener;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.sidebars.DungeonActiveSidebar;
import ga.uniquecoding.uniquedungeons.sidebars.DungeonStartingSidebar;
import ga.uniquecoding.uniquedungeons.sidebars.DungeonWaitingSidebar;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static ga.uniquecoding.uniquedungeons.utils.PlaceholderUtils.registerServerPlaceholder;

public final class UniqueDungeons extends JavaPlugin {

    Server server = Bukkit.getServer();
    private GameManager gameManager;
    private Scoreboard waitingSidebar;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig());
    }

    @Override
    public void onEnable() {
        //Command API, Game Manager creation
        CommandAPI.onEnable(this);
        this.gameManager = new GameManager(this);

        //Events
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new DungeonJoinListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new DungeonLeaveListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(gameManager), this);

        //Placeholders
        registerServerPlaceholder("%player_dungeon_starting_time%", 50, () -> gameManager.getCountdownTask().getTimeLeft());
        registerServerPlaceholder("%player_dungeon_time_left_minutes%", 50, () -> gameManager.getTimeLeftTask().getMinutes());
        registerServerPlaceholder("%player_dungeon_time_left_seconds%", 50, () -> gameManager.getTimeLeftTask().getSeconds());
        registerServerPlaceholder("%player_dungeon_player_list%", 50, () -> gameManager.getPlayers());

        //Scoreboard
        gameManager.getSidebarManager().setupSidebar();

        //Commands
        new DungeonStart(gameManager).register();
        new DungeonStop(gameManager).register();
        new DungeonJoin(gameManager).register();
        new DungeonLeave(gameManager).register();
        new DungeonState(gameManager).register();
        new SidebarDebug(gameManager).register();

        //Random message
        server.getLogger().log(Level.INFO, "The dungeons plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        //Random message 2.0
        server.getLogger().log(Level.INFO, "The dungeons plugin has been disabled!");
    }

    public Scoreboard getWaitingSidebar() {
        return waitingSidebar;
    }
}
