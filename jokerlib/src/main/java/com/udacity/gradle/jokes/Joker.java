package com.udacity.gradle.jokes;

import java.util.Random;

/**
 * Created by Chris Hare on 12/28/2015.
 */
public class Joker {

    private String[] listJokes = {"You kill vegitarian vampires with a steak to the heart.",
            "I drink, therefore I am", "What kind of shoes do ninjas wear? Sneakers.",
            "When you get a bladder infection, urine trouble", "What do you get when you put root beer in a square glass? Beer.",
            "Bugs come in through open Windows", "My attitude isn't bad. It's in beta.",
            "Programming is like sex, one mistake and you have to support it the rest of your life.",
            "Are you Google? Because I've found what I've been searching for.",
            "I want our love to be like pi, irrational and never ending.", "I like every bone in your body, especially mine."};

    public String getJoke() {
        int rnd = new Random().nextInt(listJokes.length);
        return listJokes[rnd];
    }
}
