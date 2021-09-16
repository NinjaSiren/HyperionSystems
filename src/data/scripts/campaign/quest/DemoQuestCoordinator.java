/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.campaign.quest;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 * Coordinates and tracks the state of Demo quest.
 */
public class DemoQuestCoordinator {
/**
     * The tag that is applied to the planet the player must travel to.
     */
    private static final String TAG_DESTINATION_PLANET = "Demo_destination_planet";
    static boolean isComplete = false;

    static SectorEntityToken getDestinationPlanet() {
        return Global.getSector().getEntityById(TAG_DESTINATION_PLANET);
    }

    static boolean shouldOfferQuest() {
        return true; // Set some conditions
    }

    /**
     * Called when player starts the bar event.
     */
    static void initQuest() {
        chooseAndTagDestinationPlanet();
    }

    /**
     * Player has accepted quest.
     */
    static void startQuest() {
        Global.getSector().getIntelManager().addIntel(
                new DemoIntel(getDestinationPlanet(), getDestinationPlanet()));
    }

    /**
     * Very dumb method that idempotently tags a random planet as the destination.
     */
    private static void chooseAndTagDestinationPlanet() {
        if (getDestinationPlanet() == null) {
            StarSystemAPI randomSystem = Global.getSector().getStarSystems()
                    .get(new Random().nextInt(Global.getSector().getStarSystems().size()));
            PlanetAPI randomPlanet = randomSystem.getPlanets()
                    .get(new Random().nextInt(randomSystem.getPlanets().size()));
            randomPlanet.addTag(TAG_DESTINATION_PLANET);
        }
    }

    static void completeQuest() {
        isComplete = true;
    }   
}
