package com.algorithms.ahocorasik;

import java.util.ArrayList;

public class RegularTechnique {

    public ArrayList<ArrayList<String>> search (String word, String text) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i=0; i<text.length(); i++) {
            boolean found = true;
            for (int j=0; j<word.length(); j++) {
                if (text.charAt(i)!=word.charAt(j)) {
                    found= false;
                }
            }
            if (found) {
                ArrayList<String> n = new ArrayList<>();
                n.add(word);
                n.add(String.valueOf(i));
                result.add(n);
            }
        }
        return result;
    }
}
