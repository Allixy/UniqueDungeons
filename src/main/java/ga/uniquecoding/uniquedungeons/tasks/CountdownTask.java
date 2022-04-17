package ga.uniquecoding.uniquedungeons.tasks;

import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;

public class CountdownTask extends BukkitRunnable {

    private GameManager gameManager;

    public CountdownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timeLeft = 30;

    @Override
    public void run() {

        timeLeft--;

        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }

        for (Player player : players) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(HexUtils.colorify("&7The dungeon gate opening in:&c " + timeLeft + "s")));
        }

    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
