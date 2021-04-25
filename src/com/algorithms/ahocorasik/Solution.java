package com.algorithms.ahocorasik;

import java.util.HashMap;

class Solution {
    char[][] board;
    String word;
    HashMap<Integer[], Boolean> visited = new HashMap<>();
    public boolean exist(char[][] board, String word) {
        //This is another backtracking algorithm. Start at each cell, and try moving from there
        this.board = board;
        this.word = word;
        return dfs(0, 0, "");
    }

    public boolean dfs(int x, int y, String test) {
        boolean l = false;
        boolean r = false;
        boolean u = false;
        boolean d =false;
        test += board[x][y];
        if (test == word) return true;
        //Check if there are no neighbors
        if (visited.containsKey(new Integer[] {x, y})) return false;
        visited.put(new Integer[] {x, y}, true);

        if (x>0) l = dfs(x-1, y, test);
        if (x<board[0].length-1) r = dfs(x+1, y, test);
        if (y>0) u = dfs(x, y-1, test);
        if (y<board.length-1) d = dfs(x, y+1, test);

        return (l || r || u || d);
    }
}
