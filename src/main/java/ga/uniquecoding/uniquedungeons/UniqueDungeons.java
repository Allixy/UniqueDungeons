package ga.uniquecoding.uniquedungeons;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import ga.uniquecoding.uniquedungeons.commands.DungeonCommand;
import ga.uniquecoding.uniquedungeons.commands.SidebarCommand;
import ga.uniquecoding.uniquedungeons.listeners.BlockBreakListener;
import ga.uniquecoding.uniquedungeons.listeners.DungeonJoinListener;
import ga.uniquecoding.uniquedungeons.listeners.DungeonLeaveListener;
import ga.uniquecoding.uniquedungeons.listeners.PlayerLeaveListener;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.manager.SidebarManager;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import static ga.uniquecoding.uniquedungeons.utils.PlaceholderUtils.registerServerPlaceholder;

public final class UniqueDungeons extends JavaPlugin {

    private GameManager manager;
    public static UniqueDungeons plugin;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig());
    }

    @Override
    public void onEnable() {
        plugin = this;
        CommandAPI.onEnable(this);
        this.manager = new GameManager(this);
        SidebarManager sidebarManager = manager.getSidebarManager();

        sidebarManager.setupSidebar();
        registerListeners();
        registerPlaceholders();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void registerListeners() {
        Server server = getServer();
        PluginManager pluginManager = server.getPluginManager();

        pluginManager.registerEvents(new BlockBreakListener(manager), this);
        pluginManager.registerEvents(new DungeonJoinListener(manager), this);
        pluginManager.registerEvents(new DungeonLeaveListener(manager), this);
        pluginManager.registerEvents(new PlayerLeaveListener(manager), this);
    }

    public void registerPlaceholders() {
        registerServerPlaceholder("%player_dungeon_starting_time%", 50, () -> manager.getCountdownTask().getTimeLeft());
        registerServerPlaceholder("%player_dungeon_time_left_minutes%", 50, () -> manager.getTimeLeftTask().getMinutes());
        registerServerPlaceholder("%player_dungeon_time_left_seconds%", 50, () -> manager.getTimeLeftTask().getSeconds());
        registerServerPlaceholder("%player_dungeon_player_list%", 50, () -> manager.getPlayers());
    }

    public void registerCommands() {
        CommandAPI.registerCommand(DungeonCommand.class);
        CommandAPI.registerCommand(SidebarCommand.class);
    }

    public GameManager getManager() {
        return manager;
    }
}
