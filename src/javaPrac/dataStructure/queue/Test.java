package javaPrac.dataStructure.queue;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        boolean result2 = test.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                4,
                0
        );
        System.out.println(result2);
    }

    boolean getDirections(int[][] matrix, int from, int to) {
        // 출발지에 있는 줄을 본다.
        // 값이 1인 인덱스를 찾는다.
        // 다시 현재 지점에 있는 줄을 본다.
        // 값이 1인 인덱스를 찾는다.
        //

        // true : to에 도착한다. 해당 줄에서 row[to] == 1
        // false
        // 이미 사용한 길은 0으로 바꿔서 다시 사용하지 못하게 한다.
        // 전부 0으로 이루어져 있으면 false

        if (matrix[from][to] == 1) return true;
        if (compare0(matrix[from])) return false;

        for (int i=0; i<matrix.length; i++){
            if(matrix[from][i] == 1){
                int[][] newMatrix = matrix;
                newMatrix[from] = new int[matrix.length];
                if (getDirections(newMatrix, i, to) == true)
                    return true;
            }
        }
        return false;
    }

    public boolean compare0(int[] arr){
        for (int i=0; i<arr.length; i++){
            if (arr[i] != 0){
                return false;
            }
        }
        return true;
    }
}
