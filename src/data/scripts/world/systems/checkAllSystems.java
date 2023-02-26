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
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import data.scripts.world.procgen.HS_AddIndustry;
import data.scripts.world.procgen.industries.setMarketItems;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class checkAllSystems {
    
    public checkAllSystems() {
        List<LocationAPI> marketLocations = Global.getSector().getAllLocations();
        List<StarSystemAPI> systems = Global.getSector().getStarSystems();
        EconomyAPI economies = Global.getSector().getEconomy();
        int sysCount = systems.size() - 1;
        int locCount = marketLocations.size() - 1;
        
        if(economies != null) {
            if(!marketLocations.isEmpty()) {
                for(int locCounter = 0; locCount > locCounter; locCounter++) {
                    List<MarketAPI> markets = economies.getMarkets(marketLocations.get(locCounter));
                    int marCount = markets.size() - 1;
                    
                    for(int marCounter = 0; marCount > marCounter; marCounter++) {
                        MarketAPI market = markets.get(marCounter);
                        StarSystemAPI sector = market.getStarSystem();
                        PlanetAPI planet = market.getPlanetEntity();
                        
                        if(market.hasSpaceport() || planet.hasCondition(Conditions.SPACEPORT)) {
                            FactionAPI faction = market.getFaction();
                            
                            if(market.isInEconomy()) {
                                if(!sector.getName().equals("Neue Jangala") && !sector.getName().equals("Diamant") &&
                                        !sector.getName().equals("Klat") && !sector.getName().equals("Nirraok") &&
                                        !sector.getName().equals("Phia") && !sector.getName().equals("Penelope's Star")) {
                                    
                                    List<Industry> industry = market.getIndustries();
                                    List<MarketConditionAPI> condition = market.getConditions();
                                    new setMarketItems(industry, condition, faction, market, planet);
                                    
                                    new HS_AddIndustry(planet, market, faction, sector);
                                }
                            }
                        }
                    }
                }   
            }
        }
        
        if(!marketLocations.isEmpty()) {
            for(int locCounter = 0; locCount > locCounter; locCounter++) {
                List<PlanetAPI> planets = marketLocations.get(locCounter).getPlanets();
                int plaCount = planets.size() - 1;

                for(int plaCounter = 0; plaCount > plaCounter; plaCounter++) {
                    StarSystemAPI sector = planets.get(plaCounter).getStarSystem();
                    PlanetAPI planet = planets.get(plaCounter);

                    if(planet.hasCondition(Conditions.SPACEPORT)) {
                        MarketAPI market = planet.getMarket();

                        if(market.isInEconomy()) {
                            FactionAPI faction = market.getFaction();

                            if(!sector.getName().equals("Neue Jangala") && !sector.getName().equals("Diamant") &&
                                    !sector.getName().equals("Klat") && !sector.getName().equals("Nirraok") &&
                                    !sector.getName().equals("Phia") && !sector.getName().equals("Penelope's Star")) {
                                
                                List<Industry> industry = market.getIndustries();
                                List<MarketConditionAPI> condition = market.getConditions();
                                new setMarketItems(industry, condition, faction, market, planet);
                                
                                new HS_AddIndustry(planet, market, faction, sector);
                            }
                        }
                    }
                }
            }
        }

        if(!systems.isEmpty()) {
            for(int sysCounter = 0; sysCount > sysCounter; sysCounter++) {
                StarSystemAPI sector = systems.get(sysCounter);
                List<PlanetAPI> planets = systems.get(sysCounter).getPlanets();
                int plaCount = planets.size() - 1;

                for(int plaCounter = 0; plaCount > plaCounter; plaCounter++) {
                    PlanetAPI planet = planets.get(plaCounter);

                    if(planet.hasCondition(Conditions.SPACEPORT)) {
                        MarketAPI market = planet.getMarket();

                        if(market.isInEconomy()) {
                            FactionAPI faction = market.getFaction();

                            if(!sector.getName().equals("Neue Jangala") && !sector.getName().equals("Diamant") &&
                                    !sector.getName().equals("Klat") && !sector.getName().equals("Nirraok") &&
                                    !sector.getName().equals("Phia") && !sector.getName().equals("Penelope's Star")) {
                                
                                List<Industry> industry = market.getIndustries();
                                List<MarketConditionAPI> condition = market.getConditions();
                                new setMarketItems(industry, condition, faction, market, planet);
                                
                                new HS_AddIndustry(planet, market, faction, sector);
                            }
                        }
                    }
                }
            }
        }
    }
}
