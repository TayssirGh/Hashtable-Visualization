package com.example.demo2.model;

public class Node {
    private String value ;
    private Node next;

    public Node() {
    }

    public Node(String value){
        this.value = value;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
