package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import ga.uniquecoding.uniquedungeons.events.DungeonPlayerJoinEvent;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import org.bukkit.Bukkit;

public class DungeonJoin extends CommandAPICommand {

    private GameManager gameManager;

    public DungeonJoin(GameManager gameManager) {
        super("dungeonjoin");
        this.gameManager = gameManager;

        executesPlayer((player, args) -> {
            Bukkit.getPluginManager().callEvent(new DungeonPlayerJoinEvent(player));
        });
    }
}
