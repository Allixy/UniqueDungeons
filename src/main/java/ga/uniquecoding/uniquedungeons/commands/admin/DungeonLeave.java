package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerLeaveEvent;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.Bukkit;

public class DungeonLeave extends CommandAPICommand {

    private GameManager gameManager;

    public DungeonLeave(GameManager gameManager) {
        super("dungeonleave");
        this.gameManager = gameManager;

        executesPlayer((player, args) -> {
            Bukkit.getPluginManager().callEvent(new DungeonPlayerLeaveEvent(player));
        });
    }
}
