package p.s0361;

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int max = 0;
        int ROW = grid.length;
        int COL = grid[0].length;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (grid[i][j] == '0') {
                    int res = calculate(grid, i, j, COL, ROW);
                    max = Integer.max(res, max);
                }
            }
        }
        return max;
    }

    public int calculate(char[][] grid, int i, int j, int col, int row) {
        int count = 0;
        if (i > 0) {
            for (int cursor = i - 1; cursor >= 0 && grid[cursor][j] != 'W'; cursor--) {
                if (grid[cursor][j] == 'E') {
                    count++;
                }
            }
        }
        if (i < row - 1) {
            for (int cursor = i + 1; cursor < row && grid[cursor][j] != 'W'; cursor++) {
                if (grid[cursor][j] == 'E') {
                    count++;
                }
            }
        }
        if (j > 0) {
            for (int cursor = j - 1; cursor >= 0 && grid[i][cursor] != 'W'; cursor--) {
                if (grid[i][cursor] == 'E') {
                    count++;
                }
            }
        }
        if (j < col - 1) {
            for (int cursor = j + 1; cursor < col && grid[i][cursor] != 'W'; cursor++) {
                if (grid[i][cursor] == 'E') {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
        System.out.println(solution.maxKilledEnemies(grid));
    }

}
