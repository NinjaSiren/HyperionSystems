/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.world.procgen.HS_AddIndustry;

/**
 *
 * @author NinjaSiren
 */
public class checkAllSystems {
    
    public checkAllSystems() {
        int maxCount = Global.getSector().getStarSystems().size();
        
        while(maxCount >= 0) {
            int planetCount = Global.getSector().getStarSystems().get(maxCount).getPlanets().size();
            while(planetCount >= 0) {
                StarSystemAPI sector = Global.getSector().getStarSystems().get(maxCount);
                PlanetAPI planet = sector.getPlanets().get(planetCount);
                MarketAPI planetMarket = planet.getMarket();
                if(planetMarket.hasIndustry(Industries.POPULATION)) {
                    FactionAPI planetFaction = planetMarket.getFaction();
                    new HS_AddIndustry(planet, planetMarket, planetFaction, sector);
                }
            }
        }
    }
}
