package ga.uniquecoding.uniquedungeons.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import ga.uniquecoding.uniquedungeons.UniqueDungeons;
import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerJoinEvent;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerLeaveEvent;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;

@Command("dungeon")
public class DungeonCommand {

    static GameManager manager = UniqueDungeons.plugin.getManager();

    @Default
    public static void dungeon(Player player) {
    }

    @Subcommand("join")
    public static void dungeonJoin(Player player) {
        Bukkit.getPluginManager().callEvent(new DungeonPlayerJoinEvent(player));
    }

    @Subcommand("leave")
    public static void dungeonLeave(Player player) {
        Bukkit.getPluginManager().callEvent(new DungeonPlayerLeaveEvent(player));
    }

    @Subcommand("start")
    @Permission("uniquedungeons.admin")
    public static void dungeonStart(Player player) {
        manager.setGameState(GameState.STARTING);
        player.sendMessage(HexUtils.colorify("&aThe dungeon has been successfully started!"));
    }

    @Subcommand("stop")
    @Permission("uniquedungeons.admin")
    public static void dungeonStop(Player player) {
        GameState gameState = manager.getGameState();

        if (!(gameState == GameState.END || gameState == GameState.RESTARTING)) {
            manager.setGameState(GameState.END);
            player.sendMessage(HexUtils.colorify("&aThe dungeon has been successfully stopped!"));

            if (manager.getPlayers() > 0) {
                players.clear();
            }

        } else {
            player.sendMessage(HexUtils.colorify("&cThe dungeon isn't running!"));
        }
    }

    @Subcommand("state")
    @Permission("uniquedungeons.admin")
    public static void dungeonState(Player player) {
        GameState gameState = manager.getGameState();

        player.sendMessage(HexUtils.colorify("&aThe game's current state:&l " + gameState.toString()));
    }
}
