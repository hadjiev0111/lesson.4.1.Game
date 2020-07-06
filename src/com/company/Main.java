package com.company;

import javax.swing.*;
import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 265};
    public static int[] heroesDamage = {20, 15, 25};
    public static final int HEALING = 20;
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic"};


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomNumber];
        System.out.println("Boss got" + bossDefenceType);
    }

    public static void round() {
        changeDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
        }
        healing();
        printStatistics();

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("______________");
        System.out.println("Boss Health" + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health :" + heroesHealth[2]);
        System.out.println( "Medic health:" + heroesHealth[3] );
        System.out.println("______________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void healing() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100){
                heroesHealth[i] += HEALING;
                return;

            }

        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2; // 2,3,4,5,6
                    System.out.println("Critical damage" + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}

