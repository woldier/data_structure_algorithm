package com.woldier.datastruacture.ch2.d10_tree.rdt;

/**
 * description 红黑树
 * <p>
 * 红黑树也是一种平衡二叉树,与AVL比较,插入和删除时的旋转次数更少
 * <p>
 * 红黑树 特性
 * <p>
 * 1.所有节点都有两种颜色:红,黑
 * <p>
 * 2.所有为null的节点视为黑色(即叶子节点的左右孩子视为黑色)
 * <p>
 * 3.红色节点不能相邻
 * <p>
 * 4.根节点是黑色
 * <p>
 * 5.从根到任意一个叶子节点,路径中的黑色节点数一样(黑色完美平衡)
 *
 * @author: woldier
 * @date: 2023/7/15 上午9:34
 */
public class ReadBlackTree {

    private Node root;

    private enum Color {
        READ, BLACK;
    }

    private static class Node {
        int key;
        int val;
        Node left;
        Node right;
        Node parent;
        Color color = Color.READ; //刚刚创建的对象赋值红色

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node(int key, int val, Node left, Node right, Node parent, Color color) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent; //父亲节点
        }

        /**
         * description 判断是不是左孩子
         *
         * @return 如果是父亲的左孩子返回true, 否则返回false, 不存在父亲页返回false
         * @author: woldier
         * @date: 2023/7/15 上午9:44
         */
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        /**
         * description 找到某个节点的叔叔
         * <pre>
         *
         *    {@code
         *       8
         *    6    9
         *  5   7
         *    }
         *    如当前节点为5,爷爷为8,那么他的叔叔为9,
         *    如果当前节点为6 由于他不存在爷爷,因此也不存在叔叔
         *    如果当前节点为8,由于不存在爸爸,那么也不存在叔叔
         * </pre>
         *
         * @return 如果存在, 返回叔叔节点, 否则返回false
         * @author: woldier
         * @date: 2023/7/15 上午9:47
         */
        public Node uncle() {
            if (this.parent == null || this.parent.parent == null) return null;
            if (this.parent.isLeftChild()) //判断当前节点的父亲是爷爷的左孩子还是右孩子
                return this.parent.parent.right; //父亲是爷爷的左孩子,那么叔叔就是爷爷的右孩子
            else
                return this.parent.parent.left;//父亲是爷爷的右孩子,那么叔叔就是爷爷的左孩子
        }

