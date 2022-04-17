package ga.uniquecoding.uniquedungeons.sidebars;

import ga.uniquecoding.uniquedungeons.manager.GameManager;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class DungeonWaitingSidebar {

    private GameManager gameManager;
    private Scoreboard sidebar;

    public DungeonWaitingSidebar(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Scoreboard getSidebar() {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager sbManager = instance.getScoreboardManager();

        return sbManager.createScoreboard("dungeon-waiting", c("&6&lDUNGEONS"), getLines());
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();

        lines.add(c("&r"));
        lines.add(c("&cWaiting for players..."));
        lines.add(c("&fPlayers:&e %player_dungeon_player_list%/4"));

        return lines;
    }
}
