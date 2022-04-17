package ga.uniquecoding.uniquedungeons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DungeonPlayerLeaveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    Player player;

    public DungeonPlayerLeaveEvent(Player player) {
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return  cancelled;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
