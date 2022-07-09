package Stack;

import Queue.Queue;

import java.util.Random;

public class Main {
    // 测试使用Queue运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 10000000000.0;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

    }
}
