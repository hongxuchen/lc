package t.e2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

public class Main {

    private static boolean oneRound(
            List<DeptDemand> deptDemands,
            TreeSet<Candidate> allCandidates) {
        boolean more = false;
        for (DeptDemand deptDemand : deptDemands) {
            if (!deptDemand.recuritMore()) {
                continue;
            }
            for (Candidate candidate : allCandidates) {
                if (candidate.progmGrade >= deptDemand.progmThd
                        && candidate.techGrade >= deptDemand.techThd) {
                    deptDemand.recurit(candidate);
                    allCandidates.remove(candidate);
                    more = true;
                    break;
                }
            }
        }
        return more;
    }

    private static void lastAdd(List<DeptDemand> deptDemands, TreeSet<Candidate> allCandidates) {
        for (DeptDemand deptDemand : deptDemands) {
            if (!deptDemand.hasMatched()) {
                continue;
            }
            List<Candidate> candidates = deptDemand.recurited;
            if (candidates.size() == 0) {
                continue;
            }
            if (allCandidates.isEmpty()) {
                continue;
            }
            Candidate last = candidates.get(candidates.size() - 1);
            for (Candidate candidate : allCandidates) {
                if (candidate.progmGrade == last.progmGrade
                        && candidate.techGrade == last.techGrade) {
                    deptDemand.recurit(candidate);
                    allCandidates.remove(candidate);
                    break;
                }
            }
        }
    }

    // 待实现函数，在此函数中填入答题代码
    private static List<int[]> getRecruitmentResults(
            List<DeptDemand> deptDemands,
            List<Candidate> candidateAbilities) {
        TreeSet<Candidate> allCandidates = new TreeSet<>(candidateAbilities);

        while (true) {
            boolean more = oneRound(deptDemands, allCandidates);
            if (!more) {
                break;
            }
        }

        lastAdd(deptDemands, allCandidates);

        List<int[]> results = new ArrayList<>();
        for (DeptDemand deptDemand : deptDemands) {
            List<Candidate> candidates = deptDemand.recurited;
            int[] result = candidates.stream().mapToInt(c -> c.id).toArray();
            results.add(result);
        }
        return results;
    }

    static class Candidate implements Comparable<Candidate> {
        int id;
        int progmGrade; // 机试分数
        int techGrade;  // 技面分数

        Candidate(int id, int[] grades) {
            this.id = id;
            this.progmGrade = grades[0];
            this.techGrade = grades[1];
        }

        public String toString() {
            return String.format(Locale.ROOT, "{%d : %d %d}",
                    this.id, this.progmGrade, this.techGrade);
        }

        @Override
        public int compareTo(Candidate that) {
            if (this.techGrade != that.techGrade) {
                return that.techGrade - this.techGrade;
            }
            if (this.progmGrade != that.progmGrade) {
                return that.progmGrade - this.progmGrade;
            }
            return this.id - that.id;
        }
    }

    static class DeptDemand {
        int id = -1;
        int employNum = 0; // 招聘目标
        int progmThd = 0;  // 机考门槛值
        int techThd = 0;   // 技面门槛值
        boolean hasMatched = true;

        private final List<Candidate> recurited = new ArrayList<>();

        public void recurit(Candidate candidate) {
            recurited.add(candidate);
        }

        public boolean hasMatched() {
            return hasMatched;
        }

        public void setNoMatched() {
            hasMatched = false;
        }

        public boolean recuritMore() {
            return (employNum - recurited.size()) > 0;
        }

        DeptDemand(int id, int[] arr) {
            this.id = id;
            this.employNum = arr[0];
            this.progmThd = arr[1];
            this.techThd = arr[2];
        }

        public String toString() {
            return String.format(Locale.ROOT, "{%d : %d %d %d}",
                    this.id, this.employNum, this.progmThd, this.techThd);
        }
    }

    public static void main(String[] args) {
        List<DeptDemand> deptDemands = new ArrayList<>();
        List<Candidate> candidates = new ArrayList<>();
        {
            deptDemands.add(new DeptDemand(0, new int[]{2, 130, 120}));
            deptDemands.add(new DeptDemand(1, new int[]{1, 150, 150}));
            candidates.add(new Candidate(0, new int[]{150, 100}));
            candidates.add(new Candidate(1, new int[]{160, 190}));
            candidates.add(new Candidate(2, new int[]{150, 200}));
            candidates.add(new Candidate(3, new int[]{200, 190}));
            candidates.add(new Candidate(4, new int[]{160, 190}));
            candidates.add(new Candidate(5, new int[]{160, 190}));
        }

//        deptDemands.add(new DeptDemand(0, new int[] {1, 100, 100}));
//        deptDemands.add(new DeptDemand(1, new int[] {1, 100, 120}));
//        deptDemands.add(new DeptDemand(2, new int[] {1, 100, 150}));
//        candidates.add(new Candidate(0, new int[] {100, 200}));
//        candidates.add(new Candidate(1, new int[] {100, 200}));
//        candidates.add(new Candidate(2, new int[] {100, 200}));
//        candidates.add(new Candidate(3, new int[] {100, 200}));
//        candidates.add(new Candidate(4, new int[] {100, 200}));

//        {
//            deptDemands.add(new DeptDemand(0, new int[]{2, 100, 150}));
//            deptDemands.add(new DeptDemand(0, new int[]{1, 150, 100}));
//            candidates.add(new Candidate(0, new int[]{100, 120}));
//            candidates.add(new Candidate(1, new int[]{150, 100}));
//        }

        List<int[]> results = getRecruitmentResults(deptDemands, candidates);
        for (int[] result : results) {
            System.out.println(Arrays.toString(result));
        }

    }

}
