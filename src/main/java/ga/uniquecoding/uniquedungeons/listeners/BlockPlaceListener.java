package ga.uniquecoding.uniquedungeons.listeners;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private GameManager gameManager;

    public BlockPlaceListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        if (!(gameManager.getGameState() == GameState.WAITING || gameManager.getGameState() == GameState.STARTING || gameManager.getGameState() == GameState.ACTIVE)) return;
        e.setCancelled(true);
    }
}
