package ga.uniquecoding.uniquedungeons.manager;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {

    private GameManager gameManager;

    public BlockManager(GameManager gameManager) {
        this.gameManager = gameManager;

        allowedToBreak.add(Material.CRACKED_STONE_BRICKS);
        allowedToBreak.add(Material.INFESTED_CRACKED_STONE_BRICKS);
    }

    private Set<Material> allowedToBreak = new HashSet<>();

    public boolean canBreak(Block block) {
        return allowedToBreak.contains(block.getType());
    }
}
