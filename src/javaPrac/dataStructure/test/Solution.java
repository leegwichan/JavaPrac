package javaPrac.dataStructure.test;
import java.util.*;

public class Solution {
    int goalLen;
    String ans;

    public String barcode(int len) {
        this.goalLen = len;
        BarcodeNode firstNode = new BarcodeNode("1");

        findAns(firstNode);
        return ans;

    }
    // 현재 노드 조건 충족 확인 / 미충족시, false
    // 왼쪽 노드 false시, 오른쪽 노드 실행
    // 오른쪽 노드 fales시, false
    // (길이 + 조건 통과시 true, 멤버 변수에 저장)
    public boolean findAns(BarcodeNode node){
        if (!node.passCondition()) return false;

        if (node.info.length() == this.goalLen){
            this.ans = node.info;
            return true;
        }

        node.makeLowerClass();
        if(!findAns(node.left)){
            if(!findAns(node.right)){
                return false;
            }
        }
        return true;
    }

}

class BarcodeNode{
    String info;
    BarcodeNode left;
    BarcodeNode right;

    BarcodeNode(String info){
        this.info = info;
    }

    void makeLowerClass(){
        switch (info.charAt(info.length()-1)){
            case '1':
                this.left = new BarcodeNode(this.info + "2");
                this.right = new BarcodeNode(this.info + "3");
                break;
            case '2':
                this.left = new BarcodeNode(this.info + "1");
                this.right = new BarcodeNode(this.info + "3");
                break;
            case '3':
                this.left = new BarcodeNode(this.info + "1");
                this.right = new BarcodeNode(this.info + "2");
                break;
        }
    }

    boolean passCondition(){
        for(int i=1; i <= info.length() / 2; i++){
            if (info.substring(info.length()-2*i, info.length()-i).equals(info.substring(info.length()-i, info.length()))){
                return false;
            }
        }
        return true;
    }
}

//public class Solution {
//    public String barcode(int len) {
//        return aux("", len);
//    }
//
//    public boolean isValid(String str) {
//        // index 관리를 편하게 하기 위해 string reverse
//        StringBuffer sb = new StringBuffer(str);
//        String reverse = sb.reverse().toString();
//        // 인접한 두 개의 부분 수열이 동일한지 확인한다.
//        // 최대 절반 길이만큼만 두 개의 부분 수열이 가능하다.
//        int halfLen = (int)Math.floor((double) str.length() / 2);
//
//        for(int i = 1; i <= halfLen; i++) {
//            if(reverse.substring(0, i).equals(reverse.substring(i, i + i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public String aux(String str, int len) {
//        String chr = "123";
//        // 유효성을 통과해서 만든 길이 len의 str을 리턴한다.
//        if(str.length() == len) return str;
//        // 조건을 만족하는 가장 작은 수를 찾고 있으므로,
//        // 1, 2, 3 순서대로 검토한다.
//        // 실제 수(number) 비교는 필요없다.
//        for(int i = 0; i < chr.length(); i++) {
//            String currentStr = str + chr.charAt(i);
//            if(isValid(currentStr)) {
//                String founded = aux(currentStr, len);
//                if(founded != null) return founded;
//            }
//        }
//        // 현재 str에서 1, 2, 3을 이어붙여서 유효한 문자열을 만들 수 없는 경우
//        return null;
//    }
//}


class apply{
    public static void main(String[] args) {
        long beforeTime = System.currentTimeMillis();
        Solution solution = new Solution();
        System.out.println(solution.barcode(10000));


        long afterTime = System.currentTimeMillis();
        System.out.println(afterTime - beforeTime);
    }
}
