/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.world.procgen.HS_AddIndustry;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class checkAllSystems {
    
    public checkAllSystems() {
        List<LocationAPI> marketLocations = Global.getSector().getEconomy().getLocationsWithMarkets();
        int locCount = marketLocations.size() - 1;
        int locCounter = 0;
        int marCounter = 0;
        
        do {
            List<MarketAPI> locMarkets = Global.getSector().getEconomy().getMarkets(marketLocations.get(locCounter));
            int marCount = locMarkets.size() - 1;
            do {
                MarketAPI market = locMarkets.get(marCount);
                FactionAPI faction = market.getFaction();
                StarSystemAPI sector = market.getStarSystem();
                PlanetAPI planet = market.getPlanetEntity();
                
                if(!market.getFactionId().equals("HS_Corporation_Separatist")) {
                    new HS_AddIndustry(planet, market, faction, sector);
                    marCounter++;
                } else {
                    marCounter = 0;
                }
            } while(marCount > marCounter);
            locCounter++;
        } while(locCount > locCounter);
    }
}
