package ga.uniquecoding.uniquedungeons.sidebars;

import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class DungeonStartingSidebar {

    private GameManager manager;
    private Scoreboard sidebar;

    public DungeonStartingSidebar(GameManager manager) {
        this.manager = manager;
    }

    public Scoreboard getSidebar() {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager sbManager = instance.getScoreboardManager();

        return sbManager.createScoreboard("dungeon-starting", HexUtils.colorify("&6&lDUNGEONS"), getLines());
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();

        lines.add(HexUtils.colorify("&r"));
        lines.add(HexUtils.colorify("&fPlayers:&e %player_dungeon_player_list%/4"));
        lines.add(HexUtils.colorify("&cGame starting in %player_dungeon_starting_time%s"));

        return lines;
    }
}
