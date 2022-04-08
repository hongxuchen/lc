
package u.t.s1;

class Solution {

    public int added(int result) {
        return result > 0 ? 1 : 0;
    }

    public void updateGrowth(int[][] realGrowth, int t, int mm, int nn, int[][] updates) {

        for (int i = 1; i < mm - 1; i++) {
            for (int j = 1; j < nn - 1; j++) {
                int surrounding = realGrowth[i - 1][j] + realGrowth[i + 1][j] + realGrowth[i][j - 1]
                        + realGrowth[i][j + 1];
                int surroundingAdds = 0;
                if (surrounding >= 21) {
                    surroundingAdds = -5;
                }
                int adds = added(realGrowth[i - 1][j]) + added(realGrowth[i + 1][j]) + added(realGrowth[i][j - 1])
                        + added(realGrowth[i][j + 1]);
                updates[i - 1][j - 1] = surroundingAdds + adds;
            }
        }

        for (int i = 1; i < mm - 1; i++) {
            for (int j = 1; j < nn - 1; j++) {
                realGrowth[i][j] += updates[i - 1][j - 1];
            }
        }

    }

    public int sum(int[][] realGrowth) {
        int sum = 0;
        for (int i = 1; i < realGrowth.length - 1; i++) {
            for (int j = 1; j < realGrowth[0].length - 1; j++) {
                sum += realGrowth[i][j];
            }
        }
        return sum;
    }

    public int getPlantLushSums(int[][] growth, int[] interfere, int time) {

        int m = growth.length;
        int n = growth[0].length;

        int[][] updates = new int[m][n];
        int[][] realGrowth = new int[m + 2][n + 2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                realGrowth[i + 1][j + 1] = growth[i][j];
            }
        }

        for (int t = 1; t <= time; t++) {
            updateGrowth(realGrowth, t, m + 2, n + 2, updates);
            if (t == interfere[0]) {
                int ii = interfere[1] + 1;
                int jj = interfere[2] + 1;
                realGrowth[ii][jj] += interfere[3];
            }
        }

        return sum(realGrowth);

    }

}
