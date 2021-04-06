/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.world.HS_AbandonedStation;
import data.scripts.world.HS_AddAsteroidBelts;
import data.scripts.world.HS_AutoGeneratePlanets;
import data.scripts.world.HS_JumpZoneHabitables;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_Nirraok {
    
    // Roll the dice, system background
    private int rand_bg() {
        Random rand = new Random();
        final int max = 6;
        final int min = 1;
        return min + rand.nextInt(max - min + 1);
    }
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    public void generate(SectorAPI sector) {
        
        // Add star system
        StarSystemAPI system = sector.createStarSystem("Nirraok");
        system.getLocation().set(   24500, -3200);
        system.setBackgroundTextureFilename("graphics/backgrounds/background" + rand_bg() + ".jpg");
        ProcgenUsedNames.notifyUsed("Nirraok"); 
        
        // Add stars, Phia
        PlanetAPI nirraok = system.initStar(
                    "hs_nirraok", // unique id for this star
                    StarTypes.YELLOW,  // id in planets.json
                    1650,           // radius (in pixels at default zoom)
                    2450,            // corona radius, from star edge
                    2.5f,             // solar wind burn level
                    0.7f,           // flare probability
                    2.2f);          // CR loss multipiers
        
        nirraok.setCustomDescriptionId("hs_star_nirraok");
        nirraok.setName("Nirraok");   
        
        // Sets whole system lighting color (R, G, B)
        system.setLightColor(new Color(255, 210, 100));
        nirraok.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "banded"));
        nirraok.getSpec().setGlowColor(new Color(255, 200, 70, 128));
        nirraok.getSpec().setAtmosphereThickness(0.5f);
        nirraok.applySpecChanges(); 
        
        // Autogenerate planets
        int planetAmount = 9; // Amount of planets you want
        int starsAmount = 1; // The amount of stars you manually added
        PlanetAPI[] planets = new PlanetAPI[planetAmount]; // Initiates the Planet array      
        PlanetAPI[] stars = new PlanetAPI[starsAmount]; // Initiates the Star array
        stars[0] = nirraok; // Sets the final values of the Star array
        MarketAPI[] markets = new MarketAPI[planetAmount]; // Initiates the Market array       
        SectorEntityToken[] stations = new SectorEntityToken[planetAmount]; // Initiates the SectorEntityToken array
      
        // Automatically generates random planets in the system based on the values you added
        new HS_AutoGeneratePlanets(
                system,          // The system that will have the auto-generated planets
                nirraok,         // The star that the planets will orbit on
                null,            // Use this if the center is a the middle of both
                planets,         // The PlanetAPI array where the planets will be stored
                planetAmount,    // How many planets you want to auto-generate in the system
                stars,           // The PlanetAPI array where the stars are stored
                starsAmount,     // The amount of stars in your system
                nirraok.getRadius() * 2, // Starting orbit of the planets
                60, // Starting orbital period of the planets
                875,  // Minimum orbit distance between the auto-generated planets
                2250,  // Maximum orbit distance between the auto-generated planets
                0.2f,  // Minimum orbital period of the planets that will be added, min 0.0f max 1.0f
                0.7f,  // Maximum orbital period of the planets that will be added, max 1.0f
                true,            // If true, the system will auto-generate planets
                stations,            // SectorEntityToken array, where the nexrelin stations will be stored
                markets,         // MarketAPI array where all the planet markets will be stored
                "HS_Corporation_Separatist", // The first faction that will be auto-generated on the system
                Factions.REMNANTS,  // The second faction that will be auto-generated on the system
                60);  // The percentage of factionA appearing vs factionB on the system, min=0 max=100
        
        // Automatically generates asteroid belts and rings around the star
        int pCounter = 0;
        for(; planetAmount > 0; planetAmount--) {
            if(rand(1, 10) <= 5) {
                if(pCounter > planetAmount) {
                    new HS_AddAsteroidBelts().addBeyondSystem(system, nirraok, planets[pCounter]);
                } else {
                    new HS_AddAsteroidBelts().addBetweenPlanets(system, nirraok, 
                            planets[pCounter], planets[pCounter + 1]);
                }
            }
            pCounter++;
        }
        
        // Adds an abandoned station
        new HS_AbandonedStation(system, nirraok, planets[8]);
        
        // Jump points, Habitable
        JumpPointAPI[] hab_jp = new JumpPointAPI[planetAmount]; // JumpPointAPI array
        new HS_JumpZoneHabitables(
                hab_jp,         // The JumpPointAPI array where all the jump points are stored
                planetAmount,   // How many planets that the system has
                planets,        // The PlanetAPI array where the planets will be stored
                nirraok,           // The star that the jump points will orbit on
                system);        // The system that will have the auto-generated jump points
        
        // Jump point orbits
        float orbitJP1 = planets[1].getCircularOrbitRadius() + ((
                planets[2].getCircularOrbitRadius() - planets[1].getCircularOrbitRadius()) / 2);
        float orbitJP2 = planets[4].getCircularOrbitRadius() + ((
                planets[5].getCircularOrbitRadius() - planets[4].getCircularOrbitRadius()) / 2);
        
        // Jump points, inner
        JumpPointAPI inner_jp = Global.getFactory().createJumpPoint(
                "inner_jp_4", "Nirraok Inner Jump Point");
        OrbitAPI orbit_1 = Global.getFactory().createCircularOrbit(
                nirraok, 
                planets[2].getCircularOrbitAngle() * 1.5f, 
                orbitJP1, 
                planets[2].getCircularOrbitPeriod() * 1.5f);
        inner_jp.setOrbit(orbit_1);
        inner_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(inner_jp);
        
        // Jump points, outer
        JumpPointAPI outer_jp = Global.getFactory().createJumpPoint(
                "outer_jp_4", "Nirraok Inner Jump Point");
        OrbitAPI orbit_2 = Global.getFactory().createCircularOrbit(
                nirraok, 
                planets[4].getCircularOrbitAngle() * 1.5f, 
                orbitJP2, 
                planets[4].getCircularOrbitPeriod() * 1.5f);
        outer_jp.setOrbit(orbit_2);
        outer_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(outer_jp);
        
        // Autogenerated jump points
        system.autogenerateHyperspaceJumpPoints(true, true);
                
        // Specials orbits
        float orbitNB = planets[1].getCircularOrbitRadius() - ((
                planets[2].getCircularOrbitRadius() - planets[1].getCircularOrbitRadius()) / rand(2, 10));
        float orbitCR = planets[3].getCircularOrbitRadius() - ((
                planets[4].getCircularOrbitRadius() - planets[3].getCircularOrbitRadius()) / rand(2, 10));
        float orbitSA = planets[5].getCircularOrbitRadius() - ((
                planets[6].getCircularOrbitRadius() - planets[5].getCircularOrbitRadius()) / rand(2, 10));
        
        //Nav Buoy
        SectorEntityToken nav_buoy = system.addCustomEntity(
                "nirraok_navbuoy",
                "Nirraok Nav Buoy",
                Entities.NAV_BUOY,
                "HS_Corporation_Separatist");
        nav_buoy.setCircularOrbit(
                nirraok, 
                planets[1].getCircularOrbitAngle() * 1.5f, 
                orbitNB, 
                planets[1].getCircularOrbitPeriod() * 1.5f);

        //Comm Relay
        SectorEntityToken comm_relay = system.addCustomEntity(
                "nirraok_commrelay",
                "Nirraok Relay",
                Entities.COMM_RELAY,
                "HS_Corporation_Separatist");
        comm_relay.setCircularOrbit(
                nirraok, 
                planets[4].getCircularOrbitAngle() * 1.5f, 
                orbitCR, 
                planets[4].getCircularOrbitPeriod() * 1.5f);

        //Sensor Array
        SectorEntityToken sensor_array = system.addCustomEntity(
                "nirraok_sensorarray",
                "Nirraok Sensor Array",
                Entities.SENSOR_ARRAY,
                "HS_Corporation_Separatist");
        sensor_array.setCircularOrbit(
                nirraok, 
                planets[6].getCircularOrbitAngle() * 1.5f, 
                orbitSA, 
                planets[6].getCircularOrbitPeriod() * 1.5f);

        // Cleaning the hyperspace
        HyperspaceTerrainPlugin plugin =
                (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0,
                radius + minRadius * 0.5f, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0,
                radius + minRadius, 0, 360f, 0.25f);
        
        // Other system automation stuff
        system.setStar(nirraok);
        system.setAge(StarAge.ANY);
        system.setHasSystemwideNebula(true);
    }
}
