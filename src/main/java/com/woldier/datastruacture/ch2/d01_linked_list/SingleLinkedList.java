package com.woldier.datastruacture.ch2.d01_linked_list;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author woldier
 * @version 1.0
 * @description 单向链表
 * @date 2023/6/13 19:52
 **/
public class SingleLinkedList implements Iterable<Integer> {

    /**
     * 头指针
     */
    private Node head;


    /***
     *
     * description 创建一个链表结点
     *
     * @param value
     * @return com.woldier.datastruacture.ch2.d01_linked_list.SingleLinkedList.Node
     * @author: woldier
     * @date: 2023/6/14 8:29
     */
    private Node newNode(int value) {
        return new Node(value, null);
    }

    /***
     * description 实现Iterable方法
     *
     * @return java.util.Iterator<java.lang.Integer>
     * @author: woldier
     * @date: 2023/6/14 9:18
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Node p = head;

            /**
             * hashNext是从head开始的
             * @return
             */
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                Node temp = p;
                p = p.next;
                return temp.value;
            }
        };
    }


    /***
     * description 私有内部类,链表结点,本类加了static修饰
     * <p></p>
     * 没有加static,因此是与类的对象绑定的,依附于类的对象存在  (应用场景,如JUC ReentrantLock 的同步器中使用的Node,Condition,就是非静态的,可以在这些内部类的方法中操作外部类)
     * <p></p>
     * 加了static,内部类可以独立于类的对象存在,与类对象不存在绑定关系
     * <p></p>
     * 链表结构图
     * <pre>
     *      +------+  next +-----+       +-----+
     *      | node | ----> |     | ----> |     |
     *      +------+       +-----+       +-----+
     *         ⬆
     *        head
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/14 8:28
     */
    private static class Node {
        private int value;

        private Node next;

        /**
         * description 构造方法
         *
         * @param value node存储的值
         * @param next  指向的下一个Node节点
         * @return
         * @author: woldier
         * @date: 2023/6/13 19:53
         */
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }


    /***
     *
     * description 头部插入的算法步骤如下
     * <p>1.创建新节点</p>
     * <p>2.检查head是否为null</p>
     * 2.1若为null说明链表为空直接让头指针指向新插入的节点
     * 2.2若不为null,说明链表不为空,应当将新节点的next指针指向当前head所指向的节点,再让head指向新插入的节点
     *
     *<pre>
     *     {@code
     *          Node newNode = newNode(value);  //创建一个新节点
     *          if(head==null){  //head为空
     *              head = newNode;
     *          }
     *          else{ // head 不为空
     *              newNode.next = head;
     *              head = newNode;
     *          }
     *     }
     *</pre>
     *
     * 上述代码可以进一步简化,于是就有了下面的实现
     * @param value  新插入的节点的值
     * @return void
     * @author: woldier
     * @date: 2023/6/14 8:39
     */
    public void addFirst(int value) {
        Node newNode = newNode(value);  //创建一个新节点
        newNode.next = head;  //将newNode链入链表
        head = newNode;  //重新设置头指针

    }


    /**
     * description 在链表尾端插入元素
     *
     * @param value 新插入节点的值
     * @return void
     * @author: woldier
     * @date: 2023/6/14 9:48
     */
    public void addLast(int value) {
        Node newNode = newNode(value), p = head;  //创建一个新节点
        if (p == null) {
            head = newNode;  //重新设置头指针
            return;
        }
        while (p.next != null) {
            p = p.next;
        }
        p.next = newNode;
    }

    /**
     * 根据索引获取节点
     *
     * @param index
     * @return
     */
    private Node findNode(int index) {
        int i = 0;
        Node p = head;
        while (p != null && i != index) {
            p = p.next;
            i++;
        }
        return p;
    }

    /***
     *
     * description 根据索引获取元素
     *
     * @param index
     * @return int
     * @author: woldier
     * @date: 2023/6/14 10:30
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null)
            throw getIllegalArgumentException(index);
        return node.value;
    }

    /**
     * description 索引不合法异常
     *
     * @param index
     * @return java.lang.IllegalArgumentException
     * @author: woldier
     * @date: 2023/6/14 10:50
     */
    private static IllegalArgumentException getIllegalArgumentException(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    /**
     * description 指定索引位置插入
     * 实现思想是找到插入索引位置的前一个节点pre,将newNode的next指针指向pre的next指针,然后将pre的next指针指向newNode
     * 需要考虑的边界问题是索引为0时,相当于头插法
     * 当索引超过当前节点个数+1时抛出异常
     *
     * @author: woldier
     * @date: 2023/6/14 10:51
     */
    public void insert(int index, int value) {
        if (index == 0) {  //判断index为0的情况,这时归于头插法
            addFirst(value);
            return;
        }
        Node pre;
        if ((pre = findNode(index - 1)) == null)
            throw getIllegalArgumentException(index);
        pre.next = new Node(value, pre.next);
    }


    /**
     * description 删除首节点
     * 算法思想时直接让头指针直线head.next
     * 不过考虑到GC 还应该将删除节点的next指针设置为null
     *
     * @return int 删除节点的值
     * @author: woldier
     * @date: 2023/6/14 11:22
     */
    public int removeFirst() {
        if (head == null)
            illegalIndex();

        Node p = head;  //记录旧的头节点
        head = p.next;  // 指向新的头节点
        p.next = null;  //help GC
        return p.value; //返回值
    }

    private static void illegalIndex() {
        throw new IllegalArgumentException("当前链表为空");
    }


    /***
    *
    * description 更根据索引返回节点
    *
    * @param index  索引值
    * @return int
    * @author: woldier
    * @date: 2023/6/14 11:43
    */
    public int remove(int index) {
        if (index == 0)
            return removeFirst();
        Node pre = findNode(index - 1), node; //当前节点的前驱
        if (pre == null || pre.next == null) {//待删除节点前驱为null,或者当前节点为null,说明索引不合法
            illegalIndex();
        }
        node = pre.next;
        pre.next = node.next;
        node.next = null;
        return node.value;
    }

    /***
     *
     * description 链表遍历
     *
     * @param consumer
     * @return void
     * @author: woldier
     * @date: 2023/6/14 9:15
     */
    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }


}
