package javaPrac.mathProblem.permutation;

import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        PermutationCase permutationCase = new PermutationCase();
        Prime prime = new Prime();

        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);
        numList.add(5);
        numList.add(8);

        List<Integer> caseList = permutationCase.permutationList(numList);
        long count = 0;
        for (Integer element : caseList){
            if(prime.findPrime(element)) count++;
        }

        System.out.println(count);
        System.out.println(permutationCase.nPrlist(numList,4));
    }
}

class Prime{
    // Map<Integer, Boolean> data = new HashMap<>();
    boolean findPrime(int num){
       for (int i=2; i*i<=num; i++){
           if(num % 2 == 0) return false;
       }
       return true;
    }
}

class PermutationCase{
    List<Integer> permutationList(List<Integer> numList) {
        List<Integer> ansList = new ArrayList<>();
        for (int i=1; i<= numList.size(); i++){
            ansList.addAll(nPrlist(numList,i));
        }
        return ansList;
    }

    List<Integer> nPrlist(List<Integer> numList, int r){
        if (r == 1) return numList;


        List<Integer> ansList = new ArrayList<>();

        for(int i=0; i< numList.size(); i++){
            // head tail 설정
            List<Integer> newList = copyList(numList);
            Integer head = newList.get(i); // 하나 선택 & 모든 케이스
            newList.remove(i);
            List<Integer> tail = nPrlist(newList,r-1); // 뒤에꺼는 리스트로 가져옴

            // for문으로 하나씩 더해서 리스트에 넣음
            for (Integer tailElement : tail){
                ansList.add((int) (head*Math.pow(10,r-1) + tailElement));
            }

        }

        // 만든 리스트 리턴
        return ansList;
    }

    List<Integer> copyList(List<Integer> list){
        List<Integer> newList = new ArrayList<>();
        for(Integer num : list){
           newList.add(num);
        }
        return newList;
    }
}