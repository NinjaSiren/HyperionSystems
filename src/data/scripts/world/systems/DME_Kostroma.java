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
 * 
 * Makes the DME planet Marie Galante a Hyperion planet.
 * 
 */
public class DME_Kostroma {
    
    // Roll the dice
    private float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return ((min + (max - min)) * rand.nextFloat());
    }

    public void generate(SectorAPI sector) {
        StarSystemAPI system = Global.getSector().getStarSystem("Kostroma");
         
        // Marie-Galante
        PlanetAPI mariegalante = system.getPlanets().get(3);
        mariegalante.setCustomDescriptionId("hs_planet_mariegalante");
        mariegalante.setInteractionImage("illustrations", "urban02");
        
        // Replaces decivilized condition to subpop only
        if(mariegalante.getMarket().hasCondition(Conditions.DECIVILIZED)) {
            mariegalante.getMarket().removeCondition(Conditions.DECIVILIZED);
            mariegalante.getMarket().addCondition(Conditions.DECIVILIZED_SUBPOP);
        }
        
        // Gets current conditions
        List<MarketConditionAPI> mariegalanteBaseConditions = mariegalante.getMarket().getConditions();
        
        // "Abandoned" orbital terminal
        SectorEntityToken station_mariegalante = system.getEntityById("mariegalante_station");
        station_mariegalante.setInteractionImage("illustrations", "hound_hangar");
        station_mariegalante.setCustomDescriptionId("hs_mariegalante_station");
        
        MarketAPI mariegalanteMarket = HS_AddMarketplace.addMarketplace(
                "HS_Corporation_Separatist", 
                mariegalante, 
                new ArrayList<>(Arrays.asList(station_mariegalante)),
                "Marie-Galante", // name of the market
                7, // size of the market (from the JSON)
                new ArrayList<>(
                        Arrays.asList( // list of market conditions from mariegalante.json
                                Conditions.URBANIZED_POLITY,
                                Conditions.POPULATION_7)),
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
                    mariegalanteMarket.addIndustry(cryos);
                }   
        
                // Adds current conditions
                new HS_ReAddConditions(mariegalanteBaseConditions, mariegalanteMarket);
                
                // Adds industries depending on conditions
                new HS_AddIndustry(mariegalante, mariegalanteMarket);
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
