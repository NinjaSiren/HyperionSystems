package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import data.scripts.HyperionModDependencies;
import data.scripts.world.procgen.industries.baseGameInd;
import data.scripts.world.procgen.industries.diyIndustries;
import data.scripts.world.procgen.industries.indEvoInd;
import data.scripts.world.procgen.variables.INDEVO_INDUSTRIES;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddIndustry {
    
    public HS_AddIndustry(PlanetAPI planet, MarketAPI market, FactionAPI faction, StarSystemAPI sector) { 
        
        boolean isDIY = new HyperionModDependencies().hasDIYPlanets();
        boolean isINDEVO = new HyperionModDependencies().hasIndEvolution();
        
        // Industry size depending on market size and current industries added
        int iSize = new HS_IndustryLimit().industryLimit(market, planet);
        
        // Add industries
        do {
            
            //TODO : CORONAL HYPERSHUNT

            // Aquaculture
            new baseGameInd().aquaCulture(market, planet, faction);
            if(market.hasIndustry(Industries.AQUACULTURE)) --iSize;
            if(iSize < 0) break;

            // Farming
            new baseGameInd().farmInd(market, planet, faction);
            if(market.hasIndustry(Industries.FARMING)) --iSize;
            if(iSize < 0) break;

            // Mining
            new baseGameInd().mineInd(market, planet, faction);
            if(market.hasIndustry(Industries.MINING)) --iSize;
            if(iSize < 0) break;

            // Techmining
            new baseGameInd().techMining(market, planet, faction);
            if(market.hasIndustry(Industries.TECHMINING)) --iSize;
            if(iSize < 0) break;
            
            // Light Industry
            new baseGameInd().lightInd(market, planet, faction);
            if(market.hasIndustry(Industries.LIGHTINDUSTRY)) --iSize;
            if(iSize < 0) break;

            // Refining
            new baseGameInd().refInd(market, planet, faction);
            if(market.hasIndustry(Industries.REFINING)) --iSize;
            if(iSize < 0) break;

            // Heavy Industry or Orbital Works
            new baseGameInd().heavyInd(market, planet, faction);
            if(market.hasIndustry(Industries.HEAVYINDUSTRY) || market.hasIndustry(Industries.ORBITALWORKS)) --iSize;
            if(iSize < 0) break;

            // Fuel Production
            new baseGameInd().fuelInd(market, planet, faction);
            if(market.hasIndustry(Industries.FUELPROD)) --iSize;
            if(iSize < 0) break;

            // Cryosanctum
            new baseGameInd().cryoSanctum(market, planet, faction);

            // Orbital Station
            new baseGameInd().planetStation(market, faction);
            
            // Add Industrial Evolution Industries if Industrial Evolution is enabled
            if(isINDEVO) {

                // Supercomputer
                new indEvoInd().superComputer(market, faction, planet);
                if(market.hasIndustry(new INDEVO_INDUSTRIES().SUPCOM)) --iSize;
                if(iSize < 0) break;

                // Salvage Yards
                new indEvoInd().superComputer(market, faction, planet);
                if(market.hasIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS)) --iSize;
                if(iSize < 0) break;

                // Privateer Base
                new indEvoInd().superComputer(market, faction, planet);
                if(market.hasIndustry(new INDEVO_INDUSTRIES().REPAIRDOCKS)) iSize = iSize - 2;
                if(iSize < 0) break;

                // Senate
                new indEvoInd().indEvoSenate(market, faction);

                // Centralization Bureau
                new indEvoInd().indEvoCenter(market, faction);

                // Academy
                new indEvoInd().indEvoAcademy(market, faction);

                // Variable Assembler/Manufactory or Commodity Forge
                new indEvoInd().indEvoVarForge(market, faction);
                
                // Embassy
                new indEvoInd().indEvoEmbassy(market);
                
                // Restoration Docks
                new indEvoInd().indEvoResDock(market);
                
                // Requisition Center
                new indEvoInd().indEvoResDock(market);
                
                // Engineering Hub
                new indEvoInd().indEvoEngHub(market);
                
                // Courier Port
                new indEvoInd().indEvoCourPort(market);
                
                // Military Relays or Interstellar Relay
                // new indEvoInd().relaysNoRelay(market, sector);
                
                // TODO : World Symbols

            }

            // Add DIY Planets' Industries if DIY Planets is enabled
            if(isDIY) {
                new diyIndustries(planet, market, faction, sector);
            }
        } while(iSize > 0);
    }
}
