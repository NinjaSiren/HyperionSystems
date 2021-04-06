package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
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
    
    // Roll the dice for planet tariff
    private float tariff() {
        Random rand = new Random();
        final float max = 0.5f;
        final float min = 0.0f;
        return min + rand.nextFloat() * (max - min);
    }
    
    // Adds the factions and its markets on the selected planet
    public HS_AddFactions(SectorEntityToken station, PlanetAPI planet, String factionA, String factionB, 
            MarketAPI markets, int counter, List <MarketConditionAPI> condition_list, double factions) {
        String faction;
        int marketPopulation;    

        // Randomizes market factions
        if(rand() <= factions) {
            faction = factionA;
        } else {
            faction = factionB;
        }
        
        // Randomizes market population, from 3 to 7
        if(rand() <= 0.2) {
            marketPopulation = 3;
        } else if(rand() > 0.2 && rand() <= 0.4) {
            marketPopulation = 4;
        } else if(rand() > 0.4 && rand() <= 0.6) {
            marketPopulation = 5;
        } else if(rand() > 0.6 && rand() <= 0.8) {
            marketPopulation = 6;
        } else {
            marketPopulation = 7;
        }
        
        // Set planet's market
        if(station == null) {
            markets = HS_AddMarketplaceAuto.addMarketplace(
                        faction,
                        planet,
                        null,
                        planet.getName(), // name of the market
                        marketPopulation, // size of the market (from the JSON)
                        null,
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
        } else {
            markets = HS_AddMarketplaceAuto.addMarketplace(
                    faction, // Faction ID
                    planet, // Planet 
                    new ArrayList<>(Arrays.asList(station)), // SectorEntityToken station
                    planet.getName(), // name of the market
                    marketPopulation, // size of the market (from the JSON)
                    null,
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
        }

        // Randomizes market population
        switch (marketPopulation) {
            case 3:
                markets.addCondition(Conditions.POPULATION_3);
                break;
            case 4:
                markets.addCondition(Conditions.POPULATION_4);
                break;
            case 5:
                markets.addCondition(Conditions.POPULATION_5);
                break;
            case 6:
                markets.addCondition(Conditions.POPULATION_6);
                break;
            default:
                markets.addCondition(Conditions.POPULATION_7);
                break;
        }

        // Randomizes market spaceport
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.SPACEPORT);
        } else {
            markets.addIndustry(Industries.MEGAPORT);
        }

        // Randomizes market defences
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.GROUNDDEFENSES);
        } else {
            markets.addIndustry(Industries.HEAVYBATTERIES);
        }                

        // Randomizes market military presence
        if(rand() <= 0.4) {
            markets.addIndustry(Industries.PATROLHQ);
        } else if(rand() > 0.4 && rand() <= 0.8) {
            markets.addIndustry(Industries.MILITARYBASE);
        } else {
            markets.addIndustry(Industries.HIGHCOMMAND);
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
        if(rand() <= 0.025) {
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

        // Re-adds Planet's conditions
        new HS_ReAddConditions(condition_list, markets); 

        // Adds industries depending on conditions
        new HS_AddIndustry(planet, markets);
                    
    }
}
