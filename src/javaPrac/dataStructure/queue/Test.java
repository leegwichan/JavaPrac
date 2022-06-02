package javaPrac.dataStructure.queue;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.ocean(100, new int[]{10,20,50,55}));

        int result = solution.connectedVertices(new int[][]{
                {0, 1},
                {2, 3},
                {4, 5},
        });
        System.out.println(result);

    }
}
class Solution {
    Map<Integer, Map<Integer, Long>> checkPoint = new HashMap<>();

    public long ocean1(int target, int[] type) {
        int[] newType = new int[type.length];
        for (int i=0; i<type.length; i++){
            newType[i] = type[type.length-1-i];
        }

        for (int i=2; i<=type.length; i++){
            checkPoint.put(i,new HashMap<Integer, Long>());
        }

        return findAns(target, newType);
    }

    public long ocean(int target, int[] type) {
        Arrays.sort(type);
        long[][] dp = new long[type.length][target+1];

        dp[0][0] = 1;
        for(int j=0; j<type.length; j++){
            for(int i=0; i<target+1; i++){

                if(type[j] <= i && j != 0){
                    dp[j][i] = dp[j-1][i] + dp[j][i-type[j]];
                }
                else if(type[j] <= i){
                    dp[j][i] = dp[j][i-type[j]];
                }
                else if(j != 0){
                    dp[j][i] = dp[j-1][i];
                }
                System.out.println(j + " " + i + " " + dp[j][i]);
            }
        }

        return dp[type.length-1][target];
    }

    public long findAns(int target, int[] type){
        // 리스트 한개일 때, 나누어 떨어지면 1 반환
        // 안나누어 떨어지면 0 반환
        if (type.length == 1){
            return target % type[0] == 0 ? 1L : 0L;
        }
        // 체크포인트 확인
        if(checkPoint.get(type.length).containsKey(target)){
            return checkPoint.get(type.length).get(target);
        }

        int cases = target / type[0];
        int unit = type[0];
        long count = 0;

        // 제일 큰 금액 을 0,1 ... n 개 사용했을 때
        for (int i=0; i <= cases; i++){
            // 제일 큰 금액 리스트에서 제거한 배열, 금액 차감한 target으로 재귀
            int[] newType = Arrays.copyOfRange(type, 1, type.length);
            count += findAns(target - unit*i, newType);
        }

        // checkPoint 저장
        checkPoint.get(type.length).put(target,count);
        System.out.println(type.length);
        System.out.println(checkPoint.get(type.length).toString());


        // 총갯수 반환
        return count;
    }

    public String[] removeExtremes(String[] arr) {
        // TODO:
        if(arr.length == 0) return null;
        String shortestString = arr[0];
        String longestString = arr[0];

        for(int i=1; i<arr.length; i++){
            if(shortestString.length() >= arr[i].length()){
                shortestString = arr[i];
            }
            if(longestString.length() <= arr[i].length()){
                longestString = arr[i];
            }
        }

        String finalShortestString = shortestString;
        String finalLongestString = longestString;
        String[] resultArray = Arrays.stream(arr)
                .filter(s -> !s.equals(finalShortestString))
                .filter(s -> !s.equals(finalLongestString))
                .toArray(String[]::new);

//        String[] resultArray = Arrays.stream(arr)
//                .filter(s -> !s.equals(shortestString))
//                .filter(s -> !s.equals(longestString))
//                .toArray(String[]::new); // effective final 문제

        return resultArray;

    }

    public String[] removeExtremes2(String[] arr) {
        // TODO:
        if(arr.length == 0) return null;
        int shortestString = 0;
        int longestString = 0;

        for(int i=1; i<arr.length; i++){
            if(arr[shortestString].length() >= arr[i].length()){
                shortestString = i;
            }
            if(arr[longestString].length() <= arr[i].length()){
                longestString = i;
            }
        }

//        List<String> outList = Arrays.asList(arr); // Arrays.asList 는 수정 불가
        List<String> outList = new ArrayList<>();
        for(String element : arr){
            outList.add(element);
        }
        outList.remove(shortestString);
        if(shortestString > longestString){
            outList.remove(longestString);
        } else{
            outList.remove(longestString-1);
        }

        return outList.toArray(new String[outList.size()]);

    }

    public int connectedVertices(int[][] edges) {
        // TODO:
        int[][] adjaMatrix = createMatrix(edges);
        if (adjaMatrix.length == 1) return 1;

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> pointSet = new HashSet<>();
        for(int i=0; i<adjaMatrix.length; i++){
            pointSet.add(i);
        }

        while (pointSet.size() != 0){
            Integer firstpoint = (Integer) pointSet.toArray()[0];
            pointSet.remove((Integer) firstpoint);
            queue.add(firstpoint);

            while(!queue.isEmpty()){
                Integer aPoint = queue.poll();
                for (int i=0; i < adjaMatrix.length; i++){
                    if (adjaMatrix[aPoint][i] == 1 && pointSet.contains(i)){
                        pointSet.remove((Integer) firstpoint);
                        queue.add(i);
                    }
                }
            }

            count++;
        }

        return count;
    }

    public int[][] createMatrix(int[][] edges) {
        // TODO:
        Set<Integer> elementSet = new HashSet<>();
        Map<Integer,Integer> elementIndexMap = new HashMap<>();

        for (int i=0; i<edges.length; i++){
            for (int j=0; j<edges[0].length; j++){
                elementSet.add(edges[i][j]);
            }
        }

        int index=0;
        for(Integer element : elementSet){
            elementIndexMap.put(element,index);
            index++;
        }

        int[][] matrix = new int[elementSet.size()][elementSet.size()];
        for (int i=0; i<edges.length; i++){
            int to = elementIndexMap.get(edges[i][1]);
            int from = elementIndexMap.get(edges[i][0]);

            matrix[from][to] = 1;
            matrix[to][from] = 1;
        }

        return matrix;
    }
}
