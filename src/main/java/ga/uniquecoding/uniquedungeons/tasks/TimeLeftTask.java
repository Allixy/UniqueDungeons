package ga.uniquecoding.uniquedungeons.tasks;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeLeftTask extends BukkitRunnable {

    private GameManager gameManager;

    public TimeLeftTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int seconds = 2700;
    private int minutes;

    @Override
    public void run() {

        seconds--;

        if (minutes <= 0) {
            if (seconds <= 0) {
                cancel();
                gameManager.setGameState(GameState.END);
                return;
            }
        }

        this.minutes = seconds / 60;
    }

    public int getSeconds() {
        return seconds % 60;
    }

    public int getMinutes() {
        return seconds / 60;
    }
}
