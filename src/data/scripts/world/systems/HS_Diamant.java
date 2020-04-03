package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.HyperionRandomizer;
import data.scripts.world.HS_AutoGeneratePlanets;
import data.scripts.world.HS_JumpZoneHabitables;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_Diamant {
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Roll the dice for planet tariff
    private float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return min + rand.nextFloat() * (max - min);
    }
    
    // Roll the dice, system background
    private int rand_bg() {
        Random rand = new Random();
        final int max = 6;
        final int min = 1;
        return min + rand.nextInt(max - min + 1);
    }
    
    public void generate(SectorAPI sector) {

        // Add star system
        StarSystemAPI system = sector.createStarSystem("Diamant");
        LocationAPI hyper = Global.getSector().getHyperspace();
        system.getLocation().set(-22300, -18500);
        system.setBackgroundTextureFilename("graphics/backgrounds/background" + rand_bg() + ".jpg");
        ProcgenUsedNames.notifyUsed("Diamant");
        
        // Add stars
        PlanetAPI diamant = system.initStar(
                    "diamant", // unique id for this star
                    StarTypes.ORANGE,  // id in planets.json
                    650,           // radius (in pixels at default zoom)
                    975,            // corona radius, from star edge
                    1.7f,             // solar wind burn level
                    0.45f,           // flare probability
                    1.7f);          // CR loss multipiers

        diamant.setCustomDescriptionId("hs_star_diamant");
        
        // Sets whole system lighting color (R, G, B)
        system.setLightColor(new Color(246, 182, 108));
        
        // Autogenerate planets
        int planetAmount = 10; // Amount of planets you want
        int starsAmount = 1; // The amount of stars you manually added
        
        PlanetAPI[] planets = new PlanetAPI[planetAmount]; // Initiates the Planet array
        
        PlanetAPI[] stars = new PlanetAPI[starsAmount]; // Initiates the Star array
        stars[0] = diamant; // Sets the final values of the Star array
        
        MarketAPI[] markets = new MarketAPI[planetAmount]; // Initiates the Market array
        
        SectorEntityToken[] stations = new SectorEntityToken[planetAmount]; // Initiates the SectorEntityToken array
        
        // Automatically generates random planets in the system based on the values you added
        new HS_AutoGeneratePlanets(
                system,          // The system that will have the auto-generated planets
                diamant,         // The star that the planets will orbit on
                null,            // Use this if the center is a the middle of both
                planets,         // The PlanetAPI array where the planets will be stored
                planetAmount,    // How many planets you want to auto-generate in the system
                stars,           // The PlanetAPI array where the stars are stored
                starsAmount,     // The amount of stars in your system
                diamant.getRadius() * 2, // Starting orbit of the planets
                60, // Starting orbital period of the planets
                875,  // Minimum orbit distance between the auto-generated planets
                2250,  // Maximum orbit distance between the auto-generated planets
                0.2f,  // Minimum orbital period of the planets that will be added, min 0.0f max 1.0f
                0.7f,  // Maximum orbital period of the planets that will be added, max 1.0f
                true,            // If true, the system will auto-generate planets
                stations,            // SectorEntityToken array, where the nexrelin stations will be stored
                markets,         // MarketAPI array where all the planet markets will be stored
                Factions.TRITACHYON, // The first faction that will be auto-generated on the system
                Factions.REMNANTS,  // The second faction that will be auto-generated on the system
                62.5);  // The percentage of factionA appearing vs factionB on the system, min=0 max=100
        
        // Jump points, Habitable
        JumpPointAPI[] hab_jp = new JumpPointAPI[planetAmount]; // JumpPointAPI array
        new HS_JumpZoneHabitables(
                hab_jp,         // The JumpPointAPI array where all the jump points are stored
                planetAmount,   // How many planets that the system has
                planets,        // The PlanetAPI array where the planets will be stored
                diamant,           // The star that the jump points will orbit on
                system);        // The system that will have the auto-generated jump points
        
        // Jump point orbits
        float orbitJP1 = planets[1].getCircularOrbitRadius() + ((
                planets[2].getCircularOrbitRadius() - planets[1].getCircularOrbitRadius()) / 2);
        float orbitJP2 = planets[4].getCircularOrbitRadius() + ((
                planets[5].getCircularOrbitRadius() - planets[4].getCircularOrbitRadius()) / 2);
        
        // Jump points, inner
        JumpPointAPI inner_jp = Global.getFactory().createJumpPoint(
                "inner_jp", "Diamant Inner Jump Point");
        OrbitAPI orbit_1 = Global.getFactory().createCircularOrbit(
                diamant, 
                planets[2].getCircularOrbitAngle() * 1.5f, 
                orbitJP1, 
                planets[2].getCircularOrbitPeriod() * 1.5f);
        inner_jp.setOrbit(orbit_1);
        inner_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(inner_jp);
        
        // Jump points, outer
        JumpPointAPI outer_jp = Global.getFactory().createJumpPoint(
                "outer_jp", "Diamant Inner Jump Point");
        OrbitAPI orbit_2 = Global.getFactory().createCircularOrbit(
                diamant, 
                planets[4].getCircularOrbitAngle() * 1.5f, 
                orbitJP2, 
                planets[4].getCircularOrbitPeriod() * 1.5f);
        outer_jp.setOrbit(orbit_2);
        outer_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(outer_jp);
        
        // Autogenerated jump points
        system.autogenerateHyperspaceJumpPoints(true, true);
                
        // Specials orbits
        float orbitNB = planets[0].getCircularOrbitRadius() + ((
                planets[1].getCircularOrbitRadius() - planets[0].getCircularOrbitRadius()) / 2);
        float orbitCR = planets[2].getCircularOrbitRadius() + ((
                planets[3].getCircularOrbitRadius() - planets[2].getCircularOrbitRadius()) / 2);
        float orbitSA = planets[6].getCircularOrbitRadius() + ((
                planets[7].getCircularOrbitRadius() - planets[6].getCircularOrbitRadius()) / 2);
        
        //Nav Buoy
        SectorEntityToken nav_buoy = system.addCustomEntity(
                "diamant_navbuoy",
                "Diamant Nav Buoy",
                Entities.NAV_BUOY,
                Factions.TRITACHYON);
        nav_buoy.setCircularOrbit(
                diamant, 
                planets[1].getCircularOrbitAngle() * 1.5f, 
                orbitNB, 
                planets[1].getCircularOrbitPeriod() * 1.5f);

        //Comm Relay
        SectorEntityToken comm_relay = system.addCustomEntity(
                "diamant_commrelay",
                "Diamant Relay",
                Entities.COMM_RELAY,
                Factions.TRITACHYON);
        comm_relay.setCircularOrbit(
                diamant, 
                planets[4].getCircularOrbitAngle() * 1.5f, 
                orbitCR, 
                planets[4].getCircularOrbitPeriod() * 1.5f);

        //Sensor Array
        SectorEntityToken sensor_array = system.addCustomEntity(
                "diamant_navbuoy",
                "Diamant Nav Buoy",
                Entities.SENSOR_ARRAY,
                Factions.TRITACHYON);
        sensor_array.setCircularOrbit(
                diamant, 
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
        system.setStar(diamant);
        system.setAge(StarAge.ANY);
    }
    
    // Extra industry randomizer
    private String cryoIndustries() {
        double random = rand();
        
        if(random <= 0.8) {
            return null;
        } else if(random > 0.8 && random >= 0.9) {
            return Industries.CRYOSANCTUM;
        } else {
            return "cryorevival";
        }
    }
    
    // Ship Hull Randomizers
    private String HSSship_id() {
        int number = new HyperionRandomizer(11).value;
        String ship_id;

        //Ship Randomizers
        switch (number) {
            case 0:
                return ship_id = "HS_Orionis";
            case 1:
                return ship_id = "HS_Naos";
            case 2:
                return ship_id = "HS_Betelgeuse";
            case 3:
                return ship_id = "HS_Arcturus";
            case 4:
                return ship_id = "HS_Icarus";
            case 5:
                return ship_id = "HS_Alycone";
            case 6:
                return ship_id = "HS_Bellatrix";
            case 7:
                return ship_id = "HS_Carinae";
            case 8:
                return ship_id = "HS_Sirius";
            case 9:
                return ship_id = "HS_Eridani";
            case 10:
                return ship_id = "HS_Cassiopeia";
            case 11:
                return ship_id = "HS_Perseus";
            default:
                break;
        }
        return null;
    }
    
    private String HCOship_id() {
        int number = new HyperionRandomizer(12).value;
        String ship_id;

        //Ship Randomizers
        switch (number) {
            case 0:
                return ship_id = "HS_Orionis_Confederacy";
            case 1:
                return ship_id = "HS_Naos_Confederacy";
            case 2:
                return ship_id = "HS_Atlas_Confederacy";
            case 3:
                return ship_id = "HS_Buffalo_Confederacy";
            case 4:
                return ship_id = "HS_Gemini_Confederacy";
            case 5:
                return ship_id = "HS_Alycone_Confederacy";
            case 6:
                return ship_id = "HS_Bellatrix_Confederacy";
            case 7:
                return ship_id = "HS_Carinae_Confederacy";
            case 8:
                return ship_id = "HS_Sirius_Confederacy";
            case 9:
                return ship_id = "HS_Eridani_Confederacy";
            case 10:
                return ship_id = "HS_Cassiopeia_Confederacy";
            case 11:
                return ship_id = "HS_Perseus_Confederacy";
            case 12:
                return ship_id = "HS_Valkyrie_Confederacy";
            default:
                break;
        }
        return null;
    }
}
