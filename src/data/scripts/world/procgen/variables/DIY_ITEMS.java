/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen.variables;

import com.fs.starfarer.api.impl.campaign.ids.Factions;
import data.scripts.world.procgen.HS_Randomizer;

/**
 *
 * @author NinjaSiren
 */
public class DIY_ITEMS {
    public static final String ATMOSPHERE_MINERALIZER = "atmo_mineralizer";
    public static final String ATMOSPHERE_SUBLIMATOR = "atmo_sublimator";
    public static final String HEAT_REMOVER_CORE = "solar_reflector";
    public static final String TECTONIC_REMOVER = "tectonic_attenuator";
    public static final String WEATHER_REMOVER_CORE = "weather_core";
    public static final String CLIMATE_TERRAFORMER = "climate_sculptor";
    public static final String GRAV_OSCILLATOR = "gravity_oscillator";
    public static final String RADIATION_REMOVER = "rad_remover";
    
    public String randRadRemover(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return RADIATION_REMOVER;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return RADIATION_REMOVER;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return RADIATION_REMOVER;
            }
        }
        return null;
    }
    
    public String randGravOscillator(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return GRAV_OSCILLATOR;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return GRAV_OSCILLATOR;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return GRAV_OSCILLATOR;
            }
        }
        return null;
    }
    
    public String randAtmoMineralizer(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return ATMOSPHERE_MINERALIZER;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return ATMOSPHERE_MINERALIZER;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return ATMOSPHERE_MINERALIZER;
            }
        }
        return null;
    }
    
    public String randAtmoSublimator(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return ATMOSPHERE_SUBLIMATOR;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return ATMOSPHERE_SUBLIMATOR;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return ATMOSPHERE_SUBLIMATOR;
            }
        }
        return null;
    }
    
    public String randClimateTerraform(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return CLIMATE_TERRAFORMER;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return CLIMATE_TERRAFORMER;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return CLIMATE_TERRAFORMER;
            }
        }
        return null;
    }
    
    public String randHeatRemover(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return HEAT_REMOVER_CORE;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return HEAT_REMOVER_CORE;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return HEAT_REMOVER_CORE;
            }
        }
        return null;
    }
    
    public String randTectonicRemover(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return TECTONIC_REMOVER;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return TECTONIC_REMOVER;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return TECTONIC_REMOVER;
            }
        }
        return null;
    }
    
    public String randWeatherRemover(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return WEATHER_REMOVER_CORE;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return WEATHER_REMOVER_CORE;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return WEATHER_REMOVER_CORE;
            }
        }
        return null;
    }
}
