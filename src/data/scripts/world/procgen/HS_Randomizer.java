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
    
    public int intRand(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    public float floatRand(float min, float max) {
        Random rand = new Random();
        return min + rand.nextFloat() * (max - min);
    }
    
    public double randFixed() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    
}
