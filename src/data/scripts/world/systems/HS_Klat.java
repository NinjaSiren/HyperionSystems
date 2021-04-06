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
public class HS_Klat {
    
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
        StarSystemAPI system = sector.createStarSystem("Klat");
        system.getLocation().set(-9750, 24500);
        system.setBackgroundTextureFilename("graphics/backgrounds/background" + rand_bg() + ".jpg");
        ProcgenUsedNames.notifyUsed("Klat");
        
        // Add stars, Klat
        PlanetAPI klat = system.initStar(
                    "hs_klat", // unique id for this star
                    StarTypes.BLUE_GIANT,  // id in planets.json
                    1250,           // radius (in pixels at default zoom)
                    2450,            // corona radius, from star edge
                    2,             // solar wind burn level
                    0.475f,           // flare probability
                    2.4f);          // CR loss multipiers
        klat.setCustomDescriptionId("hs_star_klat"); 
        klat.setName("Klat");
        
        // Sets whole system lighting color (R, G, B)
        system.setLightColor(new Color(0, 177, 253));
        klat.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "banded"));
        klat.getSpec().setGlowColor(new Color(0, 142, 253, 128));
        klat.getSpec().setAtmosphereThickness(0.5f);
        klat.applySpecChanges();
        
        // Autogenerate planets
        int planetAmount = 8; // Amount of planets you want
        int starsAmount = 1; // The amount of stars you manually added
        PlanetAPI[] planets = new PlanetAPI[planetAmount]; // Initiates the Planet array
        PlanetAPI[] stars = new PlanetAPI[starsAmount]; // Initiates the Star array
        stars[0] = klat; // Sets the final values of the Star array
        MarketAPI[] klat_markets = new MarketAPI[planetAmount]; // Initiates the Market array
        SectorEntityToken[] stations = new SectorEntityToken[planetAmount]; // Initiates the SectorEntityToken array
        
        // Automatically generates random planets in the system based on the values you added
        new HS_AutoGeneratePlanets(
                system,          // The system that will have the auto-generated planets
                klat,            // The star that the planets will orbit on
                null,      // Use this if the center is a the middle of both
                planets,         // The PlanetAPI array where the planets will be stored
                planetAmount,    // How many planets you want to auto-generate in the system
                stars,           // The PlanetAPI array where the stars are stored
                starsAmount,     // The amount of stars in your system
                klat.getRadius() * 1.5f, // Starting orbit of the planets
                60, // Starting orbital period of the planets
                875,  // Minimum orbit distance between the auto-generated planets
                2250,  // Maximum orbit distance between the auto-generated planets
                0.2f,  // Minimum orbital period of the planets that will be added, min 0.0f max 1.0f
                0.7f,  // Maximum orbital period of the planets that will be added, max 1.0f
                true,            // If true, the system will auto-generate planets
                stations,            // SectorEntityToken array, where the nexrelin stations will be stored
                klat_markets,         // MarketAPI array where all the planet markets will be stored
                "HS_Corporation_Separatist", // The first faction that will be auto-generated on the system
                Factions.REMNANTS,  // The second faction that will be auto-generated on the system
                60);  // The percentage of factionA appearing vs factionB on the system, min=0 max=100

        // Automatically generates asteroid belts and rings around the star
        int pCounter = 0;
        for(; planetAmount > 0; planetAmount--) {
            if(rand(1, 10) <= 5) {
                if(pCounter > planetAmount) {
                    new HS_AddAsteroidBelts().addBeyondSystem(system, klat, planets[pCounter]);
                } else {
                    new HS_AddAsteroidBelts().addBetweenPlanets(system, klat, 
                            planets[pCounter], planets[pCounter + 1]);
                }
            }
            pCounter++;
        }
        
        // Adds an abandoned station
        new HS_AbandonedStation(system, klat, planets[7]);
        
        // Jump points, Habitable
        JumpPointAPI[] hab_klat_jp = new JumpPointAPI[planetAmount]; // JumpPointAPI array
        new HS_JumpZoneHabitables(
                hab_klat_jp,         // The JumpPointAPI array where all the jump points are stored
                planetAmount,   // How many planets that the system has
                planets,        // The PlanetAPI array where the planets will be stored
                klat,           // The star that the jump points will orbit on
                system);        // The system that will have the auto-generated jump points

        // Jump point orbits
        float orbitJP1 = planets[0].getCircularOrbitRadius() + ((
                planets[1].getCircularOrbitRadius() - planets[0].getCircularOrbitRadius()) / 2);
        float orbitJP2 = planets[3].getCircularOrbitRadius() + ((
                planets[4].getCircularOrbitRadius() - planets[3].getCircularOrbitRadius()) / 2);
        
        // Jump points, inner
        JumpPointAPI inner_jp = Global.getFactory().createJumpPoint(
                "inner_jp_2", "Klat Inner Jump Point");
        OrbitAPI orbit_1 = Global.getFactory().createCircularOrbit(klat, 
                planets[0].getCircularOrbitAngle() * 1.5f, 
                orbitJP1, 
                planets[0].getCircularOrbitPeriod() * 1.5f);
        inner_jp.setOrbit(orbit_1);
        inner_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(inner_jp);
        
        // Jump points, outer
        JumpPointAPI outer_jp = Global.getFactory().createJumpPoint(
                "outer_jp_2", "Klat Inner Jump Point");
        OrbitAPI orbit_2 = Global.getFactory().createCircularOrbit(klat, 
                planets[3].getCircularOrbitAngle() * 1.5f, 
                orbitJP2, 
                planets[3].getCircularOrbitPeriod() * 1.5f);
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
                "klat_navbuoy",
                "Klat Nav Buoy",
                Entities.NAV_BUOY,
                "HS_Corporation_Separatist");
        nav_buoy.setCircularOrbit(klat, 
                planets[1].getCircularOrbitAngle() * 1.5f, 
                orbitNB, 
                planets[1].getCircularOrbitPeriod() * 1.5f);

        //Comm Relay
        SectorEntityToken comm_relay = system.addCustomEntity(
                "klat_commrelay",
                "Klat Relay",
                Entities.COMM_RELAY,
                "HS_Corporation_Separatist");
        comm_relay.setCircularOrbit(klat, 
                (planets[0].getCircularOrbitAngle() * 1.5f) - 180, 
                orbitCR, 
                planets[0].getCircularOrbitPeriod() * 1.5f);

        //Sensor Array
        SectorEntityToken sensor_array = system.addCustomEntity(
                "klat_sensorarray",
                "Klat Sensor Array",
                Entities.SENSOR_ARRAY,
                "HS_Corporation_Separatist");
        sensor_array.setCircularOrbit(klat, 
                planets[3].getCircularOrbitAngle() * 1.5f, 
                orbitSA, 
                planets[3].getCircularOrbitPeriod() * 1.5f);
        
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
        system.setStar(klat);
        system.setAge(StarAge.YOUNG);
        system.setHasSystemwideNebula(true);
    }
}
