package Predecessor;

import java.util.ArrayList;
import java.util.Scanner;

class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] array = sc.nextLine().split(" ");
        int pred = sc.nextInt();
        BST bst = new BST();

        for(int i = 0; i < array.length; i++){
            bst.add(Integer.parseInt(array[i]));
        }

        System.out.println(bst.predecessor(pred).toString());

        sc.close();
    }
}

class BST {
    private Node root;

    public BST() { this.root = null; }

    public boolean isEmpty(){
        return(this.root==null);
    }

    public void add(int v){
        if(this.isEmpty()) this.root = new Node(v);
        else {
            recursiveAdd(this.root, v);
        }
    }

    private void recursiveAdd(Node node, int v) {

        if (v < node.value) {
            if (node.left == null) {
                Node newNode = new Node(v);
                node.left = newNode;
                newNode.parent = node;
                return;
            }
            recursiveAdd(node.left, v);
        } else {
            if (node.right == null) {
                Node newNode = new Node(v);
                node.right = newNode;
                newNode.parent = node;
                return;
            }
            recursiveAdd(node.right, v);
        }
            
    }

    public ArrayList<Integer> predecessor(int entry) {
        Node node = this.recursiveSearch(entry);

        ArrayList<Integer> saida = new ArrayList<>();

        if (node.left != null)
            return max(node.left, saida);

        else {
            Node aux = node.parent;
            
            while (aux != null && aux.value > node.value)
                aux = aux.parent;
                if(aux != null)
                saida.add(aux.value);
            
            return saida;
        }
    }

    public ArrayList<Integer> max(Node first, ArrayList<Integer> array) {
           
        Node node = first;
        
        while(node.right != null)
            array.add(node.value); 
            node = node.right; 
   
        return array;
    
    }

    public Node recursiveSearch(int element) {
        return recursiveSearch(this.root, element);
    }
        
    private Node recursiveSearch(Node node, int element) {
        if (node == null) return null;
        if (element == node.value) return node;
        if (element < node.value) return recursiveSearch(node.left, element);
        else return recursiveSearch(node.right, element);
    }
}

class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    Node(int v){
        this.value = v;
    }
}
