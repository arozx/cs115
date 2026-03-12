/**
 * Wrapper class to store Strings in LinkedLists
 */
public class Node {

    private String data;
    private Node next = null;

    public Node(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public void setData(String data){
        this.data = data;
    }
}
