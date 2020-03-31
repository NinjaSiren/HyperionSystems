package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
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
    
    public HS_AddIndustry(PlanetAPI planet, MarketAPI market) {
        
        // Industry size depending on market size and current industries added
        int iSize = new HS_IndustryLimit().industryLimit(market, planet);
        
        // Add industries
        do {

            // Aquaculture
            if(planet.getMarket().hasCondition(Conditions.WATER) || 
                    planet.getMarket().hasCondition(Conditions.WATER_SURFACE) ||
                    market.hasCondition(Conditions.WATER) ||
                    market.hasCondition(Conditions.WATER_SURFACE)) {
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
                                new ArrayList<>(Arrays.asList(randNanoforge())));
                        iSize = iSize - 1;
                    } else {
                        market.addIndustry(Industries.ORBITALWORKS, 
                                new ArrayList<>(Arrays.asList(randNanoforge())));
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.55) {
                        market.addIndustry(Industries.HEAVYINDUSTRY, 
                                new ArrayList<>(Arrays.asList(randNanoforge())));
                        iSize = iSize - 1;
                    } else {
                        market.addIndustry(Industries.ORBITALWORKS, 
                                new ArrayList<>(Arrays.asList(randNanoforge())));
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
                                new ArrayList<>(Arrays.asList(randSynchotron())));
                        iSize = iSize - 1;
                    }     
                } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE)) {
                    if(rand() <= 0.75 && rand() > 0.25) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(randSynchotron()))); 
                        iSize = iSize - 1;
                    }
                } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                    if(rand() <= 0.25) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(randSynchotron())));
                        iSize = iSize - 1;
                    }
                } else {
                    if(rand() <= 0.1) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(randSynchotron())));
                        iSize = iSize - 1;
                    }
                }
            }
        } while (iSize > 0);
    }
    
    private String randNanoforge() {
        if(rand() <= 0.33) return Items.PRISTINE_NANOFORGE;
        else if(rand() > 0.33 && rand() <= 0.66) return Items.DECAYED_NANOFORGE;
        else if(rand() > 0.66) return Items.CORRUPTED_NANOFORGE;
        return "";
    }
    
    private String randSynchotron() {
        if(rand() <= 0.5) return Items.SYNCHROTRON;
        else return "";
    }
}
