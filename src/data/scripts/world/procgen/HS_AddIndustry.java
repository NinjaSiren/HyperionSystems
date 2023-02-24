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
            if(market.hasIndustry(Industries.AQUACULTURE)) --iSize;
            else new baseGameInd().aquaCulture(market, faction);
            if(iSize < 0) break;

            // Farming
            if(market.hasIndustry(Industries.FARMING)) --iSize;
            else new baseGameInd().farmInd(market, faction);
            if(iSize < 0) break;

            // Mining
            if(market.hasIndustry(Industries.MINING)) --iSize;
            else new baseGameInd().mineInd(market, planet, faction);
            if(iSize < 0) break;

            // Techmining
            if(market.hasIndustry(Industries.TECHMINING)) --iSize;
            else new baseGameInd().techMining(market, faction);
            if(iSize < 0) break;
            
            // Light Industry
            if(market.hasIndustry(Industries.LIGHTINDUSTRY)) --iSize;
            else new baseGameInd().lightInd(market, faction);
            if(iSize < 0) break;

            // Refining
            if(market.hasIndustry(Industries.REFINING)) --iSize;
            else new baseGameInd().refInd(market, faction);
            if(iSize < 0) break;

            // Heavy Industry or Orbital Works
            if(market.hasIndustry(Industries.HEAVYINDUSTRY) || market.hasIndustry(Industries.ORBITALWORKS)) --iSize;
            else new baseGameInd().heavyInd(market, faction);
            if(iSize < 0) break;

            // Fuel Production
            if(market.hasIndustry(Industries.FUELPROD)) --iSize;
            else new baseGameInd().fuelInd(market, faction);
            if(iSize < 0) break;

            // Cryosanctum
            new baseGameInd().cryoSanctum(market, faction);

            // Orbital Station
            // new baseGameInd().planetStation(market, faction);
            
            // Add Industrial Evolution Industries if Industrial Evolution is enabled
            if(isINDEVO) {

                // Supercomputer
                if(market.hasIndustry(new INDEVO_INDUSTRIES().SUPCOM)) --iSize;
                else new indEvoInd().superComputer(market, faction);
                if(iSize < 0) break;

                // Salvage Yards
                if(market.hasIndustry(new INDEVO_INDUSTRIES().SCRAPYARD)) --iSize;
                else new indEvoInd().superComputer(market, faction);
                if(iSize < 0) break;

                // Privateer Base
                if(market.hasIndustry(new INDEVO_INDUSTRIES().PIRATEHAVEN)) iSize = iSize - 2;
                else new indEvoInd().superComputer(market, faction);
                if(iSize < 0) break;

                // Senate
                new indEvoInd().indEvoSenate(market, faction);

                // Centralization Bureau
                new indEvoInd().indEvoCenter(market, faction);

                // Academy
                new indEvoInd().indEvoAcademy(market, faction);

                // Variable Assembler/Manufactory or Commodity Forge
                if(market.hasIndustry(new INDEVO_INDUSTRIES().COMFORGE)) --iSize;
                if(iSize < 0) break;
                
                if(market.hasIndustry(new INDEVO_INDUSTRIES().ADMANUF)) --iSize;
                new indEvoInd().indEvoVarForge(market, faction);
                if(iSize < 0) break;
                
                
                // Embassy
                new indEvoInd().indEvoEmbassy(market, faction);
                
                // Restoration Docks
                new indEvoInd().indEvoResDock(market, faction);
                
                // Requisition Center
                new indEvoInd().indEvoResDock(market, faction);
                
                // Engineering Hub
                new indEvoInd().indEvoEngHub(market, faction);
                
                // Courier Port
                new indEvoInd().indEvoCourPort(market, faction);
                
                // Military Relays or Interstellar Relay
                new indEvoInd().relaysNoRelay(market, sector, faction);
                
                // TODO : World Symbols

            }

            // Add DIY Planets' Industries if DIY Planets is enabled
            if(isDIY) {
                new diyIndustries(planet, market, faction, sector);
            }
        } while(iSize > 0);
    }
}