        /**
         * description 返回节点的兄弟
         *
         * @return 如果存在兄弟返回;如果不存在,或者是不存在父亲返回null
         * @author: woldier
         * @date: 2023/7/15 上午9:53
         */
        public Node sibling() {
            if (this.parent == null) return null; //判断父亲是否存在
            if (this.isLeftChild()) //判断是父亲的左孩子还是右孩子
                return this.parent.right; //是父亲的左孩子,那么兄弟就是父亲的右孩子
            else
                return this.parent.left; //是父亲的右孩子,那么兄弟就是父亲的左孩子
        }
    }


    /**
     * description 判断是否是read节点(PS 为什么这个方法不放在Node里面去实现呢?这是因为有可能Node指向null,根本没办法调用isRead)
     *
     * @param node 要判断的节点
     * @return 一个节点要为read首先不能是null节点(因为null节点默认为黑色)
     * @author: woldier
     * @date: 2023/7/15 上午9:58
     */
    private boolean isRead(Node node) {
        return node != null && node.color == Color.READ;
    }

    /**
     * description 判断是否是black节点(PS 为什么这个方法不放在Node里面去实现呢?这是因为有可能Node指向null,根本没办法调用isRead)
     *
     * @param node 要判断的节点
     * @return 如果节点指向null, 那么默认就是black, 或者是其颜色为black 返回true
     * @author: woldier
     * @date: 2023/7/15 上午9:58
     */
    private boolean isBlack(Node node) {
        return node == null || node.color == Color.BLACK;
    }

    /**
     * description L旋转 向右转动
     * <pre>
     *     {@code
     *  root → 5
     *     //    \\
     *     3      8
     *   // \\  //  \\
     *   2   4  6    10
     *  //      \\ // \\
     *  1        7 9  11
     *
     *  如果我们是移动5
     *  root → 3
     *     //    \\
     *     2      5
     *   // \\  //  \\
     *   1   4  4    8
     *  //         //  \\
     *  1          6   10
     *             \\ // \\
     *              7 9   11
     *
     *  如果移动的是子节点,可以发现移动后还要重新设置5指向2
     *
     *  root → 5
     *     //    \\
     *     2      8
     *   // \\  //  \\
     *   1   3  6    10
     *       \\ \\ // \\
     *        4  7 9  11
     *
     *
     *
     *
     *     }
     *
     * </pre>
     *
     * @param p 旋转前的根节点
     * @author: woldier
     * @date: 2023/7/15 上午10:54
     */
    private void rightRotate(Node p) {
        Node parent = p.parent;// 得到指向原来p节点的父亲,便于后续操作,需要注意的是当p为根的时候,那么parent为null
        Node newRoot = p.left;
        Node kid = newRoot.right; //得到newRoot节点的右孩子,需要注意的是,右孩子可能不存在

        p.left = kid; //将p的左孩子设置为kid
        if (kid != null) kid.parent = p; //如果kid不是null,那么还需要重新设置kid的parent指针指向p

        newRoot.right = p; //将newRoot作为子树的根,p为它的右孩子
        p.parent = newRoot; //重新设置p的父亲
        //重新设置newRoot的父亲
        switchNodeParent(p, parent, newRoot);
    }

    /**
     * description 交换两节点的父亲,并且将父亲的孩子引用更新
     * <p>
     * 此方法是rightRotate,与leftRotate方法专用的,请勿随便调用
     *
     * @param p 父亲旧的孩子
     * @param parent 父亲
     * @param newRoot 父亲新的孩子
     * @return
     * @author: woldier
     * @date: 2023/7/15 上午11:26
     */
    private void switchNodeParent(Node p, Node parent, Node newRoot) {
        //重新设置newRoot的父亲
        newRoot.parent = parent;
        if (parent == null) //如果parent为null 说明原来的p是树根,那么需要重新设置树根指针指向newRoot而不是p
            root = newRoot;
        else { //如果此时parent不为null 我们除了设置newRoot指向新的parent 还需要设置parent的儿子指针指向newRoot
            if (parent.left == p)
                parent.left = newRoot;
            else
                parent.right = newRoot;
        }
    }
    /**
     * description R旋转 向左转动
     * <pre>
     *     {@code
     *  root → 5
     *     //    \\
     *     3      8
     *   // \\  //  \\
     *   2   4  6    10
     *  //      \\ // \\
     *  1        7 9  11
     *
     *  如果我们是移动5
     *  root → 8
     *     //    \\
     *     5      10
     *   // \\  //  \\
     *   3   6  9    11
     *  //\\ \\
     *  2  4  7
     * //
     * 1
     *  如果我们移动的是8,此时需要重新设置5的孩子指针指向10
     *  root → 5
     *     //    \\
     *     3      10
     *   // \\  //  \\
     *   2   4  8    11
     *  //    // \\
     *  1     6   9
     *         \\
     *          7
     *     }
     *
     * </pre>
     *
     * @param p 旋转前的根节点
     * @author: woldier
     * @date: 2023/7/15 上午10:54
     */
    private void leftRotate(Node p) {
        Node parent = p.parent;// 得到指向原来p节点的父亲,便于后续操作,需要注意的是当p为根的时候,那么parent为null
        Node newRoot = p.right;
        Node kid = newRoot.left; //得到newRoot节点的左孩子,需要注意的是,左孩子可能不存在

        p.right = kid;//将p的右孩子设置为kid
        if (kid != null) kid.parent = p; //如果kid不是null,那么还需要重新设置kid的parent指针指向p

        newRoot.left = p; //将newRoot作为子树的根,p为它的右孩子
        p.parent = newRoot; //重新设置p的父亲

        //重新设置newRoot的父亲
        switchNodeParent(p, parent, newRoot);
    }

    public void put(int key,int val){
        doPut(key,val);
    }

    private void doPut(int key,int val){
        Node p = root,parent=null;
        while(p!=null){
            parent = p;
            if(key<p.val){ //指针指向左孩子
                p = p.left;
            } else if (key>p.val) { //指针指向右孩子
                p = p.right;
            }
            else {//找到相等的key
                p.val = val;
                return;//推出
            }
        }
        //能出来说明key不存在,我们则新建
        Node insertedNode = new Node(key, val);
        insertedNode.parent = parent;
        if(parent==null) {//说明没有进过while循环,这时的红黑树是null
            root = insertedNode;
        } else if (parent.key>key) { //判断应该是左子树还是右子树
            parent.left = insertedNode;
        }
        else {
            parent.right = insertedNode;
        }
        fixReadRead(insertedNode); //调整颜色
    }

    private void fixReadRead(Node node) {
        Node parent = node.parent; //节点的父亲
        Node uncle = node.uncle(); //节点的叔叔

        if(parent== null){//说明为根 红色变成黑色
            node.color = Color.BLACK;
        }
        else if(isBlack(parent)){//父亲的颜色为黑色,不需要调整

        } else if (isRead(uncle)) {//父亲的颜色为红色,且叔叔的颜色也为红色(需要注意的是,为红色的节点一定不会指向null,也就是说叔叔为红色,那么叔叔一定存在,并且存在爷爷)
            Node grandParent = parent.parent;
             parent.color = Color.BLACK; //父亲和叔叔都变成黑色
             uncle.color = Color.BLACK;
            grandParent.color = Color.READ; //把爷爷变成红色
            fixReadRead(grandParent);

        }
        else {//父亲为红色,叔叔为黑色,叔叔为黑色有两种情况叔叔真的是黑色,或者叔叔指向null,此时也是黑色
            //当然或许有疑问 如果祖父不存在呢?这是不可能的,因为根据红黑树特性根节点不能为红色,而现在父亲是红色那么他一定不是根节点
            Node grandParent = parent.parent;
            grandParent.color = Color.READ;
            //1. 如果父亲为左孩子,插入节点也是左孩子,此时就是LL
            if(parent.isLeftChild()&&node.isLeftChild()){
                parent.color = Color.BLACK;
                rightRotate(grandParent);

            }else if(parent.isLeftChild()){            //2.如果父亲是左孩子,插入节点是右孩子,此时就是LR
                node.color = Color.BLACK;
                leftRotate(parent); //变成LL
                rightRotate(grandParent);
            }
            //3.如果父亲是右孩子,插入节点也是右孩子,此时就是RR
            else if (!node.isLeftChild()){
                parent.color = Color.BLACK;
                leftRotate(grandParent);
            }
            //4.如果父亲是右孩子,插入节点是左还是,此时就是RL
            else {
                node.color = Color.BLACK;
                rightRotate(parent);
                leftRotate(grandParent);
            }
        }
    }

    /**
    *
    * description 查找节点
    *
    * @param key
    * @return 返回找到的节点
    * @author: woldier
    * @date: 2023/7/15 下午2:48
    */
    private Node find(int key){

        
    }

}
