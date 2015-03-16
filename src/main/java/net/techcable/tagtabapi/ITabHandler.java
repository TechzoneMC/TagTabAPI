package net.techcable.tagtabapi;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface ITabHandler {
    /**
     * Reset this player's tab list back to the unmodified vanilla version
     * 
     * @param player plaeyer to reset
     */
    public void clearTab(Player player);
    
    /**
     * Change the specified player's tab list
     * 
     * @param priority the priority of this change
     * @param player the player where this change will take effect
     * @param column the column of the tab to edit
     * @param row the row of the tab to edit
     * @param msg the tab's new text
     * @param ping the tab's ping
     */
    public void setTab(TabPriority priority, Player player, int column, int row, String msg, int ping);
    
    /**
     * Activate the tag handler
     * 
     * @param plugin plugin that is activating
     */
    public void activate(Plugin plugin);
    
    public static enum TabPriority {
        REMOVE,
        BACKGROUND,
        NORMAL,
        HIGH,
        HIGHEST;
        
        public static TabPriority fromLegacy(int priority) {
            switch (priority) {
                case -2 :
                    return REMOVE;
                case -1 :
                    return BACKGROUND;
                case 1 :
                    return HIGH;
                case 2 :
                    return HIGHEST;
                default :
                    return NORMAL;
            }
        }
    }
}