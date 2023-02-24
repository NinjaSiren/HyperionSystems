package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.HyperionModDependencies;
import data.scripts.world.procgen.variables.INDEVO_INDUSTRIES;

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
        boolean isBC = new HyperionModDependencies().hasBetterColonies();
        boolean isIE = new HyperionModDependencies().hasIndEvolution();
        
        if(!isBC) {
            // Vanilla, without Coronal Hypershunt          
            switch (marketSize) {
                case 1:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 2:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 3:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 4:
                    iLimit = 2 - mHasIndustry(market, isIE);
                    return iLimit;
                case 5:
                    iLimit = 3 - mHasIndustry(market, isIE);
                    return iLimit;
                case 6:
                    iLimit = 4 - mHasIndustry(market, isIE);
                    return iLimit;
                case 7:
                    iLimit = 4 - mHasIndustry(market, isIE);
                    return iLimit;
                case 8:
                    iLimit = 4 - mHasIndustry(market, isIE); 
                    return iLimit;
                case 9:
                    iLimit = 5 - mHasIndustry(market, isIE); 
                    return iLimit;
                case 10:
                    iLimit = 5 - mHasIndustry(market, isIE);
                    return iLimit;
            }
        } else {
            // Better Colonies, without Coronal Hypershunt
            switch (marketSize) {
                case 1:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 2:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 3:
                    iLimit = 1 - mHasIndustry(market, isIE);
                    return iLimit;
                case 4:
                    iLimit = 2 - mHasIndustry(market, isIE);
                    return iLimit;
                case 5:
                    iLimit = 3 - mHasIndustry(market, isIE);
                    return iLimit;
                case 6:
                    iLimit = 4 - mHasIndustry(market, isIE);
                    return iLimit;
                case 7:
                    iLimit = 4 - mHasIndustry(market, isIE);
                    return iLimit;
                case 8:
                    iLimit = 4 - mHasIndustry(market, isIE); 
                    return iLimit;
                case 9:
                    iLimit = 4 - mHasIndustry(market, isIE); 
                    return iLimit;
                case 10:
                    iLimit = 4 - mHasIndustry(market, isIE);
                    return iLimit;
            }
        }
        return 0;
    }
    
    // Checks if planet market has specific industry
    private int mHasIndustry(MarketAPI market, boolean isIE) {
        int exInd = 0;        
        
        // Military
        if(market.hasIndustry(Industries.MILITARYBASE)) {
            exInd = exInd + 1;
        } else if(market.hasIndustry(Industries.HIGHCOMMAND)) {
            exInd = exInd + 1;
        }

        // Ship and hardware building
        if(market.hasIndustry(Industries.HEAVYINDUSTRY)) {
            exInd = exInd + 1;
        } else if(market.hasIndustry(Industries.ORBITALWORKS)) {
            exInd = exInd + 1;
        }

        // Fuel production
        if(market.hasIndustry(Industries.FUELPROD)) {
            exInd = exInd + 1;
        } else if(market.hasIndustry("advfuelprod")) {
            exInd = exInd + 1;
        }

        // Goods production
        if(market.hasIndustry(Industries.LIGHTINDUSTRY)) {
            exInd = exInd + 1;
        }

        //Farming
        if(market.hasIndustry(Industries.FARMING)) {
            exInd = exInd + 1;
        }

        //Mining
        if(market.hasIndustry(Industries.MINING)) {
            exInd = exInd + 1;
        }

        //Refining
        if(market.hasIndustry(Industries.REFINING)) {
            exInd = exInd + 1;
        }

        //Aquaculture
        if(market.hasIndustry(Industries.AQUACULTURE)) {
            exInd = exInd + 1;
        }

        //Techmining
        if(market.hasIndustry(Industries.TECHMINING)) {
            exInd = exInd + 1;
        }
        
        //If Industrial Evolution is running in the game
        if(isIE) {
            
            //Salvage Yards
            if(market.hasIndustry(new INDEVO_INDUSTRIES().SCRAPYARD)) {
                exInd = exInd + 1;
            }
            
            //Variable Manufactory
            if(market.hasIndustry(new INDEVO_INDUSTRIES().ADMANUF)) {
                exInd = exInd + 1;
            }
            
            //Commodity Forge
            if(market.hasIndustry(new INDEVO_INDUSTRIES().COMFORGE)) {
                exInd = exInd + 1;
            }
            
            //Supercomputer
            if(market.hasIndustry(new INDEVO_INDUSTRIES().SUPCOM)) {
                exInd = exInd + 1;
            }
            
            //Privateer Base
            if(market.hasIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN)) {
                exInd = exInd + 2;
            }
        }
        
        return exInd;
    }
}