package ga.uniquecoding.uniquedungeons.manager;

import ga.uniquecoding.uniquedungeons.sidebars.DungeonActiveSidebar;
import ga.uniquecoding.uniquedungeons.sidebars.DungeonStartingSidebar;
import ga.uniquecoding.uniquedungeons.sidebars.DungeonWaitingSidebar;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;
import org.bukkit.entity.Player;

public class SidebarManager {

    private GameManager manager;
    private Scoreboard dungeonWaitingSidebar;
    private Scoreboard dungeonStartingSidebar;
    private Scoreboard dungeonActiveSidebar;

    public SidebarManager(GameManager manager) {
        this.manager = manager;
    }

    public void setupSidebar() {
        DungeonWaitingSidebar dwSidebar = new DungeonWaitingSidebar(manager);
        DungeonStartingSidebar dsSidebar = new DungeonStartingSidebar(manager);
        DungeonActiveSidebar daSidebar = new DungeonActiveSidebar(manager);

        this.dungeonWaitingSidebar = dwSidebar.getSidebar();
        this.dungeonStartingSidebar = dsSidebar.getSidebar();
        this.dungeonActiveSidebar = daSidebar.getSidebar();
    }

    public void setSidebar(Player player, String sidebar) {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager scoreboardManager = instance.getScoreboardManager();
        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());

        switch (sidebar) {
            case "WAITING" -> {
                scoreboardManager.showScoreboard(tabPlayer, dungeonWaitingSidebar);
            }

            case "STARTING" -> {
                scoreboardManager.showScoreboard(tabPlayer, dungeonStartingSidebar);
            }

            case "ACTIVE" -> {
                scoreboardManager.showScoreboard(tabPlayer, dungeonActiveSidebar);
            }

            case "RESET" -> {
                scoreboardManager.resetScoreboard(tabPlayer);
                scoreboardManager.getRegisteredScoreboards().clear();
            }
        }
    }

    public void resetSidebar(Player player) {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager sbManager = instance.getScoreboardManager();
        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());

        sbManager.resetScoreboard(tabPlayer);
    }

    public String getSidebar(Player player) {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager sidebarManager = instance.getScoreboardManager();
        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());

        return sidebarManager.getActiveScoreboard(tabPlayer).getName();
    }
}
