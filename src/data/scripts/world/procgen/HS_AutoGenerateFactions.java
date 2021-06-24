/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import data.scripts.world.procgen.variables.PLANET_TYPES;
import java.util.List;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AutoGenerateFactions {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }   

    // Roll the dice
    private double randPercent() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Autogenerate planets with/without factions and stations
    public void generateFactions(StarSystemAPI system, PlanetAPI star, String factionA, 
            String factionB, double factions, boolean enableFactions, boolean enableStations, 
            boolean enableAbandonedStation) {
        
        int systemSize;
        if(!system.getPlanets().isEmpty()) {
            systemSize = system.getPlanets().size();
            
            // Adds factions, if habitable or hazards are less than 150%
            for(int counter = systemSize - 1; counter >= 0; counter--) {
                PlanetAPI planet;
                String planetType;
                MarketAPI planetMarket;
                float planetHazardValue;
                List<MarketConditionAPI> marketCondition;

                if(system.getPlanets().get(counter) != null) {
                    planet = system.getPlanets().get(counter);
                    
                    if(system.getPlanets().get(counter).getTypeId() != null) {
                        planetType = system.getPlanets().get(counter).getTypeId();
                        
                        if(planet.getMarket() != null) {
                            planetMarket = planet.getMarket();

                            if(planetMarket.getConditions() != null) {
                                marketCondition = planetMarket.getConditions();

                                if(planetMarket.getHazardValue() != 0){
                                    planetHazardValue = planetMarket.getHazardValue();

                                    // Adds factions, if habitable or hazards are less than 200%
                                    if(enableFactions) {
                                        if(planetType.equals(new PLANET_TYPES().ARID) ||
                                                planetType.equals(new PLANET_TYPES().DESERT_A) ||
                                                planetType.equals(new PLANET_TYPES().DESERT_B) ||
                                                planetType.equals(new PLANET_TYPES().JUNGLE) ||
                                                planetType.equals(new PLANET_TYPES().OCEAN) ||
                                                planetType.equals(new PLANET_TYPES().TERRAN) ||
                                                planetType.equals(new PLANET_TYPES().TERRAN_ECCENTRIC) ||
                                                planetType.equals(new PLANET_TYPES().TUNDRA) ||
                                                planetType.equals(new PLANET_TYPES().US_ALKALI) ||
                                                planetType.equals(new PLANET_TYPES().US_ARID) ||
                                                planetType.equals(new PLANET_TYPES().US_ARID_LIFELESS) ||
                                                planetType.equals(new PLANET_TYPES().US_AURIC) ||
                                                planetType.equals(new PLANET_TYPES().US_AURIC_CLOUDY) ||
                                                planetType.equals(new PLANET_TYPES().US_CRIMSON) ||
                                                planetType.equals(new PLANET_TYPES().US_DESERT_A) ||
                                                planetType.equals(new PLANET_TYPES().US_DESERT_B) ||
                                                planetType.equals(new PLANET_TYPES().US_DESERT_C) ||
                                                planetType.equals(new PLANET_TYPES().US_JUNGLE) ||
                                                planetType.equals(new PLANET_TYPES().US_LIFELESS) ||
                                                planetType.equals(new PLANET_TYPES().US_MAGNETIC) ||
                                                planetType.equals(new PLANET_TYPES().US_METHANE) ||
                                                planetType.equals(new PLANET_TYPES().US_OCEAN_A) ||
                                                planetType.equals(new PLANET_TYPES().US_OCEAN_B) ||
                                                planetType.equals(new PLANET_TYPES().US_RED) ||
                                                planetType.equals(new PLANET_TYPES().US_RED_WIND) ||
                                                planetType.equals(new PLANET_TYPES().US_STORM) ||
                                                planetType.equals(new PLANET_TYPES().US_TERRAN)) {
                                            new HS_AddFactions().generateNow(
                                                    planet, // PlanetAPI array
                                                    factionA, // Faction A, to be generated
                                                    factionB, // Faction B, to be generated
                                                    planetMarket, // MarketAPI array
                                                    systemSize, // System planet count
                                                    marketCondition, // Planet condition list
                                                    factions, // The percentage of factionA appearing vs factionB on the system
                                                    system);
                                            
                                        } else if (planetHazardValue <= 150 && randPercent() <= 0.1) {
                                            new HS_AddFactions().generateNow(
                                                    planet, // PlanetAPI array
                                                    factionA, // Faction A, to be generated
                                                    factionB, // Faction B, to be generated
                                                    planetMarket, // MarketAPI array
                                                    systemSize, // System planet count
                                                    marketCondition, // Planet condition list
                                                    factions, // The percentage of factionA appearing vs factionB on the system
                                                    system);
                                            
                                        } else if (planetHazardValue > 150 && planetHazardValue <= 175 && 
                                                randPercent() <= 0.01) {
                                            new HS_AddFactions().generateNow(
                                                    planet, // PlanetAPI array
                                                    factionA, // Faction A, to be generated
                                                    factionB, // Faction B, to be generated
                                                    planetMarket, // MarketAPI array
                                                    systemSize, // System planet count
                                                    marketCondition, // Planet condition list
                                                    factions, // The percentage of factionA appearing vs factionB on the system
                                                    system);
                                        }
                                    }
                                }
                            }

                            // Adds stations
                            if(enableStations) {
                                new HS_AddStation(system, star, planet); 
                            }
                        } 
                    }
                }
            }
            
            // Adds an abandoned station
            if(enableAbandonedStation) {
                new HS_AbandonedStation(system, 
                        star, 
                        system.getPlanets().get(rand(0, systemSize - 1)));
            }
        }
    }
}  
