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
public class DME_Nikolaev {
    
    // Roll the dice
    private float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return ((min + (max - min)) * rand.nextFloat());
    }
    
    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Nikolaev");
        
        // Odessa
        PlanetAPI odessa = system.getPlanets().get(2);
        odessa.setCustomDescriptionId("hs_planet_odessa");
        odessa.setInteractionImage("illustrations", "urban02");
        
        // Replaces decivilized condition to subpop only
        if(odessa.getMarket().hasCondition(Conditions.DECIVILIZED)) {
            odessa.getMarket().removeCondition(Conditions.DECIVILIZED);
            odessa.getMarket().addCondition(Conditions.DECIVILIZED_SUBPOP);
        }
        
        // Gets current conditions
        List<MarketConditionAPI> odessaBaseConditions = odessa.getMarket().getConditions();
        
        // Odessa Station
        SectorEntityToken station_odessa = system.addCustomEntity("hs_odessa_station", 
                "Hyperion Odessa Station", 
                "orbital_habitat",
                "HS_Corporation_Separatist");
        station_odessa.setCircularOrbitPointingDown(station_odessa, 285, 180, 36);
        station_odessa.setInteractionImage("illustrations", "hound_hangar");
        station_odessa.setCustomDescriptionId("hs_odessa_station");
        
        MarketAPI odessaMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", 
                odessa, 
                new ArrayList<>(Arrays.asList(station_odessa)),
                "Odessa", // name of the market
                6, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from odessa.json
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_6)),
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
                    odessaMarket.addIndustry(cryos);
                }   
        
                // Adds current conditions
                new HS_ReAddConditions(odessaBaseConditions, odessaMarket);
                
                // Adds industries depending on conditions
                new HS_AddIndustry(odessa, odessaMarket);
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
}
