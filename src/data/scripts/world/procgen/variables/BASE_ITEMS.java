/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.scripts.world.procgen.variables;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import data.scripts.HyperionModDependencies;
import data.scripts.world.procgen.HS_Randomizer;

/**
 *
 * @author NinjaSiren
 */
public class BASE_ITEMS {
    
    public String randHeavyItem(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return Items.PRISTINE_NANOFORGE;
                    else if(new HS_Randomizer().randFixed() > 0.25) return Items.CORRUPTED_NANOFORGE;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return Items.PRISTINE_NANOFORGE;
                    else if(new HS_Randomizer().randFixed() > 0.75) return Items.CORRUPTED_NANOFORGE;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return Items.PRISTINE_NANOFORGE;
                    else if(new HS_Randomizer().randFixed() > 0.5) return Items.CORRUPTED_NANOFORGE;
            }
        }
        return null;
    }
    
    public String randFuelItem(String faction, MarketAPI market) {
        if(faction != null) {
            if(market.hasCondition(Conditions.NO_ATMOSPHERE)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.SYNCHROTRON;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.SYNCHROTRON;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.SYNCHROTRON;
                }
            }
        }
        return null;
    }
    
    public String randMilHot(String faction, MarketAPI market) {
        if(faction != null) {
            if(market.hasCondition(Conditions.HOT) || market.hasCondition(Conditions.VERY_HOT)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.CRYOARITHMETIC_ENGINE;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.CRYOARITHMETIC_ENGINE;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.CRYOARITHMETIC_ENGINE;
                }
            } else {
                if(new HyperionModDependencies().hasIndEvolution()) {
                    return new INDEVO_ITEMS().randMilTransmit(faction);
                }
            }
        }
        return null;
    }
    
    public String randDefDrones(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return Items.DRONE_REPLICATOR;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return Items.DRONE_REPLICATOR;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return Items.DRONE_REPLICATOR;
            }
        }
        return null;
    }
    
    public String randLightBio(String faction, MarketAPI market) {
        if(faction != null) {
                if(market.hasCondition(Conditions.HABITABLE)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.BIOFACTORY_EMBRYO;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.BIOFACTORY_EMBRYO;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.BIOFACTORY_EMBRYO;
                }
            }
        }
        return null;
    }
    
    public String randComItem(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return Items.DEALMAKER_HOLOSUITE;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return Items.DEALMAKER_HOLOSUITE;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return Items.DEALMAKER_HOLOSUITE;
            }
        }
        return null;
    }
    
    public String randPortItem(String faction, MarketAPI market, PlanetAPI planet) {
        if(faction != null) {
            if(!market.hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY) ||
                    !market.hasCondition(Conditions.EXTREME_WEATHER) ||
                    !planet.getTypeId().equals(new PLANET_TYPES().GAS_GIANT) ||
                    !planet.getTypeId().equals(new PLANET_TYPES().ICE_GIANT) ||
                    !planet.getTypeId().equals(new PLANET_TYPES().US_GAS_GIANT_A) ||
                    !planet.getTypeId().equals(new PLANET_TYPES().US_GAS_GIANT_B)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.FULLERENE_SPOOL;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.FULLERENE_SPOOL;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.FULLERENE_SPOOL;
                }
            } 
        }
        return null;
    }
    
    public String randVolItem(String faction, PlanetAPI planet) {
        if(faction != null) {
            if(planet.getTypeId().equals(new PLANET_TYPES().GAS_GIANT) ||
                    planet.getTypeId().equals(new PLANET_TYPES().ICE_GIANT) ||
                    planet.getTypeId().equals(new PLANET_TYPES().US_GAS_GIANT_A) ||
                    planet.getTypeId().equals(new PLANET_TYPES().US_GAS_GIANT_B)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.PLASMA_DYNAMO;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.PLASMA_DYNAMO;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.PLASMA_DYNAMO;
                }
            }
        }
        return null;
    }
    
    public String randOreItem(String faction, MarketAPI market) {
        if(faction != null) {
            if(market.hasCondition(Conditions.NO_ATMOSPHERE)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.MANTLE_BORE;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.MANTLE_BORE;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.MANTLE_BORE;
                }
            }
        }
        return null;
    }
    
    public String randRefItem(String faction, MarketAPI market) {
        if(faction != null) {
            if(market.hasCondition(Conditions.NO_ATMOSPHERE)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.CATALYTIC_CORE;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.CATALYTIC_CORE;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.CATALYTIC_CORE;
                }
            }
        }
        return null;
    }
    
    public String randFarmItem(String faction, MarketAPI market) {
        if(faction != null) {
            if(!market.hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !market.hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !market.hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !market.hasCondition(Conditions.VOLATILES_TRACE) ||
                    !market.hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !market.hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !market.hasCondition(Conditions.RARE_ORE_RICH) ||
                    !market.hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !market.hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.SOIL_NANITES;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.SOIL_NANITES;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.SOIL_NANITES;
                }
            }
        }
        return null;
    }
    
    public String randLightItem(String faction, MarketAPI market) {
        if(faction != null) {
            if(market.hasCondition(Conditions.DARK)) {
                switch (faction) {
                    case Factions.REMNANTS:
                        if(new HS_Randomizer().randFixed() <= 0.75) return Items.ORBITAL_FUSION_LAMP;
                        break;
                    case Factions.PIRATES:
                        if(new HS_Randomizer().randFixed() <= 0.25) return Items.ORBITAL_FUSION_LAMP;
                        break;
                    default:
                        if(new HS_Randomizer().randFixed() <= 0.5) return Items.ORBITAL_FUSION_LAMP;
                }
            }
        }
        return null;
    }
    
    public String randHyperShunt(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.75) return Items.CORONAL_PORTAL;
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.25) return Items.CORONAL_PORTAL;
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.5) return Items.CORONAL_PORTAL;
            }
        }
        return null;
    }
    
    public String randAICores(String faction) {
        if(faction != null) {
            switch (faction) {
                case Factions.REMNANTS:
                    if(new HS_Randomizer().randFixed() <= 0.9) {
                        return Commodities.ALPHA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.9 
                            || new HS_Randomizer().randFixed() <= 0.925) {
                        return Commodities.GAMMA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.925 
                            || new HS_Randomizer().randFixed() <= 0.95) {
                        return Commodities.BETA_CORE;
                    } 
                    break;
                case Factions.TRITACHYON:
                    if(new HS_Randomizer().randFixed() <= 0.9) {
                        return Commodities.ALPHA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.9 
                            || new HS_Randomizer().randFixed() <= 0.925) {
                        return Commodities.GAMMA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.925 
                            || new HS_Randomizer().randFixed() <= 0.95) {
                        return Commodities.BETA_CORE;
                    } 
                    break;
                case Factions.PIRATES:
                    if(new HS_Randomizer().randFixed() <= 0.17) {
                        return Commodities.ALPHA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.17 
                            || new HS_Randomizer().randFixed() <= 0.33) {
                        return Commodities.GAMMA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.33 
                            || new HS_Randomizer().randFixed() <= 0.5) {
                        return Commodities.BETA_CORE;
                    }
                    break;
                case Factions.LUDDIC_CHURCH:
                    break;
                case Factions.LUDDIC_PATH:
                    break;
                case Factions.HEGEMONY:
                    break;
                default:
                    if(new HS_Randomizer().randFixed() <= 0.25) {
                        return Commodities.ALPHA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.25 
                            || new HS_Randomizer().randFixed() <= 0.5) {
                        return Commodities.GAMMA_CORE;
                    } else if(new HS_Randomizer().randFixed() > 0.5
                            || new HS_Randomizer().randFixed() <= 0.75) {
                        return Commodities.BETA_CORE;
                    }
            }
        }
        return null;
    }
}
