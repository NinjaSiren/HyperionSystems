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
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import data.scripts.world.HS_AddMarketplace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class Base_Penelope extends Penelope {
    
    @Override
    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Penelope's Star");
        
        //Ithaca
        PlanetAPI ithaca = system.getPlanets().get(3);
        ithaca.setCustomDescriptionId("hs_planet_ithaca_desc");
        
        SectorEntityToken station_ithaca = system.addCustomEntity("ithaca_station", "Penelope Station", "station_mining00", "HS_Corporation_Separatist");
        station_ithaca.setCircularOrbitPointingDown(ithaca, 45, 420, 45);
        station_ithaca.setInteractionImage("illustrations", "urban01");
        
        MarketAPI ithacaMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", ithaca, new ArrayList<>(Arrays.asList(station_ithaca)),
                "Ithaca", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from ithaca.json
                                Conditions.INDUSTRIAL_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.STARFORTRESS_HIGH,
                                Industries.MINING,
                                Industries.REFINING,
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
                ithacaMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));   
                ithacaMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));                          

        station_ithaca.setCustomDescriptionId("ithaca_station");
        
        //Xuthus
        PlanetAPI xuthus = system.getPlanets().get(8);
        xuthus.setCustomDescriptionId("hs_planet_xuthus_desc");
        
        SectorEntityToken station_xuthus = system.addCustomEntity("xuthus_station", "Penelope Station", "station_mining00", "HS_Corporation_Separatist");
        station_xuthus.setCircularOrbitPointingDown(xuthus, 45, 420, 45);
        station_xuthus.setInteractionImage("illustrations", "urban02");
        
        MarketAPI xuthusMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", xuthus, new ArrayList<>(Arrays.asList(station_xuthus)),
                "Xuthus", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from xuthus.json
                                Conditions.INDUSTRIAL_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.STARFORTRESS_HIGH,
                                Industries.MINING,
                                Industries.REFINING,
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
                xuthusMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));   
                xuthusMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));                          

        station_xuthus.setCustomDescriptionId("xuthus_station");
        
        //Telepylus Station
        SectorEntityToken telepylus_station = system.getEntityById("telepylus_station");
        telepylus_station.setCustomDescriptionId("hs_station_telepylus");
        telepylus_station.setInteractionImage("illustrations", "cargo_loading");
        
        MarketAPI telepylusMarket = HS_AddMarketplace.addMarketplace(
                "HS_Corporation_Separatist", 
                telepylus_station, 
                new ArrayList<>(Arrays.asList(telepylus_station)),
                "Telepylus Station", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from telepylus.json
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_5)),
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
                0.12f); // tariff amount
                telepylusMarket.addIndustry(Industries.ORBITALWORKS, new ArrayList<>(Arrays.asList(Items.PRISTINE_NANOFORGE)));    
                telepylusMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));     
                
        List<SectorEntityToken> stable_loc = system.getEntitiesWithTag("stable_location");
        
        //Nav Buoy
        stable_loc.get(0).addTag("nav_buoy");
        
        //Comm Relay
        stable_loc.get(1).addTag("comm_relay");
        
        //Sensor Array
        stable_loc.get(2).addTag("sensor_array");
    }
}
