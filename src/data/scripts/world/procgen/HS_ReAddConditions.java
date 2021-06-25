/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import java.util.List;

/**
 *
 * @author NinjaSiren
 */
public class HS_ReAddConditions {
    
    public HS_ReAddConditions(List<MarketConditionAPI> condition_list, MarketAPI market) {
        
        // Adds current conditions
        int counter = condition_list.size() - 1;
        do {
            market.addCondition(condition_list.get(counter));
            counter = counter - 1;
        } while(counter >= 0);
        market.reapplyConditions();
    }
}
