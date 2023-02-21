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
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class HS_AutoGenerateFactions {
    
    // Autogenerate planets with/without factions and stations
    public void generateFactions(StarSystemAPI system, PlanetAPI star, String factionA, 
            String factionB, double factions, boolean enableFactions, boolean enableStations, 
            boolean enableAbandonedStation) {
        
        int systemSize;
        if(!system.getPlanets().isEmpty()) {
            systemSize = system.getPlanets().size();
            
            // Adds factions, if habitable or hazards are less than 150%
            PlanetAPI planet;
            MarketAPI planetMarket;
            float planetHazardValue;
            List<MarketConditionAPI> marketCondition;
            int counter = systemSize - 1;
            
            do {
                if(system.getPlanets().get(counter) != null) {
                    planet = system.getPlanets().get(counter);
                    
                    if(system.getPlanets().get(counter).getTypeId() != null) {
                        system.getPlanets().get(counter).getTypeId();
                        
                        if(planet.getMarket() != null) {
                            planetMarket = planet.getMarket();

                            if(planetMarket.getConditions() != null) {
                                marketCondition = planetMarket.getConditions();

                                if(planetMarket.getHazardValue() != 0){
                                    planetHazardValue = planetMarket.getHazardValue();

                                    // Adds factions, if habitable or hazards are less than 200%
                                    if(enableFactions) {
                                        if(planetMarket.hasCondition(Conditions.HABITABLE)) {
                                            new HS_AddFactions().generateNow(
                                                    planet, // PlanetAPI array
                                                    factionA, // Faction A, to be generated
                                                    factionB, // Faction B, to be generated
                                                    planetMarket, // MarketAPI array
                                                    systemSize, // System planet count
                                                    marketCondition, // Planet condition list
                                                    factions, // The percentage of factionA appearing vs factionB on the system
                                                    system);
                                            
                                        } else if (planetHazardValue <= 150 && 
                                                new HS_Randomizer().randFixed() <= 0.05) {
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
                                                new HS_Randomizer().randFixed() <= 0.005) {
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
                counter = counter - 1;
            } while(counter >= 0);
            
            // Adds an abandoned station
            if(enableAbandonedStation) {
                new HS_AbandonedStation(system, 
                        star, 
                        system.getPlanets().get(new HS_Randomizer().intRand(0, systemSize - 1)));
            }
        }
    }
}  
