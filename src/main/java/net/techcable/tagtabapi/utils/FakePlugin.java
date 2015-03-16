package net.techcable.tagtabapi.utils;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.UnknownDependencyException;

import lombok.*;
import lombok.experimental.Delegate;

/**
 * This is a magical class that makes the server think that a plugin is installed, when it really isn't
 * 
 * @author md_5
 * @author Techcable
 */
public abstract class FakePlugin extends PluginBase implements PluginLoader {

    public abstract String getFakeName();

    public FakePlugin(Plugin parent) {
        this.parent = parent;

        plugins = Reflection.getField(Reflection.makeField(SimplePluginManager.class, "plugins"), parent.getServer().getPluginManager());
        lookupNames = Reflection.getField(Reflection.makeField( SimplePluginManager.class, "lookupNames"), parent.getServer().getPluginManager());

        StringWriter write = new StringWriter();
        parent.getDescription().save(write);
        String yaml = write.toString().replaceAll(parent.getName(), getFakeName());

        try {
            description = new PluginDescriptionFile(new StringReader(yaml));
        } catch (InvalidDescriptionException ex) {
            Throwables.propagate(ex);
        }

        plugins.add(this);
        lookupNames.put(getName(), this);
    }

    @Override
    public PluginLoader getPluginLoader() {
        return this;
    }
    
    @Override
    public void disablePlugin(Plugin plugin) {
        plugins.remove(plugin);
        lookupNames.remove(plugin.getName());
    }

    private interface Excludes {
        PluginLoader getPluginLoader();

        PluginDescriptionFile getDescription();

        String getName();
    }
    @Delegate(excludes = Excludes.class, types = {CommandExecutor.class, TabCompleter.class, Plugin.class})
    private final Plugin parent;
    @Getter
    private PluginDescriptionFile description;
    private List<Plugin> plugins;
    private Map<String, Plugin> lookupNames;
    
    //Unsupported
    public Plugin loadPlugin(File file) throws InvalidPluginException, UnknownDependencyException {
        throw new UnsupportedOperationException();
    }

    public PluginDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException {
        throw new UnsupportedOperationException();
    }

    public Pattern[] getPluginFileFilters() {
        throw new UnsupportedOperationException();
    }

    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(Listener listener, Plugin plugin) {
        throw new UnsupportedOperationException();
    }

    public void enablePlugin(Plugin plugin) {
        throw new UnsupportedOperationException();
    }
}