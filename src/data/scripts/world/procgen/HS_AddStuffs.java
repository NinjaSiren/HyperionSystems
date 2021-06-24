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
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddStuffs {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    public void generateJumpPoints(StarSystemAPI system, PlanetAPI star) {
        for(int counter = system.getPlanets().size() - 1; counter >= 0; counter--) {
            
            PlanetAPI planet = system.getPlanets().get(counter);
            MarketAPI planet_market = planet.getMarket();
            String planet_name = planet.getName();
            
            float planet_or = planet.getCircularOrbitRadius()
                    + rand(30, 45);
            float planet_oa = planet.getCircularOrbitAngle()
                    + rand(-250, 250);
            float planet_op = planet.getCircularOrbitPeriod();
            
            if(planet_market != null && !planet.isMoon() && planet_market.hasSpaceport()) {
                
                JumpPointAPI jump_point = Global.getFactory().createJumpPoint(
                "hs_" + planet_name + "_jp", planet_name + " Jump Point");
                
                OrbitAPI orbit = Global.getFactory().createCircularOrbit(
                        star, planet_oa, planet_or, planet_op);
                
                jump_point.setOrbit(orbit);
                jump_point.setStandardWormholeToHyperspaceVisual();
                system.addEntity(jump_point);
            }
        }
    }
    
    public void generateStuffs(StarSystemAPI system, PlanetAPI star, String faction) {
        
        String star_name = star.getName();
        String system_name = system.getName();
        
        float[] orbit_r = new float[system.getPlanets().size()];
        float[] orbit_a = new float[system.getPlanets().size()];
        float[] orbit_p = new float[system.getPlanets().size()];
        
        for(int counter = system.getPlanets().size() - 1; counter >= 0; counter--) {
            if(system.getPlanets().get(counter).getOrbitFocus() == star ||
                    !system.getPlanets().get(counter).isMoon() &&
                    !system.getPlanets().get(counter).isStar()) {
                orbit_r[counter] = system.getPlanets().get(counter).getCircularOrbitRadius();
                orbit_p[counter] = system.getPlanets().get(counter).getCircularOrbitPeriod();
                orbit_a[counter] = system.getPlanets().get(counter).getCircularOrbitAngle();
            }
        }
        
        int max = system.getPlanets().size() - 1;
        
        //Nav Buoy
        int random = rand(0, max);
        SectorEntityToken nav_buoy = system.addCustomEntity(
                star_name + "_navbuoy",
                system_name + " Nav Buoy",
                Entities.NAV_BUOY,
                faction);
        nav_buoy.setCircularOrbit(
                star, 
                orbit_r[random], 
                orbit_a[random], 
                orbit_p[random]);

        //Comm Relay
        random = rand(0, max);
        SectorEntityToken comm_relay = system.addCustomEntity(
                star_name + "_commrelay",
                system_name + " Relay",
                Entities.COMM_RELAY,
                faction);
        comm_relay.setCircularOrbit(
                star, 
                orbit_r[random], 
                orbit_a[random], 
                orbit_p[random]);
        
        //Sensor Array
        random = rand(0, max);
        SectorEntityToken sensor_array = system.addCustomEntity(
                star_name + "_sensorarray",
                system_name + " Sensor Array",
                Entities.SENSOR_ARRAY,
                faction);
        sensor_array.setCircularOrbit(
                star, 
                orbit_r[random], 
                orbit_a[random], 
                orbit_p[random]);
    }
}
