/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddStation {
    
    // Station types
    private final String HIGHTECH = "station_hightech";
    private final String MIDLINE = "station_midline";
    private final String LOWTECH = "station_lowtech";
    
    private String stationRandomizer(int randomNumber) {
        switch (randomNumber) {
            default:
                return LOWTECH;
            case 1:
                return MIDLINE;
            case 2:
                return HIGHTECH;
            case 3:
                return LOWTECH;
        }
    }
    
    // Generates a planetary station if the planet's population is at 7 or above
    public HS_AddStation(StarSystemAPI system, PlanetAPI star, PlanetAPI planet) {
        
        int orbit_radius = (int) planet.getRadius();
        
        if(planet.getMarket().hasCondition(Conditions.POPULATION_7) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_8) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_9) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_10)) {
            if(new HS_Randomizer().intRand(0, 4) > 1) {
                SectorEntityToken station = system.addCustomEntity(
                        planet.getName() + "_station",
                        planet.getName() + " Station",
                        stationRandomizer(new HS_Randomizer().intRand(1, 3)) + 
                                new HS_Randomizer().intRand(1, 3),
                        planet.getMarket().getFactionId());
                station.setCircularOrbitPointingDown(planet, // Focus
                        new HS_Randomizer().intRand(30, 45), // Angle
                        new HS_Randomizer().intRand(orbit_radius + 128, 
                                orbit_radius + 256), // Orbit Radius
                        new HS_Randomizer().intRand(15, 30)); // Orbit Days
                station.setInteractionImage("illustrations", "orbital");
            }
        } 
    }
}
