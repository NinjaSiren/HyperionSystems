/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddStation {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    // Generates a planetary station if the planet's population is at 7 or above
    public HS_AddStation(StarSystemAPI system, PlanetAPI star, PlanetAPI planet) {
        if(planet.getMarket().hasCondition(Conditions.POPULATION_7) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_8) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_9) ||
                planet.getMarket().hasCondition(Conditions.POPULATION_10)) {
            if(rand(0, 4) > 1) {
                SectorEntityToken station = system.addCustomEntity(
                        planet.getName() + "_station",
                        planet.getName() + " Station",
                        "station_hightech" + rand(1, 3),
                        planet.getMarket().getFactionId());
                station.setCircularOrbitPointingDown(planet, rand(30, 45), rand(256, 512), rand(30, 45));
                station.setInteractionImage("illustrations", "orbital");
            }
        } 
    }
}
