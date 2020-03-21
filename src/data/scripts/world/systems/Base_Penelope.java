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
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import data.scripts.world.HS_AddIndustry;
import data.scripts.world.HS_AddMarketplace;
import data.scripts.world.HS_ReAddConditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class Base_Penelope extends Penelope {
    
    // Roll the dice
    private float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return ((min + (max - min)) * rand.nextFloat());
    }
    
    @Override
    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Penelope's Star");
        
        //Ithaca
        PlanetAPI ithaca = system.getPlanets().get(2);
        ithaca.setCustomDescriptionId("hs_planet_ithaca");
        
        // Replaces decivilized condition to subpop only
        if(ithaca.getMarket().hasCondition(Conditions.DECIVILIZED)) {
            ithaca.getMarket().removeCondition(Conditions.DECIVILIZED);
            ithaca.getMarket().addCondition(Conditions.DECIVILIZED_SUBPOP);
        }
        
        // Gets current conditions
        List<MarketConditionAPI> ithacaBaseConditions = ithaca.getMarket().getConditions();
        
        MarketAPI ithacaMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", 
                ithaca, 
                null,
                "Ithaca", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from ithaca.json
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.WAYSTATION,
                                Industries.MEGAPORT,
                                Industries.PATROLHQ,
                                Industries.HEAVYBATTERIES,
                                Industries.POPULATION)),
                new ArrayList<>(
                        Arrays.asList( // which submarkets to generate
                                Submarkets.SUBMARKET_BLACK,
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE)),
                tariff()); // tariff amount                        
                
                // Adds current conditions
                new HS_ReAddConditions(ithacaBaseConditions, ithacaMarket);
                
                // Adds industries depending on conditions
                new HS_AddIndustry(ithaca, ithacaMarket);
                
        //Xuthus
        PlanetAPI xuthus = system.getPlanets().get(7);
        xuthus.setCustomDescriptionId("hs_planet_xuthus");
        
        // Replaces decivilized condition to subpop only
        if(xuthus.getMarket().hasCondition(Conditions.DECIVILIZED)) {
            xuthus.getMarket().removeCondition(Conditions.DECIVILIZED);
            xuthus.getMarket().addCondition(Conditions.DECIVILIZED_SUBPOP);
        }
        
        // Gets current conditions
        List<MarketConditionAPI> xuthusBaseConditions = xuthus.getMarket().getConditions();
        
        MarketAPI xuthusMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", 
                xuthus, 
                null,
                "Xuthus", // name of the market
                5, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from xuthus.json
                                Conditions.INDUSTRIAL_POLITY,
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
                
                // Adds current conditions
                new HS_ReAddConditions(xuthusBaseConditions, xuthusMarket);
                
                // Adds industries depending on conditions
                new HS_AddIndustry(xuthus, xuthusMarket);
               
        //Ismara
        PlanetAPI ismara = system.getPlanets().get(9);
        ismara.setCustomDescriptionId("hs_planet_ismara");
        
        // Replaces decivilized condition to subpop only
        if(ismara.getMarket().hasCondition(Conditions.DECIVILIZED)) {
            ismara.getMarket().removeCondition(Conditions.DECIVILIZED);
            ismara.getMarket().addCondition(Conditions.DECIVILIZED_SUBPOP);
        }
        
        // Gets current conditions
        List<MarketConditionAPI> ismaraBaseConditions = ismara.getMarket().getConditions();
        
        MarketAPI ismaraMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", 
                ismara, 
                null,
                "Ismara", // name of the market
                6, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from ismara.json
                                Conditions.INDUSTRIAL_POLITY,
                                Conditions.POPULATION_6)),
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
                
                // Adds current conditions
                new HS_ReAddConditions(ismaraBaseConditions, ismaraMarket);
                
                // Adds industries depending on conditions
                new HS_AddIndustry(ismara, ismaraMarket);
                
        //Telepylus Station
        SectorEntityToken telepylus_station = system.getEntityById("telepylus_station");
        telepylus_station.setCustomDescriptionId("hs_station_telepylus");
        telepylus_station.setInteractionImage("illustrations", "cargo_loading");
        
        MarketAPI telepylusMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", 
                telepylus_station, 
                null,
                "Telepylus Station", // name of the market
                5, // size of the market (from the JSON)
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
                tariff()); // tariff amount
                telepylusMarket.addIndustry(Industries.ORBITALWORKS, 
                        new ArrayList<>(Arrays.asList(randNanoforge())));    
                telepylusMarket.addIndustry(Industries.FUELPROD, 
                        new ArrayList<>(Arrays.asList(randSynchotron()))); 
                
                String cryos = cryoIndustries();
                if(cryos != null) {
                    telepylusMarket.addIndustry(cryos);
                }      
                
        List<SectorEntityToken> stable_loc = system.getEntitiesWithTag("stable_location");
        
        //Nav Buoy
        stable_loc.get(0).addTag("nav_buoy");
        
        //Comm Relay
        stable_loc.get(1).addTag("comm_relay");
        
        //Sensor Array
        stable_loc.get(2).addTag("sensor_array");
    }
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return ((min + (max - min)) * rand.nextDouble());
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
    
    private String randNanoforge() {
        if(rand() <= 0.33) return Items.PRISTINE_NANOFORGE;
        else if(rand() > 0.33 && rand() <= 0.66) return Items.DECAYED_NANOFORGE;
        else if(rand() > 0.66) return Items.CORRUPTED_NANOFORGE;
        return "";
    }
    
    private String randSynchotron() {
        if(rand() <= 0.5) return Items.SYNCHROTRON;
        else return "";
    }
}
