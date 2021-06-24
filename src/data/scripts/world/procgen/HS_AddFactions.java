package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddFactions {
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Roll the dice
    private int rand2(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    // Roll the dice for planet tariff
    private float tariff() {
        Random rand = new Random();
        final float max = 0.5f;
        final float min = 0.0f;
        return min + rand.nextFloat() * (max - min);
    }
    
    private String popAmount(int marketPopulation) {
        // Randomizes market population
        switch (marketPopulation) {
            case 3:
                return Conditions.POPULATION_3;
            case 4:
                return Conditions.POPULATION_4;
            case 5:
                return Conditions.POPULATION_5;
            case 6:
                return Conditions.POPULATION_6;
            case 7:
                return Conditions.POPULATION_7;
            case 8:
                return Conditions.POPULATION_8;
            case 9:
                return Conditions.POPULATION_9;
            default:
                return Conditions.POPULATION_10;
        }
    }
    
    // Adds the factions and its markets on the selected planet
    public void generateNow(PlanetAPI planet, String factionA, String factionB, MarketAPI markets, 
            int counter, List<MarketConditionAPI> condition_list, double factions, StarSystemAPI system) {
        String faction;
        int marketPopulation = rand2(3, 10); // Randomizes market population, from 3 to 10

        // Randomizes market factions
        if(rand() <= factions) {
            faction = factionA;
        } else {
            faction = factionB;
        }

        markets = HS_AddMarketplace.addMarketplace(
                    faction,
                    planet,
                    null,
                    planet.getName(), // name of the market
                    marketPopulation, // size of the market
                    new ArrayList<>
                            (Arrays.asList( // list of conditions
                                    popAmount(marketPopulation))),
                    new ArrayList<>
                            (Arrays.asList( // list of industries
                                    Industries.WAYSTATION,
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    tariff()); // tariff amount
        
        // Randomizes market spaceport
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.SPACEPORT);
        } else {
            if(markets.hasCondition(Conditions.POPULATION_7) && rand() <= 0.125) {
                markets.addIndustry(Industries.MEGAPORT);
            } else if(markets.hasCondition(Conditions.POPULATION_8) && rand() <= 0.25) {
                markets.addIndustry(Industries.MEGAPORT);
            } else if(markets.hasCondition(Conditions.POPULATION_9) && rand() <= 0.375) {
                markets.addIndustry(Industries.MEGAPORT);
            } else if(markets.hasCondition(Conditions.POPULATION_10) && rand() <= 0.5) {
                markets.addIndustry(Industries.MEGAPORT);
            } else {
                markets.addIndustry(Industries.SPACEPORT);
            }
        }

        // Randomizes market defences
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.GROUNDDEFENSES);
        } else {
            if(markets.hasCondition(Conditions.POPULATION_7) && rand() <= 0.2) {
                markets.addIndustry(Industries.HEAVYBATTERIES);
            } else if(markets.hasCondition(Conditions.POPULATION_8) && rand() <= 0.3) {
                markets.addIndustry(Industries.HEAVYBATTERIES);
            } else if(markets.hasCondition(Conditions.POPULATION_9) && rand() <= 0.4) {
                markets.addIndustry(Industries.HEAVYBATTERIES);
            } else if(markets.hasCondition(Conditions.POPULATION_10) && rand() <= 0.5) {
                markets.addIndustry(Industries.HEAVYBATTERIES);
            } else {
                markets.addIndustry(Industries.GROUNDDEFENSES);
            }
        }                

        // Randomizes market military presence
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.PATROLHQ);
        } else {
            if(markets.hasCondition(Conditions.POPULATION_7) && rand() <= 0.2) {
                if(rand() <= 0.7) {
                    markets.addIndustry(Industries.MILITARYBASE);
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND);
                }
            } else if(markets.hasCondition(Conditions.POPULATION_8) && rand() <= 0.3) {
                if(rand() <= 0.6) {
                    markets.addIndustry(Industries.MILITARYBASE);
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND);
                }
            } else if(markets.hasCondition(Conditions.POPULATION_9) && rand() <= 0.4) {
                if(rand() <= 0.5) {
                    markets.addIndustry(Industries.MILITARYBASE);
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND);
                }
            } else if(markets.hasCondition(Conditions.POPULATION_10) && rand() <= 0.5) {
                if(rand() <= 0.3) {
                    markets.addIndustry(Industries.MILITARYBASE);
                } else {
                    markets.addIndustry(Industries.HIGHCOMMAND);
                }
            } else {
                markets.addIndustry(Industries.GROUNDDEFENSES);
            }
        }  
        
        // Adds military submarket whenever a planet has a military presense in it
        if(markets.hasIndustry(Industries.MILITARYBASE) || 
                markets.hasIndustry(Industries.HIGHCOMMAND)) {
            markets.addSubmarket(Submarkets.GENERIC_MILITARY);
        }

        // Adds decivilized sub-population in a small percentage of planets
        if(rand() <= 0.1) {
            markets.addCondition(Conditions.DECIVILIZED_SUBPOP);
        }

        // Adds stealth minefields in a small percentage of planets
        if(rand() <= 0.001) {
            markets.addCondition(Conditions.STEALTH_MINEFIELDS);
        }

        // Adds additional market condition depending on faction ownwing the market
        switch (faction) {
            case Factions.PIRATES:
                if(rand() <= 0.3) markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(rand() <= 0.01875) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(rand() > 0.01875 && rand() <= 0.0375) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(rand() > 0.0375 && rand() <= 0.05625) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.LUDDIC_PATH:
                if(rand() <= 0.25) markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(rand() <= 0.5) markets.addCondition(Conditions.LUDDIC_MAJORITY);
                if(rand() <= 0.01875) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(rand() > 0.0375 && rand() <= 0.05625) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.LUDDIC_CHURCH:
                if(rand() <= 0.075) markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(rand() <= 0.1) markets.addCondition(Conditions.LUDDIC_MAJORITY);
                if(rand() <= 0.05) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(rand() > 0.05 && rand() <= 0.1) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(rand() > 0.1 && rand() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.TRITACHYON:
                if(rand() <= 0.075) markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(rand() <= 0.025) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(rand() > 0.025 && rand() <= 0.125) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(rand() > 0.125 && rand() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            default:
                if(rand() <= 0.075) markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(rand() <= 0.05) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(rand() > 0.05 && rand() <= 0.1) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(rand() > 0.1 && rand() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
        } 
        
        // Re-adds current planet conditions
        new HS_ReAddConditions(condition_list, markets);
        
        // Adds industries depending on conditions
        new HS_AddIndustry(planet, markets, markets.getFaction(), system);
                    
    }
}
