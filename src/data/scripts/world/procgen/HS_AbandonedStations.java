/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.submarkets.StoragePlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.HyperionModPlugin;

/**
 *
 * @author NinjaSiren
 */
public class HS_AbandonedStations {
    
    // Roll the dice
    private int rand(int min, int max) {
        return new HS_Randomizer().intRand(min, max);
    }    
    
    public HS_AbandonedStations(StarSystemAPI system, PlanetAPI star, PlanetAPI planet) {
        //Other stations
        SectorEntityToken old_station = system.addCustomEntity(
                "hs_old_station_01",
                "Hyperion Systems Persean Sector Research Station",
                "station_research",
                null);
        old_station.setCircularOrbitPointingDown(star, 
                204, 
                (planet.getCircularOrbitRadius() * rand(2, 4)), 
                89);
        old_station.setDiscoverable(true);
        old_station.setDiscoveryXP(5000f);
        old_station.setSensorProfile(0.25f);
        old_station.setCustomDescriptionId("hs_old_station_01");
        old_station.setInteractionImage("illustrations", "abandoned_station2");
        
        //Adds market to old station
        old_station.getMemoryWithoutUpdate().set("$abandonedStation", true);
        Misc.setAbandonedStationMarket("neuejangala_abandoned_station_market", old_station);
        MarketAPI old_station_market = Global.getFactory().createMarket("old_station_hs",
                old_station.getName(), 0);
        old_station_market.setPrimaryEntity(old_station);
        old_station_market.setFactionId(old_station.getFaction().getId());
        old_station_market.addCondition(Conditions.ABANDONED_STATION);
        old_station_market.addCondition(Conditions.DECIVILIZED);
        old_station_market.addCondition(Conditions.VICE_DEMAND);
        old_station_market.addCondition(Conditions.LARGE_REFUGEE_POPULATION);
        old_station_market.addCondition(Conditions.OUTPOST);
        old_station_market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
        ((StoragePlugin) old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE)
                .getPlugin()).setPlayerPaidToUnlock(true);
        old_station.setMarket(old_station_market);
        old_station.setCustomDescriptionId("hs_old_research_station");

        //Items and Hull Randomizer
        int rand = rand(0, 3);
        boolean hasSCY = new HyperionModPlugin().isSCYNation();
        boolean hasDME = new HyperionModPlugin().isDassaultMikoyan();

