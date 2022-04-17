package ga.uniquecoding.uniquedungeons.listeners;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerLeaveEvent;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;

public class DungeonLeaveListener implements Listener {

    private GameManager gameManager;

    public DungeonLeaveListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onLeave(DungeonPlayerLeaveEvent e) {
        Player player = e.getPlayer();

        if (players.contains(player)) {
            players.remove(player);
            gameManager.getSidebarManager().resetSidebar(player);
            player.sendMessage(HexUtils.colorify("&aYou left the dungeon!"));

            for (Player target : players) {
                target.sendMessage(HexUtils.colorify("&b" + player.getName() + " left the dungeon&e (" + gameManager.getPlayers() + "/4)"));
            }

            if (gameManager.getPlayers() < 5) {
                if (gameManager.getCountdownTask() != null) gameManager.getCountdownTask().cancel();
                gameManager.setGameState(GameState.WAITING);
            }

        } else {
            player.sendMessage(HexUtils.colorify("&cYou're not in a dungeon!"));
        }
    }
}
