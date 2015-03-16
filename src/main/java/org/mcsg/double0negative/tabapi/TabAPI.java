package org.mcsg.double0negative.tabapi;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.techcable.tagtabapi.TagTabAPI;

import net.techcable.tagtabapi.TagTabAPI;
import net.techcable.tagtabapi.ITabHandler.TabPriority;
import net.techcable.tagtabapi.utils.FakePlugin;

/**
 * This class pertends it is TabAPI to be compatible with other plugins
 * 
 * @author md_5
 * @author Techcable
 */
public class TabAPI extends FakePlugin {
    
    @Override
    public String getFakeName() {
        return "TabAPI";
    }
    
    public TabAPI(TagTabAPI parent) {
        super(parent);
    }
    
	/**
	 * Priorities
	 * 
	 * -2 = no longer active, remove
	 * -1 = background, only show if nothing else is there
	 *  0 = normal
	 *  1 = high priority
	 *  2 = always show, only use if MUST show
	 * 
	 */
	public static void setPriority(Plugin plugin, Player player, int priority){
		setPriority(plugin, TabPriority.fromLegacy(priority));
	}

	/**
	 * Returns the tab list to the vanilla tab list for a player. 
	 * If another plugin holds higher priority, this does notning
	 * @param p
	 */
	public static void disableTabForPlayer(Player p){
		TagTabAPI.getTabHandler().clearTab(p);
	}

	/**
	 * Resets tab to normal tab. 
	 * @param p
	 */
	public static void resetTabList(Player p){
		TagTabAPI.getTabHandler().clearTab(p);
	}

	public static void setTabString(Plugin plugin, Player p, int x, int y, String msg){
	    setTabString(plugin, p, x, y, msg, 0);
	}
	
	/**
	 * Set the tab for a player. 
	 * 
	 * If the plugin the tab is being set from does not have a priority, It will automatically be give a base
	 * priority of 0
	 * 
	 * @param plugin
	 * @param p
	 * @param x
	 * @param y
	 * @param msg
	 * @param ping
	 */
	public static void setTabString(Plugin plugin, Player p, int x, int y, String msg, int ping){
		TagTabAPI.getTabHandler().setTab(getPriority(plugin), p, x, y, msg, ping);
	}

	/**
	 * Updates a players tab
	 * 
	 * A tab will be updated with the tab from the highest priority plugin
	 * 
	 * @param p
	 */
	public static void updatePlayer(Player p) {} //Done automatically

	/**
	 * Clear a players tab menu
	 * @param p
	 */
	public static void clearTab(Player p){
		TagTabAPI.getTabHandler().clearTab(p);
	}

	public static void updateAll() {} //Done automatically


	/* return the next null filler */
	public static String nextNull(){
		return ""; //Handled by implementation
	}

	public static int getVertSize(){
		return 20;
	}
	
	public static int getHorizSize(){
		return 3;
	}
    
    private static final Map<Plugin, TabPriority> priorityMap = new HashMap<>();
    public static TabPriority getPriority(Plugin plugin) {
        if (priorityMap.containsKey(plugin)) {
            return priorityMap.get(plugin);
        }
        return TabPriority.NORMAL;
    }
    public static void setPriority(Plugin plugin, TabPriority priority) {
        priorityMap.put(plugin, priority);
    }
}
