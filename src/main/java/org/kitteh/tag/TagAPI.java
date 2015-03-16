package org.kitteh.tag;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.techcable.tagtabapi.TagTabAPI;
import net.techcable.tagtabapi.utils.FakePlugin;

public class TagAPI extends FakePlugin {

    public TagAPI(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getFakeName() {
        return "TagAPI";
    }

    public static void refreshPlayer(Player player) {
        TagTabAPI.getTagHandler().refreshPlayer(player, player.getWorld().getPlayers());
    }

    public static void refreshPlayer(Player player, Player forWhom) {
        refreshPlayer(player, ImmutableSet.of(forWhom));
    }

    public static void refreshPlayer(Player player, Set<Player> forWhom) {
        TagTabAPI.getTagHandler().refreshPlayer(player, forWhom);
    }
}
