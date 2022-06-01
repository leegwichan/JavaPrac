package javaPrac.dataStructure.queue;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.ocean(100, new int[]{10,20,50,55}));

    }
}
class Solution {
    Map<Integer, Map<Integer, Long>> checkPoint = new HashMap<>();

    public long ocean(int target, int[] type) {
        Arrays.sort(type);

        for (int i=2; i<=type.length; i++){
            checkPoint.put(i,new HashMap<Integer, Long>());
        }

        return findAns(target, type);
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

        // 총갯수 반환
        return count;
    }
}
