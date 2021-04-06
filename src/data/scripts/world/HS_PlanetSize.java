/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_PlanetSize {
    
    // Roll the dice
    private int rand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    // Sets the planet type depending on the distance of the planet from its parent star
    // And to what type of star it is
    public int planetSize(String planetType) {
        
        // Gas Giants
        if(planetType.equals(new Planet().GAS_GIANT) || 
                planetType.equals(new Planet().US_GAS_GIANT_A) ||
                planetType.equals(new Planet().US_GAS_GIANT_B)) {
            return rand(300, 600); 

        // Ice Giants
        } else if(planetType.equals(new Planet().ICE_GIANT)) {
            return rand(200, 500);

        // Lavas and Moltens
        } else if(planetType.equals(new Planet().LAVA) || 
                planetType.equals(new Planet().LAVA_MINOR) ||
                planetType.equals(new Planet().US_LAVA) || 
                planetType.equals(new Planet().US_VOLCANIC)) {
            return rand(50, 200);

        // Cryovolcanics
        } else if(planetType.equals(new Planet().CRYOVOLCANIC) || 
                planetType.equals(new Planet().US_CRYOVOLCANIC)) {
            return rand(50, 300);

        // Frozens
        } else if(planetType.equals(new Planet().FROZEN_A) ||
                planetType.equals(new Planet().FROZEN_B) ||
                planetType.equals(new Planet().FROZEN_C) ||
                planetType.equals(new Planet().FROZEN_D) ||
                planetType.equals(new Planet().US_FROZEN_A) ||
                planetType.equals(new Planet().US_FROZEN_B)) {
            return rand(50, 200);

        // Barrens
        } else if(planetType.equals(new Planet().BARREN_A) ||
                planetType.equals(new Planet().BARREN_B) ||
                planetType.equals(new Planet().BARREN_C) ||
                planetType.equals(new Planet().US_BARREN_A) ||
                planetType.equals(new Planet().US_BARREN_B) ||
                planetType.equals(new Planet().US_BARREN_C) ||
                planetType.equals(new Planet().US_BARREN_D) ||
                planetType.equals(new Planet().US_BARREN_E) ||
                planetType.equals(new Planet().US_BARREN_F)) {
            return rand(50, 200);

        } if(planetType.equals(new Planet().BARREN_CASTIRON)) {
            return rand(50, 300);

        } else if(planetType.equals(new Planet().BARREN_VENUSLIKE)) {
            return rand(50, 200);
            
        } else if(planetType.equals(new Planet().BARREN_BOMBARDED)) {
            return rand(50, 150);
            
        // Rocky
        } else if(planetType.equals(new Planet().ROCKY_METALLIC)) {
            return rand(50, 200);
            
        } else if(planetType.equals(new Planet().ROCKY_UNSTABLE)) {
            return rand(50, 200);
            
        } else if(planetType.equals(new Planet().ROCKY_ICE)) {
            return rand(50, 200);

            
        // Irradiated and Azures
        } else if(planetType.equals(new Planet().IRRADIATED) ||
                planetType.equals(new Planet().US_AZURE)) {
            return rand(50, 275);
            
        // Toxics and Acidics
        } else if(planetType.equals(new Planet().TOXIC) ||
                planetType.equals(new Planet().TOXIC_COLD) ||
                planetType.equals(new Planet().US_ACID) ||
                planetType.equals(new Planet().US_ACIDRAIN) ||
                planetType.equals(new Planet().US_ACIDWIND) ||
                planetType.equals(new Planet().US_CHLORINE)) {
            return rand(50, 275);
            
        // Artificial
        } else if(planetType.equals(new Planet().US_ARTIFICIAL)) {
            return rand(50, 150);
            
        // Burnt
        } else if(planetType.equals(new Planet().US_BURNT)) {
            return rand(50, 200);
            
        // Dust
        } else if(planetType.equals(new Planet().US_DUST)) {
            return rand(50, 200);
            
        // All Habitables
        } else {
            return rand(75, 135);
        }
    }
}
