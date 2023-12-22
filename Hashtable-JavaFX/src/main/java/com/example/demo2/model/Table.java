package com.example.demo2.model;

public class Table {
    public Table() {
    }

    private Node[] nodes;

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public Table(int size) {
        nodes  = new Node[size];
    }
}
