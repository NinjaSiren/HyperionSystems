package data.scripts.world.systems;

import data.scripts.HyperionRandomizer;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.submarkets.StoragePlugin;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.world.HS_AddIndustry;
import data.scripts.world.HS_AddMarketplace;
import data.scripts.world.Planet;
import data.scripts.world.Star;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_Neue_Jangala {

    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return ((min + (max - min)) * rand.nextDouble());
    }
    
    // Roll the dice for planet tariff
    private float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return ((min + (max - min)) * rand.nextFloat());
    }
    
    public void generate(SectorAPI sector) {

        // Checks if Unknown Skies is enabled
        boolean isUS = Global.getSettings().getModManager().isModEnabled("US");
        
        // Add star system
        StarSystemAPI system = sector.createStarSystem("Neue Jangala");
        system.getLocation().set(-12350, -18900);
        system.setBackgroundTextureFilename("graphics/backgrounds/background5.jpg");
        ProcgenUsedNames.notifyUsed("Neue");

        // Add star
        PlanetAPI star;
        if(isUS) {
            star = system.initStar(
                    "neue_jangala", // unique id for this star
                    new Star().YELLOW_US,  // id in planets.json
                    600f,           // radius (in pixels at default zoom)
                    800,            // corona radius, from star edge
                    4f,             // solar wind burn level
                    0.3f,           // flare probability
                    1.6f);   
        } else {
            star = system.initStar(
                        "neue_jangala", // unique id for this star
                        new Star().YELLOW,  // id in planets.json
                        600f,           // radius (in pixels at default zoom)
                        800,            // corona radius, from star edge
                        4f,             // solar wind burn level
                        0.3f,           // flare probability
                        1.6f); 
        }
        star.setCustomDescriptionId("hs_star_neuejangala");

        // Sets whole system lighting color (R, G, B)
        system.setLightColor(new Color(255, 250, 195));

        // Adds magnetic field effect around the star
        SectorEntityToken neue_jangala_mfield1 = system.addTerrain(
                Terrain.MAGNETIC_FIELD,
                new MagneticFieldTerrainPlugin.MagneticFieldParams(
                400f, // terrain effect band width
                1050, // terrain effect middle radius
                star, // entity that it's around
                850f, // visual band start
                1250f, // visual band end
                new Color(50, 30, 100, 30), // base color
                0.3f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                new Color(50, 20, 110, 130),
                new Color(150, 30, 120, 150),
                new Color(200, 50, 130, 190),
                new Color(250, 70, 150, 240),
                new Color(200, 80, 130, 255),
                new Color(75, 0, 160),
                new Color(127, 0, 255)));
        neue_jangala_mfield1.setCircularOrbit(star, 0, 0, 120);

        // Venusia, a large lava planet too close to the star
        PlanetAPI venusia;
        if(isUS) {
            venusia = system.addPlanet(
                    "hs_planet_venusia", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Venusia", //Name
                    new Planet().US_LAVA, //Planet type id in planets.json
                    0, //Starting angle in orbit, i.e. 0 = to the right of the star
                    105, //Planet radius, pixels at default zoom
                    1400, //Orbit radius, pixels at default zoom
                    40); //Days it takes to complete an orbit. 1 day = 10 seconds.
        } else {
            String pType = new Planet().LAVA;
            if(rand() > 0.5) pType = new Planet().LAVA_MINOR;
            venusia = system.addPlanet(
                    "hs_planet_venusia", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    pType, //Name
                    "lava", //Planet type id in planets.json
                    0, //Starting angle in orbit, i.e. 0 = to the right of the star
                    105, //Planet radius, pixels at default zoom
                    1400, //Orbit radius, pixels at default zoom
                    40); //Days it takes to complete an orbit. 1 day = 10 seconds.
        }
        venusia.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        venusia.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        venusia.getSpec().setUseReverseLightForGlow(true);
        venusia.applySpecChanges();
        venusia.setCustomDescriptionId("hs_planet_venusia");

        // Adds magnetic fields around Venusia
        SectorEntityToken venusia_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
                        new MagneticFieldTerrainPlugin.MagneticFieldParams(60f, // terrain effect band width
                        120, // terrain effect middle radius
                        venusia, // entity that it's around
                        110f, // visual band start
                        160f, // visual band end
                        new Color(50, 30, 100, 30), // base color
                        0.6f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                        new Color(50, 20, 110, 130),
                        new Color(200, 50, 130, 190),
                        new Color(250, 70, 150, 240),
                        new Color(200, 80, 130, 255),
                        new Color(75, 0, 160),
                        new Color(127, 0, 255)
                        ));
        venusia_field.setCircularOrbit(venusia, 0, 0, 80);

            // Venusia conditions
            Misc.initConditionMarket(venusia);
            venusia.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
            venusia.getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            venusia.getMarket().addCondition(Conditions.VERY_HOT);
            venusia.getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
            venusia.getMarket().addCondition(Conditions.RUINS_SCATTERED);
            venusia.getMarket().getFirstCondition(Conditions.RUINS_SCATTERED).setSurveyed(true);
            venusia.getMarket().addCondition(Conditions.ORE_ULTRARICH);
            venusia.getMarket().getFirstCondition(Conditions.ORE_ULTRARICH).setSurveyed(true);
            venusia.getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
            venusia.getMarket().getFirstCondition(Conditions.RARE_ORE_ULTRARICH).setSurveyed(true);

        // Mercurios, a desert planet with alot of ores
        PlanetAPI mercurios;
        if(isUS) {   
            String pType = new Planet().US_DESERT_A;
            if(rand() <= 0.33) pType = new Planet().US_DESERT_B;
            else if(rand() > 0.33 && rand() <= 0.66) pType = new Planet().US_DESERT_C;
            mercurios = system.addPlanet(
                    "hs_planet_mercurios", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Mercurios", //Name
                    pType, //Planet type id in planets.json
                    58, //Starting angle in orbit, i.e. 0 = to the right of the star
                    145, //Planet radius, pixels at default zoom
                    2400, //Orbit radius, pixels at default zoom
                    52); //Days it takes to complete an orbit. 1 day = 10 seconds.
        } else {
            String pType = new Planet().DESERT_A;
            if(rand() > 0.5) pType = new Planet().DESERT_B;
            mercurios = system.addPlanet(
                "hs_planet_mercurios", //Unique id for this planet (or null to have it be autogenerated)
                star, //What the planet orbits (orbit is always circular)
                "Mercurios", //Name
                pType, //Planet type id in planets.json
                58, //Starting angle in orbit, i.e. 0 = to the right of the star
                145, //Planet radius, pixels at default zoom
                2400, //Orbit radius, pixels at default zoom
                52); //Days it takes to complete an orbit. 1 day = 10 seconds.
        }
        mercurios.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        mercurios.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        mercurios.getSpec().setUseReverseLightForGlow(true);
        mercurios.applySpecChanges();
        mercurios.setInteractionImage("illustrations", "urban03");
        mercurios.setCustomDescriptionId("hs_planet_mercurios");

        // Mercurios market
        MarketAPI mercuriosMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist",
                    mercurios,
                    null,
                    "Mercurios", // name of the market
                    5, // size of the market (from the JSON)
                    new ArrayList<>(
                            Arrays.asList( // list of market conditions from nikolaev.json
                                    Conditions.HABITABLE,
                                    Conditions.HOT,
                                    Conditions.FARMLAND_POOR,
                                    Conditions.ORGANICS_COMMON,
                                    Conditions.ORE_ULTRARICH,
                                    Conditions.RARE_ORE_RICH,
                                    Conditions.URBANIZED_POLITY,
                                    Conditions.DESERT,
                                    Conditions.POPULATION_5)),
                    new ArrayList<>
                            (Arrays.asList( // list of industries
                                    Industries.WAYSTATION,
                                    Industries.MEGAPORT,
                                    Industries.HEAVYBATTERIES,
                                    Industries.PATROLHQ,
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    tariff()); // tariff amount
        
                    // Adds industries depending on conditions
                    new HS_AddIndustry(mercurios, mercuriosMarket);
                    
        // Adds magnetic field around Mercurios
        SectorEntityToken mercurios_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
			new MagneticFieldTerrainPlugin.MagneticFieldParams(60f, // terrain effect band width
			175, // terrain effect middle radius
			mercurios, // entity that it's around
			155f, // visual band start
			195f, // visual band end
			new Color(50, 30, 100, 30), // base color
			0.6f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
			new Color(50, 20, 110, 130),
			new Color(200, 50, 130, 190),
			new Color(250, 70, 150, 240),
			new Color(200, 80, 130, 255),
			new Color(75, 0, 160),
			new Color(127, 0, 255)
			));
        mercurios_field.setCircularOrbit(mercurios, 0, 0, 100);

        // Neue Jangala, home of the Hyperion Confederate capital
        PlanetAPI neuejangala;
        if(isUS) {
            neuejangala = system.addPlanet(
                    "hs_planet_neuejangala", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Neue Jangala", //Name
                    new Planet().US_TERRAN, //Planet type id in planets.json
                    126, //Starting angle in orbit, i.e. 0 = to the right of the star
                    180, //Planet radius, pixels at default zoom
                    3600, //Orbit radius, pixels at default zoom
                    64); //Days it takes to complete an orbit. 1 day = 10 seconds.
        } else {
            neuejangala = system.addPlanet(
                    "hs_planet_neuejangala", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Neue Jangala", //Name
                    new Planet().TERRAN, //Planet type id in planets.json
                    126, //Starting angle in orbit, i.e. 0 = to the right of the star
                    180, //Planet radius, pixels at default zoom
                    3600, //Orbit radius, pixels at default zoom
                    64); //Days it takes to complete an orbit. 1 day = 10 seconds.
        }
        neuejangala.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        neuejangala.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        neuejangala.getSpec().setUseReverseLightForGlow(true);
        neuejangala.applySpecChanges();
        neuejangala.setInteractionImage("illustrations", "urban01");
        neuejangala.setCustomDescriptionId("hs_planet_neuejangala");

            // Home sweet home port.
            SectorEntityToken station = system.addCustomEntity(
                    "neue_jangala_port",
                    "Neue Jangala Highport",
                    "station_jangala_type",
                    "HS_Corporation_Separatist");
            station.setCircularOrbitPointingDown(neuejangala, 45, 420, 45);
            station.setInteractionImage("illustrations", "orbital");
            station.setCustomDescriptionId("hs_neue_jangala_port");

            // add the marketplace to Neue Jangala and Neue Jangala Highport.
            MarketAPI neuejangalaMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist",
                    neuejangala,
                    new ArrayList<>(Arrays.asList(station)),
                    "Neue Jangala", // name of the market
                    9, // size of the market (from the JSON)
                    new ArrayList<>(
                            Arrays.asList( // list of market conditions from nikolaev.json
                                    Conditions.HABITABLE,
                                    Conditions.MILD_CLIMATE,
                                    Conditions.FARMLAND_BOUNTIFUL,
                                    Conditions.ORGANICS_ABUNDANT,
                                    Conditions.ORE_MODERATE,
                                    Conditions.RARE_ORE_SPARSE,
                                    Conditions.URBANIZED_POLITY,
                                    Conditions.REGIONAL_CAPITAL,
                                    Conditions.TERRAN,
                                    Conditions.POPULATION_9)),
                    new ArrayList<>
                            (Arrays.asList( // list of industries
                                    Industries.STARFORTRESS_HIGH,
                                    Industries.HIGHCOMMAND,
                                    Industries.WAYSTATION,
                                    Industries.MEGAPORT,
                                    Industries.HEAVYBATTERIES,
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.GENERIC_MILITARY,
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    tariff()); // tariff amount
                    
                    String cryos = cryoIndustries();
                    if(cryos != null) {
                        neuejangalaMarket.addIndustry(cryos);
                    }   

                    // Adds industries depending on conditions
                    new HS_AddIndustry(neuejangala, neuejangalaMarket);
                    
                    // Neue Jangala jump point
                    JumpPointAPI neue_jangala_jp = Global.getFactory().createJumpPoint(
                            "neue_jangala_jp", "Neue Jangala Jump Point");
                    neue_jangala_jp.setCircularOrbit(star,
                            126, //Starting angle in orbit, i.e. 0 = to the right of the star
                            3600, //Orbit radius, pixels at default zoom
                            64); //Days to orbit
                    neue_jangala_jp.setRelatedPlanet(neuejangala);
                    system.addEntity(neue_jangala_jp);

        // Amazonia, super-earth sized jungle planet with alot of natural resources
        PlanetAPI amazonia;
        if(isUS) {
            amazonia = system.addPlanet(
                    "hs_planet_amazonia", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Amazonia", //Name
                    new Planet().US_JUNGLE, //Planet type id in planets.json
                    178, //Starting angle in orbit, i.e. 0 = to the right of the star
                    240, //Planet radius, pixels at default zoom
                    5400, //Orbit radius, pixels at default zoom
                    76); //Days it takes to complete an orbit. 1 day = 10 seconds.
        } else {
            amazonia = system.addPlanet(
                    "hs_planet_amazonia", //Unique id for this planet (or null to have it be autogenerated)
                    star, //What the planet orbits (orbit is always circular)
                    "Amazonia", //Name
                    new Planet().JUNGLE, //Planet type id in planets.json
                    178, //Starting angle in orbit, i.e. 0 = to the right of the star
                    240, //Planet radius, pixels at default zoom
                    5400, //Orbit radius, pixels at default zoom
                    76); //Days it takes to complete an orbit. 1 day = 10 seconds.
        }
        amazonia.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        amazonia.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        amazonia.getSpec().setUseReverseLightForGlow(true);
        amazonia.applySpecChanges();
        amazonia.setInteractionImage("illustrations", "urban02");
        amazonia.setCustomDescriptionId("hs_planet_amazonia");

        // Amazonia market
        MarketAPI amazoniaMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist",
                    amazonia,
                    null,
                    "Amazonia", // name of the market
                    7, // size of the market (from the JSON)
                    new ArrayList<>(
                            Arrays.asList( // list of market conditions from nikolaev.json
                                    Conditions.HABITABLE,
                                    Conditions.MILD_CLIMATE,
                                    Conditions.INIMICAL_BIOSPHERE,
                                    Conditions.FARMLAND_BOUNTIFUL,
                                    Conditions.ORGANICS_PLENTIFUL,
                                    Conditions.ORE_RICH,
                                    Conditions.RARE_ORE_MODERATE,
                                    Conditions.URBANIZED_POLITY,
                                    Conditions.JUNGLE,
                                    Conditions.POPULATION_7)),
                    new ArrayList<>
                            (Arrays.asList( // list of industries
                                    Industries.WAYSTATION,
                                    Industries.MEGAPORT,
                                    Industries.HEAVYBATTERIES,
                                    Industries.PATROLHQ,
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    tariff()); // tariff amount
        
                    // Adds industries depending on conditions
                    new HS_AddIndustry(amazonia, amazoniaMarket);
                    
        // Marutaina, a large gas giant with volatiles around
        PlanetAPI marutaina;
        if(isUS) {
            String pType = new Planet().US_GAS_GIANT_A;
            if(rand() > 0.5) pType = new Planet().US_GAS_GIANT_B;
            marutaina = system.addPlanet(
                    "hs_planet_marutaina",
                    star,
                    "Marutaina",
                    pType,
                    150,
                    520,
                    10800,
                    300);
        } else {
            marutaina = system.addPlanet(
                    "hs_planet_marutaina",
                    star,
                    "Marutaina",
                    new Planet().GAS_GIANT,
                    150,
                    520,
                    10800,
                    300);
        }
        marutaina.getSpec().setPlanetColor(new Color(255, 210, 180, 255));
        marutaina.getSpec().setAtmosphereColor(new Color(135, 45, 15, 135));
        marutaina.getSpec().setCloudColor(new Color(215, 215, 200, 200));
        marutaina.getSpec().setIconColor(new Color(155, 125, 75, 255));
        marutaina.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "aurorae"));
        marutaina.getSpec().setGlowColor(new Color(135, 45, 15, 135));
        marutaina.getSpec().setUseReverseLightForGlow(true);
        marutaina.getSpec().setAtmosphereThickness(0.6f);
        marutaina.applySpecChanges();
        marutaina.setCustomDescriptionId("hs_planet_marutaina");

        // Marutania's dual magnetic fields
        SectorEntityToken marutaina_field1 = system.addTerrain(Terrain.MAGNETIC_FIELD,
                new MagneticFieldTerrainPlugin.MagneticFieldParams(200f, // terrain effect band width
                        625, // terrain effect middle radius
                        marutaina, // entity that it's around
                        525f, // visual band start
                        725f, // visual band end
                        new Color(50, 30, 100, 60), // base color
                        1f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                        new Color(50, 20, 110, 135),
                        new Color(150, 30, 120, 150),
                        new Color(200, 50, 130, 190),
                        new Color(250, 70, 150, 240),
                        new Color(200, 80, 130, 255),
                        new Color(75, 0, 160),
                        new Color(127, 0, 255)
                ));
        marutaina_field1.setCircularOrbit(marutaina, 0, 0, 100);

        SectorEntityToken marutaina_field2 = system.addTerrain(Terrain.MAGNETIC_FIELD,
                new MagneticFieldTerrainPlugin.MagneticFieldParams(400f, // terrain effect band width
                        900, // terrain effect middle radius
                        marutaina, // entity that it's around
                        700f, // visual band start
                        1100f, // visual band end
                        new Color(50, 30, 100, 30), // base color
                        0.3f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                        new Color(50, 20, 110, 135),
                        new Color(150, 30, 120, 150),
                        new Color(200, 50, 130, 190),
                        new Color(250, 70, 150, 240),
                        new Color(200, 80, 130, 255),
                        new Color(75, 0, 160),
                        new Color(127, 0, 255)
                ));
        marutaina_field2.setCircularOrbit(marutaina, 0, 0, 100);

            // Marutainia conditions
            Misc.initConditionMarket(marutaina);
            marutaina.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
            marutaina.getMarket().addCondition(Conditions.HIGH_GRAVITY);
            marutaina.getMarket().addCondition(Conditions.ABANDONED_STATION);
            marutaina.getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
            marutaina.getMarket().getFirstCondition(Conditions.VOLATILES_PLENTIFUL).setSurveyed(true);

        // Marutaina's ring system
        system.addRingBand(marutaina, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 1520, 70f);
        system.addRingBand(marutaina, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 1500, 80f);
        system.addRingBand(marutaina, "misc", "rings_ice0", 256f, 1, Color.white, 256f, 1445, 90f);

        // Marutainia's moons
        // Titania, an Io-like volcanic hellhole.
        PlanetAPI titania;
        if(isUS) {
            titania = system.addPlanet(
                    "hs_planet_titania",
                    marutaina,
                    "Titania",
                    new Planet().US_LAVA,
                    36,
                    90,
                    960,
                    45);
        } else {
            String pType = new Planet().LAVA;
            if(rand() > 0.5) pType = new Planet().LAVA_MINOR;
            titania = system.addPlanet(
                    "hs_planet_titania",
                    marutaina,
                    "Titania",
                    pType,
                    36,
                    90,
                    960,
                    45);
        }
        titania.setCustomDescriptionId("hs_planet_titania");
        titania.getSpec().setPlanetColor(new Color(220, 245, 255, 255));
        titania.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        titania.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        titania.getSpec().setUseReverseLightForGlow(true);
        titania.applySpecChanges();

        // Titania's magnetic field
        SectorEntityToken titania_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
                new MagneticFieldTerrainPlugin.MagneticFieldParams(90f, // terrain effect band width
                110, // terrain effect middle radius
                titania, // entity that it's around
                95f, // visual band start
                145f, // visual band end
                new Color(50, 30, 100, 60), // base color
                0.6f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                new Color(50, 20, 110, 130),
                new Color(200, 50, 130, 190),
                new Color(250, 70, 150, 240),
                new Color(200, 80, 130, 255),
                new Color(75, 0, 160),
                new Color(127, 0, 255)
                ));
        titania_field.setCircularOrbit(titania, 0, 0, 100);

            // Titania conditions
            Misc.initConditionMarket(titania);
            titania.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
            titania.getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            titania.getMarket().addCondition(Conditions.IRRADIATED);
            titania.getMarket().addCondition(Conditions.VERY_HOT);
            titania.getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
            titania.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
            titania.getMarket().getFirstCondition(Conditions.VOLATILES_DIFFUSE).setSurveyed(true);
            titania.getMarket().addCondition(Conditions.ORE_ULTRARICH);
            titania.getMarket().getFirstCondition(Conditions.ORE_ULTRARICH).setSurveyed(true);
            titania.getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
            titania.getMarket().getFirstCondition(Conditions.RARE_ORE_ULTRARICH).setSurveyed(true);

        // Sedinia, a barren shithole of little value.
        PlanetAPI sedina;
        if(isUS) {
            String pType = new Planet().US_BARREN_A;
            if(rand() <= 0.167) pType = new Planet().US_BARREN_B;
            else if(rand() > 0.167 && rand() <= 0.334) pType = new Planet().US_BARREN_C;
            else if(rand() > 0.334 && rand() <= 0.501) pType = new Planet().US_BARREN_D;
            else if(rand() > 0.501 && rand() <= 0.668) pType = new Planet().US_BARREN_E;
            else if(rand() > 0.668 && rand() <= 0.835) pType = new Planet().US_BARREN_F;
            sedina = system.addPlanet(
                    "hs_planet_sedinia",
                    marutaina,
                    "Sedinia",
                    pType,
                    180,
                    120,
                    2100,
                    80);
        } else {
            String pType = new Planet().BARREN_A;
            if(rand() <= 0.33) pType = new Planet().BARREN_B;
            else if(rand() > 0.33 && rand() <= 0.66) pType = new Planet().BARREN_C;
            sedina = system.addPlanet(
                    "hs_planet_sedinia",
                    marutaina,
                    "Sedinia",
                    pType,
                    180,
                    120,
                    2100,
                    80);
        }
        sedina.getSpec().setPlanetColor(new Color(220, 245, 255, 255));
        sedina.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        sedina.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        sedina.getSpec().setUseReverseLightForGlow(true);
        sedina.applySpecChanges();
        sedina.setCustomDescriptionId("hs_planet_sedinia");
        sedina.setInteractionImage("illustrations", "urban00");

        // Sedinia Freeport.
        SectorEntityToken pirateStation = system.addCustomEntity(
                "hs_pirateport",
                "Sedinia Freeport",
                "station_pirate_type",
                Factions.PIRATES);
        pirateStation.setCircularOrbitPointingDown(sedina, 45, 420, 45);
        pirateStation.setInteractionImage("illustrations", "pirate_station");

        // Sedinia market
        MarketAPI sedinaMarket = HS_AddMarketplace.addMarketplace(Factions.PIRATES,
                    sedina,
                    new ArrayList<>(Arrays.asList(pirateStation)),
                    "Sedinia", // name of the market
                    5, // size of the market (from the JSON)
                    new ArrayList<>(
                            Arrays.asList( // list of market conditions from nikolaev.json
                                    Conditions.NO_ATMOSPHERE,
                                    Conditions.VERY_COLD,
                                    Conditions.ORGANIZED_CRIME,
                                    Conditions.DECIVILIZED_SUBPOP,
                                    Conditions.VOLATILES_ABUNDANT,
                                    Conditions.ORE_ULTRARICH,
                                    Conditions.RARE_ORE_RICH,
                                    Conditions.FREE_PORT,
                                    Conditions.VICE_DEMAND,
                                    Conditions.INDUSTRIAL_POLITY,
                                    Conditions.STEALTH_MINEFIELDS,
                                    Conditions.POPULATION_5)),
                    new ArrayList<>
                            (Arrays.asList( // list of industries
                                    Industries.BATTLESTATION,
                                    Industries.PATROLHQ,
                                    Industries.WAYSTATION,
                                    Industries.MEGAPORT,
                                    Industries.HEAVYBATTERIES,
                                    Industries.POPULATION)),
                    new ArrayList<>(
                            Arrays.asList( // which submarkets to generate
                                    Submarkets.SUBMARKET_BLACK,
                                    Submarkets.SUBMARKET_OPEN,
                                    Submarkets.SUBMARKET_STORAGE)),
                    tariff()); // tariff amount
        
                    // Adds industries depending on conditions
                    new HS_AddIndustry(sedina, sedinaMarket);

        // Mirar, a large planet with alot of volatiles
        PlanetAPI mirar;
        if(isUS) {
            mirar = system.addPlanet(
                "hs_planet_mirar",
                star,
                "Mirar",
                new Planet().US_CRYOVOLCANIC,
                210,
                310,
                14200,
                340);
        } else {
            mirar = system.addPlanet(
                "hs_planet_mirar",
                star,
                "Mirar",
                new Planet().CRYOVOLCANIC,
                210,
                310,
                14200,
                340);
        }
        mirar.setCustomDescriptionId("hs_planet_mirar");
        mirar.getSpec().setPlanetColor(new Color(220, 245, 255, 255));
        mirar.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        mirar.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        mirar.getSpec().setUseReverseLightForGlow(true);
        mirar.applySpecChanges();
        mirar.setInteractionImage("illustrations", "industrial_megafacility");

            // Mirar market
            MarketAPI mirarMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist",
                        mirar,
                        null,
                        "Mirar", // name of the market
                        6, // size of the market (from the JSON)
                        new ArrayList<>(
                                Arrays.asList( // list of market conditions from nikolaev.json
                                        Conditions.THIN_ATMOSPHERE,
                                        Conditions.VERY_COLD,
                                        Conditions.HIGH_GRAVITY,
                                        Conditions.RUINS_SCATTERED,
                                        Conditions.VOLATILES_PLENTIFUL,
                                        Conditions.ORE_RICH,
                                        Conditions.RARE_ORE_ABUNDANT,
                                        Conditions.INDUSTRIAL_POLITY,
                                        Conditions.POPULATION_6)),
                        new ArrayList<>
                                (Arrays.asList( // list of industries
                                        Industries.HIGHCOMMAND,
                                        Industries.WAYSTATION,
                                        Industries.MEGAPORT,
                                        Industries.HEAVYBATTERIES,
                                        Industries.POPULATION)),
                        new ArrayList<>(
                                Arrays.asList( // which submarkets to generate
                                        Submarkets.GENERIC_MILITARY,
                                        Submarkets.SUBMARKET_BLACK,
                                        Submarkets.SUBMARKET_OPEN,
                                        Submarkets.SUBMARKET_STORAGE)),
                        tariff()); // tariff amount
            
                        // Adds industries depending on conditions
                        new HS_AddIndustry(mirar, mirarMarket);

        //Dust and asteroid belts
        // Inner asteroid belt
        system.addAsteroidBelt(
                star, // Star or planet that it orbits
                100, // Number of asteroids
                3000, // orbit radius
                500, // belt width
                54, // minimum orbit days
                64, // maximum orbit days
                Terrain.ASTEROID_BELT, // terrain ID
                "Neue Inner Belt"); // belt name
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 2750, 201f, null, null);
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 1, Color.white, 256f, 2850, 225f, null, null);

        // Outer asteroid belt
        system.addAsteroidBelt(
                star, // Star or planet that it orbits
                100, // Number of asteroids
                4200, // orbit radius
                500, // belt width
                64, // minimum orbit days
                74, // maximum orbit days
                Terrain.ASTEROID_BELT, // terrain ID
                "Neue Outer Belt"); // belt name
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 4500, 201f, null, null);
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 1, Color.white, 256f, 4600, 225f, null, null);

        // Outer outer asteroid belts
        system.addAsteroidBelt(
                star, // Star or planet that it orbits
                100, // Number of asteroids
                7500, // orbit radius
                500, // belt width
                100, // minimum orbit days
                190, // maximum orbit days
                Terrain.ASTEROID_BELT, // terrain ID
                "Outer Belt Alpha"); // belt name
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 7600, 201f, null, null);
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 1, Color.white, 256f, 7700, 225f, null, null);

        system.addAsteroidBelt(
                star, // Star or planet that it orbits
                100, // Number of asteroids
                8600, // orbit radius
                500, // belt width
                100, // minimum orbit days
                190, // maximum orbit days
                Terrain.ASTEROID_BELT, // terrain ID
                "Outer Belt Beta"); // belt name
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 8600, 201f, null, null);
        system.addRingBand(star,
                "misc", "rings_asteroids0", 256f, 1, Color.white, 256f, 8700, 225f, null, null);

        // Jump points
        // Inner jump point
        JumpPointAPI inner_jp = Global.getFactory().createJumpPoint(
                "inner_jp", "Neue Jangala Inner Jump Point");
        OrbitAPI orbit_1 = Global.getFactory().createCircularOrbit(star, 126, 1900, 46);
        inner_jp.setOrbit(orbit_1);
        inner_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(inner_jp);

        // Neue Jangala jump point
        JumpPointAPI neue_jp = Global.getFactory().createJumpPoint(
                "neue_jp", "Neue Jangala Jump Point");
        OrbitAPI orbit_2 = Global.getFactory().createCircularOrbit(star, 141, 3600, 64);
        neue_jp.setOrbit(orbit_2);
        neue_jp.setRelatedPlanet(neuejangala);
        neue_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(neue_jp);
        
        // Outer jump point
        JumpPointAPI outer_jp = Global.getFactory().createJumpPoint(
                "outer_jp", "Neue Jangala Outer Jump Point");
        OrbitAPI orbit_3 = Global.getFactory().createCircularOrbit(star, 253, 8050, 112);
        outer_jp.setOrbit(orbit_3);
        outer_jp.setStandardWormholeToHyperspaceVisual();
        system.addEntity(outer_jp);

        // Autogenerated jump points
        system.autogenerateHyperspaceJumpPoints(true, true);

        //Nav Buoy
        SectorEntityToken nav_buoy = system.addCustomEntity(
                "neue_jangala_navbuoy",
                "Neue Jangala Nav Buoy",
                "nav_buoy",
                "HS_Corporation_Separatist");
        nav_buoy.setCircularOrbit(star, 89, 2750, 56);

        //Comm Relay
        SectorEntityToken comm_relay = system.addCustomEntity(
                "neue_jangala_commrelay",
                "Neue Jangala Relay",
                "comm_relay",
                "HS_Corporation_Separatist");
        comm_relay.setCircularOrbit(star, 168, 4800, 70);

        //Sensor Array
        SectorEntityToken sensor_array = system.addCustomEntity(
                "neue_jangala_navbuoy",
                "Neue Jangala Nav Buoy",
                "sensor_array",
                "HS_Corporation_Separatist");
        sensor_array.setCircularOrbit(star, 320, 14700, 380);

        //Inactive Gate
        SectorEntityToken inactive_gate = system.addCustomEntity(
                "neue_jangala_gate",
                "Neue Jangala Gate",
                "inactive_gate",
                null);
        inactive_gate.setCircularOrbit(marutaina, 200, 1140, 63);

        //Other stations
        SectorEntityToken old_station = system.addCustomEntity(
                "hs_old_station_01",
                "Hyperion Systems Persean Sector Research Station",
                "station_research",
                null);
        old_station.setCircularOrbitPointingDown(star, 204, 7150, 89);
        old_station.setDiscoverable(true);
        old_station.setDiscoveryXP(5000f);
        old_station.setSensorProfile(0.25f);
        old_station.setCustomDescriptionId("hs_old_station_01");
        old_station.setInteractionImage("illustrations", "abandoned_station2");

        //Adds market to old station
        old_station.getMemoryWithoutUpdate().set("$abandonedStation", true);
        Misc.setAbandonedStationMarket("neuejangala_abandoned_station_market", old_station);
        MarketAPI old_station_market = Global.getFactory().createMarket("old_station_hs",
                old_station.getName(), 0);
                    old_station_market.setPrimaryEntity(old_station);
                    old_station_market.setFactionId(old_station.getFaction().getId());
                    old_station_market.addCondition(Conditions.ABANDONED_STATION);
                    old_station_market.addCondition(Conditions.DECIVILIZED);
                    old_station_market.addCondition(Conditions.VICE_DEMAND);
                    old_station_market.addCondition(Conditions.LARGE_REFUGEE_POPULATION);
                    old_station_market.addCondition(Conditions.OUTPOST);
                    old_station_market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
                    ((StoragePlugin) old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE)
                            .getPlugin()).setPlayerPaidToUnlock(true);
                    old_station.setMarket(old_station_market);
                    old_station.setCustomDescriptionId("hs_old_research_station");

                    //Items and Hull Randomizer
                    int rand = new HyperionRandomizer(3).value;
                    boolean hasSCY = Global.getSettings().getModManager().isModEnabled("SCY");
                    boolean hasDME = Global.getSettings().getModManager()
                            .isModEnabled("istl_dam");

                    switch (rand) {
                        case 1:
                            // Specials
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                            new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                            new SpecialItemData(Items.SYNCHROTRON, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                            new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                            if(hasDME) {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("istl_securedata", 2);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("istl_sigma_matter2", 2);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("istl_sigma_matter1", 4);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("istl_sigma_matter3", 6);
                            }

                            // AI Cores
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("gamma_core", 9);

                            // Weapons
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("guardian", 4);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("squall", 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("pilum", 4);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("chaingun", 6);

                            //Commodities
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCrew(new HyperionRandomizer(200).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addMarines(new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addFuel(new HyperionRandomizer(1000).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("food", new HyperionRandomizer(50).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organics", new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("volatiles", new HyperionRandomizer(75).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("metals", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("lobster", new HyperionRandomizer(100).value);

                            //Illegals
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organs", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("drugs", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                            //Hulls
                            if(hasDME) {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addMothballedShip(FleetMemberType.SHIP, DMEship_id(), null);
                            } else {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                            }
                            break;
                            
                        case 2:
                            // Specials
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                            new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                   
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                            new SpecialItemData(Items.SYNCHROTRON, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                            new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                            if(hasSCY) {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("SCY_intelChip", 2);
                            }

                            // AI Cores
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("beta_core", 6);

                            // Weapons
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("hil", 8);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("pilum", 4);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("annihilator", 16);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("heavymg", 8);

                            //Commodities
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCrew(new HyperionRandomizer(200).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addMarines(new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addFuel(new HyperionRandomizer(1000).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("food", new HyperionRandomizer(50).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organics", new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("volatiles", new HyperionRandomizer(75).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("metals", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("lobster", new HyperionRandomizer(100).value);

                            //Illegals
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organs", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("drugs", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                            //Hulls
                            if(hasSCY) {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addMothballedShip(FleetMemberType.SHIP, SCYship_id(), null);
                            } else {
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                            }
                            break;
                            
                        case 3:
                            // Specials
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                            new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                                    .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                            new SpecialItemData(Items.SYNCHROTRON, null), 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                            new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addItems(CargoAPI.CargoItemType.SPECIAL, "HCO_package", 1);

                            // AI Cores
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("alpha_core", 3);

                            // Weapons
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("tachyonlance", 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("squall", 2);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("pilum", 4);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addWeapons("annihilator", 16);

                            //Commodities
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCrew(new HyperionRandomizer(200).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addMarines(new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addFuel(new HyperionRandomizer(1000).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("food", new HyperionRandomizer(50).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organics", new HyperionRandomizer(100).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("volatiles", new HyperionRandomizer(75).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("metals", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("lobster", new HyperionRandomizer(100).value);

                            //Illegals
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("organs", new HyperionRandomizer(10).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("drugs", new HyperionRandomizer(25).value);
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                            //Hulls
                            old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                    .addMothballedShip(FleetMemberType.SHIP, HCOship_id(), null);
                            break;
                            
                        case 0:
                            default:
                                // Specials
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                                new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                                new SpecialItemData(Items.SYNCHROTRON, null), 2);

                                // Weapons
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addWeapons("lrpdlaser", 16);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addWeapons("vulcan", 32);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addWeapons("lightdualac", 16);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addWeapons("heavymg", 8);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addWeapons("chaingun", 6);

                                //Commodities
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("volatiles", 75);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("metals", 25);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("rare_metals", 10);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("heavy_machinery", 40);

                                //Illegals
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("organs", 10);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("drugs", 25);
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addCommodity("hand_weapons", 15);

                                //Hulls
                                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                                        .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                                break;
                    }

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

    private String DMEship_id() {
        int number = new HyperionRandomizer(28).value;
        String ship_id;

        //Ship Randomizers
        switch (number) {
            case 0:
                return ship_id = "istl_flatmouse";
            case 1:
                return ship_id = "istl_puddlejumper";
            case 2:
                return ship_id = "istl_bouclier";
            case 3:
                return ship_id = "istl_carabao";
            case 4:
                return ship_id = "istl_sevastopol";
            case 5:
                return ship_id = "istl_chamois";
            case 6:
                return ship_id = "istl_lodestar";
            case 7:
                return ship_id = "istl_mouflon";
            case 8:
                return ship_id = "istl_centaur";
            case 9:
                return ship_id = "istl_samoyed";
            case 10:
                return ship_id = "istl_stoat";
            case 11:
                return ship_id = "istl_burya";
            case 12:
                return ship_id = "istl_naja";
            case 13:
                return ship_id = "istl_borzoi";
            case 14:
                return ship_id = "istl_vesper";
            case 15:
                return ship_id = "istl_starsylph";
            case 16:
                return ship_id = "istl_leyte";
            case 17:
                return ship_id = "istl_tunguska";
            case 18:
                return ship_id = "istl_wanderer";
            case 19:
                return ship_id = "istl_tereshkova";
            case 20:
                return ship_id = "istl_maskirovka";
            case 21:
                return ship_id = "istl_snowgoose";
            case 22:
                return ship_id = "istl_mindanao";
            case 23:
                return ship_id = "istl_baikal";
            case 24:
                return ship_id = "istl_baikal_brone";
            case 25:
                return ship_id = "istl_kormoran";
            case 26:
                return ship_id = "istl_tonnerre";
            case 27:
                return ship_id = "istl_zelenograd";
            case 28:
                return ship_id = "istl_jeannedarc";
            default:
                break;
        }
        return null;
    }
    
    private String SCYship_id() {
        int number = new HyperionRandomizer(24).value;
        String ship_id;

        //Ship Randomizers
        switch (number) {
            case 0:
                return ship_id = "SCY_alecto";
            case 1:
                return ship_id = "SCY_tisiphone";
            case 2:
                return ship_id = "SCY_centaur";
            case 3:
                return ship_id = "SCY_lealaps";
            case 4:
                return ship_id = "SCY_talos";
            case 5:
                return ship_id = "SCY_megaera";
            case 6:
                return ship_id = "SCY_stymphalianbird";
            case 7:
                return ship_id = "SCY_argus";
            case 8:
                return ship_id = "SCY_baliusF";
            case 9:
                return ship_id = "SCY_baliusT";
            case 10:
                return ship_id = "SCY_geryon";
            case 11:
                return ship_id = "SCY_lamia";
            case 12:
                return ship_id = "SCY_lamiaA";
            case 13:
                return ship_id = "SCY_hydra";
            case 14:
                return ship_id = "SCY_pyraemon";
            case 15:
                return ship_id = "SCY_corocotta";
            case 16:
                return ship_id = "SCY_corocottaA";
            case 17:
                return ship_id = "SCY_dracanae";
            case 18:
                return ship_id = "SCY_manticore";
            case 19:
                return ship_id = "SCY_erymanthianboar";
            case 20:
                return ship_id = "SCY_siren";
            case 21:
                return ship_id = "SCY_khalkotauroi";
            case 22:
                return ship_id = "SCY_keto";
            case 23:
                return ship_id = "SCY_nemeanlion";
            case 24:
                return ship_id = "SCY_xanthus";
            default:
                break;
        }
        return null;
    }
}
