package ga.uniquecoding.uniquedungeons.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerManager {

    private GameManager gameManager;

    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void dungeonTp(Player player) {
        player.teleport(new Location(Bukkit.getWorld("world"), -100, 119, -95));
    }
}
