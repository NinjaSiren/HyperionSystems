/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.industries;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.world.procgen.HS_Randomizer;
import data.scripts.world.procgen.variables.BASE_ITEMS;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author NinjaSiren
 */
public class baseGameInd {
    
    // Adds Spaceport, Planetary Defences, and Military Bases
    public void initBaseInd(MarketAPI markets, FactionAPI faction, PlanetAPI planet) {
        
        // Spaceport
        if(markets.hasCondition(Conditions.POPULATION_5) && new HS_Randomizer().randFixed() <= 0.13) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_6) && new HS_Randomizer().randFixed() <= 0.19) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_7) && new HS_Randomizer().randFixed() <= 0.28) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_8) && new HS_Randomizer().randFixed() <= 0.42) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_9) && new HS_Randomizer().randFixed() <= 0.63) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_10) && new HS_Randomizer().randFixed() <= 0.95) {
            markets.addIndustry(Industries.MEGAPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else {
            markets.addIndustry(Industries.SPACEPORT, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randPortItem(faction.getId(), markets, planet), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        }

        // Planet Defences
        if(markets.hasCondition(Conditions.POPULATION_5) && new HS_Randomizer().randFixed() <= 0.13) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_6) && new HS_Randomizer().randFixed() <= 0.19) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()), 
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_7) && new HS_Randomizer().randFixed() <= 0.28) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()),                             
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_8) && new HS_Randomizer().randFixed() <= 0.42) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()),                             
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_9) && new HS_Randomizer().randFixed() <= 0.63) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()),                             
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else if(markets.hasCondition(Conditions.POPULATION_10) && new HS_Randomizer().randFixed() <= 0.95) {
            markets.addIndustry(Industries.HEAVYBATTERIES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()),                             
                            new BASE_ITEMS().randAICores(faction.getId()))));
        } else {
            markets.addIndustry(Industries.GROUNDDEFENSES, 
                    new ArrayList<>(Arrays.asList(
                            new BASE_ITEMS().randDefDrones(faction.getId()),                             
                            new BASE_ITEMS().randAICores(faction.getId()))));
        }
        
        // Military Base
        if(!markets.hasIndustry(Industries.HIGHCOMMAND) || !planet.getMarket().hasIndustry(Industries.MILITARYBASE) ||
                !markets.hasIndustry(Industries.PATROLHQ)) {
            if(markets.hasCondition(Conditions.POPULATION_5) && new HS_Randomizer().randFixed() <= 0.2) {
                if(new HS_Randomizer().randFixed() <= 0.7) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(markets.hasCondition(Conditions.POPULATION_6) && new HS_Randomizer().randFixed() <= 0.3) {
                if(new HS_Randomizer().randFixed() <= 0.6) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(markets.hasCondition(Conditions.POPULATION_7) && new HS_Randomizer().randFixed() <= 0.4) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(markets.hasCondition(Conditions.POPULATION_8) && new HS_Randomizer().randFixed() <= 0.5) {
                if(new HS_Randomizer().randFixed() <= 0.3) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(markets.hasCondition(Conditions.POPULATION_9) && new HS_Randomizer().randFixed() <= 0.6) {
                if(new HS_Randomizer().randFixed() <= 0.275) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(markets.hasCondition(Conditions.POPULATION_10) && new HS_Randomizer().randFixed() <= 0.7) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    markets.addIndustry(Industries.MILITARYBASE, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else {
                markets.addIndustry(Industries.PATROLHQ, 
                        new ArrayList<>(Arrays.asList(
                                new BASE_ITEMS().randMilHot(faction.getId(), markets),                             
                                new BASE_ITEMS().randAICores(faction.getId()))));
            }
        }
    }
    
    // Aquaculture
    public void aquaCulture(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(market.hasCondition(Conditions.WATER) || market.hasCondition(Conditions.WATER_SURFACE) ||
                planet.hasCondition(Conditions.WATER) || planet.hasCondition(Conditions.WATER_SURFACE) ||
                planet.getTypeId().contains("OCEAN")) {
            if(!market.hasIndustry(Industries.AQUACULTURE) || !planet.getMarket().hasIndustry(Industries.AQUACULTURE)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.AQUACULTURE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }         
        }
    }
    
    // Farming
    public void farmInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.FARMING) || !planet.getMarket().hasIndustry(Industries.FARMING)) {
            if(market.hasCondition(Conditions.FARMLAND_RICH) || market.hasCondition(Conditions.FARMLAND_BOUNTIFUL)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.FARMING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFarmItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.FARMLAND_ADEQUATE)) {
                if(new HS_Randomizer().randFixed() <= 0.75 && new HS_Randomizer().randFixed() > 0.25) {
                    market.addIndustry(Industries.FARMING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFarmItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.FARMLAND_POOR)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.FARMING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFarmItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Mining
    public void mineInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.MINING) || !planet.getMarket().hasIndustry(Industries.MINING)) {

            // Metals
            if(planet.getMarket().hasCondition(Conditions.ORE_RICH) || planet.getMarket().hasCondition(Conditions.ORE_ULTRARICH) ||
                    market.hasCondition(Conditions.ORE_RICH) || market.hasCondition(Conditions.ORE_ULTRARICH) ||
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_RICH) || planet.getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH) ||
                    market.hasCondition(Conditions.RARE_ORE_RICH) || market.hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_ABUNDANT) || market.hasCondition(Conditions.ORE_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) || market.hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
                if(new HS_Randomizer().randFixed() <= 0.45) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_MODERATE) || market.hasCondition(Conditions.ORE_MODERATE) || 
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) || market.hasCondition(Conditions.RARE_ORE_MODERATE)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_SPARSE) || market.hasCondition(Conditions.ORE_SPARSE)) {
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }

            // Rare Metals
            else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH) ||
                    market.hasCondition(Conditions.RARE_ORE_RICH) || market.hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) || 
                    market.hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
                if(new HS_Randomizer().randFixed() <= 0.45) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) || 
                    market.hasCondition(Conditions.RARE_ORE_MODERATE)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) || 
                    market.hasCondition(Conditions.RARE_ORE_SPARSE)) {
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } 

            // Organics
            else if(planet.getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL) ||
                    market.hasCondition(Conditions.ORGANICS_ABUNDANT) || market.hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_COMMON) || 
                    market.hasCondition(Conditions.ORGANICS_COMMON)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } 
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_TRACE) || 
                    market.hasCondition(Conditions.ORGANICS_TRACE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randOreItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }

            // Volatiles
            else if(planet.getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    market.hasCondition(Conditions.VOLATILES_ABUNDANT) || market.hasCondition(Conditions.VOLATILES_PLENTIFUL)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randVolItem(faction.getId(), planet),                             
                                    new BASE_ITEMS().randAICores(faction.getId())))); 
                }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) || 
                    market.hasCondition(Conditions.VOLATILES_DIFFUSE)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randVolItem(faction.getId(), planet),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_TRACE) || 
                    market.hasCondition(Conditions.VOLATILES_TRACE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.MINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randVolItem(faction.getId(), planet),                             
                                    new BASE_ITEMS().randAICores(faction.getId())))); 
                }
            }
        }
    }
    
    // Techmining
    public void techMining(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.TECHMINING) || !planet.getMarket().hasIndustry(Industries.TECHMINING)) {
            if(planet.getMarket().hasCondition(Conditions.RUINS_VAST) || 
                    planet.getMarket().hasCondition(Conditions.RUINS_EXTENSIVE)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.TECHMINING, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(new HS_Randomizer().randFixed() <=  0.5) {
                    market.addIndustry(Industries.TECHMINING, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RUINS_SCATTERED)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.TECHMINING, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Light Industry
    public void lightInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.LIGHTINDUSTRY) || !planet.getMarket().hasIndustry(Industries.LIGHTINDUSTRY)) {

            // If organics are present
            if(planet.getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.LIGHTINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randLightBio(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_COMMON)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(Industries.LIGHTINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randLightBio(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORGANICS_TRACE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.LIGHTINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randLightBio(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }

            // If organics are not present
            } else {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(Industries.LIGHTINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randLightBio(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Refining
    public void refInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.REFINING) || !planet.getMarket().hasIndustry(Industries.REFINING)) {

            // If ores are present
            if(planet.getMarket().hasCondition(Conditions.ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_ABUNDANT)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_MODERATE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.ORE_SPARSE)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }

            // If rare metals are present
            else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_RICH) || 
                    planet.getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_MODERATE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.RARE_ORE_SPARSE)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
                
            // No ores in the planet whatsoever
            } else {
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    market.addIndustry(Industries.REFINING, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randRefItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Heavy Industry
    public void heavyInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.HEAVYINDUSTRY) || !planet.getMarket().hasIndustry(Industries.HEAVYINDUSTRY) || 
                !market.hasIndustry(Industries.ORBITALWORKS) || !planet.getMarket().hasIndustry(Industries.ORBITALWORKS)) {

            // If there is a refining industry on the planet
            if(market.hasIndustry(Industries.REFINING) || planet.getMarket().hasIndustry(Industries.REFINING)) {
                if(new HS_Randomizer().randFixed() <= 0.4) {
                    market.addIndustry(Industries.HEAVYINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randHeavyItem(faction.getId()),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.6) {
                    market.addIndustry(Industries.ORBITALWORKS, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randHeavyItem(faction.getId()),                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }

            // If there is no refining industry on the planet
            } else {
                if(new HS_Randomizer().randFixed() <= 0.6) {
                    market.addIndustry(Industries.HEAVYINDUSTRY, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randHeavyItem(faction.getId()),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.8) {
                    market.addIndustry(Industries.ORBITALWORKS, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randHeavyItem(faction.getId()),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Fuel Production
    public void fuelInd(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.FUELPROD) || !planet.getMarket().hasIndustry(Industries.FUELPROD)) {

            if(planet.getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) || 
                    planet.getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) {
                        market.addIndustry(Industries.FUELPROD, 
                                new ArrayList<>(Arrays.asList(
                                        new BASE_ITEMS().randFuelItem(faction.getId(), market),                             
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE)) {
                if(new HS_Randomizer().randFixed() <= 0.75 && 
                        new HS_Randomizer().randFixed() > 0.25) {
                    market.addIndustry(Industries.FUELPROD, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFuelItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(planet.getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(Industries.FUELPROD, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFuelItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(Industries.FUELPROD, 
                            new ArrayList<>(Arrays.asList(
                                    new BASE_ITEMS().randFuelItem(faction.getId(), market),                             
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Cryosanctum
    public void cryoSanctum(MarketAPI market, PlanetAPI planet, FactionAPI faction) {
        if(!market.hasIndustry(Industries.CRYOSANCTUM) || !planet.getMarket().hasIndustry(Industries.CRYOSANCTUM)) {
            if(market.hasCondition(Conditions.POPULATION_5) || planet.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.01) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasCondition(Conditions.POPULATION_6) || planet.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.025) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasCondition(Conditions.POPULATION_7) || planet.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasCondition(Conditions.POPULATION_8) || planet.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.075) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasCondition(Conditions.POPULATION_9) || planet.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasCondition(Conditions.POPULATION_10) || planet.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.125) {
                    market.addIndustry(Industries.CRYOSANCTUM, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }
    
    // Orbital Station
    public void planetStation(MarketAPI market, FactionAPI faction) {
        if(faction != null) {
            switch (faction.getId()) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.17) {
                        market.addIndustry(Industries.ORBITALSTATION_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.17 
                            && new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(Industries.BATTLESTATION_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.5 
                            && new HS_Randomizer().randFixed() <= 0.9) {
                        market.addIndustry(Industries.STARFORTRESS_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.17) {
                        market.addIndustry(Industries.ORBITALSTATION, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.17 
                            && new HS_Randomizer().randFixed() <= 0.33) {
                        market.addIndustry(Industries.BATTLESTATION, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.33  
                            && new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(Industries.STARFORTRESS, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                    break;
                case "HS_Corporation_Separatist":
                    if(new HS_Randomizer().randFixed() <= 0.25) {
                        market.addIndustry(Industries.ORBITALSTATION_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.25 
                            && new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(Industries.BATTLESTATION_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    } else if(new HS_Randomizer().randFixed() > 0.5 
                            && new HS_Randomizer().randFixed() <= 0.75) {
                        market.addIndustry(Industries.STARFORTRESS_HIGH, 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                    break;
                default:
                    if(randStation() != null) {
                        market.addIndustry(randStation(), 
                        new ArrayList<>(Arrays.asList(                            
                                new BASE_ITEMS().randAICores(faction.getId()))));
                    }
            }
        }
    }
   
    // Randomizes station type (Incl. LOW/MID/HIGH tech)
    public String randStation() {
        if(new HS_Randomizer().randFixed() <= 0.25) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                return Industries.ORBITALSTATION;
            } else if(new HS_Randomizer().randFixed() > 0.33 
                    && new HS_Randomizer().randFixed() <= 0.66) {
                return Industries.BATTLESTATION;
            } else {
                return Industries.STARFORTRESS;
            }
        } else if(new HS_Randomizer().randFixed() > 0.25 
                && new HS_Randomizer().randFixed() <= 0.5) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                return Industries.ORBITALSTATION_MID;
            } else if(new HS_Randomizer().randFixed() > 0.33 
                    && new HS_Randomizer().randFixed() <= 0.66) {
                return Industries.BATTLESTATION_MID;
            } else {
                return Industries.STARFORTRESS_MID;
            }
        } else if(new HS_Randomizer().randFixed() > 0.5 
                && new HS_Randomizer().randFixed() <= 0.75) {
            if(new HS_Randomizer().randFixed() <= 0.33) {
                return Industries.ORBITALSTATION_HIGH;
            } else if(new HS_Randomizer().randFixed() > 0.33 
                    && new HS_Randomizer().randFixed() <= 0.66) {
                return Industries.BATTLESTATION_HIGH;
            } else {
                return Industries.STARFORTRESS_HIGH;
            }
        } else {
            return null;
        }
    }
}
