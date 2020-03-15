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
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.PersonAPI;

import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.DerelictShipEntityPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.procgen.DefenderDataOverride;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.ProcgenUsedNames;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.DerelictThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.SalvageSpecialAssigner;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.ShipRecoverySpecial;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin.AsteroidFieldParams;
import com.fs.starfarer.api.impl.campaign.terrain.DebrisFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin.MagneticFieldParams;
import com.fs.starfarer.api.util.Misc;
import data.scripts.world.AddMarketplace;

/**
 *
 * @author NinjaSiren
 */
public class HS_neuejangala {

    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Neue Jangala");
	LocationAPI hyper = Global.getSector().getHyperspace();
        ProcgenUsedNames.notifyUsed("Neue");
        system.setBackgroundTextureFilename("graphics/backgrounds/background5.jpg");
        
        // create the star and generate the hyperspace anchor for this system
        PlanetAPI star = system.initStar("neuejangala", // unique id for this star 
            "star_yellow",  // id in planets.json
            600f, // radius (in pixels at default zoom)
            800, // corona radius, from star edge
            4f, // solar wind burn level
            0.3f, // flare probability
            1.6f); // cr loss mult

        system.setLightColor(new Color(235, 235, 225)); // light color in entire system, affects all entities
        
        SectorEntityToken neuejangala_field1 = system.addTerrain(Terrain.MAGNETIC_FIELD,
                                new MagneticFieldTerrainPlugin.MagneticFieldParams(400f, // terrain effect band width 
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
                                new Color(127, 0, 255)
                                ));
        neuejangala_field1.setCircularOrbit(star, 0, 0, 120);
        
        // Inner dust belt.
        system.addRingBand(star, "misc", "rings_dust0", 256f, 3, Color.white, 256f, 1650, 90f, Terrain.RING, "Neue Jangala Dustband");

                                             /*
                                            * addPlanet() parameters:
                                            * 1. Unique id for this planet (or null to have it be autogenerated)
                                            * 2. What the planet orbits (orbit is always circular)
                                            * 3. Name
                                            * 4. Planet type id in planets.json
                                            * 5. Starting angle in orbit, i.e. 0 = to the right of the star
                                            * 6. Planet radius, pixels at default zoom
                                            * 7. Orbit radius, pixels at default zoom
                                            * 8. Days it takes to complete an orbit. 1 day = 10 seconds.
                                            */              
        
        // Neue Jangala, home of the Hyperion Confederate capital.
        PlanetAPI neuejangala = system.addPlanet("hs_planet_neuejangala", star, "Neue Jangala", "terran", 146, 160, 2400, 60);
        neuejangala.setCustomDescriptionId("hs_planet_neuejangala");
                
        // Home sweet home port.
        SectorEntityToken station_neuejangala = system.addCustomEntity("nuuk_port", "Nuuk Base", "station_mining00", "HS_Corporation_Separatist");
        station_neuejangala.setCircularOrbitPointingDown(system.getEntityById("hs_planet_neuejangala"), 45, 420, 45);
        station_neuejangala.setInteractionImage("illustrations", "orbital");

