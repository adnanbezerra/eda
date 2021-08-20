import java.util.Scanner;

class Solution {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        Fila fila = new Fila();

        while(!entrada.startsWith("end")){

            if(entrada.startsWith("add")){
                String[] dados = entrada.split(" ");
                fila.add(Integer.parseInt(dados[1]));
                entrada = sc.nextLine();
            }

            if(entrada.startsWith("remove")){
                if(!fila.pop()) System.out.println("empty");
                entrada = sc.nextLine();
            }

            if(entrada.startsWith("element")){
                System.out.println(fila.head());
                entrada = sc.nextLine();
            }

            if(entrada.startsWith("search")){
                String[] dados = entrada.split(" ");
                System.out.println(fila.search(Integer.parseInt(dados[1])));
                entrada = sc.nextLine();
            }

            if(entrada.startsWith("print")){
                System.out.println(fila.toString());
                entrada = sc.nextLine();
            }
        }

        sc.close();
    }

}

class Fila {

    private Node head;
    private Node tail;
    private int size;

    public Fila(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(int entrada) {
        size++;
    }

    public int search(int entrada) {
        int contador = 0;
        Node aux = this.head;

        if(this.buscador(entrada, aux)) return contador;

        return -1;
    }

    private boolean buscador(int entrada, Node aux){
        if(aux.next == null) return false;
    }

    public int head() {
        return head.elemento;
    }

    public boolean pop() {
        size--;
        return false;
    }

    @Override
    public String toString(){
        return "e true man";
    }
}

class Node {

    public int elemento;
    public int next;
    public int prev;

    public Node(int entrada){
        this.elemento = entrada;
    }
}