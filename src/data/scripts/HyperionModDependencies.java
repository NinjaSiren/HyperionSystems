/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts;

import com.fs.starfarer.api.Global;

/**
 *
 * @author NinjaSiren
 */
public class HyperionModDependencies {
    
    // Important Mods
    boolean hasLazyLib = Global.getSettings().getModManager().isModEnabled("lw_lazylib");
    boolean hasGraphicsLib = Global.getSettings().getModManager().isModEnabled("shaderLib");
    boolean hasMagicLib = Global.getSettings().getModManager().isModEnabled("MagicLib");
    
    // Optional Mods
    boolean isExerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
    boolean isDIYPlanets = Global.getSettings().getModManager().isModEnabled("diyplanets");
    boolean isBetterColonies = Global.getSettings().getModManager().isModEnabled("timid_admins");
    boolean isConsoleCommands = Global.getSettings().getModManager().isModEnabled("lw_console");
    boolean isSCYNation = Global.getSettings().getModManager().isModEnabled("SCY");
    boolean isUnknownSkies = Global.getSettings().getModManager().isModEnabled("US");
    boolean isDassaultMikoyan = Global.getSettings().getModManager().isModEnabled("istl_dam");
    boolean isIndustrialEvolution = Global.getSettings().getModManager().isModEnabled("IndEvo");
    
    // Returns
    public boolean hasLazyLib() {
        return hasLazyLib;
    }
    
    public boolean hasGraphicsLib() {
        return hasGraphicsLib;
    }
    
    public boolean hasMagicLib() {
        return hasMagicLib;
    }
    
    public boolean hasExerelin() {
        return isExerelin;
    }
    
    public boolean hasDIYPlanets() {
        return isDIYPlanets;
    }
    
    public boolean hasBetterColonies() {
        return isBetterColonies;
    }
    
    public boolean hasConsoleCommands() {
        return isConsoleCommands;
    }
    
    public boolean hasSCYNation() {
        return isSCYNation;
    }
    
    public boolean hasUnknownSkies() {
        return isUnknownSkies;
    }
    
    public boolean hasDassaultMikoyan() {
        return isDassaultMikoyan;
    }
    
    public boolean hasIndEvolution() {
        return isIndustrialEvolution;
    }
}
