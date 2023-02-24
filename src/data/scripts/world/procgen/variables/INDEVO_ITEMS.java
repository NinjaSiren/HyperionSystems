/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.variables;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import data.scripts.world.procgen.HS_Randomizer;

/**
 *
 * @author NinjaSiren
 */
public class INDEVO_ITEMS {
    
    // Industry Items
    public final String LOG_CORE = "IndEvo_log_core";
    public final String NEURAL_COMPOUNDS = "IndEvo_neurals";
    public final String ANALYSER = "IndEvo_analyser";
    public final String INTERFACES = "IndEvo_interface";
    public final String TRANSMITTER = "IndEvo_transmitter";
    public final String NANITES = "IndEvo_nanites";
    public final String SALVAGE_DRONES = "IndEvo_drones";
    public final String SIMULATOR = "IndEvo_simulator";
    
    // VPC Items
    public final String VPC_PARTS = "IndEvo_vpc_IndEvo_parts";
    public final String VPC_SUPPLIES = "IndEvo_vpc_supplies";
    public final String VPC_MARINES = "IndEvo_vpc_marines";
    public final String VPC_HEAVY_MACHINERY = "IndEvo_vpc_heavy_machinery";
    public final String VPC_DOMESTIC_GOODS = "IndEvo_vpc_domestic_goods";
    public final String VPC_DRUGS = "IndEvo_vpc_drugs";
    public final String VPC_HAND_WEAPONS = "IndEvo_vpc_hand_weapons";
    public final String VPC_LUXURY_GOODS = "IndEvo_vpc_luxury_goods";
    public final String VPC_MARINES_HAND_WEAPONS = "IndEvo_vpc_marines_hand_weapons";
    public final String VPC_SUPPLIES_FUEL = "IndEvo_vpc_supply_fuel";
    
    public String indItems(String item, int counter) {
        counter = counter - 1;
        for(int count = 7; counter >= count ; count--) {
            switch(count) {
                case 0:
                    return LOG_CORE;
                case 1:
                    return NEURAL_COMPOUNDS;
                case 2:
                    return ANALYSER;
                case 3:
                    return INTERFACES;
                case 4:
                    return TRANSMITTER;
                case 5:
                    return NANITES;
                case 6:
                    return SALVAGE_DRONES;
                case 7:
                    return SIMULATOR;
            }
        }
        return null;
    }
    
    public String randMilItem(MarketAPI market) {
        if(new HS_Randomizer().randFixed() <= 0.5) return VPC_MARINES_HAND_WEAPONS;
        else return VPC_MARINES;
    }
    
    public String randManuItem(MarketAPI market) {
        if(new HS_Randomizer().randFixed() <= 0.2) return VPC_HEAVY_MACHINERY;
        else if(new HS_Randomizer().randFixed() > 0.2 || new HS_Randomizer().randFixed() <= 0.4) return VPC_SUPPLIES;
        else if(new HS_Randomizer().randFixed() > 0.4 || new HS_Randomizer().randFixed() <= 0.6) return VPC_HAND_WEAPONS;
        else if(new HS_Randomizer().randFixed() > 0.6 || new HS_Randomizer().randFixed() <= 0.8) return VPC_SUPPLIES_FUEL;
        else return VPC_MARINES_HAND_WEAPONS;
    }
    
    public String randLightItem(MarketAPI market) {
        if(new HS_Randomizer().randFixed() <= 0.33) return VPC_DOMESTIC_GOODS;
        else if(new HS_Randomizer().randFixed() > 0.33 || new HS_Randomizer().randFixed() <= 0.66) return VPC_LUXURY_GOODS;
        else return VPC_DRUGS;
    }
    
    public String randSenate(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return NEURAL_COMPOUNDS;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return NEURAL_COMPOUNDS;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return NEURAL_COMPOUNDS;
            }
        }
        return null;
    }
    
    public String randCenBea(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return LOG_CORE;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return LOG_CORE;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return LOG_CORE;
            }
        }
        return null;
    }
    
    public String randMilTransmit(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return TRANSMITTER;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return TRANSMITTER;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return TRANSMITTER;
            }
        }
        return null;
    }
    
    public String randSupItem(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return SIMULATOR;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return SIMULATOR;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return SIMULATOR;
            }
        }
        return null;
    }
}
