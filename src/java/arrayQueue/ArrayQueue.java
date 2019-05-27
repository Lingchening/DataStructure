package java.arrayQueue;

public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity){
        items = new String[capacity];
        this.n = capacity;
    }

    public boolean enqueue(String e){
        if(tail == n){
            if(head == 0)
                return false;
            for (int i = head; i < tail; ++i){
                items[i-head] = items[i];
            }
            head = 0;
            tail -= head;
        }
        items[tail] = e;
        ++tail;
        return true;
    }

    public String dequeue(){
        if(head == tail)
            return null;
        String result = items[head];
        ++head;
        return result;
    }
}
