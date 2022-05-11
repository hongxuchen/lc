package p.s1912;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class MovieRentingSystem1 {
    static class Movie implements Comparable<Movie> {
        int movieId;
        int shopId;
        int price;

        public Movie(int movieId,int shopId,int price) {
            this.movieId = movieId;
            this.shopId = shopId;
            this.price = price;
        }

        @Override
        public int compareTo(Movie o) {
            int ret = this.price - o.price;//价格 升序排序
            if (ret != 0) {
                return ret;
            }
            ret = this.shopId - o.shopId;
            if (ret != 0) {
                return ret;
            }
            return this.movieId - o.movieId;
        }
    }

    static class Shop {
        Set<Movie> unRentedSet = new HashSet<>();
        Map<Integer, Movie> movieMapping = new HashMap<>(); // 在商店中建立id与Movie的映射关系，以方便从set中取出Movie对象。
    }

    Set<Movie> rentedMovies = new TreeSet<>();
    Map<Integer, TreeSet<Movie>> movieMap = new HashMap<>();//排序
    Map<Integer, Shop> shopMap = new HashMap<>();

    public MovieRentingSystem1(int shopNum, int[][] entries) {
        for (int i = 0; i < shopNum; i++) {
            shopMap.put(i, new Shop());
        }
        for (int[] entry : entries) {
            final int shopId = entry[0];
            final int movieId = entry[1];
            final int price = entry[2];
            Shop shop = shopMap.get(shopId);
            TreeSet<Movie> set = movieMap.getOrDefault(movieId, new TreeSet<>());//排序
            Movie movie = new Movie(movieId, shopId, price);
            set.add(movie);
            movieMap.put(movieId, set);
            shop.unRentedSet.add(movie);
            shop.movieMapping.put(movieId, movie);
        }
    }

    //找到拥有指定电影且 未借出 的商店中 最便宜的 5 个 。
    public List<Integer> search(int movieId) {
        if(!movieMap.containsKey(movieId)){
            return new LinkedList<>();
        }
        Set<Movie> movies = movieMap.get(movieId);
        List<Integer> retList = new ArrayList<>();
        int num = 0;
        for (Movie movie : movies) {
            if (!rentedMovies.contains(movie)) {
                retList.add(movie.shopId);
            }
            if (rentedMovies.size() == 5) {
                break;
            }
        }
        System.out.println(retList);
        return retList;
    }

    //从指定商店借出指定电影，题目保证指定电影在指定商店 未借出 。
    public synchronized void rent(int shopId, int movieId) {
        Movie movie = shopMap.get(shopId).movieMapping.get(movieId);
        shopMap.get(shopId).unRentedSet.remove(movie);
        rentedMovies.add(movie);

    }

    //在指定商店返还 之前已借出 的指定电影。
    public void drop(int shopId, int movieId) {
        Movie movie = shopMap.get(shopId).movieMapping.get(movieId);
        rentedMovies.remove(movie);
        shopMap.get(shopId).unRentedSet.add(movie);
    }

    //返回 最便宜的 5 部已借出电影（可能有重复的电影 ID）
    public List<List<Integer>> report() {
        List<List<Integer>> retList = new LinkedList<>();
        for (Movie movie : rentedMovies) {
            retList.add(Arrays.asList(movie.shopId, movie.movieId));
            if (retList.size() == 5) {
                break;
            }
        }
        System.out.println(retList);
        return retList;
    }

    public static void main(String[] args) {
        MovieRentingSystem1 MovieRentingSystem1 = new MovieRentingSystem1(70,new int[][]{{0,8780,2738},{20,8214,6582},{64,3470,334},{56,5093,3915},{8,3501,8380},{62,3888,3526},{37,9669,7305},{12,5267,9439},{23,5268,6714},{65,41,6665},{24,9625,9801},{40,6331,1088},{12,6849,9647},{48,9725,9210},{0,4316,5003},{68,9660,8204},{48,6951,8007},{9,3470,4576},{50,2854,4721},{0,7253,9719},{0,3415,2467},{3,5093,6765},{7,4316,2918},{64,1061,6021},{46,5043,4438},{24,8993,4256},{60,4598,500},{0,7907,8894},{44,7367,7173},{69,4316,1200},{53,7798,8978},{10,6898,9653},{59,8313,3671},{4,1385,358},{42,7367,9860},{17,8912,5660},{25,3031,9804},{21,8921,4800},{16,9109,9569},{37,4316,1210},{44,7533,9893},{66,4598,7577},{22,5452,636},{25,6849,8934},{10,3630,5028},{56,5168,452},{2,9725,5511},{48,4548,6777},{39,5768,3549},{58,2058,4970},{41,3415,3889},{48,2627,2499},{62,7907,9564},{40,7798,8432},{5,7393,1237},{20,2627,3097},{33,6898,521},{48,9625,4180},{51,301,9896},{61,7533,5511},{28,6951,8716},{32,2442,2814},{59,3188,1955},{25,2058,2197},{59,1756,3278},{24,9669,1754},{48,833,7483},{58,41,6847},{55,2627,1230},{32,4423,3264},{1,8313,8915},{47,7367,7240},{11,6723,1963},{51,9894,3776},{18,8313,8420},{55,1061,1465},{45,1207,7340},{50,1207,733},{27,8519,9968},{28,8204,4983},{43,3888,9306},{37,788,3089},{10,5267,9421},{25,1129,4091},{63,2570,4336},{26,8537,8000},{2,9691,32},{53,7043,2395},{58,7043,9909},{18,9725,1997},{30,8893,4015},{35,8214,7117},{0,9258,3104},{11,8519,8041},{23,9052,4134},{20,5403,9987}});
        MovieRentingSystem1.search(9669);
        MovieRentingSystem1.rent(18,8313);
        MovieRentingSystem1.report();
        MovieRentingSystem1.rent(45,1207);
        MovieRentingSystem1.drop(45,1207);
        MovieRentingSystem1.report();
        MovieRentingSystem1.rent(41,3415);
        MovieRentingSystem1.report();
        MovieRentingSystem1.report();
        MovieRentingSystem1.rent(0,7253);
        MovieRentingSystem1.rent(46,5043);
        MovieRentingSystem1.rent(24,9669);
        MovieRentingSystem1.drop(46,5043);
        MovieRentingSystem1.rent(45,1207);
        MovieRentingSystem1.search(2423);
        MovieRentingSystem1.search(7798);
        MovieRentingSystem1.report();
        MovieRentingSystem1.drop(41,3415);
        MovieRentingSystem1.search(7140);
        MovieRentingSystem1.search(4316);
    }
}