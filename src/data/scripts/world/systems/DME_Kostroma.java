package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import data.scripts.world.HS_AddMarketplace;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author NinjaSiren
 * 
 * Makes the DME planet Marie Galante a Hyperion planet.
 * 
 */
public class DME_Kostroma {
    
    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Kostroma");
        PlanetAPI mariegalante = system.getPlanets().get(4);
        mariegalante.setCustomDescriptionId("hs_planet_mariegalante_desc");
        
        SectorEntityToken station_mariegalante = system.addCustomEntity("mariegalante_station", "Kostroma Station", "station_mining00", "HS_Corporation_Separatist");
        station_mariegalante.setCircularOrbitPointingDown(mariegalante, 45, 420, 45);
        station_mariegalante.setInteractionImage("illustrations", "urban03");
        
        MarketAPI mariegalanteMarket = HS_AddMarketplace.addMarketplace("HS_Corporation_Separatist", mariegalante, new ArrayList<>(Arrays.asList(station_mariegalante)),
                "Marie Galante", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from mariegalante.json
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_7)),
                new ArrayList<>
                        (Arrays.asList( // list of industries
                                Industries.STARFORTRESS_HIGH,
                                Industries.HIGHCOMMAND,
                                Industries.FARMING,
                                Industries.MINING,
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
        mariegalante.setMarket(mariegalanteMarket);

        station_mariegalante.setCustomDescriptionId("mariegalante_station");
    }
}
