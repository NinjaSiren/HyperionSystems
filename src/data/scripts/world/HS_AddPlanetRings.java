/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddPlanetRings {
    
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
    
    public HS_AddPlanetRings(StarSystemAPI system, PlanetAPI planet, PlanetAPI star) {
        int ringType = rand(0, 5);
        
        if(planet.getTypeId().contains("GIANT")) {
            if(planet.getRadius() <= 450 && rand(1, 10) >= 3) {
                switch (ringType) {
                    case 0:
                        debrisField(system, planet);
                        break;
                    case 1:
                        asteroidField(system, planet);
                        break;
                    case 2:
                        iceField(system, planet);
                        break;
                    case 3:
                        dustField(system, planet);
                        break;
                    case 4:
                        specialField(system, planet);
                        break;
                    default:
                        break;
                }
            } else {
                if(rand(1, 10) >= 8) {
                    switch (ringType) {
                        case 0:
                            debrisField(system, planet);
                            break;
                        case 1:
                            asteroidField(system, planet);
                            break;
                        case 2:
                            iceField(system, planet);
                            break;
                        case 3:
                            dustField(system, planet);
                            break;
                        case 4:
                            specialField(system, planet);
                            break;
                        default:
                            break;
                    }
                }
            }
            
            // Add Magnetic Effects
            if(star.getTypeId().contains("NEUTRON") ||
                    star.getTypeId().contains("HOLE")) {
                magneticField(system, planet, star);
            } else {
                if(planet.getCircularOrbitRadius() <= 2000) {
                    magneticField(system, planet, star);
                }
            }
            
        // For rocky planets
        } else if(planet.getRadius() >= 190) {
            if(rand(1, 10) >= 5) {
                switch (ringType) {
                    case 0:
                        debrisField(system, planet);
                        break;
                    case 1:
                        asteroidField(system, planet);
                        break;
                    case 2:
                        iceField(system, planet);
                        break;
                    case 3:
                        dustField(system, planet);
                        break;
                    case 4:
                        specialField(system, planet);
                        break;
                    default:
                        break;
                }
            }
            
            // Add Magnetic Effects
            if(star.getTypeId().contains("NEUTRON") ||
                    star.getTypeId().contains("HOLE")) {
                magneticField(system, planet, star);
            } else {
                if(planet.getCircularOrbitRadius() <= 1000) {
                    magneticField(system, planet, star);
                }
            }
        }
    }
    
    // Adds magnetic field
    private void magneticField(StarSystemAPI system, PlanetAPI planet, PlanetAPI star) {
        SectorEntityToken planet_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
				new MagneticFieldTerrainPlugin.MagneticFieldParams(
                                        planet.getRadius() + randFloat(100, 300), // terrain effect band width 
					(planet.getRadius() + randFloat(100, 300)) / 2f, // terrain effect middle radius
					planet, // entity that it's around
					planet.getRadius() + randFloat(25, 100), // visual band start
					planet.getRadius() + randFloat(25, 100) + randFloat(100, 300), // visual band end
					star.getSpec().getGlowColor(), // base color
					randFloat(0, 0.5f), // probability to spawn aurora sequence, checked once/day when no aurora in progress
					new Color(140, 100, 235),
					new Color(180, 110, 210),
					new Color(150, 140, 190),
					new Color(140, 190, 210),
					new Color(90, 200, 170), 
					new Color(65, 230, 160),
					new Color(20, 220, 70)));
	planet_field.setCircularOrbit(planet, 
                rand(0, 50), // Orbit Angle
                (planet.getRadius() * randFloat(1, 2)) + rand(16, 64), // Orbit Radius
                rand(25, 75)); // Orbit Days
    }
    
    /*
    *    Adds debris ring
    *       bandWidthInTexture -
    *       middleWidth -
    *       focus -
    *       customName -
    */
    private void debrisField(StarSystemAPI system, PlanetAPI planet) {
        // TODO: add orbital debris
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
    private void asteroidField(StarSystemAPI system, PlanetAPI planet) {
        system.addAsteroidBelt(planet, 
                rand(32, 128), 
                ((int) (planet.getRadius() * 2)) + rand(64, 256), 
                rand(5, 25), 
                25, 
                50, 
                Terrain.ASTEROID_BELT, 
                null);
    }
    
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
    private void iceField(StarSystemAPI system, PlanetAPI planet) {
        system.addRingBand(planet, 
                "misc", 
                "rings_ice0", 
                randFloat(128, 256), 
                rand(0, 10),
                Color.WHITE, 
                randFloat(128, 256), 
                (planet.getRadius() * randFloat(1, 2)) + rand(64, 256), 
                rand(25, 75), 
                Terrain.RING, 
                null);
    }
    
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
    private void dustField(StarSystemAPI system, PlanetAPI planet) {
        system.addRingBand(planet, 
                "misc", 
                "rings_dust0", 
                randFloat(128, 256), 
                rand(0, 10),
                Color.GRAY, 
                randFloat(128, 256), 
                (planet.getRadius() * randFloat(1, 2)) + rand(64, 256), 
                rand(25, 75),
                Terrain.RING, 
                null);
    }
    
    /*
    *    Adds special rings
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
    private void specialField(StarSystemAPI system, PlanetAPI planet) {
        system.addRingBand(planet, 
                "misc", 
                "rings_special0", 
                randFloat(128, 256), 
                rand(0, 10),
                Color.LIGHT_GRAY, 
                randFloat(128, 256), 
                (planet.getRadius() * randFloat(1, 2)) + rand(64, 256),  
                rand(25, 75),
                Terrain.RING, 
                null);
    }
}
