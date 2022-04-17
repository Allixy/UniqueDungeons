package ga.uniquecoding.uniquedungeons.listeners;

import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;
import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class PlayerLeaveListener implements Listener {

    private GameManager gameManager;

    public PlayerLeaveListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (players.contains(player)) {
            players.remove(player);
            Bukkit.broadcastMessage(c("&b" + player.getName() + " left the dungeon&e (" + gameManager.getPlayers() + "/4)"));
        }
    }
}
