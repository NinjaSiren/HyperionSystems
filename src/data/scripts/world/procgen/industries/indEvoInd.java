/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.industries;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.world.procgen.HS_Randomizer;
import data.scripts.world.procgen.variables.BASE_ITEMS;
import data.scripts.world.procgen.variables.INDEVO_INDUSTRIES;
import data.scripts.world.procgen.variables.INDEVO_ITEMS;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author NinjaSiren
 */
public class indEvoInd {
    
    // Supercomputer
    public void superComputer(MarketAPI market, FactionAPI faction, PlanetAPI planet) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().SUPCOM)) {
            if(planet.hasCondition(Conditions.COLD) || planet.hasCondition(Conditions.VERY_COLD) ||
                    market.hasCondition(Conditions.COLD) || market.hasCondition(Conditions.VERY_COLD)) {
                if(new HS_Randomizer().randFixed() <= 0.6) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SUPCOM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randSupItem(faction.getId()), 
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }

    // Salvage Yards
    public void salvageYards(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().SCRAPYARD)) {
            if(market.hasIndustry(Industries.HEAVYINDUSTRY)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SCRAPYARD, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (market.hasIndustry(Industries.ORBITALWORKS)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SCRAPYARD, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else {
                if(new HS_Randomizer().randFixed() <= 0.25) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SCRAPYARD, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }

    // Privateer Base
    public void privateerBase(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN)) {
            if(faction.getId().equals(Factions.PIRATES)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else {
                if(market.hasIndustry(Industries.PATROLHQ)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) {
                        market.addIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasIndustry(Industries.MILITARYBASE)) {
                    if(new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasIndustry(Industries.HIGHCOMMAND)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) {
                        market.addIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else {
                    if(new HS_Randomizer().randFixed() <= 0.01) {
                        market.addIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                }
            }
        }
    }

    // Senate
    public void indEvoSenate(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().SENATE)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().SENATE, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }

    // Centralization Bureau
    public void indEvoCenter(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().ADINFRA)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADINFRA, 
                            new ArrayList<>(Arrays.asList(                            
                                    new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }

    // Academy
    public void indEvoAcademy(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().ACADEMY)) {
            if(market.hasIndustry(Industries.PATROLHQ)) {
                if(market.hasCondition(Conditions.POPULATION_5)) {
                    if(new HS_Randomizer().randFixed() <= 0.03) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_6)) {
                    if(new HS_Randomizer().randFixed() <= 0.05) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_7)) {
                    if(new HS_Randomizer().randFixed() <= 0.07) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_8)) {
                    if(new HS_Randomizer().randFixed() <= 0.11) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_9)) {
                    if(new HS_Randomizer().randFixed() <= 0.17) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_10)) {
                    if(new HS_Randomizer().randFixed() <= 0.25) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                }
            } else if(market.hasIndustry(Industries.MILITARYBASE)) {
                if(market.hasCondition(Conditions.POPULATION_5)) {
                    if(new HS_Randomizer().randFixed() <= 0.07) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_6)) {
                    if(new HS_Randomizer().randFixed() <= 0.10) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_7)) {
                    if(new HS_Randomizer().randFixed() <= 0.15) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_8)) {
                    if(new HS_Randomizer().randFixed() <= 0.22) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_9)) {
                    if(new HS_Randomizer().randFixed() <= 0.33) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_10)) {
                    if(new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY, 
                                new ArrayList<>(Arrays.asList(                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                }
            } else if(market.hasIndustry(Industries.HIGHCOMMAND)) {
                if(market.hasCondition(Conditions.POPULATION_5)) {
                    if(new HS_Randomizer().randFixed() <= 0.1) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_6)) {
                    if(new HS_Randomizer().randFixed() <= 0.15) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_7)) {
                    if(new HS_Randomizer().randFixed() <= 0.22) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_8)) {
                    if(new HS_Randomizer().randFixed() <= 0.33) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_9)) {
                    if(new HS_Randomizer().randFixed() <= 0.5) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                } else if(market.hasCondition(Conditions.POPULATION_10)) {
                    if(new HS_Randomizer().randFixed() <= 0.75) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                    }
                }
            } else {
                if(new HS_Randomizer().randFixed() <= 0.01) {
                        market.addIndustry(new INDEVO_INDUSTRIES().ACADEMY,                         
                                new ArrayList<>(Arrays.asList(                                                            
                                        new BASE_ITEMS().randAICores(faction.getId()))));
                }
            }
        }
    }

    // Variable Assembler/Manufactory or Commodity Forge
    public void indEvoVarForge(MarketAPI market, FactionAPI faction) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().ADASSEM) || !!market.hasIndustry(new INDEVO_INDUSTRIES().ADMANUF) ||
                !market.hasIndustry(new INDEVO_INDUSTRIES().COMFORGE)) {
            if(!market.hasIndustry(Industries.MILITARYBASE) || !market.hasIndustry(Industries.HIGHCOMMAND)) {
                if(new HS_Randomizer().randFixed() <= 0.20) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADASSEM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randMilItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.20 && new HS_Randomizer().randFixed() <= 0.40) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADMANUF, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randMilItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.40 && new HS_Randomizer().randFixed() <= 0.60) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMFORGE, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randMilItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (!market.hasIndustry(Industries.FUELPROD)) {
                if(new HS_Randomizer().randFixed() <= 0.20) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADASSEM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_SUPPLIES_FUEL, new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.20 && new HS_Randomizer().randFixed() <= 0.40) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADMANUF, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_SUPPLIES_FUEL, new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.40 && new HS_Randomizer().randFixed() <= 0.60) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMFORGE, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_SUPPLIES_FUEL, new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (!market.hasIndustry(Industries.HEAVYINDUSTRY) || !market.hasIndustry(Industries.ORBITALWORKS)) {
                if(new HS_Randomizer().randFixed() <= 0.20) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADASSEM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randManuItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.20 && new HS_Randomizer().randFixed() <= 0.40) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADMANUF, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randManuItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.40 && new HS_Randomizer().randFixed() <= 0.60) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMFORGE, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randManuItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (!market.hasIndustry(Industries.LIGHTINDUSTRY)) {
                if(new HS_Randomizer().randFixed() <= 0.20) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADASSEM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randLightItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.20 && new HS_Randomizer().randFixed() <= 0.40) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADMANUF, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randLightItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.40 && new HS_Randomizer().randFixed() <= 0.60) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMFORGE, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().randLightItem(market), new BASE_ITEMS().randAICores(faction.getId()))));
                }
            } else if (!market.hasIndustry(new INDEVO_INDUSTRIES().SCRAPYARD)) {
                if(new HS_Randomizer().randFixed() <= 0.20) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADASSEM, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_PARTS, new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.20 && new HS_Randomizer().randFixed() <= 0.40) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ADMANUF, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_PARTS, new BASE_ITEMS().randAICores(faction.getId()))));
                } else if(new HS_Randomizer().randFixed() > 0.40 && new HS_Randomizer().randFixed() <= 0.60) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMFORGE, 
                            new ArrayList<>(Arrays.asList(
                                    new INDEVO_ITEMS().VPC_PARTS)));
                }
            }
        }
    }
    
    // Embassy
    public void indEvoEmbassy(MarketAPI market) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().EMBASSY)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().EMBASSY);
                }
            }
        }
    }
    
    // Restoration Docks
    public void indEvoResDock(MarketAPI market) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS) && 
                market.hasIndustry(Industries.ORBITALWORKS) || market.hasIndustry(Industries.HEAVYINDUSTRY)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS);
                }
            }
        }
    }
    
    // Requisition Center
    public void indEvoReqCen(MarketAPI market) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().REQCENTER) && 
                market.hasIndustry(Industries.MILITARYBASE) || market.hasIndustry(Industries.HIGHCOMMAND)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().REQCENTER);
                }
            }
        }
    }
    
    // Engineering Hub
    public void indEvoEngHub(MarketAPI market) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().ENGHUB) && 
                market.hasIndustry(Industries.TECHMINING)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().ENGHUB);
                }
            }
        }
    }
    
    // Courier Port
    public void indEvoCourPort(MarketAPI market) {
        if(!market.hasIndustry(new INDEVO_INDUSTRIES().PORT) && 
                market.hasIndustry(Industries.MEGAPORT)) {
            if(market.hasCondition(Conditions.POPULATION_5)) {
                if(new HS_Randomizer().randFixed() <= 0.1) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            } else if(market.hasCondition(Conditions.POPULATION_6)) {
                if(new HS_Randomizer().randFixed() <= 0.15) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            } else if(market.hasCondition(Conditions.POPULATION_7)) {
                if(new HS_Randomizer().randFixed() <= 0.22) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            } else if(market.hasCondition(Conditions.POPULATION_8)) {
                if(new HS_Randomizer().randFixed() <= 0.33) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            } else if(market.hasCondition(Conditions.POPULATION_9)) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            } else if(market.hasCondition(Conditions.POPULATION_10)) {
                if(new HS_Randomizer().randFixed() <= 0.75) {
                    market.addIndustry(new INDEVO_INDUSTRIES().PORT);
                }
            }
        }
    }
    
    // Military Relays or Interstellar Relay
    public void relaysNoRelay(MarketAPI market, StarSystemAPI sector) {
        if(sector.getEntityById(Entities.COMM_RELAY).isInOrNearSystem(sector)) {
            if(sector.getEntityById(Entities.COMM_RELAY).getFaction() != market.getFaction() || 
                    sector.getEntityById(Entities.COMM_RELAY).getFaction() != market.getFaction() ||
                    !sector.getEntityById(Entities.COMM_RELAY_MAKESHIFT).isAlive() || 
                    !sector.getEntityById(Entities.COMM_RELAY).isAlive()) {
                if(new HS_Randomizer().randFixed() <= 0.5) {
                    market.addIndustry(new INDEVO_INDUSTRIES().COMARRAY);
                } else {
                    market.addIndustry(new INDEVO_INDUSTRIES().INTARRAY);
                }
            }
        }
    }
    
    // TODO : World Symbols
    
}
