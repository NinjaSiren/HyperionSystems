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
import data.scripts.world.AddMarketplace;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author NinjaSiren
 */
public class DME_Kostroma {
/*
    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Kostroma");
        PlanetAPI mariegalante = system.getPlanets().get(3);
        mariegalante.setCustomDescriptionId("dme_planet_mariegalante");
        
        SectorEntityToken station_mariegalante = system.addCustomEntity("mariegalante_station", "Kostroma Space Station", "station_mining00", "HS_Corporation_Separatist");
        station_mariegalante.setCircularOrbitPointingDown(system.getEntityById("istl_planet_mariegalante"), 45, 420, 45);
        station_mariegalante.setInteractionImage("illustrations", "orbital");
        
        MarketAPI mariegalanteMarket = AddMarketplace.addMarketplace("HS_Corporation_Separatist", mariegalante, new ArrayList<>(Arrays.asList(station_mariegalante)),
                "Marie Galante", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from mariegalante.json
                                Conditions.DECIVILIZED_SUBPOP,
                                Conditions.URBANIZED_POLITY,
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
                mariegalanteMarket.addIndustry(Industries.FUELPROD, new ArrayList<>(Arrays.asList(Items.SYNCHROTRON)));                           

        station_mariegalante.setCustomDescriptionId("mariegalante_station");
    }
    */
}
