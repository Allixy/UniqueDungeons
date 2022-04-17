package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquedungeons.enums.GameState;
import ga.uniquecoding.uniquedungeons.manager.GameManager;

import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class DungeonStart extends CommandAPICommand {

    private GameManager gameManager;

    public DungeonStart(GameManager gameManager) {
        super("dungeonstart");
        this.gameManager = gameManager;

        withPermission("uniquedungeons.admin").executesPlayer((player, args) -> {
            gameManager.setGameState(GameState.STARTING);
            player.sendMessage(c("&aThe dungeon has been successfully started!"));
        });
    }
}