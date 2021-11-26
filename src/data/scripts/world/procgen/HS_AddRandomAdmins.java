/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;

/**
 *
 * @author NinjaSiren
 */
public class HS_AddRandomAdmins {
    
    public PersonAPI generateAdmins(MarketAPI market) {
        if (market != null && !market.getAdmin().getPost().equals(Ranks.POST_ADMINISTRATOR)) {
            PersonAPI person = Global.getFactory().createPerson();
            
            if(market.getFactionId() != null) {
                person.setFaction(market.getFactionId());
            } else {
                person.setFaction(market.getFaction().getId());
            }
            
            person.setGender(FullName.Gender.ANY);
            person.setRankId(Ranks.CITIZEN);
            person.setPostId(Ranks.POST_ADMINISTRATOR);
            person.setId(market.getId() + "_admin");
            person.setPortraitSprite("graphics/portraits/random/portrait" + 
                    new HS_Randomizer().intRand(1, 99) + ".png");
            person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 
                    new HS_Randomizer().intRand(0, 3));
            person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 
                    new HS_Randomizer().intRand(0, 3));
            person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 
                    new HS_Randomizer().intRand(0, 3));
            person.setMarket(market);
            return person;
        }
        return null;
    }
}
