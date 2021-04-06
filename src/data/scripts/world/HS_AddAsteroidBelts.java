/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddAsteroidBelts {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    // Roll the dice
    private float randFloat(float min, float max) {
        Random rand = new Random();
        return min + rand.nextFloat() * (max - min);
    }
    
    /*
    *    Adds asteroid field
    *       focus -
    *       number of asteroids
    *       belt orbit radius
    *       belt orbit width
    *       minimum orbit days
    *       maxiumum orbit days
    *       terrainId -
    *       optionalName -
    */
    public void addBetweenPlanets(StarSystemAPI system, PlanetAPI star, PlanetAPI planetA, PlanetAPI planetB) {
        system.addAsteroidBelt(star, 
                rand(64, 128), 
                planetA.getCircularOrbitRadius() + 
                        ((planetB.getCircularOrbitRadius() - planetA.getCircularOrbitRadius()) / rand(2, 10)),  
                rand(64, 256), 
                planetA.getCircularOrbitPeriod(), 
                planetB.getCircularOrbitPeriod(), 
                Terrain.ASTEROID_BELT, 
                system.getName() + " Belt");
        
        /*
        *    Adds dust rings
        *       focus -
        *       category - graphics category in settings.json
        *       key - id within category
        *       bandWidthInTexture -
        *       bandIndex -
        *       color -
        *       bandWidthInEngine -
        *       middleRadius -
        *       orbitDays -
        *       terrainId -
        *       optionalName -
        */
        system.addRingBand(star, 
                "misc", 
                "rings_dust0", 
                randFloat(128, 256), 
                rand(0, 10),
                Color.LIGHT_GRAY, 
                randFloat(128, 512), 
                planetA.getCircularOrbitRadius() + 
                        ((planetB.getCircularOrbitRadius() - planetA.getCircularOrbitRadius()) / rand(2, 10)), 
                rand(25, 75), 
                Terrain.RING, 
                null);
    }
    
    /*
    *    Adds asteroid field
    *       focus -
    *       number of asteroids
    *       belt orbit radius
    *       belt orbit width
    *       minimum orbit days
    *       maxiumum orbit days
    *       terrainId -
    *       optionalName -
    */
    public void addBeyondSystem(StarSystemAPI system, PlanetAPI star, PlanetAPI planetA) {
        system.addAsteroidBelt(star, 
                rand(64, 128), 
                (planetA.getCircularOrbitRadius() * rand(2, 4)) / 2, 
                rand(64, 256), 
                planetA.getCircularOrbitPeriod(), 
                planetA.getCircularOrbitPeriod() * rand(2, 4), 
                Terrain.ASTEROID_BELT, 
                system.getName() + " Belt");
        
        /*
        *    Adds ice rings
        *       focus -
        *       category - graphics category in settings.json
        *       key - id within category
        *       bandWidthInTexture -
        *       bandIndex -
        *       color -
        *       bandWidthInEngine -
        *       middleRadius -
        *       orbitDays -
        *       terrainId -
        *       optionalName -
        */
        system.addRingBand(star, 
                "misc", 
                "rings_ice0", 
                randFloat(128, 256), 
                rand(0, 10),
                Color.WHITE,  
                randFloat(128, 512), 
                (planetA.getCircularOrbitRadius() * rand(2, 4)) / 2,
                rand(25, 75), 
                Terrain.RING, 
                null);
    }
}
