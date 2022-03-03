
package e;

public class BSearch {
    static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

    public static int search(int[] array, int left, int right, int key) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (key == array[mid]) {
            return mid;
        } else if (key < array[mid]) {
            return search(array, left, mid - 1, key);
        } else {
            return search(array, mid + 1, right, key);
        }
    }

    public static void main(String[] args) {
        int pos = search(array, 0, array.length, 7);
        System.out.println(pos);
    }

}
