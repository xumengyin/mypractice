package com.example.xumengyin.mypractice;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
    @Test
    public void intValue() throws Exception {
        int key=0x00000239;
        System.out.print(String.valueOf(key));
    }
    @Test
    public void testBindaryTree(){
        Node<Integer>root =new Node<>(0);
        Node<Integer>node1 =new Node<>(1);
        Node<Integer>node2 =new Node<>(2);
        Node<Integer>node3 =new Node<>(3);
        Node<Integer>node4 =new Node<>(4);
        Node<Integer>node5 =new Node<>(5);
        Node<Integer>node6 =new Node<>(6);
        Node<Integer>node7 =new Node<>(7);
        Node<Integer>node8 =new Node<>(8);
        Node<Integer>node9 =new Node<>(9);
        Node<Integer>node10 =new Node<>(10);
        BinaryTree<Integer> tree=new BinaryTree<>(root);
        root.leftChild=node1;
        root.rightChild=node2;
        node1.leftChild=node3;
        node1.rightChild=node4;
        node2.leftChild=node5;
        node2.rightChild=node6;
        node3.rightChild=node7;


        tree.preIndex(tree.root);

    }




    public class BinaryTree <T>
    {
        Node<T>root;
        public BinaryTree(Node<T>root)
        {
            this.root=root;
        }


        public void preIndex(Node node)
        {
            if(node==null)
                return;
            System.out.println("data:"+node.data);
            preIndex(node.leftChild);
            preIndex(node.rightChild);
        }
    }
    public class Node<T>
    {
        T data;
        public Node<T>leftChild;
        public Node<T>rightChild;

        public Node(T data)
        {
            this.data = data;
        }
    }


}