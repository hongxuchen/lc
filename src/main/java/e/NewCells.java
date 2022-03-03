package e;

import java.util.Scanner;

public class NewCells {

    /**
     * 0 -- 1
     * 1 -- 2
     * 2 -- 4
     * 3 -- 8 - 1
     * f(n) = 2*f(n-1) - f(n-3)
     */

    public static int cellName(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 4;
        }
        return cellName(n - 1) * 2 - cellName(n - 3);
    }

    public static int cellNameIteration(int n) {
        int[] num = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                num[i] = 1;
            } else if (i == 1) {
                num[i] = 2;
            } else if (i == 2) {
                num[i] = 4;
            } else {
                num[i] = num[i - 1] * 2 - num[i - 3];
            }
        }
        return num[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int hour = scanner.nextInt();
            if (hour >= 0) {
                int totalCells = cellNameIteration(hour);
                System.out.println(totalCells);
            } else {
                System.exit(0);
            }
        }
    }
}
