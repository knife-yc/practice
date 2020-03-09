package com.yc.practice.test.tree;

import java.util.List;

/**
 * 二叉树
 */
public class BinaryTree<T> {

    private BinaryTreeNode<T> root;

    public void initBiTree(BinaryTreeNode[] nodes) {

    }

    public void createBiTree(BinaryTreeNode[] nodes) {

    }

    public void destroyBiTree() {

    }

    /**
     * 保留根节点
     *
     * @param rootNode
     */
    public void clearBiTree(BinaryTreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        BinaryTreeNode leftChild = rootNode.left;
        BinaryTreeNode rightChild = rootNode.right;
        rootNode.data = null;
        rootNode.left = null;
        rootNode.right = null;
        rootNode.parent = null;
        if (leftChild != null) {
            clearBiTree(leftChild);
        }
        if (rightChild != null) {
            clearBiTree(rightChild);
        }
    }

    /**
     * 初始条件：二叉树T存在。
     * 操作结果：若 T 为空二叉树，则返回 true, 否则 false。
     *
     * @param binaryTree
     */
    public boolean biTreeEmpty(BinaryTree binaryTree) {
        return false;
    }

    /**
     * 初始条件：二叉树T存在。
     * 操作结果：返回T的深度。
     *
     * @param binaryTree
     * @return
     */
    public int biTreeDepth(BinaryTree binaryTree) {
        return 0;
    }

    /**
     * 初始条件：二叉树T存在。
     * 操作结果：返回T的根 。
     */
    public BinaryTreeNode<T> root(BinaryTree binaryTree) {
        return null;
    }

    /**
     * 初始条件：二叉树 T存在，e 是 T中某个结点。
     * 操作结果：返回e的值。
     *
     * @param binaryTree
     * @param e
     * @return
     */
    public T value(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }

    /**
     * 初始条件：二叉树T存在， e 是T中某个结点。
     * 操作结果：结点 e 赋值为 value。
     */
    public void assign(BinaryTree binaryTree, BinaryTreeNode e, T value) {

    }

    /**
     * 初始条件：二叉树 T存在，e 是 T中某个结点。
     * 操作结果：若 e 是T的非根结点，则返回它的双亲，否则返回 “空”。
     */
    public BinaryTreeNode parent(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }

    /**
     * 初始条件：二叉树T存在， e 是T中某个结点。
     * 操作结果：返回e的左孩子。若e 无左孩子，则返回 “空”。
     */
    public BinaryTreeNode leftChild(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }

    /**
     * 初始条件：二叉树T存在， e 是T中某个结点。
     * 操作结果：返回 e的右孩子。若 e 无右孩子，则返回 “ 空”。
     */
    public BinaryTreeNode rightChild(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }

    /**
     * 初始条件：二叉树T存在， e 是T中某个结点。
     * 操作结果：返回 e的左兄弟。若 e 是T的左孩子或无左兄弟，则返回 “空”。
     *
     * @param binaryTree
     * @param e
     * @return
     */
    public BinaryTreeNode LeftSibling(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }


    /**
     * 初始条件：二叉树T存在， e 是T中某个结点。
     * 操作结果：返回 e的右兄弟。若 e 是T的右孩子或无右兄弟，则返回 “空”。
     *
     * @param binaryTree
     * @param e
     * @return
     */
    public BinaryTreeNode rightSibling(BinaryTree binaryTree, BinaryTreeNode e) {
        return null;
    }

    /**
     * 初始条件：二叉树 T存在， p 指向 T中某个结点，LR 为 0 或 1, 非空二叉树 c 与 T 不相交且右子树为空。
     * 操作结果：根据 LR 为 0 或 1, 插入 c 为 T中 p 所指结点的左或右子树。p 所指结点的原有左或右子树则成
     * 为 c的右子树。
     */
    /**
     * @param binaryTree 树
     * @param p          插入节点的双亲
     * @param lr         左子树还是右子树
     * @param c          插入的节点
     */
    public void insertChild(BinaryTree binaryTree, BinaryTreeNode p, int lr, BinaryTreeNode c) {

    }


    /**
     * 初始条件：二叉树T存在，p指向T中某个结点，LR为0或1。
     * 操作结果：根据LR为0或1, 删除T中p所指结点的左或右子树。
     *
     * @param binaryTree
     * @param p
     * @param lr
     */
    public void deleteChild(BinaryTree binaryTree, BinaryTreeNode p, int lr) {

    }

    /**
     * 初始条件：二叉树T存在。
     * 操作结果：先序遍历T, 对每个结点访问一次。
     *
     * @param root 根节点
     * @param list 存储数据的列表
     */
    public void preOrderTraverse(BinaryTreeNode root, List<T> list) {
        if (root == null) {
            return;
        }
        BinaryTreeNode leftChild = root.getLeft();
        BinaryTreeNode rightChild = root.getRight();

        T data = (T) root.getData();
        list.add(data);
        if (leftChild != null) {
            preOrderTraverse(leftChild, list);
        }
        if (rightChild != null) {
            preOrderTraverse(rightChild, list);
        }
    }

    /**
     * 初始条件：二叉树T存在。
     * 操作结果：中序遍历T, 对每个结点访问一次。
     */
    public void inOrderTraverse(BinaryTreeNode root, List<T> list) {
        if (root == null) {
            return;
        }
        BinaryTreeNode leftChild = root.getLeft();
        if (leftChild != null) {
            inOrderTraverse(leftChild, list);
        }
        T data = (T) root.getData();
        list.add(data);

        BinaryTreeNode rightChild = root.getRight();
        if (rightChild != null) {
            inOrderTraverse(rightChild, list);
        }
    }


    /**
     * 初始条件：二叉树T存在。
     * 操作结果：后序遍历T, 对每个结点访问一次
     *
     * @param root
     * @param list
     */
    public void postOrderTraverse(BinaryTreeNode root, List<T> list) {
        if (root == null) {
            return;
        }
        BinaryTreeNode leftChild = root.getLeft();
        if (leftChild != null) {
            postOrderTraverse(leftChild, list);
        }

        BinaryTreeNode rightChild = root.getRight();
        if (rightChild != null) {
            postOrderTraverse(rightChild, list);
        }

        T data = (T) root.getData();
        list.add(data);
    }


    /**
     * 初始条件：二叉树T存在。
     * 操作结果：操作结果：层序遍历T, 对每个结点访问一次。
     *
     * @param binaryTree
     */
    public void levelOrderTraverse(BinaryTree binaryTree) {

    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    static final class BinaryTreeNode<T> {

        private T data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;
        private BinaryTreeNode parent;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode left) {
            this.left = left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode right) {
            this.right = right;
        }

        public BinaryTreeNode getParent() {
            return parent;
        }

        public void setParent(BinaryTreeNode parent) {
            this.parent = parent;
        }
    }
}
