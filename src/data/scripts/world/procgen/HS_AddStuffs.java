/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddStuffs {
    
    public void generateJumpPoints(StarSystemAPI system, PlanetAPI star) {
        for(int counter = system.getPlanets().size() - 1; counter >= 0; counter--) {
            
            PlanetAPI planet = system.getPlanets().get(counter);
            MarketAPI planet_market = planet.getMarket();
            String planet_name = planet.getName();
            
            float planet_or = planet.getCircularOrbitRadius()
                    + new HS_Randomizer().intRand(30, 45);
            float planet_oa = planet.getCircularOrbitAngle()
                    + new HS_Randomizer().intRand(-250, 250);
            float planet_op = planet.getCircularOrbitPeriod();
            
            if(planet_market.isInEconomy()) {
                
                JumpPointAPI jump_point = Global.getFactory().createJumpPoint(
                "hs_" + planet_name + "_jp", planet_name + " Jump Point");
                
                OrbitAPI orbit = Global.getFactory().createCircularOrbit(
                        star, planet_oa, planet_or, planet_op);
                
                jump_point.setOrbit(orbit);
                jump_point.setAutoCreateEntranceFromHyperspace(true);
                jump_point.setRelatedPlanet(planet);
                system.addEntity(jump_point);
            }
        }
    }
}
