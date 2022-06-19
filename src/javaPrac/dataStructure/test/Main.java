package javaPrac.dataStructure.test;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int times = Integer.parseInt(br.readLine());

        for (int i = 1; i <= times; i++){
            st = new StringTokenizer(br.readLine());
            bw.write(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + "\n");
        }

        bw.flush();
        bw.close();

    }
}

//import java.util.*;
//import java.io.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int times = sc.nextInt();
//
//        int maxNum = Integer.MIN_VALUE;
//        int minNum = Integer.MAX_VALUE;
//        for (int i=0; i<times; i++){
//            int a = sc.nextInt();
//            if (minNum > a) minNum = a;
//            if (maxNum < a) maxNum = a;
//        }
//
//        System.out.println(minNum + " " + maxNum);
//    }
//}
