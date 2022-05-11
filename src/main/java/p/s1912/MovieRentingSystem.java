package p.s1912;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class MovieRentingSystem {

    private final Map<Integer, TreeSet<MovieItem>> inShop = new HashMap<>();
    private final Set<MovieItem> rented = new TreeSet<>();
    private final Map<Integer, Map<Integer, MovieItem>> movieMap = new HashMap<>();

    private static final int COUNT = 5;

    static class MovieItem implements Comparable<MovieItem> {
        int id;
        int shop;
        int price;

        public MovieItem(int id, int shop, int price) {
            this.id = id;
            this.shop = shop;
            this.price = price;
        }

        public List<Integer> asList() {
            return List.of(shop, id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MovieItem movieItem = (MovieItem) o;
            return id == movieItem.id && shop == movieItem.shop && price == movieItem.price;
        }

        @Override
        public int hashCode() {
            return id * 7 + shop * 11 + price * 13;
        }

        @Override
        public String toString() {
            return "MovieItem{" +
                    "id=" + id +
                    ", shop=" + shop +
                    ", price=" + price +
                    '}';
        }

        @Override
        public int compareTo(MovieRentingSystem.MovieItem other) {
            if (this.price != other.price) {
                return this.price - other.price;
            }
            if (this.shop != other.shop) {
                return this.shop - other.shop;
            }
            return this.id - other.id;
        }

    }

    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] entry : entries) {
            MovieItem movieItem = new MovieItem(entry[1], entry[0], entry[2]);
            if (inShop.containsKey(movieItem.id)) {
                inShop.get(movieItem.id).add(movieItem);
            } else {
                TreeSet<MovieItem> itemTreeSet = new TreeSet<>();
                itemTreeSet.add(movieItem);
                inShop.put(movieItem.id, itemTreeSet);
            }
            if (movieMap.containsKey(movieItem.shop)) {
                Map<Integer, MovieItem> idMap = movieMap.get(movieItem.shop);
                if (idMap == null) {
                    idMap = new HashMap<>();
                    movieMap.put(movieItem.id, idMap);
                }
                idMap.put(movieItem.id, movieItem);
            } else {
                Map<Integer, MovieItem> idMap = new HashMap<>();
                idMap.put(movieItem.id, movieItem);
                movieMap.put(movieItem.shop, idMap);
            }
        }
    }

    public List<Integer> search(int movie) {
        TreeSet<MovieItem> movieItems = inShop.get(movie);
        if (movieItems == null || movieItems.isEmpty()) {
            return new ArrayList<>();
        }
        return movieItems.stream().limit(COUNT).map(s -> s.shop).collect(Collectors.toList());
    }

    public void rent(int shop, int movie) {
        TreeSet<MovieItem> items = inShop.get(movie);
        MovieItem movieItem = movieMap.get(shop).get(movie);
        rented.add(movieItem);
        items.remove(movieItem);
    }

    public void drop(int shop, int movie) {
        MovieItem movieItem = movieMap.get(shop).get(movie);
        TreeSet<MovieItem> treeSet = inShop.get(movieItem.id);
        treeSet.add(movieItem);
        rented.remove(movieItem);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> candidates = new ArrayList<>();
        int count = 0;
        for (MovieItem item : rented) {
            if (count >= COUNT) {
                break;
            }
            candidates.add(item.asList());
            count++;
        }
        return candidates;
    }

    public static void main(String[] args) {

//        {
//            int[][] entries = new int[][]{{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}};
//            MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, entries);
//            System.out.println(movieRentingSystem.search(1));  // 返回 [1, 0, 2] ，商店 1，0 和 2 有未借出的 ID 为 1 的电影。商店 1 最便宜，商店 0 和 2 价格相同，所以按商店编号排序。
//            movieRentingSystem.rent(0, 1); // 从商店 0 借出电影 1 。现在商店 0 未借出电影编号为 [2,3] 。
//            System.out.println("xx " + movieRentingSystem.rented);
//            movieRentingSystem.rent(1, 2); // 从商店 1 借出电影 2 。现在商店 1 未借出的电影编号为 [1] 。
//            System.out.println("xx " + movieRentingSystem.rented);
//            System.out.println(movieRentingSystem.report());   // 返回 [[0, 1], [1, 2]] 。商店 0 借出的电影 1 最便宜，然后是商店 1 借出的电影 2 。
//            movieRentingSystem.drop(1, 2); // 在商店 1 返还电影 2 。现在商店 1 未借出的电影编号为 [1,2] 。
//            System.out.println("xx " + movieRentingSystem.rented);
//            System.out.println(movieRentingSystem.search(2));  // 返回 [0, 1] 。商店 0 和 1 有未借出的 ID 为 2 的电影。商店 0 最便宜，然后是商店 1 。
//        }

//        {
//            int[][] entries = new int[][]{
//                    {4, 374, 55},
//                    {1, 6371, 21},
//                    {8, 3660, 24},
//                    {1, 56, 32},
//                    {5, 374, 71},
//                    {3, 4408, 36},
//                    {6, 9322, 73},
//                    {6, 9574, 92},
//                    {8, 7834, 62},
//                    {2, 6084, 27},
//                    {7, 3262, 89},
//                    {2, 8959, 53},
//                    {0, 3323, 41},
//                    {6, 6565, 45},
//                    {0, 4239, 20}
//            };
//            MovieRentingSystem movieRentingSystem = new MovieRentingSystem(10, entries);
//            movieRentingSystem.rent(0, 4239);
//            movieRentingSystem.drop(0, 4239);
//            movieRentingSystem.rent(3, 4408);
//            movieRentingSystem.rent(2, 6084);
//            movieRentingSystem.rent(0, 4239);
//            movieRentingSystem.drop(0, 4239);
//            System.out.println(movieRentingSystem.search(9346));
//            movieRentingSystem.report();
//            movieRentingSystem.rent(6, 9322);
//            System.out.println(movieRentingSystem.search(8698));
//        }

        {
            MovieRentingSystem MovieRentingSystem = new MovieRentingSystem(4, new int[][]{{3, 3, 3}, {0, 4, 3}, {2, 3, 2}});
            MovieRentingSystem.search(3);
            MovieRentingSystem.rent(3, 3);
            MovieRentingSystem.search(4);
            MovieRentingSystem.rent(0,4);
            System.out.println(MovieRentingSystem.report());
        }
    }

}

