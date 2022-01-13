/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.systems;

import java.util.Random;

/**
 *
 * @author NinjaSiren
 * 
 * Sets a random number for a random system background
 * 
 */
public class HS_RandSysBG {
   
    public int rand_bg() {
        Random rand = new Random();
        final int max = 6;
        final int min = 1;
        return min + rand.nextInt(max - min + 1);
    }
}
