package net.techcable.tagtabapi;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface ITagHandler {
    /**
     * Refresh a given player's visibile nametags
     * 
     * @param player player to refresh for
     * @param forWhom who this player should see updated
     */
    public void refreshPlayer(Player player, Collection<Player> forWhom);
    
    /**
     * Activate the tag handler
     * 
     * @param plugin plugin that is activating
     */
    public void activate(Plugin plugin);
}