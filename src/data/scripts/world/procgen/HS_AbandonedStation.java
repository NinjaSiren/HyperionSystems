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
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_AbandonedStation {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }    
    
    public HS_AbandonedStation(StarSystemAPI system, PlanetAPI star, PlanetAPI planet) {
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
        int rand = new HyperionRandomizer(3).value;
        boolean hasSCY = new HyperionModPlugin().isSCYNation();
        boolean hasDME = new HyperionModPlugin().isDassaultMikoyan();

        switch (rand) {
            case 1:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(IIR_id(), null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(IIR_id(), null), 2);
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
                if(hasDME) {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addMothballedShip(FleetMemberType.SHIP, DMEship_id(), null);
                } else {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                }
                break;

            case 2:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(IIR_id(), null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                   
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                new SpecialItemData(IIR_id(), null), 2);
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
                if(hasSCY) {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addMothballedShip(FleetMemberType.SHIP, SCYship_id(), null);
                } else {
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                            .addMothballedShip(FleetMemberType.SHIP, HSSship_id(), null);
                }
                break;

            case 3:
                // Specials
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                 
                                new SpecialItemData(IIR_id(), null), 2);
                old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                        .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                new SpecialItemData(IIR_id(), null), 2);
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
                                    new SpecialItemData(IIR_id(), null), 2);
                    old_station_market.getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo()                                    
                            .addItems(CargoAPI.CargoItemType.SPECIAL,                                                                                             
                                    new SpecialItemData(IIR_id(), null), 2);

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
    
    // Industry Item Randomizers
    private String IIR_id() {
        int number = new HyperionRandomizer(14).value;

        switch (number) {
            case 0:
                return Items.BIOFACTORY_EMBRYO;
            case 1:
                return Items.CATALYTIC_CORE;
            case 2:
                return Items.CORONAL_PORTAL;
            case 3:
                return Items.CORRUPTED_NANOFORGE;
            case 4:
                return Items.CRYOARITHMETIC_ENGINE;
            case 5:
                return Items.DEALMAKER_HOLOSUITE;
            case 6:
                return Items.DRONE_REPLICATOR;
            case 7:
                return Items.FULLERENE_SPOOL;
            case 8:
                return Items.JANUS;
            case 9:
                return Items.MANTLE_BORE;
            case 10:
                return Items.ORBITAL_FUSION_LAMP;
            case 11:
                return Items.PLASMA_DYNAMO;
            case 12: return 
                    Items.PRISTINE_NANOFORGE;
            case 13: return 
                    Items.SOIL_NANITES;
            case 14: return 
                    Items.SYNCHROTRON;
            default:
                break;
        }      
        return null;
    }
    
    // Ship Hull Randomizers
    private String HSSship_id() {
        int number = new HyperionRandomizer(11).value;

        //Ship Randomizers
        switch (number) {
            case 0:
               return "HS_Orionis";
            case 1:
                return "HS_Naos";
            case 2:
                return "HS_Scuti";
            case 3:
                return "HS_Arcturus";
            case 4:
                return "HS_Izar";
            case 5:
                return "HS_Alycone";
            case 6:
                return "HS_Becrux";
            case 7:
                return "HS_Carinae";
            case 8:
                return "HS_Sirius";
            case 9:
                return "HS_Eridani";
            case 10:
                return "HS_Cassiopeia";
            case 11:
                return "HS_Perseus";
            default:
                break;
        }
        return null;
    }
    
    private String HCOship_id() {
        int number = new HyperionRandomizer(12).value;

        //Ship Randomizers
        switch (number) {
            case 0:
                return "HS_Orionis_Confederacy";
            case 1:
                return "HS_Naos_Confederacy";
            case 2:
                return "HS_Atlas_Confederacy";
            case 3:
                return "HS_Buffalo_Confederacy";
            case 4:
                return "HS_Gemini_Confederacy";
            case 5:
                return "HS_Alycone_Confederacy";
            case 6:
                return "HS_Becrux_Confederacy";
            case 7:
                return "HS_Carinae_Confederacy";
            case 8:
                return "HS_Sirius_Confederacy";
            case 9:
                return "HS_Eridani_Confederacy";
            case 10:
                return "HS_Cassiopeia_Confederacy";
            case 11:
                return "HS_Perseus_Confederacy";
            case 12:
                return "HS_Valkyrie_Confederacy";
            default:
                break;
        }
        return null;
    }

    private String DMEship_id() {
        int number = new HyperionRandomizer(28).value;

        //Ship Randomizers
        switch (number) {
            case 0:
                return "istl_flatmouse";
            case 1:
                return "istl_puddlejumper";
            case 2:
                return "istl_bouclier";
            case 3:
                return "istl_carabao";
            case 4:
                return "istl_sevastopol";
            case 5:
                return "istl_chamois";
            case 6:
                return "istl_lodestar";
            case 7:
                return "istl_mouflon";
            case 8:
                return "istl_centaur";
            case 9:
                return "istl_samoyed";
            case 10:
                return "istl_stoat";
            case 11:
                return "istl_burya";
            case 12:
                return "istl_naja";
            case 13:
                return "istl_borzoi";
            case 14:
                return "istl_vesper";
            case 15:
                return "istl_starsylph";
            case 16:
                return "istl_leyte";
            case 17:
                return "istl_tunguska";
            case 18:
                return "istl_wanderer";
            case 19:
                return "istl_tereshkova";
            case 20:
                return "istl_maskirovka";
            case 21:
                return "istl_snowgoose";
            case 22:
                return "istl_mindanao";
            case 23:
                return "istl_baikal";
            case 24:
                return "istl_baikal_brone";
            case 25:
                return "istl_kormoran";
            case 26:
                return "istl_tonnerre";
            case 27:
                return "istl_zelenograd";
            case 28:
                return "istl_jeannedarc";
            default:
                break;
        }
        return null;
    }
    
    private String SCYship_id() {
        int number = new HyperionRandomizer(24).value;

        //Ship Randomizers
        switch (number) {
            case 0:
                return "SCY_alecto";
            case 1:
                return "SCY_tisiphone";
            case 2:
                return "SCY_centaur";
            case 3:
                return "SCY_lealaps";
            case 4:
                return "SCY_talos";
            case 5:
                return "SCY_megaera";
            case 6:
                return "SCY_stymphalianbird";
            case 7:
                return "SCY_argus";
            case 8:
                return "SCY_baliusF";
            case 9:
                return "SCY_baliusT";
            case 10:
                return "SCY_geryon";
            case 11:
                return "SCY_lamia";
            case 12:
                return "SCY_lamiaA";
            case 13:
                return "SCY_hydra";
            case 14:
                return "SCY_pyraemon";
            case 15:
                return "SCY_corocotta";
            case 16:
                return "SCY_corocottaA";
            case 17:
                return "SCY_dracanae";
            case 18:
                return "SCY_manticore";
            case 19:
                return "SCY_erymanthianboar";
            case 20:
                return "SCY_siren";
            case 21:
                return "SCY_khalkotauroi";
            case 22:
                return "SCY_keto";
            case 23:
                return "SCY_nemeanlion";
            case 24:
                return "SCY_xanthus";
            default:
                break;
        }
        return null;
    }
}
