package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import data.scripts.world.procgen.variables.PLANET_TYPES;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_JumpZoneHabitables {
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Adds jump points when planet is habitable
    public HS_JumpZoneHabitables(JumpPointAPI[] jp, int planetAmount, PlanetAPI[] planets, PlanetAPI star, 
            StarSystemAPI system) {
        
        // Jump points, Habitable
        int jp_counter = 0;
        float angle;
        do {
            if(jp_counter < 0 || jp_counter >= planetAmount) break;
            
            // If planet is habitable
            else if(planets[jp_counter].getTypeId().equals(new PLANET_TYPES().ARID) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().DESERT_A) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().DESERT_B) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().JUNGLE) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().OCEAN) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().TERRAN) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().TERRAN_ECCENTRIC) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().TUNDRA) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_ALKALI) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_ARID) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_ARID_LIFELESS) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_AURIC) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_AURIC_CLOUDY) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_CRIMSON) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_DESERT_A) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_DESERT_B) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_DESERT_C) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_JUNGLE) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_LIFELESS) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_MAGNETIC) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_METHANE) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_OCEAN_A) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_OCEAN_B) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_RED) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_RED_WIND) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_STORM) ||
                                planets[jp_counter].getTypeId().equals(new PLANET_TYPES().US_TERRAN)) {
                
                // Creates the planet jump point
                jp[jp_counter] = Global.getFactory().createJumpPoint(
                        planets[jp_counter].getName() + "_jp", 
                        planets[jp_counter].getName() + " Jump Point");
                
                // Sets the jump point orbit angle, +- 60 or +- 120 degrees from the planet
                if(rand() <= 0.25) angle = planets[jp_counter].getCircularOrbitAngle() - 60;
                else if(rand() > 0.25 && rand() <= 0.5) angle = planets[jp_counter].getCircularOrbitAngle() - 120;
                else if(rand() > 0.5 && rand() <= 0.75) angle = planets[jp_counter].getCircularOrbitAngle() + 120;
                else angle = planets[jp_counter].getCircularOrbitAngle() + 60;
                
                // Sets the orbit of the jump point
                OrbitAPI orbit_1 = Global.getFactory().createCircularOrbit(star, 
                        angle, 
                        planets[jp_counter].getCircularOrbitRadius(), 
                        planets[jp_counter].getCircularOrbitPeriod());
                
                jp[jp_counter].setOrbit(orbit_1); // Enables the orbit of the jump point
                jp[jp_counter].setRelatedPlanet(planets[jp_counter]); // Sets the planet connected to the jump point
                jp[jp_counter].setStandardWormholeToHyperspaceVisual(); // Shows it to the hyperspace
                system.addEntity(jp[jp_counter]); //  Adds it on the system as an entity
            }
            jp_counter++;
        } while(jp_counter < planetAmount);
    }
}
