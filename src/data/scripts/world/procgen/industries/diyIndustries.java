/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.industries;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import data.scripts.world.procgen.HS_Randomizer;
import data.scripts.world.procgen.variables.DIY_INDUSTRIES;
import data.scripts.world.procgen.variables.DIY_ITEMS;
import data.scripts.world.procgen.variables.PLANET_TYPES;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author NinjaSiren
 */
public class diyIndustries {
    public diyIndustries(PlanetAPI planet, MarketAPI market, FactionAPI faction, StarSystemAPI sector) {
        
        // Radiation Removal
        if(market.hasCondition(Conditions.IRRADIATED)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                market.addIndustry(new DIY_INDUSTRIES().RAD_REMOVER, 
                        new ArrayList<>(Arrays.asList(
                                new DIY_ITEMS().RADIATION_REMOVER)));
            }
        }

        // Water Transport Infrastructure
        if(planet.getTypeId().equals(new PLANET_TYPES().FROZEN_A) ||
                planet.getTypeId().equals(new PLANET_TYPES().FROZEN_B) ||
                planet.getTypeId().equals(new PLANET_TYPES().FROZEN_C) ||
                planet.getTypeId().equals(new PLANET_TYPES().FROZEN_D) ||
                planet.getTypeId().equals(new PLANET_TYPES().CRYOVOLCANIC) ||
                planet.getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                planet.getTypeId().equals(new PLANET_TYPES().ROCKY_ICE) ||
                planet.getTypeId().equals(new PLANET_TYPES().US_CRYOVOLCANIC) ||
                planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B) ||
                planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_A) ||
                planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_B) ||
                planet.getTypeId().equals(new PLANET_TYPES().TUNDRA)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY);
                }
            }
        }

        // Water Capture Infrastructure & Cometary Capture Infrastructure
        if(!planet.getTypeId().equals(new PLANET_TYPES().FROZEN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_C) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_D) ||
                !planet.getTypeId().equals(new PLANET_TYPES().CRYOVOLCANIC) ||
                !planet.getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                !planet.getTypeId().equals(new PLANET_TYPES().ROCKY_ICE) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_CRYOVOLCANIC) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().TUNDRA)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_RECIEVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_RECIEVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().WATER_RECIEVER);
                }
            }
        } else if(!planet.getTypeId().equals(new PLANET_TYPES().FROZEN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_C) ||
                !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_D) ||
                !planet.getTypeId().equals(new PLANET_TYPES().CRYOVOLCANIC) ||
                !planet.getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                !planet.getTypeId().equals(new PLANET_TYPES().ROCKY_ICE) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_CRYOVOLCANIC) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_A) ||
                !planet.getTypeId().equals(new PLANET_TYPES().US_FROZEN_B) ||
                !planet.getTypeId().equals(new PLANET_TYPES().TUNDRA)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().COMET_WATER);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().COMET_WATER);
                }  else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().COMET_WATER);
                }
            }
        }

        // Environmental Agency
        if(market.hasCondition(Conditions.POLLUTION)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().POLLUTION_REMOVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().POLLUTION_REMOVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().POLLUTION_REMOVER);
                }
            }
        }

        // Integration Corps / Subjugation Corps
        if(market.hasCondition(Conditions.DECIVILIZED) || market.hasCondition(Conditions.DECIVILIZED_SUBPOP)) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) market.addIndustry(
                            new DIY_INDUSTRIES().DECIVILIZED_REMOVER_INTEGRATE);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) market.addIndustry(
                            new DIY_INDUSTRIES().DECIVILIZED_REMOVER_INTEGRATE);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().DECIVILIZED_REMOVER_INTEGRATE);
                }
            } else if(new HS_Randomizer().randFixed() > 0.33 && 
                    new HS_Randomizer().randFixed() <= 0.66) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) market.addIndustry(
                            new DIY_INDUSTRIES().DECIVILIZED_REMOVER_SUBJUGATE);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) market.addIndustry(
                            new DIY_INDUSTRIES().DECIVILIZED_REMOVER_SUBJUGATE);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.9) 
                        market.addIndustry(new DIY_INDUSTRIES().DECIVILIZED_REMOVER_SUBJUGATE);
                }
            }
        }

        // Atmospheric Filter
        if(market.hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_FILTER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().ATMOSPHERE_MINERALIZER)));
            }
        }

        // Atmospheric Pump
        if(market.hasCondition(Conditions.NO_ATMOSPHERE)) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_PUMP, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().ATMOSPHERE_SUBLIMATOR)));
            } else if(new HS_Randomizer().randFixed() > 0.33 && new HS_Randomizer().randFixed() <= 0.66) {
                market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_PUMP_EXPENSIVE, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().ATMOSPHERE_SUBLIMATOR)));
            }
        }

        // Stellar Mirror
        if(market.hasCondition(Conditions.VERY_COLD) || market.hasCondition(Conditions.COLD)) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                market.addIndustry(new DIY_INDUSTRIES().COLD_REMOVER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().HEAT_REMOVER_CORE)));
            } else if(new HS_Randomizer().randFixed() > 0.33 && new HS_Randomizer().randFixed() <= 0.66) {
                market.addIndustry(new DIY_INDUSTRIES().STELLAR_MIRROR_EXPENSIVE, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().HEAT_REMOVER_CORE)));
            }
        }

        // Stellar Shade
        if(market.hasCondition(Conditions.VERY_HOT) || market.hasCondition(Conditions.HOT)) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                market.addIndustry(new DIY_INDUSTRIES().HEAT_REMOVER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().HEAT_REMOVER_CORE)));
            } else if(new HS_Randomizer().randFixed() > 0.33 && new HS_Randomizer().randFixed() <= 0.66) {
                market.addIndustry(new DIY_INDUSTRIES().STELLAR_MIRROR_EXPENSIVE, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().HEAT_REMOVER_CORE)));
            }
        }

        // Stellar Distributor
        if(market.hasCondition(Conditions.POOR_LIGHT)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                market.addIndustry(new DIY_INDUSTRIES().POOR_LIGHT_REMOVER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().HEAT_REMOVER_CORE)));
            }
        }

        // Tectonic Engine
        if(market.hasCondition(Conditions.TECTONIC_ACTIVITY) || market.hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY)) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                market.addIndustry(new DIY_INDUSTRIES().TECTONIC_REMOVER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().TECTONIC_REMOVER)));
            } else if(new HS_Randomizer().randFixed() > 0.33 && new HS_Randomizer().randFixed() <= 0.66) {
                market.addIndustry(new DIY_INDUSTRIES().TECTONIC_REMOVER_EXPENSIVE, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().TECTONIC_REMOVER)));
            }
        }

        // Cyclonic Regulator
        if(market.hasCondition(Conditions.EXTREME_WEATHER)) {
            if(new HS_Randomizer().randFixed() <= 0.5) {
                market.addIndustry(new DIY_INDUSTRIES().EXTREME_WEATHER_REMOVER, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().WEATHER_REMOVER_CORE)));
            }
        }

        // Genepurge Project
        if(market.hasCondition(Conditions.INIMICAL_BIOSPHERE)) {
            if(new HS_Randomizer().randFixed() <= 0.5) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().INIMICAL_REMOVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().INIMICAL_REMOVER);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().INIMICAL_REMOVER);
                }
            }
        }

        // N-Brane Oscillator
        if(market.hasCondition(Conditions.LOW_GRAVITY) || market.hasCondition(Conditions.HIGH_GRAVITY)) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                market.addIndustry(new DIY_INDUSTRIES().GRAV_OSCILLATOR, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().GRAV_OSCILLATOR)));
            }
        }

        // Climate Sculpting Project
        if(planet.getTypeId().contains("TERRAN") || planet.getTypeId().contains("WATER")) {
            if(new HS_Randomizer().randFixed() <= 0.75) {
                market.addIndustry(new DIY_INDUSTRIES().PERFECT_CLIMATE_TERRAN_WATER_ONLY, 
                        new ArrayList<>(Arrays.asList(new DIY_ITEMS().CLIMATE_TERRAFORMER)));
            }
        }

        // Lobster Breeding Program
        if(!market.hasCondition(Conditions.VOLTURNIAN_LOBSTER_PENS) && planet.getTypeId().equals(new PLANET_TYPES().OCEAN) || 
                planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) || planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B)) {
            if(new HS_Randomizer().randFixed() <= 0.5) {
                if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                    if(new HS_Randomizer().randFixed() <= 0.5) 
                        market.addIndustry(new DIY_INDUSTRIES().LOBSTER_BREEDING);
                } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) 
                        market.addIndustry(new DIY_INDUSTRIES().LOBSTER_BREEDING);
                } else if(faction.getId().equalsIgnoreCase(Factions.PIRATES)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) 
                        market.addIndustry(new DIY_INDUSTRIES().LOBSTER_BREEDING);
                }
            }
        }
    }
}
