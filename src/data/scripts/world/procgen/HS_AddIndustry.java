package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import data.scripts.HyperionModPlugin;
import data.scripts.world.procgen.variables.DIY_INDUSTRIES;
import data.scripts.world.procgen.variables.DIY_ITEMS;
import data.scripts.world.procgen.variables.PLANET_TYPES;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddIndustry {
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    public HS_AddIndustry(PlanetAPI planet, MarketAPI market, FactionAPI faction, StarSystemAPI sector) { 
        
        boolean isDIY = new HyperionModPlugin().isDIYPlanets();
        
        // Industry size depending on market size and current industries added
        int iSize = new HS_IndustryLimit().industryLimit(market, planet);
        
        // Add industries
        do {
            
            //TODO : CORONAL HYPERSHUNT
            
            // Aquaculture
            if(planet.getMarket().hasCondition(Conditions.WATER) || 
                    planet.getMarket().hasCondition(Conditions.WATER_SURFACE) ||
                    market.hasCondition(Conditions.WATER) ||
                    market.hasCondition(Conditions.WATER_SURFACE) && 
                    planet.getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                    planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                    planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B)) {
                if(!market.hasIndustry(Industries.AQUACULTURE) ||
                        !planet.getMarket().hasIndustry(Industries.AQUACULTURE)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.AQUACULTURE);
                        iSize = iSize - 1;
                    }
                }         
            }
            
            if(iSize < 0) break;
            
            // Farming
            if(planet.getMarket().hasCondition(Conditions.FARMLAND_RICH) || 
                    planet.getMarket().hasCondition(Conditions.FARMLAND_BOUNTIFUL) ||
                    market.hasCondition(Conditions.FARMLAND_RICH) ||
                    market.hasCondition(Conditions.FARMLAND_BOUNTIFUL)) {
                if(rand() <= 0.75) {
                    if(!planet.getMarket().hasIndustry(Industries.FARMING) ||
                            !market.hasIndustry(Industries.FARMING)) {
                        market.addIndustry(Industries.FARMING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.FARMLAND_ADEQUATE) ||
                    market.hasCondition(Conditions.FARMLAND_ADEQUATE)) {
                if(rand() <= 0.75 && rand() > 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.FARMING) ||
                            !market.hasIndustry(Industries.FARMING)) {
                        market.addIndustry(Industries.FARMING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.FARMLAND_POOR) ||
                    market.hasCondition(Conditions.FARMLAND_POOR)) {
                if(rand() <= 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.FARMING) ||
                            !market.hasIndustry(Industries.FARMING)) {
                        market.addIndustry(Industries.FARMING);
                        iSize = iSize - 1;
                    }
                }
            }      
            
            if(iSize < 0) break;
            
            // Metals
            if(planet.getMarket().hasCondition(Conditions.ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.ORE_ULTRARICH) ||
                    market.hasCondition(Conditions.ORE_RICH) ||
                    market.hasCondition(Conditions.ORE_ULTRARICH)) {
                if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                        !market.hasIndustry(Industries.MINING)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    market.hasCondition(Conditions.ORE_ABUNDANT)) {
                if(rand() <= 1 && rand() > 0.45) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    market.hasCondition(Conditions.ORE_MODERATE)) {
                if(rand() <= 0.45 && rand() > 0.15) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    market.hasCondition(Conditions.ORE_SPARSE)) {
                if(rand() <= 0.15) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            }

            // Rare Metals
            else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH) ||
                    market.hasCondition(Conditions.RARE_ORE_RICH) || 
                    market.hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    market.hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
                if(rand() <= 1 && rand() > 0.45) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    market.hasCondition(Conditions.RARE_ORE_MODERATE)) {
                if(rand() <= 0.45 && rand() > 0.15) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    market.hasCondition(Conditions.RARE_ORE_SPARSE)) {
                if(rand() <= 0.15) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } 

            // Volatiles
            else if(planet.getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    market.hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    market.hasCondition(Conditions.VOLATILES_PLENTIFUL)) {
                if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.MINING);   
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    market.hasCondition(Conditions.VOLATILES_DIFFUSE)) {
                if(rand() <= 0.75 && rand() > 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }        
                }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_TRACE) ||
                    market.hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand() <= 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            }

            // Organics
            else if(planet.getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL) ||
                    market.hasCondition(Conditions.ORGANICS_ABUNDANT) ||
                    market.hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
                if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_COMMON) ||
                    market.hasCondition(Conditions.ORGANICS_COMMON)) {
                if(rand() <= 0.75 && rand() > 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    } 
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_TRACE) ||
                    market.hasCondition(Conditions.ORGANICS_TRACE)) {
                if(rand() <= 0.25) {
                    if(!planet.getMarket().hasIndustry(Industries.MINING) || 
                            !market.hasIndustry(Industries.MINING)) {
                        market.addIndustry(Industries.MINING);
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Techmining
            if(!market.hasIndustry(Industries.TECHMINING) ||
                    !planet.getMarket().hasIndustry(Industries.TECHMINING)) {
                if(planet.getMarket().hasCondition(Conditions.RUINS_VAST) || 
                        planet.getMarket().hasCondition(Conditions.RUINS_EXTENSIVE)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.TECHMINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand() <= 0.75 && rand() > 0.25) {
                        market.addIndustry(Industries.TECHMINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.RUINS_SCATTERED)) {
                    if(rand() <= 0.25) {
                        market.addIndustry(Industries.TECHMINING);
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Light Industry
            if(!market.hasIndustry(Industries.LIGHTINDUSTRY) ||
                    !planet.getMarket().hasIndustry(Industries.LIGHTINDUSTRY)) {

                // If organics are present
                if(planet.getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.LIGHTINDUSTRY);  
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_COMMON)) {
                    if(rand() <= 0.75 && rand() > 0.25) {
                        market.addIndustry(Industries.LIGHTINDUSTRY); 
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_TRACE)) {
                    if(rand() <= 0.25) {
                        market.addIndustry(Industries.LIGHTINDUSTRY);
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.1) {
                        market.addIndustry(Industries.LIGHTINDUSTRY);
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Refining
            if(!market.hasIndustry(Industries.REFINING) ||
                    !planet.getMarket().hasIndustry(Industries.REFINING)) {

                // If ores are present
                if(planet.getMarket().hasCondition(Conditions.ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.ORE_ABUNDANT)) {
                    if(rand() <= 1 && rand() > 0.45) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.ORE_MODERATE)) {
                    if(rand() <= 0.45 && rand() > 0.15) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.ORE_SPARSE)) {
                    if(rand() <= 0.15) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                }

                // If rare metals are present
                else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_RICH) || 
                        planet.getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
                    if(rand() <= 1 && rand() > 0.45) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_MODERATE)) {
                    if(rand() <= 0.45 && rand() > 0.15) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_SPARSE)) {
                    if(rand() <= 0.15) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.1) {
                        market.addIndustry(Industries.REFINING);
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Heavy Industry or Orbital Works
            if(!market.hasIndustry(Industries.HEAVYINDUSTRY) ||
                    !planet.getMarket().hasIndustry(Industries.HEAVYINDUSTRY) ||
                    !market.hasIndustry(Industries.ORBITALWORKS) ||
                    !planet.getMarket().hasIndustry(Industries.ORBITALWORKS)) {
             
                // If a refining industry is in the planet
                if(market.hasIndustry(Industries.REFINING) || 
                        planet.getMarket().hasIndustry(Industries.REFINING)) {
                    if(rand() <= 0.4) {
                        market.addIndustry(Industries.HEAVYINDUSTRY, 
                                new ArrayList<>(Arrays.asList(
                                        randNanoforge(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    } else {
                        market.addIndustry(Industries.ORBITALWORKS, 
                                new ArrayList<>(Arrays.asList(
                                        randNanoforge(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.55) {
                        market.addIndustry(Industries.HEAVYINDUSTRY, 
                                new ArrayList<>(Arrays.asList(
                                        randNanoforge(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    } else {
                        market.addIndustry(Industries.ORBITALWORKS, 
                                new ArrayList<>(Arrays.asList(
                                        randNanoforge(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Fuel Production
            if(!market.hasIndustry(Industries.FUELPROD) ||
                    !planet.getMarket().hasIndustry(Industries.FUELPROD)) {

                // If volatiles are present
                if(planet.getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) || 
                        planet.getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(
                                        randSynchotron(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    }     
                } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE)) {
                    if(rand() <= 0.75 && rand() > 0.25) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(
                                        randSynchotron(
                                                faction.getId())))); 
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                    if(rand() <= 0.25) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(
                                        randSynchotron(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.1) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(
                                        randSynchotron(
                                                faction.getId()))));
                        iSize = iSize - 1;
                    }
                }
            }
            
            if(iSize < 0) break;
            
            // Cryosanctum
            if(!market.hasIndustry(Industries.CRYOSANCTUM) ||
                    !planet.getMarket().hasIndustry(Industries.CRYOSANCTUM)) {
                if(market.hasCondition(Conditions.POPULATION_5) || 
                        planet.hasCondition(Conditions.POPULATION_5)) {
                    if(rand() <= 0.01) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                } else if (market.hasCondition(Conditions.POPULATION_6) || 
                        planet.hasCondition(Conditions.POPULATION_6)) {
                    if(rand() <= 0.025) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                } else if (market.hasCondition(Conditions.POPULATION_7) || 
                        planet.hasCondition(Conditions.POPULATION_7)) {
                    if(rand() <= 0.05) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                } else if (market.hasCondition(Conditions.POPULATION_8) || 
                        planet.hasCondition(Conditions.POPULATION_8)) {
                    if(rand() <= 0.075) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                } else if (market.hasCondition(Conditions.POPULATION_9) || 
                        planet.hasCondition(Conditions.POPULATION_9)) {
                    if(rand() <= 0.1) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                } else if (market.hasCondition(Conditions.POPULATION_10) || 
                        planet.hasCondition(Conditions.POPULATION_10)) {
                    if(rand() <= 0.125) {
                        market.addIndustry(Industries.CRYOSANCTUM);
                    }
                }
            }
            
            // Add DIY Planets' Industries if DIY Planets is enabled
            if(isDIY) {
                
                // Radiation Removal
                if(planet.hasCondition(Conditions.IRRADIATED) ||
                        market.hasCondition(Conditions.IRRADIATED)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(new DIY_INDUSTRIES().RAD_REMOVER, 
                                new ArrayList<>(Arrays.asList(randRadRemover(faction.getId()))));
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
                    if(rand() <= 0.75) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(
                                    new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(
                                    new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY);
                        }
                    }
                }
                
                // Water Capture Infrastructure & Cometary Capture Infrastructure
                int counter = sector.getPlanets().size();
                if(hasWaterPlanetType(sector, counter) && 
                        !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_A) ||
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
                    if(rand() <= 0.75) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(new DIY_INDUSTRIES().WATER_RECIEVER);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(new DIY_INDUSTRIES().WATER_RECIEVER);
                        }
                    }
                } else if(!hasWaterPlanetType(sector, counter) && 
                        !planet.getTypeId().equals(new PLANET_TYPES().FROZEN_A) ||
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
                    if(rand() <= 0.75) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(new DIY_INDUSTRIES().COMET_WATER);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(new DIY_INDUSTRIES().COMET_WATER);
                        }
                    }
                }
                
                // Environmental Agency
                if(planet.hasCondition(Conditions.POLLUTION) ||
                        market.hasCondition(Conditions.POLLUTION)) {
                    if(rand() <= 0.75) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(new DIY_INDUSTRIES().POLLUTION_REMOVER);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(new DIY_INDUSTRIES().POLLUTION_REMOVER);
                        }
                    }
                }
                
                // Integration Corps / Subjugation Corps
                if(planet.hasCondition(Conditions.DECIVILIZED) ||
                        market.hasCondition(Conditions.DECIVILIZED) ||
                        planet.hasCondition(Conditions.DECIVILIZED_SUBPOP) ||
                        market.hasCondition(Conditions.DECIVILIZED_SUBPOP)) {
                    if(rand() <= 0.33) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(
                                    new DIY_INDUSTRIES().DECIVILIZED_REMOVER_INTEGRATE);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(
                                    new DIY_INDUSTRIES().DECIVILIZED_REMOVER_INTEGRATE);
                        }
                    } else if(rand() > 0.33 && rand() <= 0.66) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(
                                    new DIY_INDUSTRIES().DECIVILIZED_REMOVER_SUBJUGATE);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(
                                    new DIY_INDUSTRIES().DECIVILIZED_REMOVER_SUBJUGATE);
                        }
                    }
                }
                
                // Atmospheric Filter
                if(planet.hasCondition(Conditions.TOXIC_ATMOSPHERE) ||
                        market.hasCondition(Conditions.TOXIC_ATMOSPHERE) ||
                        planet.hasCondition(Conditions.DENSE_ATMOSPHERE) ||
                        market.hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_FILTER, 
                                new ArrayList<>(Arrays.asList(randAtmoMineralizer(faction.getId()))));
                    }
                }
                
                // Atmospheric Pump
                if(planet.hasCondition(Conditions.NO_ATMOSPHERE) ||
                        market.hasCondition(Conditions.NO_ATMOSPHERE) ||
                        planet.hasCondition(Conditions.THIN_ATMOSPHERE) ||
                        market.hasCondition(Conditions.THIN_ATMOSPHERE)) {
                    if(rand() <= 0.33) {
                        market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_PUMP, 
                                new ArrayList<>(Arrays.asList(randAtmoSublimator(faction.getId()))));
                    } else if(rand() > 0.33 && rand() <= 0.66) {
                        market.addIndustry(new DIY_INDUSTRIES().ATMOSPHERE_PUMP_EXPENSIVE, 
                                new ArrayList<>(Arrays.asList(randAtmoSublimator(faction.getId()))));
                    }
                }
                
                // Stellar Mirror
                if(planet.hasCondition(Conditions.VERY_COLD) ||
                        market.hasCondition(Conditions.VERY_COLD) ||
                        planet.hasCondition(Conditions.COLD) ||
                        market.hasCondition(Conditions.COLD)) {
                    if(rand() <= 0.33) {
                        market.addIndustry(new DIY_INDUSTRIES().COLD_REMOVER, 
                                new ArrayList<>(Arrays.asList(randHeatRemover(faction.getId()))));
                    } else if(rand() > 0.33 && rand() <= 0.66) {
                        market.addIndustry(new DIY_INDUSTRIES().STELLAR_MIRROR_EXPENSIVE, 
                                new ArrayList<>(Arrays.asList(randHeatRemover(faction.getId()))));
                    }
                }
                
                // Stellar Shade
                if(planet.hasCondition(Conditions.VERY_HOT) ||
                        market.hasCondition(Conditions.VERY_HOT) ||
                        planet.hasCondition(Conditions.HOT) ||
                        market.hasCondition(Conditions.HOT)) {
                    if(rand() <= 0.33) {
                        market.addIndustry(new DIY_INDUSTRIES().HEAT_REMOVER, 
                                new ArrayList<>(Arrays.asList(randHeatRemover(faction.getId()))));
                    } else if(rand() > 0.33 && rand() <= 0.66) {
                        market.addIndustry(new DIY_INDUSTRIES().STELLAR_MIRROR_EXPENSIVE, 
                                new ArrayList<>(Arrays.asList(randHeatRemover(faction.getId()))));
                    }
                }
                
                // Stellar Distributor
                if(planet.hasCondition(Conditions.POOR_LIGHT) ||
                        market.hasCondition(Conditions.POOR_LIGHT)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(new DIY_INDUSTRIES().POOR_LIGHT_REMOVER, 
                                new ArrayList<>(Arrays.asList(randHeatRemover(faction.getId()))));
                    }
                }
                
                // Tectonic Engine
                if(planet.hasCondition(Conditions.TECTONIC_ACTIVITY) ||
                        market.hasCondition(Conditions.TECTONIC_ACTIVITY) ||
                        planet.hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY) ||
                        market.hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY)) {
                    if(rand() <= 0.33) {
                        market.addIndustry(new DIY_INDUSTRIES().TECTONIC_REMOVER, 
                                new ArrayList<>(Arrays.asList(randTectonicRemover(faction.getId()))));
                    } else if(rand() > 0.33 && rand() <= 0.66) {
                        market.addIndustry(new DIY_INDUSTRIES().TECTONIC_REMOVER_EXPENSIVE, 
                                new ArrayList<>(Arrays.asList(randTectonicRemover(faction.getId()))));
                    }
                }
                
                // Cyclonic Regulator
                if(planet.hasCondition(Conditions.EXTREME_WEATHER) ||
                        market.hasCondition(Conditions.EXTREME_WEATHER)) {
                    if(rand() <= 0.5) {
                        market.addIndustry(new DIY_INDUSTRIES().EXTREME_WEATHER_REMOVER, 
                                new ArrayList<>(Arrays.asList(randWeatherRemover(faction.getId()))));
                    }
                }
                
                // Genepurge Project
                if(planet.hasCondition(Conditions.INIMICAL_BIOSPHERE) ||
                        market.hasCondition(Conditions.INIMICAL_BIOSPHERE)) {
                    if(rand() <= 0.5) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(new DIY_INDUSTRIES().INIMICAL_REMOVER);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(new DIY_INDUSTRIES().INIMICAL_REMOVER);
                        }
                    }
                }
                
                // N-Brane Oscillator
                if(planet.hasCondition(Conditions.LOW_GRAVITY) ||
                        market.hasCondition(Conditions.LOW_GRAVITY) ||
                        planet.hasCondition(Conditions.HIGH_GRAVITY) ||
                        market.hasCondition(Conditions.HIGH_GRAVITY)) {
                    if(rand() <= 0.75) {
                        market.addIndustry(new DIY_INDUSTRIES().GRAV_OSCILLATOR, 
                                new ArrayList<>(Arrays.asList(randGravOscillator(faction.getId()))));
                    }
                }
                
                // Climate Sculpting Project
                if(planet.getTypeId().contains("TERRAN") ||
                        planet.getTypeId().contains("WATER")) {
                    if(rand() <= 0.75) {
                        market.addIndustry(new DIY_INDUSTRIES().PERFECT_CLIMATE_TERRAN_WATER_ONLY, 
                                new ArrayList<>(Arrays.asList(randClimateTerraform(faction.getId()))));
                    }
                }
                
                // Lobster Breeding Program
                if(!planet.hasCondition(Conditions.VOLTURNIAN_LOBSTER_PENS) ||
                        !market.hasCondition(Conditions.VOLTURNIAN_LOBSTER_PENS) &&
                        planet.getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                        planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                        planet.getTypeId().equals(new PLANET_TYPES().US_OCEAN_B)) {
                    if(rand() <= 0.5) {
                        if(faction.getId().equalsIgnoreCase("HS_Corporation_Separatist")) {
                            if(rand() <= 0.5) market.addIndustry(new DIY_INDUSTRIES().LOBSTER_BREEDING);
                        } else if(faction.getId().equalsIgnoreCase(Factions.REMNANTS)) {
                            if(rand() <= 0.75) market.addIndustry(new DIY_INDUSTRIES().LOBSTER_BREEDING);
                        }
                    }
                }
            }
        } while(iSize > 0);
    }
    
    private boolean hasWaterPlanetType(StarSystemAPI sector, int counter) {
        counter = counter - 1;
        do {
            if(sector.getPlanets().get(counter).getMarket().hasIndustry(
                    new DIY_INDUSTRIES().WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY)) {
                return true;
            }
            if(counter < 0) break;
            counter = counter - 1;
        } while(counter > 0);
        return false;
    }

    private String randNanoforge(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return Items.PRISTINE_NANOFORGE;
                else if(rand() > 0.5) return Items.CORRUPTED_NANOFORGE;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return Items.PRISTINE_NANOFORGE;
                else if(rand() > 0.25) return Items.CORRUPTED_NANOFORGE;
            }
        }
        return null;
    }
    
    private String randSynchotron(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return Items.SYNCHROTRON;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return Items.SYNCHROTRON;
            }
        }
        return null;
    }
    
    private String randRadRemover(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().RADIATION_REMOVER;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().RADIATION_REMOVER;
            }
        }
        return null;
    }
    
    private String randGravOscillator(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().GRAV_OSCILLATOR;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().GRAV_OSCILLATOR;
            }
        }
        return null;
    }
    
    private String randAtmoMineralizer(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().ATMOSPHERE_MINERALIZER;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().ATMOSPHERE_MINERALIZER;
            }
        }
        return null;
    }
    
    private String randAtmoSublimator(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().ATMOSPHERE_SUBLIMATOR;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().ATMOSPHERE_SUBLIMATOR;
            }
        }
        return null;
    }
    
    private String randClimateTerraform(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().CLIMATE_TERRAFORMER;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().CLIMATE_TERRAFORMER;
            }
        }
        return null;
    }
    
    private String randHeatRemover(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().HEAT_REMOVER_CORE;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().HEAT_REMOVER_CORE;
            }
        }
        return null;
    }
    
    private String randTectonicRemover(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().TECTONIC_REMOVER;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().TECTONIC_REMOVER;
            }
        }
        return null;
    }
    
    private String randWeatherRemover(String faction) {
        if(faction != null) {
            if(faction.equalsIgnoreCase("HS_Corporation_Separatist")) {
                if(rand() <= 0.5) return new DIY_ITEMS().WEATHER_REMOVER_CORE;
            } else if(faction.equalsIgnoreCase(Factions.REMNANTS)) {
                if(rand() <= 0.75) return new DIY_ITEMS().WEATHER_REMOVER_CORE;
            }
        }
        return null;
    }
}
