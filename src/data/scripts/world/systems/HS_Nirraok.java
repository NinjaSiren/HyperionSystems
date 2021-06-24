/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.world.procgen.HS_AddStuffs;
import data.scripts.world.procgen.HS_AutoGenerateFactions;
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
        system.getLocation().set(24500, -3200);
        system.setAge(StarAge.ANY);
        system.setBackgroundTextureFilename("graphics/backgrounds/background" + rand_bg() + ".jpg");
        ProcgenUsedNames.notifyUsed("Nirraok"); 
        
        // Initialize star variables
        int starSize = rand(800, 900);
        
        // Add stars, Phia
        PlanetAPI nirraok = system.initStar(
                    "hs_nirraok", // unique id for this star
                    StarTypes.YELLOW,  // id in planets.json
                    starSize,           // radius (in pixels at default zoom)
                    starSize * 1.5f,            // corona radius, from star edge
                    2.5f,             // solar wind burn level
                    0.7f,           // flare probability
                    2.2f);          // CR loss multipiers
        
        nirraok.setCustomDescriptionId("hs_star_nirraok");
        nirraok.setName("Nirraok");   
            
        // Other system automation stuff
        system.setStar(nirraok);
        system.setHasSystemwideNebula(true);       
        
        // Sets whole system lighting color (R, G, B)
        system.setLightColor(new Color(255,255,255,255));
        nirraok.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "banded"));
        nirraok.getSpec().setGlowColor(new Color(255, 200, 70, 128));
        nirraok.getSpec().setAtmosphereThickness(0.5f);
        nirraok.applySpecChanges(); 

        // Randomly generates planets
        StarSystemGenerator.addOrbitingEntities(
                system, // System
                nirraok, // Star
                system.getAge(), // Sets the potential entities added depending on system age
                10, rand(10, 20), // Min-Max entities to add
                nirraok.getRadius() * 2, // Radius to start at
                1, // Naming offset
                true, // Custom or system based names
                true); // Should habitables appear
        
        // Automatically generates random factions in the system based on the values you added
        new HS_AutoGenerateFactions().generateFactions(
                system, // The system that will have the auto-generated planets
                nirraok, // The star that the planets will orbit on
                "HS_Corporation_Separatist", // Faction ID of the first faction
                Factions.REMNANTS, // Faction ID of the second faction
                0.6, // The percentage of factionA appearing vs factionB on the system, min=0 max=100
                true, // Do we generate factions
                false, // Do we generate stations
                true); // Do we generate an abandoned station

        //Nav Buoy
        SectorEntityToken nav_buoy = system.addCustomEntity(
                "nirraok_navbuoy",
                "Nirraok Nav Buoy",
                Entities.NAV_BUOY,
                "HS_Corporation_Separatist");
        nav_buoy.setCircularOrbit(
                nirraok, 
                system.getPlanets().get(1).getCircularOrbitRadius() * 1.5f, 
                system.getPlanets().get(1).getCircularOrbitAngle() * 1.5f, 
                system.getPlanets().get(1).getCircularOrbitPeriod() * 1.5f);

        //Comm Relay
        SectorEntityToken comm_relay = system.addCustomEntity(
                "nirraok_commrelay",
                "Nirraok Relay",
                Entities.COMM_RELAY,
                "HS_Corporation_Separatist");
        comm_relay.setCircularOrbit(
                nirraok, 
                system.getPlanets().get(2).getCircularOrbitRadius() * 1.5f, 
                system.getPlanets().get(2).getCircularOrbitAngle() * 1.5f, 
                system.getPlanets().get(2).getCircularOrbitPeriod() * 1.5f);

        //Sensor Array
        SectorEntityToken sensor_array = system.addCustomEntity(
                "nirraok_sensorarray",
                "Nirraok Sensor Array",
                Entities.SENSOR_ARRAY,
                "HS_Corporation_Separatist");
        sensor_array.setCircularOrbit(
                nirraok, 
                system.getPlanets().get(3).getCircularOrbitRadius() * 1.5f, 
                system.getPlanets().get(3).getCircularOrbitAngle() * 1.5f, 
                system.getPlanets().get(3).getCircularOrbitPeriod() * 1.5f);
        
        // Adds nav buoy, comm relay, and sensor array
        new HS_AddStuffs().generateStuffs(system, nirraok, "HS_Corporation_Separatist");
        
        // Add jump points on habitable or working colonies
        new HS_AddStuffs().generateJumpPoints(system, nirraok);
        
        // Autogenerated jump points
        system.autogenerateHyperspaceJumpPoints(true, true);
        
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
    }
}
