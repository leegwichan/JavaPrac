package javaPrac.mathProblem.combination;

import java.util.*;
public class CombinationExample {
    public static void main(String[] args) {
        Combination combination = new Combination();
        String sample = "123456789";
        List<String> answers = combination.combinationCase(sample,7);
        for(int i=0; i<answers.size(); i++) {
            System.out.println(answers.get(i));
        }
    }
}

class Combination{
    public List<String> combinationCase(String str, int r){
        List<String> ans = new ArrayList<>();
        if(r == 1){
           char[] chars = str.toCharArray();
           for (char letter : chars){
               ans.add(String.valueOf(letter));
           }
           return ans;
        }

        char[] charArray = str.toCharArray();

        for(int i=0; i<=charArray.length-r; i++){
            char head = charArray[i];
            char[] remainder = Arrays.copyOfRange(charArray,i+1,charArray.length);

            List<String> tail = combinationCase(charToString(remainder), r-1);
            // List<String> tail = combinationCase(remainder.toString(), r-1);

            for(String element : tail){
                ans.add(head + element);
            }
        }

       return ans;
    }

    private String charToString(char[] charArray){
        StringBuilder sb = new StringBuilder();
        for(char character : charArray){
            sb.append(character+"");
        }

        return sb.toString();
    }
}