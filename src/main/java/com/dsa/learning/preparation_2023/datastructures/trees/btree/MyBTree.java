package com.dsa.learning.preparation_2023.datastructures.trees.btree;

/**
 * Study Sources:
 *
 * https://www.programiz.com/dsa/b-tree
 * https://www.programiz.com/dsa/insertion-into-a-b-tree
 * http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/chap19.htm
 * https://en.wikipedia.org/wiki/B-tree
 * https://www.cs.utexas.edu/users/djimenez/utsa/cs3343/lecture16.html#:~:text=These%20bounds%20can%20be%20expressed,has%20at%20least%20t%20children.
 * https://prepinsta.com/data-structures/insertion-in-b-tree/
 *
 * https://www.youtube.com/watch?v=aNU9XYYCHu8
 */
public class MyBTree {

    private int orderOfTree; // Order/Degree of the B-Tree ....
    private Node root;

    public MyBTree(int treeOrder) {
        orderOfTree = treeOrder;
        root = new Node();
        root.keysCount = 0;
        root.isLeafNode = true;
    }

    public class Node {
        int keysCount;

        int key[] = new int[orderOfTree - 1];
        Node child[] = new Node[orderOfTree];
        boolean isLeafNode = true;

    }

    /**
     * Insertion k process ROOT Node se start hoti hai. Uske andar jo keys hai usse nayi add hone wali key ko compare kr k
     * check krte hai k kis child node m jana hai ....
     * @param key
     */
    public void insert(final int key) {
        Node r = root;
        if (r.keysCount == orderOfTree -1) {
            Node s = new Node();
            root = s;
            s.isLeafNode = false;
            s.keysCount = 0;
//            s.child[0] = r;
            split(s, 0, r);
            _insert(s, key);
        } else {
            _insert(r, key);
        }
    }

    private void _insert(Node x, int k) {

        if (x.isLeafNode) {
            int i = 0;
            for (i = x.keysCount - 1; i >= 0; i--) {
                if(!(k < x.key[i])){
                    break;
                }
                else {
                    x.key[i + 1] = x.key[i];
                }
            }
            x.key[i + 1] = k;
            x.keysCount = x.keysCount + 1;
        } else {  // case of root/internal node ....
            int i = 0;
            for (i = x.keysCount - 1; i >= 0; i--) {
                // Ye loop basically hume is root node k keys k us index tak pahucha rh hai jaha ye nayi key fit baithti hai.
                // Fir hum us index k child node pe jayenge is key ko insert krne and so on ....
                if(!(k < x.key[i])){
                    break;
                }
            }
            ;
            i++;
            Node tmp = x.child[i];
            if (tmp.keysCount == (orderOfTree-1)) {
                split(x, i, tmp);
                if (k > x.key[i]) {
                    i++;
                }
            }
            _insert(x.child[i], k);
        }
    }

    /**
     * lowerNode k Median-Key ko hum upperNode m move kr rh hai and lowerNode k keys (median se greater) unko hum ek
     * naye Node m move kr rh hai ....
     */
    private void split(Node upperNode, int pos, Node lowerNode) {

        Node newNode2 = new Node();
        newNode2.isLeafNode = lowerNode.isLeafNode; // coz both these nodes are now on same level ....

        int indexOfKeyToShift = orderOfTree/2 - 1;
        int k = 0;

        for (int j = indexOfKeyToShift+1; j < orderOfTree-1; j++) {
            newNode2.key[k++] = lowerNode.key[j];
            newNode2.keysCount++;
            lowerNode.key[j] = 0;
        }

        upperNode.key[pos] = lowerNode.key[indexOfKeyToShift]; // Key move ho rh hai upar create k nayi wali node pe ....
        upperNode.keysCount++;

        lowerNode.key[indexOfKeyToShift] = 0;
        lowerNode.keysCount = indexOfKeyToShift;

        upperNode.child[pos] = lowerNode;
        upperNode.child[pos + 1] = newNode2;
    }

    public void display() {
        display(root);
    }

    // Display the tree
    private void display(Node x) {
        assert (x == null);
        System.out.println();
        for (int i = 0; i < x.keysCount; i++) {
            System.out.print(x.key[i] + " ");
        }
        if (!x.isLeafNode) {
            for (int i = 0; i < x.keysCount + 1; i++) {
                display(x.child[i]);
            }
        }
    }

    public static void main(String[] args) {
        MyBTree b = new MyBTree(4);
        b.insert(8);
        b.display();
        b.insert(9);  b.display();
        b.insert(10);  b.display();
        b.insert(11);  b.display();
        b.insert(15); b.display();
        b.insert(20); b.display();
        b.insert(17); b.display();

        b.display();
    }
    
}
