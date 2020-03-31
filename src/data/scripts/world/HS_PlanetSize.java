/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.campaign.PlanetAPI;
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
    
        // Roll the dice
    private double rand2() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Sets the planet type depending on the distance of the planet from its parent star
    // And to what type of star it is
    public int planetSize(String planetType, PlanetAPI[] star, int star_count, float orbit_radius,
            PlanetAPI[] planet, int planetNumber) {

        float[] habitableZoneMin = new float[star_count];
        float[] habitableZoneMax = new float[star_count];
        float[] habitableZoneMiddleMin = new float[star_count];
        float[] habitableZoneMiddleMax = new float[star_count];
        int counter = 0;
        do {
            if(counter < 0 || counter >= star_count) break;
            else {
                habitableZoneMin[counter] = starHabitableMin(star[counter]);
                habitableZoneMax[counter] = starHabitableMin(star[counter]) * 1.25f;
                habitableZoneMiddleMin[counter] = ((starHabitableMin(star[counter]) - 
                        starHabitableMin(star[counter])) / 4) + habitableZoneMin[counter]; 
                habitableZoneMiddleMax[counter] =  starHabitableMin(star[counter]) - 
                        ((starHabitableMin(star[counter]) - starHabitableMin(star[counter])) / 4);
                counter++;
            }
        } while(counter < star_count + 1);
        
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
            return rand(70, 225);
        }
    }
    
    // Gets what star type and returns habitable zone min orbit
    private float starHabitableMin(PlanetAPI star) {
        float starRadius = star.getRadius();
        float starCoronaRadius = star.getSpec().getCoronaSize();
        
        // Yellow Main Sequence
        if(star.getTypeId().equals(new Star().YELLOW) ||
                star.getTypeId().equals(new Star().YELLOW_US)) {
            return ((starRadius * 1.725f) + (starCoronaRadius / 2));
            
        // Red Giant
        } else if(star.getTypeId().equals(new Star().RED_GIANT)) {
                return ((starRadius * 2.3f) + (starCoronaRadius / 2));
            
        // Red Supergiant
        } else if(star.getTypeId().equals(new Star().RED_SUPERGIANT)) {
                return ((starRadius * 4.6f) + (starCoronaRadius / 2));
                
        // Red Dwarf
        } else if(star.getTypeId().equals(new Star().RED_DWARF) ||
                star.getTypeId().equals(new Star().RED_DWARF_US)) {
            return ((starRadius * 1.05f) + (starCoronaRadius / 2));
        
        // Orange Main Sequence
        } else if(star.getTypeId().equals(new Star().ORANGE)) {
            return ((starRadius * 1.575f) + (starCoronaRadius / 2));
            
        // Orange Giant
        } else if(star.getTypeId().equals(new Star().ORANGE_GIANT) ||
                star.getTypeId().equals(new Star().ORANGE_GIANT_US)) {
            return ((starRadius * 3.15f) + (starCoronaRadius / 2));

        // Blue Giant
        } else if(star.getTypeId().equals(new Star().BLUE_GIANT) ||
                star.getTypeId().equals(new Star().BLUE_GIANT_US)) {
            return ((starRadius * 5.175f) + (starCoronaRadius / 2));
            
        // Blue Supergiant
        } else if(star.getTypeId().equals(new Star().BLUE_SUPERGIANT)) {
            return ((starRadius * 10.35f) + (starCoronaRadius / 2));
        
        // White Dwarf
        } else if(star.getTypeId().equals(new Star().WHITE_DWARF) ||
                star.getTypeId().equals(new Star().WHITE_DWARF_US)) {
            return ((starRadius * 1.725f) + (starCoronaRadius / 2));
        
        // Brown Dwarf
        } else if(star.getTypeId().equals(new Star().BROWN_DWARF) ||
                star.getTypeId().equals(new Star().BROWN_DWARF_US)) {
            return ((starRadius * 1.015f) + (starCoronaRadius / 2));
        
        // Neutron Star
        } else if(star.getTypeId().equals(new Star().NEUTRON_STAR)) {
            return starRadius * 10;
        
        // Black Hole
        } else if(star.getTypeId().equals(new Star().BLACK_HOLE)) {
            return 0;
            
        // Nebula Young
        } else if(star.getTypeId().equals(new Star().NEBULA_YOUNG)) {
                return 0;
                
        // Nebula Average
        } else if(star.getTypeId().equals(new Star().NEBULA_AVERAGE)) {
            return 0;

        // Nebula Old
        } else if(star.getTypeId().equals(new Star().NEBULA_OLD)) {
            return 0;
        }
        return 0;
    } 
}
