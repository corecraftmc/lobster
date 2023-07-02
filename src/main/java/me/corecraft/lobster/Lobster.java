package me.corecraft.lobster;

import me.corecraft.lobster.api.ApiManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobster extends JavaPlugin {

    private ApiManager apiManager;

    @Override
    public void onEnable() {
        this.apiManager = new ApiManager();
        this.apiManager.load();
    }

    @Override
    public void onDisable() {
        if (this.apiManager != null) this.apiManager.unload();
    }

    public ApiManager getApiManager() {
        return this.apiManager;
    }
}