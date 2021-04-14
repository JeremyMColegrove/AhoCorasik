package com.algorithms.ahocorasik;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
    private HashMap<Character, Cell> children;
    private Cell failure;
    private Cell parent;
    private boolean isRoot = false;
    private boolean end;
    private String word;
    private char value;

    public Cell (char c) {
        children = new HashMap<>();
        value = c;
    }

    public Cell() {
        children = new HashMap<>();
    };

    public char getValue() {
        return value;
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }
    public Cell getChild(char c) {
        return children.get(c);
    }

    public HashMap<Character, Cell> getAllChildrenAsHashmap() {
        return children;
    }
    public void addChild(char c) {
        Cell cell = new Cell(c);
        cell.parent = this;
        children.put(c, cell);
    }

    public Cell getFailure() {
        return failure;
    }

    public void setFailure(Cell failure) {
        this.failure = failure;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
