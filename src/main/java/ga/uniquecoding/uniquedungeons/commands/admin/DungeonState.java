package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquedungeons.manager.GameManager;

import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class DungeonState extends CommandAPICommand {

    private GameManager gameManager;

    public DungeonState(GameManager gameManager) {
        super("dungeonstate");
        this.gameManager = gameManager;

        withPermission("uniquedungeons.admin").executesPlayer((player, args) -> {
            player.sendMessage(c("&aThe game's current state:&l " + gameManager.getGameState().toString()));
        });
    }
}
