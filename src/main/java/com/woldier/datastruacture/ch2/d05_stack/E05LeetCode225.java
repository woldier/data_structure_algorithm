package com.woldier.datastruacture.ch2.d05_stack;

import com.woldier.datastruacture.ch2.d04_queue.ArrayQueue;
import com.woldier.datastruacture.ch2.d04_queue.ArrayQueue2;

/**
 * description 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 * <p>
 * 注意：
 * <p>
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 * <p>
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 *  
 * <p>
 * 进阶：你能否仅用一个队列来实现栈。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/28 下午6:54
 */
public class E05LeetCode225 {
    /**
     * description 本实现直接采用一个队列来实现
     * <p>
     * 算法思想是,当队列元素为0或者1时入队不用额外操作,当超过1时入队后需要把前面的元素都进行出队在入队,
     * <p>
     * 出队则不用额外操作
     * <pre>
     *
     *     {@code
     *
     *
     *     }
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/28 下午7:00
     */
    ArrayQueue2<Integer> queue;

    public E05LeetCode225() {
        queue = new ArrayQueue2<>(100);
    }

    public void push(int x) {
        queue.offer(x);
        int length = queue.getLength();
        if (length > 1) { //如果队列的元素个数为0或者1 那么直接放入不需要额外的操作
            for (int i = 0; i < length - 1; i++) {
                queue.offer(queue.poll());
            }
        }

    }

    public int pop() {
        if(!empty()) return queue.poll();
        return 0;
    }

    public int top() {
        if(!empty()) return queue.peek();
        return 0;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
