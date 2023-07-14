package com.woldier.datastruacture.ch2.d10_tree.avl;

/**
 * description 平衡二叉树
 *
 * @author: woldier
 * @date: 2023/7/14 下午2:31
 */
public class AVLTree {
    private AVLNode root;
    /**
     * 平衡二叉树节点类
     */
    private class AVLNode {
        int key;
        int val;
        AVLNode left;  //左子树
        AVLNode right; //右子树
        private int height = 1;  //高度,创建时都为1

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public AVLNode(int key, int val, AVLNode left, AVLNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
    /**
     * description 返回节点的height
     *
     * @param p 节点
     * @return 返回节点的对应高度, 高度大于等一, 当节点为null 时返回0
     * @author: woldier
     * @date: 2023/7/14 下午3:31
     */
    private int height(AVLNode p) {
        return p == null ? 0 : p.height;
    }

    private void updateHeight(AVLNode p) {
        if (p == null) throw new RuntimeException("不能为null");
        p.height = Integer.max(height(p.left), height(p.right)) + 1;
    }

    /**
     *
     * description 计算一个节点的平衡因子
     * 如果返回的值是 -1 , 0 , 1 表示是平衡的,否则表示不平衡
     *
     * @param p 节点
     * @return  返回平衡因子
     * @author: woldier
     * @date: 2023/7/14 下午3:35
     */
    private int bf(AVLNode p){
        return height(p.left) - height(p.right);
    }

    /**
    *
    * description LL型号,需要右旋
     * <pre>
     *     {@code
     *         8
     *       /   \
     *      6     9
     *     / \
     *    5   7
     *   /
     *  4
     *
     *   这是属于LL的情况
     *         6
     *       /   \
     *      5     8
     *     /     / \
     *    4     7   9
     *  -----------------------------------
     *  当然对于L-的情况也适用(这里的-表示左子树平衡) 对于L-这种情况二叉树是平衡的
     *         8
     *       /   \
     *      6     9
     *     / \
     *    5   7
     *
     *         6
     *       /   \
     *      5     8
     *           / \
     *          7   9
     *
     *     }
     * </pre>
    *
    * @param p 需要调整的节点
    * @return  返回调整后的根节点
    * @author: woldier
    * @date: 2023/7/14 下午5:00
    */
    private AVLNode rightRotate(AVLNode p){
        AVLNode newRoot = p.left;  //新的root
        AVLNode kid = newRoot.right; //新root的右孩子(旧的),需要托付给其他人

        newRoot.right = p ; //右旋
        p.left = kid; //给kid找个爸爸

        //更新深度 (由于kid 调整后高度不会 发生变化因此,不需要更新)
        updateHeight(p); //先更新子
        updateHeight(newRoot); //再更新父亲
        return newRoot;
    }
    /**
     *
     * description RR型号,需要左旋
     * <pre>
     *     {@code
     *         2
     *       /   \
     *      1     4
     *           / \
     *          3   5
     *               \
     *                6
     *
     *   这是属于LL的情况
     *         4
     *       /   \
     *      2     5
     *     / \     \
     *    1  3      6
     *  -----------------------------------
     *  当然对于R-的情况也适用(这里的-表示右子树平衡) 对于R-这种情况二叉树是平衡的
     *         2
     *       /   \
     *      1     4
     *           / \
     *          3   5
     *               \
     *                6
     *
     *         4
     *       /   \
     *      2     5
     *     / \
     *    1  3
     *
     *     }
     * </pre>
     *
     * @param p 需要调整的节点
     * @return  返回调整后的根节点
     * @author: woldier
     * @date: 2023/7/14 下午5:00
     */
    private AVLNode leftRotate(AVLNode p){
        AVLNode newRoot = p.right;//新的root
        AVLNode kid = newRoot.left;//新root的右孩子(旧的),需要托付给其他人

        newRoot.left = p ;//左旋
        p.right = kid;//给kid找个新爸爸

        //更新深度 (由于kid 调整后高度不会 发生变化因此,不需要更新)
        updateHeight(p); //先更新子
        updateHeight(newRoot); //再更新父亲
        return newRoot;
    }

    /**
     *
     * description LL型号,需要右旋
     * <pre>
     *     {@code
     *         6
     *       /   \
     *      2     7
     *     / \
     *    1   4
     *       / \
     *      3   5
     *
     *   这是属于LR的情况 ,因此先移动L中的R 使其变为LL
     *         6
     *       /   \
     *      4     7
     *     / \
     *    2   5
     *   / \
     *  1   3
     *  -----------------------------------
     *  然后现在就属于LL 继续移动
     *         5
     *       /   \
     *      2     6
     *     / \   / \
     *    1   3 5   7

     *     }
     * </pre>
     *
     * @param p 需要调整的节点
     * @return  返回调整后的根节点
     * @author: woldier
     * @date: 2023/7/14 下午5:00
     */
    private AVLNode leftRightRotate(AVLNode p){
        p.left = leftRotate(p.left); //LR变成LL
        return rightRotate(p); //LL的处理
    }

    private AVLNode rightLeftRotate(AVLNode p){
        p.right = rightRotate(p.right); //RL变为RR
        return leftRotate(p); //处理RR
    }


    /**
    *
    * description 检查节点是否失衡,重新平衡代码
    *
    * @param node
    * @return
    * @author: woldier
    * @date: 2023/7/14 下午7:12
    */
    private AVLNode balance(AVLNode node){
        if(node == null) return null;
        int bf = bf(node);
        if(bf>1&&bf(node.left)>=0){//LL
            return rightRotate(node);
        }
        if(bf>0&&bf(node.left)<0){//LR
            return leftRightRotate(node);
        }
        if(bf<-1&&bf(node.right)<=0){//RR
            return leftRotate(node);
        }
        else if(bf<-1&&bf(node.right)>0){//RL
            return rightLeftRotate(node);
        }
        return node;

    }
    /**
    *
    * description 放入元素
    *
    * @param key
    * @param val
    * @return
    * @author: woldier
    * @date: 2023/7/14 下午8:17
    */
    public void put(int key,int val){
        doPut(root,key,val);
    }
    /**
    *
    * description 放入节点,递归实现
    *
     * @param node  节点
     * @param key  键
     * @return     * @return  返回删除后的root节点    * @author: woldier
    * @author: woldier
    * @date: 2023/7/14 下午8:16
    */
    private AVLNode doPut(AVLNode node,int key,int val){
        if(node == null) return new AVLNode(key,val); //查询的key不存在
        if(node.key == key){//查询的key存在
            node.val = val;
            return node;
        }
        if(node.key<key){//往左边找
            node.left = doPut(node.left,key,val);
        }
        else {//往右边找
            node.right = doPut(node.right,key,val);
        }
        updateHeight(node);//更新height
        return balance(node);//平衡
    }

    public void  remove(int key){
        doRemove(root,key);
    }

    /**
    *
    * description 递归删除
    *
    * @param node  节点
    * @param key  键
    * @return  返回删除后的root节点
    * @author: woldier
    * @date: 2023/7/14 下午8:15
    */
    private AVLNode doRemove(AVLNode node,int key){
        if(node==null) return null;//说明不存在
        if(node.key == key) {//存在
            node = removeHead(node);
        }
        if(node.key<key){ //往左边
            node.left =  doRemove(node.left, key);
        }
        else if(node.key>key){//往右边
            node.right = doRemove(node.right,key);
        }
        //更新高度
        updateHeight(node);
        // 平衡
        return balance(node);
    }

    /**
    *
    * description 移除root节点
    *
    * @param node 将要移除的元素
    * @return
    * @author: woldier
    * @date: 2023/7/14 下午8:15
    */
    private  AVLNode removeHead(AVLNode node) {
        //只有右子树
        if(node.left==null){
            node = node.right;
        }
        //只有左子树
        if(node.right==null){
            node = node.left;
        }
        //左右子树都有
        else {
            //找到node的后继
            AVLNode s = node.right;//初始化后继
            AVLNode sp = node; //初始化后继的parent
            while(s.left!=null){ //当有左孩子,那么左移
                sp = s;
                s =s.left;
            }
            node.val = s.val;
            if(sp == node) {//如果当前的s的父亲就是node
                node.right = s.right;
            }else {  //如果不是,那么需要将s的右孩子安排好
                sp.left = s.right;
            }
            s.right = null;//help GC
        }
        return node;
    }
}
