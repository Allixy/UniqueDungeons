package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.GameManager;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;
import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class DungeonStop extends CommandAPICommand {

    private GameManager gameManager;

    public DungeonStop(GameManager gameManager) {
        super("dungeonstop");
        this.gameManager = gameManager;

        withPermission("uniquedungeons.admin").executesPlayer((player, args) -> {
            if (!(gameManager.getGameState() == GameState.END || gameManager.getGameState() == GameState.RESTARTING)) {
                gameManager.setGameState(GameState.END);
                player.sendMessage(c("&aThe dungeon has been successfully stopped!"));

                if (gameManager.getPlayers() > 0) {
                    players.clear();
                }
            } else {
                player.sendMessage(c("&cThe dungeon isn't running!"));
            }
        });
    }
}
