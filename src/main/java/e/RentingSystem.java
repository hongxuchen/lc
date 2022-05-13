package e;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentingSystem {

    static class House {
        int id;
        int area;
        int price;
        int rooms;
        int[] address;

        public House(int id, int area, int price, int rooms, int[] address) {
            this.id = id;
            this.area = area;
            this.price = price;
            this.rooms = rooms;
            this.address = address;
        }

        public void update(int area, int price, int rooms, int[] address) {
            this.area = area;
            this.price = price;
            this.rooms = rooms;
            this.address = address;
        }

    }

    private final Map<Integer, House> houses = new HashMap<>();

    public boolean addRoom(int id, int area, int price, int rooms, int[] address) {
        if (!houses.containsKey(id)) {
            House house = new House(id, area, price, rooms, address);
            houses.put(id, house);
            return true;
        } else {
            House house = houses.get(id);
            house.update(area, price, rooms, address);
            return false;
        }
    }

    public boolean deleteRoom(int id) {
        if (houses.containsKey(id)) {
            houses.remove(id);
            return true;
        }
        return false;
    }

    public List<Integer> queryRoom(int area, int price, int rooms, int[] address, int[][] orderBy) {
        Comparator<House> comparator = new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                for (int[] orderEntry : orderBy) {
                    switch (orderEntry[0]) {
                        case 1: {
                            int areaDiff = o1.area - o2.area;
                            if (areaDiff != 0) {
                                return areaDiff * orderEntry[1];
                            }
                            break;
                        }
                        case 2: {
                            int priceDiff = o1.price - o2.price;
                            if (priceDiff != 0) {
                                return priceDiff * orderEntry[1];
                            }
                            break;
                        }
                        case 3: {
                            int distDiff = (Math.abs(o1.address[0] - address[0]) + Math.abs(o1.address[1] - address[1])) - (Math.abs(o2.address[0] - address[0]) + Math.abs(o2.address[1] - address[1]));
                            if (distDiff != 0) {
                                return distDiff * orderEntry[1];
                            }
                            break;
                        }
                    }
                }
                return o1.id - o2.id;
            }
        };
        return houses.values().stream().filter(h -> h.area >= area && h.price <= price && h.rooms == rooms).sorted(comparator).map(h -> h.id).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        RentingSystem obj = new RentingSystem();
        System.out.println(obj.deleteRoom(10)); // 返回 false
        System.out.println(obj.addRoom(3, 24, 200, 2, new int[]{0, 1})); // 返回 true
        System.out.println(obj.addRoom(3, 24, 500, 2, new int[]{0, 1})); // 返回 false
        System.out.println(obj.addRoom(3, 27, 500, 4, new int[]{1, 1})); // 返回 false
        System.out.println(obj.addRoom(1, 27, 500, 4, new int[]{20, 43})); // 返回 true
        System.out.println(obj.addRoom(6, 35, 227, 4, new int[]{2, 4})); // 返回 true
        System.out.println(obj.addRoom(9, 20, 3540, 4, new int[]{4, 321})); // 返回 true
        List<Integer> res = obj.queryRoom(25, 900, 4, new int[]{10, 1}, new int[][]{{1, 1}, {2, -1}, {3, 1}});
        System.out.println(res);
    }

}
