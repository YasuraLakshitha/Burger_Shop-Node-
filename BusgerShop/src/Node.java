public class Node {

    private Node next;
    private Order obj;

    public Node(Order obj) {
        this.obj = obj;
    }

    public Node getNext() {
        return next;
    }

    public Order getObj(){
        return obj;
    }
    public void setObj(Order obj){
        this.obj = obj;
    }

    public void setNext(Node next){
        this.next = next;
    }
}
