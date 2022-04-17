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

    private GameManager gameManager;
    private Scoreboard dungeonWaitingSidebar;
    private Scoreboard dungeonStartingSidebar;
    private Scoreboard dungeonActiveSidebar;

    public SidebarManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setupSidebar() {
        DungeonWaitingSidebar dwSidebar = new DungeonWaitingSidebar(gameManager);
        DungeonStartingSidebar dsSidebar = new DungeonStartingSidebar(gameManager);
        DungeonActiveSidebar daSidebar = new DungeonActiveSidebar(gameManager);

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

    public Scoreboard getDungeonWaitingSidebar() {
        return dungeonWaitingSidebar;
    }

    public Scoreboard getDungeonStartingSidebar() {
        return dungeonStartingSidebar;
    }

    public Scoreboard getDungeonActiveSidebar() {
        return dungeonActiveSidebar;
    }
}
