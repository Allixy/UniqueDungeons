package ga.uniquecoding.uniquedungeons.manager;

import ga.uniquecoding.uniquedungeons.UniqueDungeons;
import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.tasks.CountdownTask;
import ga.uniquecoding.uniquedungeons.tasks.TimeLeftTask;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private final UniqueDungeons plugin;
    private GameState gameState = GameState.WAITING;
    private final BlockManager blockManager;
    private final PlayerManager playerManager;
    private final SidebarManager sidebarManager;
    private CountdownTask countdownTask;
    private TimeLeftTask timeLeftTask;
    public static Set<Player> players = new HashSet<>();
    private final World world;

    public GameManager(UniqueDungeons plugin) {
        this.plugin = plugin;
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);
        this.sidebarManager = new SidebarManager(this);
        this.world = plugin.getServer().getWorld("world");
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;

        switch(gameState) {
            case WAITING -> {
                for (Player player : players) {
                    sidebarManager.setSidebar(player, "WAITING");
                    player.sendMessage(HexUtils.colorify("&cWaiting for more players.."));
                }
            }

            case STARTING -> {
                this.countdownTask = new CountdownTask(this);
                this.countdownTask.runTaskTimer(plugin, 0, 20);

                for (Player player : players) {
                    sidebarManager.setSidebar(player, "STARTING");
                }
            }

            case ACTIVE -> {
                this.timeLeftTask = new TimeLeftTask(this);
                this.timeLeftTask.runTaskTimer(plugin, 0, 20);

                for (Player player : players) {
                    sidebarManager.setSidebar(player, "ACTIVE");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(HexUtils.colorify("&aThe gate has been opened!")));
                }
            }

            case END -> {
                if (this.countdownTask != null) this.countdownTask.cancel();
                if (this.timeLeftTask != null) this.timeLeftTask.cancel();

                for (Player player : players) {
                    sidebarManager.resetSidebar(player);
                    player.sendMessage(HexUtils.colorify("&c&lGame over!"));
                }
            }

            case RESTARTING -> {
                players.clear();
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public SidebarManager getSidebarManager() {
        return sidebarManager;
    }

    public CountdownTask getCountdownTask() {
        return countdownTask;
    }

    public TimeLeftTask getTimeLeftTask() {
        return timeLeftTask;
    }

    public UniqueDungeons getPlugin() {
        return plugin;
    }

    public int getPlayers() {
        return players.size();
    }
}
