package com.algorithms.ahocorasik;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class AhoCorasik {
    private Cell root ;
    public AhoCorasik() {
        root = new Cell();
        root.setRoot(true);
        root.setFailure(root);
        root.setParent(root);
    }

    public void addWords(String[] strings) {
        for (String s : strings) {
            addWord(s);
        }
    }

    public void addWord(String word) {
        word = word.toLowerCase();
        //Iterate through word and add the letters
        Cell current = root;
        for (char c : word.toCharArray()) {
            //If it doesnt exist yet as a child, make it exist! Poof!
            if (!current.hasChild(c)) {
                current.addChild(c);
            }
            //O(1) lookup time for each child (faster than arraylist)
            current = current.getChild(c);
        }
        current.setWord(word);
        current.setEnd(true);

    }

    public void generateFailureLinks() {
        //Generate our failure links
        HashMap<Character, Cell> children;
        Queue<Cell> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        Cell current, next;
        Cell failure;
        while (!queue.isEmpty()) {
            //We now have our first cell
            current = queue.poll();
            children = current.getAllChildrenAsHashmap();
            next = current.getParent().getFailure();
            //System.out.println("The first next node is " + next.getValue());

            while (!next.isRoot() && !next.hasChild(current.getValue())) {
                next = next.getFailure();
            }

            if (next.hasChild(current.getValue()) && next.getChild(current.getValue())!=current) {
                failure = next.getChild(current.getValue());
            } else {
                failure = root;
            }
            current.setFailure(failure);

            //Enqueue all of the children
            children.entrySet().forEach(entry -> {queue.add(entry.getValue());});
        }
    }

    public HashMap<String, Integer> search(String text) {
        text = text.toLowerCase();
        HashMap<String, Integer> result = new HashMap<>();
        Cell current = root;
        for (char c : text.toCharArray()) {
            if (current.hasChild(c)) {
                current = current.getChild(c);
            } else {
                //Here we traverse our failure links until we either get to the root or find a node with child
                while (!current.isRoot() && !current.hasChild(c)) {
                    current = current.getFailure();
                }
                //If at all possible, continue traversal
                if (current.hasChild(c)) {
                    current = current.getChild(c);
                }

            }

            //See how many endings to this word there are
            Cell finalWord = current;

            while (!finalWord.isRoot()) {
                if (finalWord.isEnd()) {
                    if (result.containsKey(finalWord.getWord())) {
                        result.put(finalWord.getWord(), result.get(finalWord.getWord()) + 1);
                    } else {
                        result.put(finalWord.getWord(), 1);
                    }
                }
                finalWord = finalWord.getFailure();
            }
        }
        return result;
    }

}
