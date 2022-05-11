package p.s1396;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class UndergroundSystem {

    static class StationInfo {
        int total;
        int passenger;

        public StationInfo(int total, int passenger) {
            this.total = total;
            this.passenger = passenger;
        }

        public void addStationInfo(int costTime) {
            this.total += costTime;
            this.passenger++;
        }

    }

    public static int getIDOf(String from, String to) {
        return Objects.hash(from, to);
    }

    static class PassengerInfo {
        String fromStation;
        int fromTime;

        public PassengerInfo(String fromStation, int fromTime) {
            this.fromStation = fromStation;
            this.fromTime = fromTime;
        }
    }

    private final Map<Integer, StationInfo> stationInfoMap = new HashMap<>();
    private final Map<Integer, PassengerInfo> passengerInfoMap = new HashMap<>();

    public UndergroundSystem() {
    }

    public void checkIn(int id, String stationName, int t) {
        passengerInfoMap.put(id, new PassengerInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        PassengerInfo passengerInfo = passengerInfoMap.get(id);
        if (passengerInfo == null) {
            throw new AssertionError();
        }
        int statationId = getIDOf(passengerInfo.fromStation, stationName);
        if (stationInfoMap.containsKey(statationId)) {
            StationInfo stationInfo = stationInfoMap.get(statationId);
            stationInfo.addStationInfo(t - passengerInfo.fromTime);
        } else {
            StationInfo stationInfo = new StationInfo(t-passengerInfo.fromTime, 1);
            stationInfoMap.put(statationId, stationInfo);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        int stationId = getIDOf(startStation, endStation);
        StationInfo stationInfo = stationInfoMap.get(stationId);
        if (stationInfo == null) {
            throw new AssertionError();
        }
        return 1.0d * stationInfo.total / stationInfo.passenger;
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45 ,"Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -> "Waterloo" ，用时 15-3 = 12
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -> "Waterloo" ，用时 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -> "Cambridge" ，用时 22-8 = 14
        undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.00000 。只有一个 "Paradise" -> "Cambridge" 的行程，(14) / 1 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000 。有两个 "Leyton" -> "Waterloo" 的行程，(10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -> "Waterloo" ，用时 38-24 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 12.00000 。有三个 "Leyton" -> "Waterloo" 的行程，(10 + 12 + 14) / 3 = 12

    }

}
