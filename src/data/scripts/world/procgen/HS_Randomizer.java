/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_Randomizer {
    
    
    // Randomizer for integer values, limits are inputted
    public int intRand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    // Randomizer for float values, limits are inputted
    public float floatRand(float min, float max) {
        Random rand = new Random();
        return min + rand.nextFloat() * (max - min);
    }
    
    // Randomizer for double values, limits are fixed (1.0 max, 0.0 min)
    public double randFixed() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    
}
