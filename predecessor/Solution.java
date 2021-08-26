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
        Node node = this.search(entry);
        Node aux = node;

        ArrayList<Integer> saida = new ArrayList<>();

        if (aux.left != null){
            saida.add(aux.value);
            aux = node.left;

            while(aux.right != null){
                saida.add(aux.value); 
                aux = aux.right; 
            }
            saida.add(aux.value);
            return saida;
        }

        else {
            saida.add(aux.value);
            aux = node.parent;
            
            while (aux != null && aux.value > node.value){
                saida.add(aux.value);
                aux = aux.parent;
            }

            saida.add(aux.value);
            return saida;
        }
    }

    public Node search(int element) {
        
        Node aux = this.root;
            
        while (aux != null) {   
            if (aux.value == element) return aux;
            if (element < aux.value) aux = aux.left;
            if (element > aux.value) aux = aux.right;
        }
            
        return null;
    
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
