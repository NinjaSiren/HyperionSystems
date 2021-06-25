package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.HyperionModPlugin;

/**
 *
 * @author NinjaSiren
 * 
 * Any mod that changes the settings.json with the maxIndustries settings is compatible
 * 
 */
public final class HS_IndustryLimit {
    
    // Sets the market's industry limit to HS_AddIndustry.class
    public int industryLimit(MarketAPI market, PlanetAPI planet) {
        int iLimit;
        int marketSize = market.getSize();
        
        // Vanilla, with Coronal Hypershunt
        if(!new HyperionModPlugin().isBetterColonies()) {
            if(market.getIndustry(Industries.POPULATION).getSpecialItem() != null) {
                if(market.getIndustry(Industries.POPULATION)
                            .getSpecialItem().toString().equals("coronal_portal")) {
                    switch(marketSize) {
                        case 1:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 2:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 3:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 4:
                            iLimit = 3 - mHasIndustry(market, planet);
                            return iLimit;
                        case 5:
                            iLimit = 4 - mHasIndustry(market, planet);
                            return iLimit;
                        case 6:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                        case 7:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                        case 8:
                            iLimit = 5 - mHasIndustry(market, planet); 
                            return iLimit;
                        case 9:
                            iLimit = 5 - mHasIndustry(market, planet); 
                            return iLimit;
                        case 10:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                    }
                }
            // Vanilla, without Coronal Hypershunt
            } else {              
                switch (marketSize) {
                    case 1:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 2:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 3:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 4:
                        iLimit = 2 - mHasIndustry(market, planet);
                        return iLimit;
                    case 5:
                        iLimit = 3 - mHasIndustry(market, planet);
                        return iLimit;
                    case 6:
                        iLimit = 4 - mHasIndustry(market, planet);
                        return iLimit;
                    case 7:
                        iLimit = 4 - mHasIndustry(market, planet);
                        return iLimit;
                    case 8:
                        iLimit = 4 - mHasIndustry(market, planet); 
                        return iLimit;
                    case 9:
                        iLimit = 5 - mHasIndustry(market, planet); 
                        return iLimit;
                    case 10:
                        iLimit = 5 - mHasIndustry(market, planet);
                        return iLimit;
                }
            }
        // Better Colonies, with Coronal Hypershunt
        } else {
            if(market.getIndustry(Industries.POPULATION).getSpecialItem() != null) {
                if(market.getIndustry(Industries.POPULATION)
                            .getSpecialItem().toString().equals("coronal_portal")) {
                    switch (marketSize) {
                        case 1:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 2:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 3:
                            iLimit = 2 - mHasIndustry(market, planet);
                            return iLimit;
                        case 4:
                            iLimit = 3 - mHasIndustry(market, planet);
                            return iLimit;
                        case 5:
                            iLimit = 4 - mHasIndustry(market, planet);
                            return iLimit;
                        case 6:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                        case 7:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                        case 8:
                            iLimit = 5 - mHasIndustry(market, planet); 
                            return iLimit;
                        case 9:
                            iLimit = 5 - mHasIndustry(market, planet); 
                            return iLimit;
                        case 10:
                            iLimit = 5 - mHasIndustry(market, planet);
                            return iLimit;
                    }
                }
            // Better Colonies, without Coronal Hypershunt
            } else {
                switch (marketSize) {
                    case 1:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 2:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 3:
                        iLimit = 1 - mHasIndustry(market, planet);
                        return iLimit;
                    case 4:
                        iLimit = 2 - mHasIndustry(market, planet);
                        return iLimit;
                    case 5:
                        iLimit = 3 - mHasIndustry(market, planet);
                        return iLimit;
                    case 6:
                        iLimit = 4 - mHasIndustry(market, planet);
                        return iLimit;
                    case 7:
                        iLimit = 4 - mHasIndustry(market, planet);
                        return iLimit;
                    case 8:
                        iLimit = 4 - mHasIndustry(market, planet); 
                        return iLimit;
                    case 9:
                        iLimit = 4 - mHasIndustry(market, planet); 
                        return iLimit;
                    case 10:
                        iLimit = 4 - mHasIndustry(market, planet);
                        return iLimit;
                }
            }
        }
        return 0;
    }
    
    // Checks if planet market has specific industry
    private int mHasIndustry(MarketAPI market, PlanetAPI planet) {
        int exInd = 0;        
        
        // Military
        if(market.hasIndustry(Industries.HIGHCOMMAND) ||
                market.hasIndustry(Industries.MILITARYBASE) ||
                planet.getMarket().hasIndustry(Industries.HIGHCOMMAND) ||
                planet.getMarket().hasIndustry(Industries.MILITARYBASE)) {
            exInd = exInd + 1;
        }   

        // Ship and hardware building
        if(market.hasIndustry(Industries.HEAVYINDUSTRY) ||
                market.hasIndustry(Industries.ORBITALWORKS) ||
                planet.getMarket().hasIndustry(Industries.HEAVYINDUSTRY) ||
                planet.getMarket().hasIndustry(Industries.ORBITALWORKS)) {
            exInd = exInd + 1;
        } 

        // Fuel production
        if(market.hasIndustry(Industries.FUELPROD) ||
                planet.getMarket().hasIndustry(Industries.FUELPROD)) {
            exInd = exInd + 1;
        } if(market.hasIndustry("advfuelprod") ||
                planet.getMarket().hasIndustry("advfuelprod")) {
            exInd = exInd + 1;
        }

        // Goods production
        if(market.hasIndustry(Industries.LIGHTINDUSTRY) ||
                planet.getMarket().hasIndustry(Industries.LIGHTINDUSTRY)) {
            exInd = exInd + 1;
        }

        //Farming
        if(market.hasIndustry(Industries.FARMING) ||
                planet.getMarket().hasIndustry(Industries.FARMING)) {
            exInd = exInd + 1;
        }

        //Mining
        if(market.hasIndustry(Industries.MINING) ||
                planet.getMarket().hasIndustry(Industries.MINING)) {
            exInd = exInd + 1;
        }

        //Refining
        if(market.hasIndustry(Industries.REFINING) ||
                planet.getMarket().hasIndustry(Industries.REFINING)) {
            exInd = exInd + 1;
        }

        //Aquaculture
        if(market.hasIndustry(Industries.AQUACULTURE) ||
                planet.getMarket().hasIndustry(Industries.AQUACULTURE)) {
            exInd = exInd + 1;
        }

        //Techmining
        if(market.hasIndustry(Industries.TECHMINING) ||
                planet.getMarket().hasIndustry(Industries.TECHMINING)) {
            exInd = exInd + 1;
        }
        
        return exInd;
    }
}
