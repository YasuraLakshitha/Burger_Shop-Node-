public class List {

    private Node top;

    public boolean add(Order obj){
        Node n = new Node(obj);
        if(isEmpty()){top = n;return true;}
        Node temp = top;
        while(true){
            if(temp.getNext()==null){temp.setNext(n);return true;}
            temp = temp.getNext();
        }
    }

    public void setTop(Node top){
        this.top = top;
    }

    public int size(){
        int count = 0;
        Node temp = top;
        while(true){
            if(isEmpty()){return count;}
            if(temp.getNext()==null){return ++count;}
            temp = temp.getNext();
            count++;
        }
    }

    public Node getLast(){
        Node temp = top;
        if(isEmpty()){return null;}
        while(true){
            if(temp.getNext()==null){return temp;}
            temp = temp.getNext();
        }
    }

    public Node getNode(int index){
        if(isEmpty()){return null;}
        Node temp = top;
        int count=0;
        while(true){
            if(count==index){return temp;}
            temp = temp.getNext();
            count++;
        }
    }
    public void remove(int index){
        if(isEmpty()){return;}
        Node temp = top;
        int count = 0;
        while(true){
            if(count==index-1){temp.setNext(temp.getNext().getNext());return;}
            temp = temp.getNext();
            count++;
        }
    }
    private boolean isEmpty(){
        return top == null;
    }
}
