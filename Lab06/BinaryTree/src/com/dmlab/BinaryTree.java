package com.dmlab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<Key extends Comparable<? super Key>, E> {

    class Node {
        private Key mKey;
        private E mValue;

        private Node mLeft;
        private Node mRight;

        public Node(Key key, E value) {
            mKey = key;
            mValue = value;
            mLeft = null;
            mRight = null;
        }

        public Key getKey() {
            return mKey;
        }

        public E getValue() {
            return mValue;
        }

        /**
         * Insert a node to the subtree, the root of which is this node.
         * If the subtree already has node with the given key in the param,
         * replace the value of the node in the subtree.
         * Please compare keys using compareTo method.
         * e.g. when "int compare = KEY0.compareTo(KEY1)"
         * if compare == 0, this means KEY0 is equal to KEY1
         * if compare > 0, KEY0 > KEY1
         * if compare < 0, KEY0 < KEY1
         *
         * @param key
         * @param value
         * @return None
         */
        public void insert(Key key, E value) {
            Node newNode = new Node(key, value);
            Node curr = this;

            while (true) {
                Key currKey = curr.getKey();

                if (key.compareTo(currKey) == 0) {
                    curr.mValue = value;
                    break;
                } else if (key.compareTo(currKey) < 0) {
                    if (curr.mLeft == null) {
                        curr.mLeft = newNode;
                        break;
                    } else {
                        curr = curr.mLeft;
                        continue;
                    }
                } else {
                    if (curr.mRight == null) {
                        curr.mRight = newNode;
                        break;
                    } else {
                        curr = curr.mRight;
                        continue;
                    }
                }
            }
        }

        /**
         * Find the value of item by the key in the subtree, the root of which is this node.
         *
         * @param key
         * @return the value of item matched with the given key.
         * null, if there is no matching node in this subtree.
         */
        public E find(Key key) {
            Node curr = this;

            while (curr != null) {
                Key currKey = curr.getKey();

                if (key.compareTo(currKey) == 0) {
                    return curr.getValue();
                } else if (key.compareTo(currKey) < 0) {
                    curr = curr.mLeft;
                } else {
                    curr = curr.mRight;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "[" + String.valueOf(mKey) + ":" + String.valueOf(mValue) + "]";
        }

        /**
         * Traverse with the preorder in this subtree.
         *
         * @return The String to be printed-out which contains series of nodes as the preorder traversal.
         */
        public String preorder() {
            String str = "";
            str += this.toString();

            if (this.mLeft != null) {
                str += this.mLeft.preorder();
            }
            if (this.mRight != null) {
                str += this.mRight.preorder();
            }

            return str;
        }

        /**
         * Traverse with the inorder in this subtree.
         *
         * @return The String to be printed-out which contains series of nodes as the inorder traversal.
         */
        public String inorder() {
            String str = "";

            if (this.mLeft != null) {
                str += this.mLeft.inorder();
            }
            str += this.toString();

            if (this.mRight != null) {
                str += this.mRight.inorder();
            }

            return str;
        }

        public Node findMin() {
            if (mLeft == null)
                return this;
            else
                return mLeft.findMin();
        }

        public Node findMax() {
            if (mRight == null)
                return this;
            else
                return mRight.findMax();
        }

        /**
         * Delete a node,the key of which match with the key given as param, from this subtree.
         * You may need to define new method to find minimum of the subtree.
         *
         * @return the node of which parent needs to point after the deletion.
         */
        public Node delete(Key key) {
            Node curr = this;
            Key currKey = curr.getKey();

            if (key.compareTo(currKey) < 0) {
                curr.mLeft = curr.mLeft.delete(key);
                return curr;
            } else if (key.compareTo(currKey) > 0) {
                curr.mRight = curr.mRight.delete(key);
                return curr;
            } else {
                if (curr.mLeft == null && curr.mRight == null) {
                    return null;
                } else if (curr.mLeft == null) {
                    return curr.mRight;
                } else if (curr.mRight == null) {
                    return curr.mLeft;
                } else {
                    Node min = curr.mRight.findMin();
                    curr.mKey = min.getKey();
                    curr.mValue = min.getValue();
                    curr.mRight = curr.mRight.delete(min.getKey());
                    return curr;
                }
            }
        }

        /**
         * Flatten binary search tree into binary tree
         */
        public void flattenBinaryTree(Node root) {
            if (root.mLeft != null) {
                Node rightNode;
                if (root.mRight != null) {
                    rightNode = root.mRight;
                } else {
                    rightNode = null;
                }
                root.mRight = root.mLeft;
                root.mLeft = null;
                flattenBinaryTree(root.mRight);
                Node tempNode = root.mRight;

                while (true) {
                    if (tempNode.mRight == null) break;
                    else {
                        tempNode = tempNode.mRight;
                    }
                }
                tempNode.mRight = rightNode;
                flattenBinaryTree(rightNode);
            } else {
                if (root.mRight != null) {
                    flattenBinaryTree(root.mRight);
                }
            }
        }

        /**
         * Find the lowest common ancestor
         * If k1 or k2 are not in the tree, return the root
         *
         * @return
         */
        public Node lowestCommonAncestor(Node root, Key k1, Key k2) {
            if (k1.equals(mRoot.getKey()) || k2.equals(mRoot.getKey())) {
                return null;
            } else if (root.find(k1) == null || root.find(k2) == null) {
                return root;
            } else if (k1.compareTo(root.getKey()) < 0 && k2.compareTo(root.getKey()) < 0) {
                return lowestCommonAncestor(root.mLeft, k1, k2);
            } else if (k1.compareTo(root.getKey()) > 0 && k2.compareTo(root.getKey()) > 0) {
                return lowestCommonAncestor(root.mRight, k1, k2);
            } else return root;
        }

    }

    private Node mRoot;

    public BinaryTree() {
        mRoot = null;
    }

    public void insert(Key key, E value) {
        if (mRoot == null) {
            mRoot = new Node(key, value);
        } else {
            mRoot.insert(key, value);
        }
    }

    public void delete(Key key) {
        if (mRoot == null)
            return;
        mRoot = mRoot.delete(key);
    }

    public E find(Key key) {
        if (mRoot == null)
            return null;
        return mRoot.find(key);
    }

    public void preorder() {
        System.out.print("preorder : ");
        if (mRoot == null) {
            System.out.println("None");
            return;
        }
        System.out.println(mRoot.preorder());
    }

    public void inorder() {
        System.out.print("inorder : ");
        if (mRoot == null) {
            System.out.println("None");
            return;
        }
        System.out.println(mRoot.inorder());

    }

    public void flattenBinaryTree() {
        System.out.println("Flatten binary seatch tree");
        if (mRoot == null) ;
        else
            mRoot.flattenBinaryTree(mRoot);
    }

    public void lowestCommonAncestor(Key k1, Key k2) {
        if (mRoot == null)
            System.out.println("The tree is empty");
        else if (k1 == null || k2 == null) {
            System.out.println("Error, the key is null");
        } else if (mRoot.find(k1) != null && mRoot.find(k2) != null) {
            Node n = mRoot.lowestCommonAncestor(mRoot, k1, k2);
            System.out.println("LCA of " + k1 + " and " + k2 + " : [" + n.getKey() + ":" + n.getValue() + "]");
        } else {
            System.out.println("The key doesnt exist");
        }

    }
}
