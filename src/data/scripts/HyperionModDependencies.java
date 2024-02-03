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
    private static boolean hasLazyLib = false;
    //private static boolean hasGraphicsLib = false;
    private static boolean hasMagicLib = false;
    
    // Optional Mods
    private static boolean isExerelin = false;
    private static boolean isDIYPlanets = false;
    private static boolean isBetterColonies = false;
    private static boolean isSCYNation = false;
    private static boolean isUnknownSkies = false;
    private static boolean isDassaultMikoyan = false;
    private static boolean isIndustrialEvolution = false;
    
    
    // Set all mod dependencies
    public HyperionModDependencies() {      
        hasLazyLib = Global.getSettings().getModManager().isModEnabled("lw_lazylib");
        //hasGraphicsLib = Global.getSettings().getModManager().isModEnabled("shaderLib"); 
        hasMagicLib = Global.getSettings().getModManager().isModEnabled("MagicLib");
        isExerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        isDIYPlanets = Global.getSettings().getModManager().isModEnabled("diyplanets");
        isBetterColonies = Global.getSettings().getModManager().isModEnabled("timid_admins");
        isSCYNation = Global.getSettings().getModManager().isModEnabled("SCY");
        isUnknownSkies = Global.getSettings().getModManager().isModEnabled("US");
        isDassaultMikoyan = Global.getSettings().getModManager().isModEnabled("istl_dam");
        isIndustrialEvolution = Global.getSettings().getModManager().isModEnabled("IndEvo");
    }
    
    // Returns
    public boolean hasLazyLib() {
        return hasLazyLib;
    }
    
    /*
    public boolean hasGraphicsLib() {
        return hasGraphicsLib;
    }
    */
    
    public boolean hasMagicLib() {
        return hasMagicLib;
    }
    
    public boolean isExerelin() {
        return isExerelin;
    }
    
    public boolean isDIYPlanets() {
        return isDIYPlanets;
    }
    
    public boolean isBetterColonies() {
        return isBetterColonies;
    }
    
    public boolean isSCYNation() {
        return isSCYNation;
    }
    
    public boolean isUnknownSkies() {
        return isUnknownSkies;
    }
    
    public boolean isDassaultMikoyan() {
        return isDassaultMikoyan;
    }
    
    public boolean isIndEvolution() {
        return isIndustrialEvolution;
    }
}
