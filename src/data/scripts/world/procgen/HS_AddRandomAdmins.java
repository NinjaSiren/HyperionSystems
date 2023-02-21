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
        PersonAPI person = Global.getFactory().createPerson();
        person.setGender(FullName.Gender.ANY);
        person.setRankId(Ranks.CITIZEN);
        person.setPostId(Ranks.POST_ADMINISTRATOR);
        person.setId(market.getId() + "_admin");
        person.setPortraitSprite("graphics/portraits/random/portrait" + 
                new HS_Randomizer().intRand(1, 99) + ".png");
        person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 
                new HS_Randomizer().intRand(0, 3));
        person.getStats().setSkillLevel(Skills.HYPERCOGNITION, 
                new HS_Randomizer().intRand(0, 3));
        return person;
    }
}
