import org.w3c.dom.Node;

import java.util.Scanner;

public class LBTree {
    class Node {
        Node left;
        Node right;
        int value;
        boolean color;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;

            color = true;

        }
    }
    private static Node root = null;

    Node rotateLeft(Node node){
        System.out.println("Поворот влево\n");
        Node child = node.right;
        Node childLeft = child.left;
        child.left = node;
        node.right = childLeft;
        return child;
    }
    Node rotateRight(Node node){
        System.out.println("Вращение вправо\n");
        Node child = node.left;
        Node childRight = child.right;

        child.right = node;
        node.left = childRight;

        return child;
    }

    boolean isRed(Node node){
        if (node == null){
            return false;
        }
        return (node.color);
    }
    void swapColors(Node node1, Node node2){
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }
    Node insert(Node node, int value){
        if (node == null){
            return new Node(value);
        }
        if (value < node.value){
            node.left = insert(node.left,value);
        } else if (value > node.value) {
            node.right = insert(node.right,value);
        }else {
            return node;
        }
        if ((isRed(node.right) && !isRed(node.left))){
            node = rotateLeft(node);
            swapColors(node,node.left);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
            swapColors(node,node.right);
        }
        if (isRed(node.left) && isRed(node.right)){
            node.color = !node.color;

            node.left.color = false;
            node.right.color = false;
        }
        return node;
    }
    void inorder(Node node){
        if (node != null)
        {
            inorder(node.left);
            char c = '●';
            if (!node.color)
                c = '◯';
            System.out.print(node.value + ""+c+" ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        LBTree node = new LBTree();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Введите целое число");

            int num = scan.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\nвведите y(продолжить) или n(выход))");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

}

