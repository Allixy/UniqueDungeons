package ga.uniquecoding.uniquedungeons.listeners;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.BlockManager;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private GameManager gameManager;

    public BlockBreakListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player player = e.getPlayer();
        BlockManager blockManager = gameManager.getBlockManager();

        if (!(gameManager.getGameState() == GameState.WAITING || gameManager.getGameState() == GameState.STARTING || gameManager.getGameState() == GameState.ACTIVE)) return;

        if (!blockManager.canBreak(block)) {
            e.setCancelled(true);
        }
    }
}
