/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayplanner;

/**
 *The Node class is the abstraction of the towns hierarchy, which is 
 * represented through a tree data structure where the root is the globe, 
 * the next level are the countries, the third districts and the last one 
 * contains the towns/villages
 * @author ruxi
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;
public class Node<T>{
    private T data = null;
    private List<Node> children = new ArrayList<>();
    private Node parent = null;
    private int type;

    public Node(T data, int type) {
        this.data = data;
        this.type = type;
    }
    
    public String toString() {
        return data.toString();
    }
    
    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data, int type) {
        Node<T> newChild = new Node<>(data, type);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<Node> children) {
        for(Node t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<Node> getChildren() {
        return children;
    }
    
    public Node<T> getChild(T data) {
        int index = -1;
        for (Node child : children) {
            if (child.getData().equals(data))
                index = children.indexOf(child);
        }
        if(index != -1)
            return children.get(index);
        return null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
    
    public int hadThisChild(T data) {
       int index = -1;
       for (Node child : children) {
           if (child.getData().equals(data)) {
               index = children.indexOf(child);
           }
       }
       return index;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
