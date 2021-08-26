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

        this.size++;
        Node novo = new Node(entrada);

        if(this.isEmpty()){
            this.head = novo;
            this.tail = novo;
        } else {
            recursiveAddLast(this.head, novo);
        }
    }

    private void recursiveAddLast(Node current, Node novo) {

        if(current.next == null) {
            current.next = novo;
            novo.prev = current;
        }

        else recursiveAddLast(current.next, novo);

    }

    private boolean isEmpty() {
        return (this.head == null);
    }

    public int search(int entrada) {
        Node aux = this.head;
        Node busca = new Node(entrada);

        int contador = 0;

        return this.buscaRecursiva(aux, busca, contador);

    }

    private int buscaRecursiva(Node aux, Node compara, int contador){

        // eu notei que, se a lista estiver vazia, ocorre um NullPointerException na busca...
        // conheço bem a malemolência dos testes do tst, conheço bem demais.
        if(aux == null) return -1;

        boolean compare = (aux.elemento.getConteudo() == compara.elemento.getConteudo());

        if(compare) return contador;
        if(aux.next == null && !compare) return -1;

        contador++;
        return buscaRecursiva(aux.next, compara, contador);
    }

    public int head() {
        return head.elemento.getConteudo();
    }

    public boolean pop() {
        if(this.isEmpty()) return false;

        if(this.head.next == null){
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
 
        size--;
        return true;
    }

    @Override
    public String toString(){
        String resultado = "";
        Node aux = this.head;

        if(aux == null) return "empty";

        resultado = toStringRecursivo(resultado, aux);
        
        return resultado;
    }

    private String toStringRecursivo(String string, Node node) {

        if(node.next == null) {
            string += node.elemento.getConteudo();
            return string;
        } else {
            string += node.elemento.getConteudo() + " ";

        }

        return toStringRecursivo(string, node.next);
    }
}

class Node {

    public Elemento elemento;
    public Node next;
    public Node prev;

    public Node(int entrada){
        this.elemento = new Elemento(entrada);
        this.next = null;
        this.prev = null;
    }
}

class Elemento {
    private int conteudo;

    public Elemento(int entrada){
        this.conteudo = entrada;
    }

    public int getConteudo() {
        return conteudo;
    }
}
