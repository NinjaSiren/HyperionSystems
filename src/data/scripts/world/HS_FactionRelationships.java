/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;

/**
 *
 * @author NinjaSiren
 */
public class HS_FactionRelationships {
    
    // Automatically changes faction relationship on the fly, depending on the status (boolean)
    public HS_FactionRelationships(FactionAPI factionA, FactionAPI factionB, boolean relationship , 
            float value, SectorAPI sector) {
        
        // If good relationship
        if(relationship) {
            Global.getSector().getFaction(factionA.getId()).adjustRelationship(factionB.getId(), + value);
            Global.getSector().getFaction(factionB.getId()).adjustRelationship(factionA.getId(), + value);
        }
        
        // If bad relationship
        else {
            Global.getSector().getFaction(factionA.getId()).adjustRelationship(factionB.getId(), - value);
            Global.getSector().getFaction(factionB.getId()).adjustRelationship(factionA.getId(), - value);
        }
    }
}
