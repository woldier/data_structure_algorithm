package com.woldier.datastruacture.ch2.d01_linked_list;

import java.util.Iterator;

/**
 * @author woldier
 * @version 1.0
 * @description 双向循环链表
 * @date 2023/6/14 15:55
 **/
public class DoublyLinkedListSentinel implements Iterable<Integer>{

    private Node head;

    private Node tail;

    /**
     * description 初始化头指针与尾指针
     *
     * @return
     * @author: woldier
     * @date: 2023/6/14 16:00
     */
    public DoublyLinkedListSentinel() {
        head = new Node(null, Integer.MIN_VALUE, null);
        tail = new Node(null, Integer.MIN_VALUE, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {
                return p!=tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p=p.next;
                return value;
            }
        };
    }

    /**
     * description 私有内部类,链表结点,本类加了static修饰
     * <p></p>
     * 没有加static,因此是与类的对象绑定的,依附于类的对象存在  (应用场景,如JUC ReentrantLock 的同步器中使用的Node,Condition,就是非静态的,可以在这些内部类的方法中操作外部类)
     * <p></p>
     * 加了static,内部类可以独立于类的对象存在,与类对象不存在绑定关系
     * <p></p>
     * 链表结构图(带哨兵链表)
     * <pre>
     * index   -1              0              1
     *      +------+  next  +-----+  next  +-----+
     *      | 哨兵  | <----> |     | >----> | 哨兵 |
     *      +------+  prev  +-----+  prev  +-----+
     *         ⬆                             ⬆
     *        head                          tail
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/14 16:00
     */

    private static class Node {
        /**
         * 前驱
         */
        private Node prev;
        /**
         * 结点值
         */
        private int value;
        /**
         * 后继
         */
        private Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * description 根据索引查找
     *
     * @param index 索引值 有效范围是[-1,lentgh] 当链表为空时可用索引为[-1,0]
     * @return com.woldier.datastruacture.ch2.d01_linked_list.DoublyLinkedListSentinel.Node  返回找到的节点,当索引值不合法时会返回null
     * @author: woldier
     * @date: 2023/6/14 16:06
     */
    private Node findNode(int index) {

        Node p = head;
        int i = -1;
        while (i != index && p.next != null) {
            i++;
            p = p.next;
        }
        return p;

    }

    /**
     * description 头插法
     *
     * @param value
     * @return void
     * @author: woldier
     * @date: 2023/6/14 16:23
     */
    public void addFirst(int value) {
        Node node = findNode(-1);  //得到一个指向原哨兵的指针
        head.value = value;  //将原来的哨兵节点赋值,使其退位为普通节点
        head = new Node(null, Integer.MIN_VALUE, head); //头指针指向新的哨兵节点
        node.prev = head; // 插入节点的前驱指向哨兵
    }
    /**
    *
    * description 添加到队尾
    *
    *
    * @return void
    * @author: woldier
    * @date: 2023/6/15 17:40
    */
    public void addLast( int value){
        Node node = tail; //保存原哨兵指针
        tail.value = value;
        tail = new Node(tail,value,null);
        node.next = tail;
    }

    /**
     * description 插入到指定索引
     *
     * @param index
     * @param value
     * @return void
     * @author: woldier
     * @date: 2023/6/15 17:30
     */
    public void add(int index, int value) {
        Node node = findNode(index-1);  //得到一个指向插入位置前一个节点的指针
        if(node==null||node == tail)
            throw getIllegalArgumentException(index);
        Node newNode = new Node(node, value, node.next);
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
    }

    private static IllegalArgumentException getIllegalArgumentException(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }


    public void remove(int index){
        Node node = findNode(index-1);
        if(node==null||node.next == tail)
            throw getIllegalArgumentException(index);

        node.next.next.prev = node;
        node.next = node.next.next;
    }

}
