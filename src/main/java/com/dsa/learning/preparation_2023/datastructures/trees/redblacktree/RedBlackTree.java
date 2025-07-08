package com.dsa.learning.preparation_2023.datastructures.trees.redblacktree;


/**
 * ChatGPT ....
 *
 * To insert a new node into a red-black tree, we follow these steps:
 *
 * Perform a standard binary search tree insertion of the new node.
 * And color the new node red.
 *
 * If the parent of the new node is black, then the tree is still balanced, and we are done.
 *
 * If the parent of the new node is red, then we must perform a rotation or a color flip to balance the tree.
 *
 * If the parent of the new node is the left child of the grandparent, and the uncle (i.e., the right child of the grandparent) is also red,
 * then we perform a color flip.
 *
 * If the parent of the new node is the right child of the grandparent, and the uncle (i.e., the left child of the grandparent) is also red,
 * then we perform a color flip.
 *
 * If the parent of the new node is the left child of the grandparent, and the uncle is black or NULL, then we perform a rotation (left rotation)
 * followed by a color flip.
 *
 * If the parent of the new node is the right child of the grandparent, and the uncle is black or NULL, then we perform a rotation (right rotation)
 * followed by a color flip.
 *
 */


/**
 * GeeksForGeeks.com
 *
 * Algorithm:
 * Let x be the newly inserted node.
 *
 * Perform standard BST insertion and make the colour of newly inserted nodes as RED.
 *
 * If x is the root, change the colour of x as BLACK (Black height of complete tree increases by 1).
 *
 * Do the following if the color of x’s parent is not BLACK and x is not the root.
 *
 * a) If x’s uncle is RED (Grandparent must have been black from property 4)
 * (i) Change the colour of parent and uncle as BLACK.
 * (ii) Colour of a grandparent as RED.
 * (iii) Change x = x’s grandparent, repeat steps 2 and 3 for new x.
 *
 * b) If x’s uncle is BLACK, then there can be four configurations for x, x’s parent (p) and x’s grandparent (g) (This is similar to AVL Tree)
 * (i) Left Left Case (p is left child of g and x is left child of p)
 * (ii) Left Right Case (p is left child of g and x is the right child of p)
 * (iii) Right Right Case (Mirror of case i)
 * (iv) Right Left Case (Mirror of case ii)
 *
 * Re-coloring after rotations:
 * For Left Left Case [3.b (i)] and Right Right case [3.b (iii)], swap colors of grandparent and parent after rotations
 * For Left Right Case [3.b (ii)]and Right Left Case [3.b (iv)], swap colors of grandparent and inserted node after rotations
 */


import java.util.Objects;

/**
 * Studies from below links:
 *
 * https://www.happycoders.eu/algorithms/red-black-tree-java/
 * https://www.geeksforgeeks.org/insertion-in-red-black-tree/
 *
 * https://www.youtube.com/watch?v=qA02XWRTBdw&t=3s
 *
 */

public class RedBlackTree {

    Node root;

    public Node insert(Node node, Node parent, int key) {

        if (node == null){
            Node newNode = new Node(key, "RED");
            newNode.parent = parent;

            // This parent's left/right child is required below while Rotations, that's why its set here rather then during recursion call below ....
            if(Objects.nonNull(parent)){

                if(key < parent.key){
                    parent.left = newNode;
                }
                else{
                    parent.right = newNode;
                }
            }

            fixRedBlackPropertiesAfterInsert(newNode);
            return newNode;
        }

        if (key < node.key){
            insert(node.left, node, key);
        }
        else if (key > node.key){
            insert(node.right, node, key);
        }
        else{
            node.key = key;
        }

        return node;
    }

    private Node insertNode(int key){

        Node newNode = new Node(key, "RED");

        fixRedBlackPropertiesAfterInsert(newNode);

        return newNode;
    }

    private void fixRedBlackPropertiesAfterInsert(Node node){

        Node parent = node.parent;

        // Case: Parent is null, we've reached the root, the end of the recursion
        if (parent == null) {
            // The following line enforces black roots. If x is the root, change the colour of x as BLACK.
            node.color = "BLACK";
            return;
        }

        // Parent is black --> nothing to do
        if (parent.color == "BLACK") {
            return;
        }


        // From here on, parent is RED ....

        Node grandparent = parent.parent;

        if (grandparent == null) {
            // As this method is only called on red nodes (either on newly inserted ones - or -
            // recursively on red grandparents), all we have to do is to recolor the root black.
            parent.color = "BLACK";
            return;
        }

        // Get the uncle (may be null/nil, in which case its color is BLACK)
        Node uncle = getUncle(parent);

        // Case: Uncle is red -> recolor parent, grandparent and uncle
        if (uncle != null && uncle.color == "RED") {
            parent.color = "BLACK";
            grandparent.color = "RED";
            uncle.color = "BLACK";

            // Call recursively for grandparent, which is now red.
            // It might be root or have a red parent, in which case we need to fix more...
            fixRedBlackPropertiesAfterInsert(grandparent);
        }
        // Uncle is either NULL or is BLACK ....
        else if (parent == grandparent.left) {
            // Case: Uncle is black and node is left->right "inner child" of its grandparent
            if (node == parent.right) {
                rotateLeft(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case: Uncle is black and node is left->left "outer child" of its grandparent
            rotateRight(grandparent);

            // Recolor original parent and grandparent
            parent.color = "BLACK"; // Newly inserted node is referred as Parent in 2nd last step ....
            grandparent.color = "RED";
        }
        else {
            // Case: Uncle is black and node is right->left "inner child" of its grandparent
            if (node == parent.left) {
                rotateRight(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case: Uncle is black and node is right->right "outer child" of its grandparent
            rotateLeft(grandparent);

            // Recolor original parent and grandparent
            parent.color = "BLACK"; // Newly inserted node is referred as Parent in 2nd last step ....
            grandparent.color = "RED";
        }

    }


    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }
//
//    Node rightRotate(Node y) {
//        Node x = y.left;
//        Node T2 = x.right;
//
//        // Perform rotation
//        x.right = y;
//        y.left = T2;
//
//        // Return new root
//        return x;
//    }
//
//    // Rotate left subtree rooted with x
//    Node leftRotate(Node x) {
//        Node y = x.right;
//        Node T2 = y.left;
//
//        // Perform rotation
//        y.left = x;
//        x.right = T2;
//
//        // Return new root
//        return y;
//    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            parent = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.key + " - " + node.color);
            inorder(node.right);
        }
    }

}
