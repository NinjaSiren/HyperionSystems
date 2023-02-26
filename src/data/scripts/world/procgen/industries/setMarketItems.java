/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.industries;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.HyperionModDependencies;
import data.scripts.world.procgen.variables.BASE_ITEMS;
import data.scripts.world.procgen.variables.INDEVO_INDUSTRIES;
import data.scripts.world.procgen.variables.INDEVO_ITEMS;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class setMarketItems {
    public setMarketItems(List<Industry> iList, List<MarketConditionAPI> cList, FactionAPI faction, MarketAPI market, 
            PlanetAPI planet) {
        
        boolean isIE = new HyperionModDependencies().hasIndEvolution();
        String factionID = faction.getId();
        
        for(Industry industry : iList) {
            
            for(MarketConditionAPI condition : cList) {
                
                // Population
                if(!condition.getId().equals(Conditions.DARK)) {
                    if(industry.getId().equals(Industries.POPULATION)) {
                        
                        if(industry.getSpecialItem() == null) {
                            String data = new BASE_ITEMS().randLightItem(factionID, market);
                            if(data != null) {
                                industry.getSpecialItem().setData(data);
                            }                 
                        }
                        
                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                }
                
                // Spaceport
                if(!condition.getId().equals(Conditions.EXTREME_TECTONIC_ACTIVITY) ||
                        !condition.getId().equals(Conditions.EXTREME_WEATHER)) {
                    if(industry.getId().equals(Industries.SPACEPORT) || industry.getId().equals(Industries.MEGAPORT)) {
                        
                        if(industry.getSpecialItem() == null) {
                            String data = new BASE_ITEMS().randPortItem(factionID, market, planet);
                            if(data != null) {
                                industry.getSpecialItem().setData(data);
                            }                 
                        }
                        
                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                }
                
                // Waystation
                if(industry.getId().equals(Industries.WAYSTATION)) {

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Military
                if(condition.getId().equals(Conditions.VERY_HOT) || condition.getId().equals(Conditions.HOT)) {
                    if(industry.getId().equals(Industries.MILITARYBASE) || industry.getId().equals(Industries.HIGHCOMMAND) ||
                            industry.getId().equals(Industries.PATROLHQ)) {
                        
                        if(industry.getSpecialItem() == null) {
                            String data = new BASE_ITEMS().randMilHot(factionID, market);
                            if(data != null) {
                                industry.getSpecialItem().setData(data);
                            }                 
                        }
                        
                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                } else {
                    if(isIE) {
                        if(industry.getId().equals(Industries.MILITARYBASE) || industry.getId().equals(Industries.HIGHCOMMAND)) {
                        
                            if(industry.getSpecialItem() == null) {
                                String data = new INDEVO_ITEMS().randMilTransmit(factionID);
                                if(data != null) {
                                    industry.getSpecialItem().setData(data);
                                }                 
                            }

                            if(industry.getAICoreId() == null) {
                                String data = new BASE_ITEMS().randAICores(factionID);
                                if(data != null) {
                                    industry.setAICoreId(data);
                                }
                            }
                        }
                    }
                }
                
                // Mining
                if(industry.getId().equals(Industries.MINING)) {
                    
                    if(condition.getId().equals(Conditions.NO_ATMOSPHERE)) {
                        String data = new BASE_ITEMS().randOreItem(factionID, market);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    } else if(condition.getId().equals(Conditions.NO_ATMOSPHERE) || planet.isGasGiant()) {
                        String data = new BASE_ITEMS().randVolItem(factionID, planet);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Refining
                if(industry.getId().equals(Industries.REFINING)) {
                    
                    if(condition.getId().equals(Conditions.NO_ATMOSPHERE)) {
                        String data = new BASE_ITEMS().randRefItem(factionID, market);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Farming
                if(industry.getId().equals(Industries.FARMING)) {
                    
                    if(!condition.getId().contains("volatiles") || !condition.getId().contains("rare")) {
                        String data = new BASE_ITEMS().randFarmItem(factionID, market);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Aquaculture
                if(industry.getId().equals(Industries.AQUACULTURE)) {
                    
                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Heavy Industry
                if(industry.getId().equals(Industries.HEAVYINDUSTRY) || industry.getId().equals(Industries.ORBITALWORKS)) {
                    
                    if(condition.getId().equals(Conditions.NO_ATMOSPHERE)) {
                        String data = new BASE_ITEMS().randHeavyItem(factionID);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Light Industry
                if(industry.getId().equals(Industries.LIGHTINDUSTRY)) {
                    
                    if(condition.getId().equals(Conditions.HABITABLE)) {
                        String data = new BASE_ITEMS().randLightBio(factionID, market);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Techmining
                if(industry.getId().equals(Industries.TECHMINING)) {

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Fuel Production
                if(industry.getId().equals(Industries.FUELPROD)) {
                    
                    if(condition.getId().equals(Conditions.NO_ATMOSPHERE)) {
                        String data = new BASE_ITEMS().randFuelItem(factionID, market);
                        if(data != null) {
                            if(industry.getSpecialItem() == null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }
                    }

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Cryosanctum
                if(industry.getId().equals(Industries.CRYOSANCTUM)) {

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                // Space Station
                if(industry.getId().contains("orbital") || industry.getId().contains("fortress") || 
                        industry.getId().contains("station") && !industry.getId().equals(Industries.WAYSTATION)) {

                    if(industry.getAICoreId() == null) {
                        String data = new BASE_ITEMS().randAICores(factionID);
                        if(data != null) {
                            industry.setAICoreId(data);
                        }
                    }
                }
                
                if(isIE) {
                    
                    // Supercomputer
                    if(industry.getId().equals(new INDEVO_INDUSTRIES().SUPCOM)) {
                        
                        if(industry.getSpecialItem() == null) {
                            String data = new INDEVO_ITEMS().randSupItem(factionID);
                            if(data != null) {
                                industry.getSpecialItem().setData(data);
                            }
                        }

                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                    
                    // Variable Manufactory
                    if(industry.getId().equals(new INDEVO_INDUSTRIES().ADMANUF)) {

                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                    
                    // Commodity Forge
                    if(industry.getId().equals(new INDEVO_INDUSTRIES().COMFORGE)) {

                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                    
                    // Scrapyard Port
                    if(industry.getId().equals(new INDEVO_INDUSTRIES().SCRAPYARD)) {
                        
                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                    
                    // Privateer Port
                    if(industry.getId().equals(new INDEVO_INDUSTRIES().PIRATEHAVEN)) {

                        if(industry.getAICoreId() == null) {
                            String data = new BASE_ITEMS().randAICores(factionID);
                            if(data != null) {
                                industry.setAICoreId(data);
                            }
                        }
                    }
                }
            }
        }
    }
}
