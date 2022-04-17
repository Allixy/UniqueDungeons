package ga.uniquecoding.uniquedungeons.listeners;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerJoinEvent;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;
import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class DungeonJoinListener implements Listener {

    private GameManager gameManager;

    public DungeonJoinListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onJoin(DungeonPlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!players.contains(player)) {
            players.add(player);
            player.sendMessage(c("&aSuccessfully joined a dungeon! Waiting for players " + gameManager.getPlayers() + "/4"));
            Bukkit.broadcastMessage(c("&b" + player.getName() + " joined the dungeon&e (" + gameManager.getPlayers() + "/4)"));
        } else {
            player.sendMessage(c("&cYou're already in a dungeon!"));
            return;
        }

        if (gameManager.getGameState() == GameState.END || gameManager.getGameState() == GameState.RESTARTING) {
            gameManager.setGameState(GameState.WAITING);
        }

        if (gameManager.getGameState() == GameState.WAITING) {
            gameManager.getSidebarManager().setSidebar(player, "WAITING");
        }

        if (gameManager.getPlayers() == 4) {
            for (Player target : players) {
                target.sendMessage(c("&aTeleporting into a dungeon.."));
                gameManager.getPlayerManager().dungeonTp(target);
            }
            gameManager.setGameState(GameState.STARTING);
        }
    }
}