        // add the marketplace to Amazonia and Amazonia Highport.
        MarketAPI neuejangalaMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", neuejangala, new ArrayList<>(Arrays.asList(station_neuejangala)),
                "Neue Jangala", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from neuejangala.json
                                Conditions.HABITABLE,
                                Conditions.MILD_CLIMATE,
                                Conditions.TERRAN,
                                Conditions.RUINS_EXTENSIVE,
                                Conditions.FARMLAND_BOUNTIFUL,
                                Conditions.ORGANICS_ABUNDANT,
                                Conditions.ORE_MODERATE,
                                Conditions.RARE_ORE_MODERATE,                  
                                Conditions.URBANIZED_POLITY,
                                Conditions.REGIONAL_CAPITAL,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.STARFORTRESS_HIGH,
                                Industries.HIGHCOMMAND,
                                Industries.FARMING,
                                Industries.MINING,
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
                0.12f); // tariff amount
                neuejangalaMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));                  

            station_neuejangala.setCustomDescriptionId("nuuk_base");
                        
        // Inner system nav buoy
	SectorEntityToken neuejangala_inner_buoy = system.addCustomEntity(null, "Neue Jangala Nav Buoy", "nav_buoy", "HS_Corporation_Separatist"); 
	neuejangala_inner_buoy.setCircularOrbitPointingDown(star, 90, 2700, 60);
        
                                             /*
                                            * addPlanet() parameters:
                                            * 1. Unique id for this planet (or null to have it be autogenerated)
                                            * 2. What the planet orbits (orbit is always circular)
                                            * 3. Name
                                            * 4. Planet type id in planets.json
                                            * 5. Starting angle in orbit, i.e. 0 = to the right of the star
                                            * 6. Planet radius, pixels at default zoom
                                            * 7. Orbit radius, pixels at default zoom
                                            * 8. Days it takes to complete an orbit. 1 day = 10 seconds.
                                            */           
        
        // Venusia, a large volcanic world too close to its parent star.
        PlanetAPI venusia = system.addPlanet("hs_planet_venusia", star, "Venusia", "lava", 13, 90, 1400, 45);
        venusia.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        venusia.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        venusia.getSpec().setUseReverseLightForGlow(true);
        venusia.applySpecChanges();
        venusia.setInteractionImage("illustrations", "urban00");         
        venusia.setCustomDescriptionId("hs_planet_venusia");

        // Home sweet home port.
        SectorEntityToken station_venusia = system.addCustomEntity("venusia_port", "Venusia Port", "station_mining00", "HS_Corporation_Separatist");
        station_venusia.setCircularOrbitPointingDown(system.getEntityById("hs_planet_venusia"), 45, 420, 45);
        station_venusia.setInteractionImage("illustrations", "orbital");

        // add the marketplace to Amazonia and Amazonia Highport.
        MarketAPI venusiaMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", venusia, new ArrayList<>(Arrays.asList(station_venusia)),
                "Venusia", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from venusia.json
                                Conditions.DENSE_ATMOSPHERE,
                                Conditions.VERY_HOT,
                                Conditions.IRRADIATED,
                                Conditions.EXTREME_TECTONIC_ACTIVITY,
                                Conditions.RUINS_SCATTERED,
                                Conditions.ORE_ULTRARICH,
                                Conditions.RARE_ORE_ULTRARICH,                
                                Conditions.INDUSTRIAL_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.MINING,
                                Industries.REFINING,
                                Industries.LIGHTINDUSTRY,
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
                0.12f); // tariff amount
                venusiaMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));        
        
        SectorEntityToken venusia_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
                        new MagneticFieldTerrainPlugin.MagneticFieldParams(60f, // terrain effect band width 
                        110, // terrain effect middle radius
                        venusia, // entity that it's around
                        80f, // visual band start
                        140f, // visual band end
                        new Color(50, 30, 100, 30), // base color
                        0.6f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
                        new Color(50, 20, 110, 130),
                        new Color(200, 50, 130, 190),
                        new Color(250, 70, 150, 240),
                        new Color(200, 80, 130, 255),
                        new Color(75, 0, 160), 
                        new Color(127, 0, 255)
                        ));
        venusia_field.setCircularOrbit(venusia, 0, 0, 130);        
        
                                            /*
                                            * addPlanet() parameters:
                                            * 1. Unique id for this planet (or null to have it be autogenerated)
                                            * 2. What the planet orbits (orbit is always circular)
                                            * 3. Name
                                            * 4. Planet type id in planets.json
                                            * 5. Starting angle in orbit, i.e. 0 = to the right of the star
                                            * 6. Planet radius, pixels at default zoom
                                            * 7. Orbit radius, pixels at default zoom
                                            * 8. Days it takes to complete an orbit. 1 day = 10 seconds.
                                            */        
        
        // Mercurios, a desert planet with alot of ores.
        PlanetAPI mercurios = system.addPlanet("hs_planet_mercurios", star, "Mercurios", "desert", 58, 125, 3200    , 53);
        mercurios.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        mercurios.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        mercurios.getSpec().setUseReverseLightForGlow(true);
        mercurios.applySpecChanges();
        mercurios.setInteractionImage("illustrations", "urban00");         
        mercurios.setCustomDescriptionId("hs_planet_mercurios");

        // Home sweet home port.
        SectorEntityToken station_mercurios = system.addCustomEntity("mercurios_station", "Mercurios Station", "station_mining00", "HS_Corporation_Separatist");
        station_mercurios.setCircularOrbitPointingDown(system.getEntityById("hs_planet_mercurios"), 45, 420, 45);
        station_mercurios.setInteractionImage("illustrations", "orbital");        
        
        // add the marketplace to Amazonia and Amazonia Highport.
        MarketAPI mercuriosMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", mercurios, new ArrayList<>(Arrays.asList(station_mercurios)),
                "Mercurios", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from mercurios.json
                                Conditions.HABITABLE,
                                Conditions.DESERT,
                                Conditions.HOT,
                                Conditions.RUINS_WIDESPREAD,
                                Conditions.FARMLAND_POOR,
                                Conditions.ORGANICS_COMMON,
                                Conditions.ORE_ULTRARICH,                
                                Conditions.RARE_ORE_ULTRARICH,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.FARMING,
                                Industries.MINING,
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
                0.12f); // tariff amount
                mercuriosMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));    
                mercuriosMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));
        
        SectorEntityToken mercurios_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
			new MagneticFieldTerrainPlugin.MagneticFieldParams(60f, // terrain effect band width 
			110, // terrain effect middle radius
			mercurios, // entity that it's around
			80f, // visual band start
			140f, // visual band end
			new Color(50, 30, 100, 30), // base color
			0.6f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
			new Color(50, 20, 110, 130),
			new Color(200, 50, 130, 190),
			new Color(250, 70, 150, 240),
			new Color(200, 80, 130, 255),
			new Color(75, 0, 160), 
			new Color(127, 0, 255)
			));
        mercurios_field.setCircularOrbit(mercurios, 0, 0, 150);                                             
                                             
        // Amazonia, where a loathesome Putinoid met his end.
        PlanetAPI amazonia = system.addPlanet("hs_planet_amazonia", star, "Amazonia", "jungle", 257, 200, 5200, 100);
        amazonia.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
        amazonia.getSpec().setGlowColor(new Color(255, 255, 255, 255));
        amazonia.getSpec().setUseReverseLightForGlow(true);
        amazonia.applySpecChanges();
        amazonia.setInteractionImage("illustrations", "urban00"); 
        amazonia.setCustomDescriptionId("hs_planet_amazonia");

        // Home sweet home port.
        SectorEntityToken station_amazonia = system.addCustomEntity("amazonia_station", "Mercurios Station", "station_mining00", "HS_Corporation_Separatist");
        station_amazonia.setCircularOrbitPointingDown(system.getEntityById("hs_planet_amazonia"), 45, 420, 45);
        station_amazonia.setInteractionImage("illustrations", "orbital");        
        
        // add the marketplace to Amazonia and Amazonia Highport.
        MarketAPI amazoniaMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", amazonia, new ArrayList<>(Arrays.asList(station_amazonia)),
                "Mercurios", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from amazonia.json
                                Conditions.HABITABLE,
                                Conditions.MILD_CLIMATE,
                                Conditions.INIMICAL_BIOSPHERE,
                                Conditions.FARMLAND_BOUNTIFUL,
                                Conditions.ORGANICS_PLENTIFUL,
                                Conditions.ORE_RICH,
                                Conditions.RARE_ORE_RICH,
                                Conditions.RUINS_VAST,                
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.FARMING,
                                Industries.MINING,
                                Industries.TECHMINING,
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
                0.12f); // tariff amount
                amazoniaMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));          
        
        // Add a jump point to the system.
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint("neuejangala_jump_point_alpha", "Neue Jangala Passage");
        OrbitAPI orbit = Global.getFactory().createCircularOrbit(star, 60, 5200, 100);
        jumpPoint1.setOrbit(orbit);
        jumpPoint1.setRelatedPlanet(amazonia);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint1);

        // Add a comm relay to the system.
        SectorEntityToken neuejangala_relay = system.addCustomEntity("neuejangala_relay", // unique id
                "Neue Jangala Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "HS_Corporation_Separatist"); // faction
        neuejangala_relay.setCircularOrbitPointingDown(star, -60, 5200, 100);

        // And now, the outer system.
        system.addRingBand(star, "misc", "rings_dust0", 256f, 2, Color.gray, 256f, 6280, 203f, null, null);
        system.addRingBand(star, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 6320, 211f, null, null);
        system.addAsteroidBelt(star, 150, 6300, 170, 200, 250, Terrain.ASTEROID_BELT, "Neue Jangala Belt");

        system.addRingBand(star, "misc", "rings_ice0", 256f, 3, Color.white, 256f, 6670, 220f, null, null);
        system.addRingBand(star, "misc", "rings_asteroids0", 256f, 2, Color.white, 256f, 6715, 223f, null, null);
        system.addRingBand(star, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 6760, 226f, null, null);
        system.addAsteroidBelt(star, 150, 6600, 170, 200, 250, Terrain.ASTEROID_BELT, "Neue Jangala Belt");
        system.addAsteroidBelt(star, 100, 6750, 128, 200, 300, Terrain.ASTEROID_BELT, "Neue Jangala Belt");

            // Belt debris fields.
            DebrisFieldTerrainPlugin.DebrisFieldParams params1 = new DebrisFieldTerrainPlugin.DebrisFieldParams(
                450f, // field radius - should not go above 1000 for performance reasons
                1.2f, // density, visual - affects number of debris pieces
                10000000f, // duration in days 
                0f); // days the field will keep generating glowing pieces
            params1.source = DebrisFieldTerrainPlugin.DebrisFieldSource.MIXED;
            params1.baseSalvageXP = 500; // base XP for scavenging in field
            SectorEntityToken debrisBelt1 = Misc.addDebrisField(system, params1, StarSystemGenerator.random);
            debrisBelt1.setSensorProfile(1000f);
            debrisBelt1.setDiscoverable(true);
            debrisBelt1.setCircularOrbit(star, 120f, 6480, 240f);
            debrisBelt1.setId("neuejangala_debrisBelt1");

            DebrisFieldTerrainPlugin.DebrisFieldParams params2 = new DebrisFieldTerrainPlugin.DebrisFieldParams(
                300f, // field radius - should not go above 1000 for performance reasons
                1.2f, // density, visual - affects number of debris pieces
                10000000f, // duration in days 
                0f); // days the field will keep generating glowing pieces
            params2.source = DebrisFieldTerrainPlugin.DebrisFieldSource.MIXED;
            params2.baseSalvageXP = 500; // base XP for scavenging in field
            SectorEntityToken debrisBelt2 = Misc.addDebrisField(system, params2, StarSystemGenerator.random);
            debrisBelt2.setSensorProfile(1000f);
            debrisBelt2.setDiscoverable(true);
            debrisBelt2.setCircularOrbit(star, 360f, 6480, 240f);
            debrisBelt2.setId("neuejangala_debrisBelt2");

        // Abandoned Belt Station.
        SectorEntityToken neutralStation = system.addCustomEntity("neuejangala_abandoned_station", "Abandoned Station", "station_mining00", "neutral");
        neutralStation.setCircularOrbitWithSpin(star, 240, 6480, 240, 5, 15);
        neutralStation.setDiscoverable(true);
        neutralStation.setDiscoveryXP(1500f);
        neutralStation.setSensorProfile(0.25f);
        neutralStation.setCustomDescriptionId("neuejangala_beltstation");
        neutralStation.setInteractionImage("illustrations", "abandoned_station2");

        neutralStation.getMemoryWithoutUpdate().set("$abandonedStation", true);
        Misc.setAbandonedStationMarket("neuejangala_abandoned_station_market", neutralStation);
        neutralStation.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo().addCommodity("metals", 500);
        neutralStation.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo().addCommodity("rare_metals", 150);
        neutralStation.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo().addCommodity("food", 100);
        neutralStation.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo().addCommodity("heavy_machinery", 50);

        //Salvage in belt.
        SectorEntityToken scrap1 = DerelictThemeGenerator.addSalvageEntity(system, Entities.EQUIPMENT_CACHE_SMALL, Factions.DERELICT);
        scrap1.setId("neuejangala_scrap1");
        scrap1.setCircularOrbit(star, 165, 6420, 223);
        Misc.setDefenderOverride(scrap1, new DefenderDataOverride(Factions.DERELICT, 0, 0, 0));
        scrap1.setDiscoverable(Boolean.TRUE);

        SectorEntityToken scrap2 = DerelictThemeGenerator.addSalvageEntity(system, Entities.WEAPONS_CACHE_SMALL_REMNANT, Factions.DERELICT);
        scrap2.setId("neuejangala_scrap2");
        scrap2.setCircularOrbit(star, 285, 6420, 223);
        Misc.setDefenderOverride(scrap2, new DefenderDataOverride(Factions.DERELICT, 0, 0, 0));
        scrap2.setDiscoverable(Boolean.TRUE);

        SectorEntityToken scrap3 = DerelictThemeGenerator.addSalvageEntity(system, Entities.WEAPONS_CACHE_REMNANT, Factions.DERELICT);
        scrap3.setId("neuejangala_scrap3");
        scrap3.setCircularOrbit(star, 45, 6480, 223);
        Misc.setDefenderOverride(scrap3, new DefenderDataOverride(Factions.DERELICT, 0, 0, 0));
        scrap3.setDiscoverable(Boolean.TRUE);

        // Nikolaev Gate.
        SectorEntityToken gate = system.addCustomEntity("neuejangala_gate", // unique id
                 "Neue Jangala Gate", // name - if null, defaultName from custom_entities.json will be used
                 "inactive_gate", // type of object, defined in custom_entities.json
                 null); // faction

        gate.setCircularOrbit(star, 180+60, 8000, 400);

        //Add a second jump in Marutaina's L3 point. Depreciated because I can't get it to look nice.
        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint("neuejangala_jump_point_beta", "Neue Jangala Outer Jump-point");
        jumpPoint2.setCircularOrbit( star, -30, 10800, 270);
        jumpPoint2.setRelatedPlanet(null);
        system.addEntity(jumpPoint2);

        // Marutaina system.
        PlanetAPI marutaina = system.addPlanet("hs_planet_marutaina", star, "Marutaina", "gas_giant", 150, 500, 10800, 300);
        marutaina.getSpec().setPlanetColor(new Color(255, 210, 180, 255));
        marutaina.getSpec().setAtmosphereColor(new Color(135, 45, 15, 135));
        marutaina.getSpec().setCloudColor(new Color(215, 215, 200, 200));
        marutaina.getSpec().setIconColor(new Color(155, 125, 75, 255));
        marutaina.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "aurorae"));
        marutaina.getSpec().setGlowColor(new Color(135, 45, 15, 135));
        marutaina.getSpec().setUseReverseLightForGlow(true);
        marutaina.getSpec().setAtmosphereThickness(0.6f);
        marutaina.applySpecChanges();

        marutaina.getMarket().addCondition(Conditions.HIGH_GRAVITY);
        marutaina.getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
        marutaina.getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
        marutaina.getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
        
        marutaina.setCustomDescriptionId("hs_planet_marutaina");

        SectorEntityToken marutaina_field1 = system.addTerrain(Terrain.MAGNETIC_FIELD,
                new MagneticFieldParams(200f, // terrain effect band width 
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
                new MagneticFieldParams(400f, // terrain effect band width 
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

        // Marutaina Rings
        system.addRingBand(marutaina, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 1520, 70f);
        system.addRingBand(marutaina, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 1500, 80f);
        system.addRingBand(marutaina, "misc", "rings_ice0", 256f, 1, Color.white, 256f, 1445, 90f, Terrain.RING, "Marutaina Ring");

        // Marutaina Moons

        // Rouge, an Io-like volcanic hellhole.
        PlanetAPI titania = system.addPlanet("hs_planet_titania", marutaina, "Rouge", "lava", 36, 90, 960, 45);
        titania.setCustomDescriptionId("hs_planet_titania");

        // Add fixed conditions to Rouge.
        Misc.initConditionMarket(titania);
        titania.getMarket().addCondition(Conditions.HOT);
        titania.getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
        titania.getMarket().addCondition(Conditions.ORE_ULTRARICH);
        titania.getMarket().getFirstCondition(Conditions.ORE_ULTRARICH).setSurveyed(true);
        titania.getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
        titania.getMarket().getFirstCondition(Conditions.RARE_ORE_ULTRARICH).setSurveyed(true);

        SectorEntityToken titania_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
                        new MagneticFieldTerrainPlugin.MagneticFieldParams(90f, // terrain effect band width 
                        160, // terrain effect middle radius
                        titania, // entity that it's around
                        120f, // visual band start
                        210f, // visual band end
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

        // Noir, a barren shithole of little value.
        PlanetAPI sedina = system.addPlanet("hs_planet_sedinia", marutaina, "Noir", "barren", 180, 120, 2100, 80);
        sedina.getSpec().setPlanetColor(new Color(220, 245, 255, 255));
        sedina.setCustomDescriptionId("hs_planet_sedinia");

        // Add fixed conditions to Noir.
        Misc.initConditionMarket(sedina);
        sedina.getMarket().addCondition(Conditions.NO_ATMOSPHERE);
        sedina.getMarket().addCondition(Conditions.LOW_GRAVITY);
        sedina.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        sedina.getMarket().getFirstCondition(Conditions.VOLATILES_DIFFUSE).setSurveyed(true);

        // Marutaina tethers 
        SectorEntityToken marutaina_shade1 = system.addCustomEntity("marutaina_shade1", "Power Shade", "stellar_shade", "HS_Corporation_Separatist");
        marutaina_shade1.setCircularOrbitPointingDown(marutaina, 0, 720, 30);
        marutaina_shade1.setCustomDescriptionId("hs_powershade");

        SectorEntityToken marutaina_shade2 = system.addCustomEntity("marutaina_shade2", "Power Shade", "stellar_shade", "HS_Corporation_Separatist");
        marutaina_shade2.setCircularOrbitPointingDown(marutaina, 72, 720, 30);
        marutaina_shade2.setCustomDescriptionId("hs_powershade");

        SectorEntityToken marutaina_shade3 = system.addCustomEntity("marutaina_shade3", "Power Shade", "stellar_shade", "HS_Corporation_Separatist");
        marutaina_shade3.setCircularOrbitPointingDown(marutaina, 144, 720, 30);
        marutaina_shade3.setCustomDescriptionId("hs_powershade");

        SectorEntityToken marutaina_shade4 = system.addCustomEntity("marutaina_shade4", "Power Shade", "stellar_shade", "HS_Corporation_Separatist");
        marutaina_shade4.setCircularOrbitPointingDown(marutaina, 216, 720, 30);
        marutaina_shade4.setCustomDescriptionId("hs_powershade");

        SectorEntityToken marutaina_shade5 = system.addCustomEntity("marutaina_shade5", "Power Shade", "stellar_shade", "HS_Corporation_Separatist");
        marutaina_shade5.setCircularOrbitPointingDown(marutaina, 288, 720, 30);
        marutaina_shade5.setCustomDescriptionId("hs_powershade");

        // Metengard Base
        SectorEntityToken labStation = system.addCustomEntity("neuejangala_lab", "Metengard Base", "station_mining00", "HS_Corporation_Separatist");
        labStation.setCircularOrbitPointingDown(system.getEntityById("hs_planet_sedinia"), 30, 300, 30);

        // add the marketplace to Metengard Base ---------------
        MarketAPI lavoisierMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", labStation, null,
                "Metengard Base", // name of the market
                4, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from neuejangala.json
                                Conditions.FREE_PORT,
                                Conditions.OUTPOST,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.BATTLESTATION_HIGH,
                                Industries.HIGHCOMMAND,
                                Industries.WAYSTATION,
                                Industries.MEGAPORT,
                                Industries.HEAVYBATTERIES,
                                Industries.LIGHTINDUSTRY,
                                Industries.POPULATION)),
                new ArrayList<>(
                        Arrays.asList( // which submarkets to generate
                                Submarkets.SUBMARKET_BLACK,
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE)),
                0.12f); // tariff amount
                lavoisierMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));
                lavoisierMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));

        labStation.setCustomDescriptionId("neuejangala_lab");
        
        // Marutaina Trojans
        SectorEntityToken marutainaL4 = system.addTerrain(Terrain.ASTEROID_FIELD,
                new AsteroidFieldParams(
                        840f, // min radius
                        1080f, // max radius
                        35, // min asteroid count
                        64, // max asteroid count
                        7f, // min asteroid radius 
                        21f, // max asteroid radius
                        "Marutaina L4 Shoal Zone")); // null for default name

        // Juliette, debris and wrecks in Marutaina L4
        PlanetAPI mirar = system.addPlanet("hs_planet_mirar", star, "Juliette", "barren", 210, 40, 10850, 300);
        mirar.setCustomDescriptionId("hs_planet_mirar");

        // Add fixed conditions to Juliette.
        Misc.initConditionMarket(mirar);
        mirar.getMarket().addCondition(Conditions.NO_ATMOSPHERE);
        mirar.getMarket().addCondition(Conditions.LOW_GRAVITY);
        mirar.getMarket().addCondition(Conditions.RUINS_SCATTERED);
        mirar.getMarket().getFirstCondition(Conditions.RUINS_SCATTERED).setSurveyed(true);
        mirar.getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
        mirar.getMarket().getFirstCondition(Conditions.VOLATILES_DIFFUSE).setSurveyed(true);

        SectorEntityToken scrap4 = DerelictThemeGenerator.addSalvageEntity(system, Entities.SUPPLY_CACHE, Factions.DERELICT);
        scrap4.setId("neuejangala_scrap4");
        scrap4.setCircularOrbit(mirar, 105, 240, 135);
        Misc.setDefenderOverride(scrap4, new DefenderDataOverride(Factions.DERELICT, 0, 0, 0));
        scrap4.setDiscoverable(Boolean.TRUE);

        DebrisFieldTerrainPlugin.DebrisFieldParams params3 = new DebrisFieldTerrainPlugin.DebrisFieldParams(
            700f, // field radius - should not go above 1000 for performance reasons
            1.0f, // density, visual - affects number of debris pieces
            10000000f, // duration in days 
            0f); // days the field will keep generating glowing pieces
        params3.source = DebrisFieldTerrainPlugin.DebrisFieldSource.SALVAGE;
        params3.baseSalvageXP = 500; // base XP for scavenging in field
        SectorEntityToken debrisL4 = Misc.addDebrisField(system, params3, StarSystemGenerator.random);
        debrisL4.setSensorProfile(600f);
        debrisL4.setDiscoverable(true);
        debrisL4.setCircularOrbit(star, 210f, 10800, 300f);
        debrisL4.setId("neuejangala_debrisL4");

        // Add some ship derelicts in Juliette orbit.
        addDerelict(system, mirar, "HS_Naos_C_Sniper", ShipRecoverySpecial.ShipCondition.GOOD, 120f, (Math.random()<0.5));
        addDerelict(system, mirar, "HS_Orionis_C_Assault", ShipRecoverySpecial.ShipCondition.PRISTINE, 150f, true);
        addDerelict(system, mirar, "HS_Atlas_C_Offensive", ShipRecoverySpecial.ShipCondition.AVERAGE, 210f, (Math.random()<0.5));
        addDerelict(system, mirar, "HS_Alycone_C_Assault", ShipRecoverySpecial.ShipCondition.AVERAGE, 240f, (Math.random()<0.5));
        addDerelict(system, mirar, "HS_Perseus_Support", ShipRecoverySpecial.ShipCondition.BATTERED, 300f, (Math.random()<0.5));
        addDerelict(system, mirar, "HS_Betelgeuse_Defensive", ShipRecoverySpecial.ShipCondition.BATTERED, 360f, (Math.random()<0.5));
        addDerelict(system, mirar, "HS_Arcturus_Defensive", ShipRecoverySpecial.ShipCondition.BATTERED, 440f, true);
        addDerelict(system, mirar, "HS_Icarus_Support", ShipRecoverySpecial.ShipCondition.WRECKED, 520f, false);

        SectorEntityToken marutainaL5 = system.addTerrain(Terrain.ASTEROID_FIELD,
                new AsteroidFieldParams(
                        840f, // min radius
                        1080f, // max radius
                        35, // min asteroid count
                        64, // max asteroid count
                        7f, // min asteroid radius 
                        21f, // max asteroid radius
                        "Marutaina L5 Shoal Zone")); // null for default name

        marutainaL4.setCircularOrbit(star, 210f, 10800, 300);
        marutainaL5.setCircularOrbit(star, 90f, 10800, 300);

        // Marutaina L3 sensor array
        SectorEntityToken marutainaL3_array = system.addCustomEntity(null, "Neue Jangala Detector Array", "sensor_array", "HS_Corporation_Separatist"); 
        marutainaL3_array.setCircularOrbitPointingDown(star, 330, 10800, 300);

        // New Heaven (Marutaina L5 Port)
        SectorEntityToken pirStation = system.addCustomEntity("newheaven", "New Heaven", "station_sporeship_derelict", "pirates");
        pirStation.setCircularOrbitWithSpin(star, 90, 10850, 300, 7, 21);

        // add the marketplace to New Heaven ---------------
        MarketAPI pirbaseMarket = AddMarketplace.addMarketplace("pirates", pirStation, null,
                "New Heaven", // name of the market
                4, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from neuejangala.json
                                Conditions.FREE_PORT,
                                Conditions.STEALTH_MINEFIELDS,
                                Conditions.ORGANIZED_CRIME,
                                Conditions.POPULATION_4)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.BATTLESTATION,
                                Industries.WAYSTATION,
                                Industries.SPACEPORT,
                                Industries.POPULATION)),
                new ArrayList<>(
                        Arrays.asList( // which submarkets to generate
                                Submarkets.SUBMARKET_BLACK,
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE)),
                0.3f); // tariff amount

        pirStation.setCustomDescriptionId("station_newheaven");

        // Add some DME derelicts in New Heaven orbit.
        addDerelict(system, pirStation, "HS_Gemini_C_Offensive", ShipRecoverySpecial.ShipCondition.GOOD, 200f, (Math.random()<0.5));
        addDerelict(system, pirStation, "HS_Valkyrie_C_Defensive", ShipRecoverySpecial.ShipCondition.BATTERED, 320f, true);

        // Outer belt.
        system.addRingBand(star, "misc", "rings_dust0", 256f, 3, Color.white, 256f, 15200, 450f, Terrain.RING, "Neue Jangala Outer Band");

        system.addRingBand(star, "misc", "rings_dust0", 256f, 2, Color.gray, 256f, 15580, 453f, null, null);
        system.addRingBand(star, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 15620, 461f, null, null);
        system.addAsteroidBelt(star, 150, 15600, 170, 200, 520, Terrain.ASTEROID_BELT, "Neue Jangala Outer Belt");

        //procgen body in outer system.
        float radiusAfter = StarSystemGenerator.addOrbitingEntities(system, star, StarAge.AVERAGE,
                1, 3, // min/max entities to add
                16800, // radius to start adding at 
                5, // name offset - next planet will be <system name> <roman numeral of this parameter + 1>
                true); // whether to use custom or system-name based names
        system.autogenerateHyperspaceJumpPoints(true, true);

                HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
                NebulaEditor editor = new NebulaEditor(plugin);
                float minRadius = plugin.getTileSize() * 2f;

                float radius = system.getMaxRadiusInHyperspace();
                editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
                editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
    }

    private void addDerelict(StarSystemAPI system, 
            SectorEntityToken focus, 
            String variantId, 
            ShipRecoverySpecial.ShipCondition condition, 
            float orbitRadius, 
            boolean recoverable) {
        DerelictShipEntityPlugin.DerelictShipData params = new DerelictShipEntityPlugin.DerelictShipData(new ShipRecoverySpecial.PerShipData(variantId, condition), false);
        SectorEntityToken ship = BaseThemeGenerator.addSalvageEntity(system, Entities.WRECK, Factions.NEUTRAL, params);
        ship.setDiscoverable(true);

        float orbitDays = orbitRadius / (10f + (float) Math.random() * 5f);
        ship.setCircularOrbit(focus, (float) Math.random() * 360f, orbitRadius, orbitDays);

        if (recoverable) {
            SalvageSpecialAssigner.ShipRecoverySpecialCreator creator = new SalvageSpecialAssigner.ShipRecoverySpecialCreator(null, 0, 0, false, null, null);
            Misc.setSalvageSpecial(ship, creator.createSpecial(ship, null));
        }
    }
}
