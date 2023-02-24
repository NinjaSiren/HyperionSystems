package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import data.scripts.world.procgen.industries.baseGameInd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddFactions {
    
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
        int marketPopulation = new HS_Randomizer().intRand(3, 10); // Randomizes market population, from 3 to 10

        // Randomizes market factions
        if(new HS_Randomizer().randFixed() <= factions) {
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
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    new HS_MarketTariff().tariff()); // tariff amount
        
        // Adds Spaceport, Planetary Defences, and Military Bases
        new baseGameInd().initBaseInd(markets, markets.getFaction(), planet);
        
        // Adds military submarket whenever a planet has a military presense in it
        if(markets.hasIndustry(Industries.MILITARYBASE) || markets.hasIndustry(Industries.HIGHCOMMAND)) {
            markets.addSubmarket(Submarkets.GENERIC_MILITARY);
        }
        
        // Adds decivilized sub-population in a small percentage of planets
        if(new HS_Randomizer().randFixed() <= 0.1) {
            markets.addCondition(Conditions.DECIVILIZED_SUBPOP);
        }

        // Adds stealth minefields in a small percentage of planets
        if(new HS_Randomizer().randFixed() <= 0.001) {
            markets.addCondition(Conditions.STEALTH_MINEFIELDS);
        }

        // Adds additional market condition depending on faction ownwing the market
        switch (faction) {
            case Factions.PIRATES:
                if(new HS_Randomizer().randFixed() <= 0.3) 
                    markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(new HS_Randomizer().randFixed() <= 0.01875) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.01875 
                        && new HS_Randomizer().randFixed() <= 0.0375) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.0375 
                        && new HS_Randomizer().randFixed() <= 0.05625) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.LUDDIC_PATH:
                if(new HS_Randomizer().randFixed() <= 0.25) 
                    markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(new HS_Randomizer().randFixed() <= 0.5) 
                    markets.addCondition(Conditions.LUDDIC_MAJORITY);
                if(new HS_Randomizer().randFixed() <= 0.01875) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.0375 
                        && new HS_Randomizer().randFixed() <= 0.05625) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.LUDDIC_CHURCH:
                if(new HS_Randomizer().randFixed() <= 0.075) 
                    markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(new HS_Randomizer().randFixed() <= 0.1) 
                    markets.addCondition(Conditions.LUDDIC_MAJORITY);
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.05 
                        && new HS_Randomizer().randFixed() <= 0.1) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.1 
                        && new HS_Randomizer().randFixed() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            case Factions.TRITACHYON:
                if(new HS_Randomizer().randFixed() <= 0.075) 
                    markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(new HS_Randomizer().randFixed() <= 0.025) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.025 
                        && new HS_Randomizer().randFixed() <= 0.125) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.125 
                        && new HS_Randomizer().randFixed() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
            default:
                if(new HS_Randomizer().randFixed() <= 0.075) 
                    markets.addCondition(Conditions.ORGANIZED_CRIME);
                if(new HS_Randomizer().randFixed() <= 0.05) {
                    markets.addCondition(Conditions.RURAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.05 
                        && new HS_Randomizer().randFixed() <= 0.1) {
                    markets.addCondition(Conditions.INDUSTRIAL_POLITY);
                } else if(new HS_Randomizer().randFixed() > 0.1 
                        && new HS_Randomizer().randFixed() <= 0.15) {
                    markets.addCondition(Conditions.URBANIZED_POLITY);
                }   break;
        } 
        
        // Re-adds current planet conditions
        new HS_ReAddConditions(condition_list, markets);
        
        // Adds industries depending on conditions
        new HS_AddIndustry(planet, markets, markets.getFaction(), system);
    }
}