        switch (rand) {
            case 1:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(Items.SYNCHROTRON, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                if(hasDME) {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("istl_securedata", 2);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("istl_sigma_matter2", 2);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("istl_sigma_matter1", 4);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("istl_sigma_matter3", 6);
                }

                // AI Cores
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("gamma_core", 9);

                // Weapons
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("guardian", 4);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("squall", 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("pilum", 4);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("chaingun", 6);

                //Commodities
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCrew(new HyperionRandomizer(200).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMarines(new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addFuel(new HyperionRandomizer(1000).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("food", new HyperionRandomizer(50).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organics", new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("volatiles", new HyperionRandomizer(75).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("metals", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("lobster", new HyperionRandomizer(100).value);

                //Illegals
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organs", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("drugs", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                //Hulls
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                break;

            case 2:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                   
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                new SpecialItemData(Items.SYNCHROTRON, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                if(hasSCY) {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("SCY_intelChip", 2);
                }

                // AI Cores
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("beta_core", 6);

                // Weapons
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("hil", 8);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("pilum", 4);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("annihilator", 16);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("heavymg", 8);

                //Commodities
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCrew(new HyperionRandomizer(200).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMarines(new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addFuel(new HyperionRandomizer(1000).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("food", new HyperionRandomizer(50).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organics", new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("volatiles", new HyperionRandomizer(75).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("metals", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("lobster", new HyperionRandomizer(100).value);

                //Illegals
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organs", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("drugs", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                //Hulls
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                break;

            case 3:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                new SpecialItemData(Items.SYNCHROTRON, null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                new SpecialItemData(Items.HIGH_TECH_PACKAGE, null), 1);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL, "HCO_package", 1);

                // AI Cores
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("alpha_core", 3);

                // Weapons
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("tachyonlance", 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("squall", 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("pilum", 4);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addWeapons("annihilator", 16);

                //Commodities
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCrew(new HyperionRandomizer(200).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMarines(new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addFuel(new HyperionRandomizer(1000).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("food", new HyperionRandomizer(50).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organics", new HyperionRandomizer(100).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("volatiles", new HyperionRandomizer(75).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("metals", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("rare_metals", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("heavy_machinery", new HyperionRandomizer(40).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("domestic_goods", new HyperionRandomizer(30).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("luxury_goods", new HyperionRandomizer(35).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("lobster", new HyperionRandomizer(100).value);

                //Illegals
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("organs", new HyperionRandomizer(10).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("drugs", new HyperionRandomizer(25).value);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addCommodity("hand_weapons", new HyperionRandomizer(15).value);

                //Hulls
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMothballedShip(FleetMemberType.SHIP, HCOship_id(), null);
                break;

            case 0:
                default:
                    // Specials
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addItems(CargoAPI.CargoItemType.SPECIAL, 
                                    new SpecialItemData(Items.PRISTINE_NANOFORGE, null), 2);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                            .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                    new SpecialItemData(Items.SYNCHROTRON, null), 2);

                    // Weapons
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addWeapons("lrpdlaser", 16);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addWeapons("vulcan", 32);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addWeapons("lightdualac", 16);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addWeapons("heavymg", 8);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addWeapons("chaingun", 6);

                    //Commodities
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("volatiles", 75);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("metals", 25);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("rare_metals", 10);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("heavy_machinery", 40);

                    //Illegals
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("organs", 10);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("drugs", 25);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addCommodity("hand_weapons", 15);

                    //Hulls
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                    break;
        }
    }
    
    // Ship Hull Randomizers
    private String HSSship_id() {
        int number = rand(0, 12);

        //Ship Randomizers
        switch (number) {
            case 0: {
                return "HS_Krakatoa";
            }
            case 1: {
                return "HS_Perseus";
            }
            case 2: {
                return "HS_Orionis";
            }
            case 3: {
                return "HS_Naos";
            }
            case 4: {
                return "HS_Arcturus";
            }
            case 5: {
                return "HS_Scuti";
            }
            case 6: {
                return "HS_Izar";
            }
            case 7: {
                return "HS_Becrux";
            }
            case 8: {
                return "HS_Alycone";
            }
            case 9: {
                return "HS_Sirius";
            }
            case 10: {
                return "HS_Carinae";
            }
            case 11: {
                return "HS_Cassiopeia";
            }
            case 12: {
                return "HS_Eridani";
            }
            default: {
            }
        }
        return null;
    }
    
    private String HCOship_id() {
        int number = rand(0, 12);

        //Ship Randomizers
        switch (number) {
            case 0: {
                return "HS_Krakatoa_Confederacy";
            }
            case 1: {
                return "HS_Perseus_Confederacy";
            }
            case 2: {
                return "HS_Orionis_Confederacy";
            }
            case 3: {
                return "HS_Naos_Confederacy";
            }
            case 4: {
                return "HS_Arcturus_Confederacy";
            }
            case 5: {
                return "HS_Scuti_Confederacy";
            }
            case 6: {
                return "HS_Izar_Confederacy";
            }
            case 7: {
                return "HS_Becrux_Confederacy";
            }
            case 8: {
                return "HS_Alycone_Confederacy";
            }
            case 9: {
                return "HS_Sirius_Confederacy";
            }
            case 10: {
                return "HS_Carinae_Confederacy";
            }
            case 11: {
                return "HS_Cassiopeia_Confederacy";
            }
            case 12: {
                return "HS_Eridani_Confederacy";
            }
            default: {
            }
        }
        return null;
    }
}
