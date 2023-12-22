package com.example.demo2.service;

import com.example.demo2.model.Node;
import com.example.demo2.model.Table;

public class Service {
    private Table table;

    public Service(Table t){

        this.table = t;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean exists(String value, Table table){
        int index = hash(value);
        Node node = table.getNodes()[index];
        while ( node != null){
            if (node.getValue().equals(value)){
                return true;
            }
            node = node.getNext();
        }
        return false;
    }
    public int hash(String value){
        int index =0;
        for(int j = 0; j<value.length(); j++){
            index+= value.charAt(j);
            index = index * 31;
        }
        return Math.abs(index%table.getNodes().length);
    }


    public boolean add(String value) {
        if (exists(value, this.table)) {
            return false;
        }

        int index = hash(value);
        Node node = new Node(value);

        if (this.table.getNodes()[index] == null) {
            this.table.getNodes()[index] = node;

        }

        else {
            Node currentNode = this.table.getNodes()[index];
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(node);
        }

        return true;
    }
    public boolean remove(String value)  {
        int i = hash(value);
        if (table.getNodes()[i] == null){
            return false;
        }
        Node p = table.getNodes()[i];
        Node q = null;
        while(p!= null){
            if(value.equals(p.getValue())){
                if(q == null){
                    table.getNodes()[i] = p.getNext();
                }
                else {
                    q.setNext(p.getNext());
                }
                return true;
            }
            q = p;
            p = p.getNext();
        }

        return false;
    }
    public Table list(){
        Table list = new Table(table.getNodes().length);
        for(int i = 0; i<table.getNodes().length; i++){
            Node linkedList = table.getNodes()[i];
            list.getNodes()[i] = linkedList;
        }
        return list;
    }
}
