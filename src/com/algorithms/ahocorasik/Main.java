package com.algorithms.ahocorasik;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException {
	    // Read in the entire bible
        String path = new File("bible.txt").getAbsolutePath();
        String bible = Files.readString(Path.of(path), StandardCharsets.UTF_8);

        //Add the words
        String[] words = new String[]{
                "Jesus",
                "time",
                "people",
                "People",
                "health",
                "Lord",
                "mercy",
                "health",
                "communion",
                "compassion",
                "love",
                "grace",
                "angel",
                "temptation",
                "sin",
                "blessing",
                "forgiven",
                "your",
                "in",
                "temple",
        };

        //Create our AhoCorasik Thingy
        AhoCorasik ahoCorasik = new AhoCorasik();

        //Add the words to our thingy
        ahoCorasik.addWords(words);

        //Generate our failure links
        ahoCorasik.generateFailureLinks();

        //Run our thing!
        long a = System.currentTimeMillis();
        HashMap<String, Integer> results = ahoCorasik.search(bible);
        long b = System.currentTimeMillis();

        //Print out our results!
        results.forEach((key, value) -> System.out.println(value + " -> " + key));
        System.out.println("Searched took " + (b-a) + " milliseconds");

    }
}
