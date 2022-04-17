package ga.uniquecoding.uniquedungeons.tasks;

import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

public class GateOpeningTask extends BukkitRunnable {
    private GameManager gameManager;

    public GateOpeningTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void run() {

    }
}
