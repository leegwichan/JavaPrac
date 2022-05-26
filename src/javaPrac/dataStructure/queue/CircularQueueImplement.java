package javaPrac.dataStructure.queue;

public class CircularQueueImplement {
    public static void main(String[] args) {
        CirQueue<Integer> cirQueue = new CirQueue<>(3);

        System.out.println(cirQueue.offer(1));
        System.out.println(cirQueue.offer(2));
        System.out.println(cirQueue.offer(3));
        System.out.println(cirQueue.offer(4));
        System.out.println();

        System.out.println(cirQueue.poll());
        System.out.println(cirQueue.poll());
        System.out.println(cirQueue.poll());
        System.out.println(cirQueue.poll());
        System.out.println();

        cirQueue.offer(213);
        cirQueue.clear();
        System.out.println(cirQueue.poll());

    }
}

class CirQueue<T>{
    private int front = 0; // 맨 앞에 있는 데이터의 index를 가리킴
    private int rear = 0; // 데이터 추가시, 뒤쪽에 데이터가 들어갈 자리를 가리킴
    private T[] dataArray; // 데이터 저장 공간 (실제 사용할 공간보다 하나 많이 설정)
    private int length; // dataArray 길이 (매번 dataArray 호출하는거보다 생성할 때 저장하는게 나을거 같아서)

    // constructor
    CirQueue(int queueLength) {
        this.dataArray = (T[]) new Object[queueLength + 1]; // dataArray Queue 공간보다 하나 많이 설정
        this.length = queueLength + 1; // dataArray 길이
    }

    // enqueue, if queue is full, throw Exception
    public boolean add(T element) throws Exception {

        // Queue가 다 차있을 때 (dataArray 가 하나 비어있을 때) 에러 발생
        if(front - rear == 1 || front - rear == -1 * (length - 1)){
            Exception exception = new IllegalStateException();
            throw exception;
        }

        dataArray[rear] = element; // 데이터 추가
        rear = (rear + 1) % length; // rear 를 한칸 뒤로 미룸
        return true; // 데이터 추가 성공시 true 반환
    }
    // enqueue, if queue is full, return false
    public boolean offer(T element)  {

        // Queue가 다 차있을 때 (dataArray 가 하나 비어있을 때) false 반환
        if(front - rear == 1 || front - rear == -1 * (length - 1)){
            return false;
        }
        dataArray[rear] = element; // 데이터 추가
        rear = (rear + 1) % length; // rear 를 한칸 뒤로 미룸
        return true; // 데이터 추가 성공시 true 반환
    }

    // dequeue and return element
    public T poll() {

        // dataArray가 비어있을 때, null 반환
        if (front == rear) return null;

        T positionData = dataArray[front]; // 맨 앞 데이터를 가져옴
        dataArray[front] = null; // 꺼낸 데이터 자리을 비움

        front = (front + 1) % length; // 맨 앞을 가리키는 변수를 하나 뒤로 미룸

        return positionData; // 꺼낸 데이터를 반환
    }

    // dequeue
    public void remove() {
        if (front == rear) return; // dataArray가 비어있을 때, null 반환

        dataArray[front] = null; // 맨 앞에 해당하는 데이터를 비움

        front = (front + 1) % length; // 맨 앞을 가리키는 변수를 하나 뒤로 미룸
    }

    // initialized
    public void clear() {
        // arrayList 초기화
        for(int i=0; i < dataArray.length; i++){
            dataArray[i] = null;
        }
        front = 0; // 맨 앞 index를 가리키는 변수 초기화
        rear = 0; // 데이터 추가할 index를 가리키는 변수 초기화
    }
}

