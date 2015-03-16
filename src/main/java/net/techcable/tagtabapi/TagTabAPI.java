package net.techcable.tagtabapi;

import lombok.*;

import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.TagAPI;
import org.mcsg.double0negative.tabapi.TabAPI;

public class TagTabAPI extends JavaPlugin {
    
    private TabAPI tabApi;
    private TagAPI tagApi;
    @Override
    public void onEnable() {
        //TODO Implement Handlers
        
        //Compatibility with TabAPI and TagAPI
        tabApi = new TabAPI(this);
        tagApi = new TagAPI(this);
    }
    
    @Override
    public void onDisable() {
        tagApi.disablePlugin(tagApi);
        tabApi.disablePlugin(tabApi);
        tagApi = null;
        tabApi = null;
    }
    
    //Handlers
    @Getter
    @Setter
    private static ITagHandler tagHandler;
    @Getter
    @Setter
    private static ITabHandler tabHandler;
}