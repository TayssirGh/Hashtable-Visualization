package tn.usousse.eniso.ia1.stage.example.service;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;

import java.lang.Math;
public class Service {
    private final Table table;

    public Service(Table t){

        this.table = t;
    }

    public boolean exists(String value, Table table){
        int i = hash(value);
        Node n = table.getNodes()[i];
        while ( n != null){
            if (n.getValue().equals(value)){
                return true;
            }
            n = n.getNext();
        }
        return false;
    }
    public int hash(String value){
        int i =0;
        for(int j = 0; j<value.length(); j++){
            i+=(int) value.charAt(j);
        }
        return Math.abs(i%3);
    }


    public boolean add(String value) {
        if (exists(value, this.table)) {
            return false;
        }

        int i = hash(value);
        Node n = new Node(value);

        if (this.table.getNodes()[i] == null) {
            this.table.getNodes()[i] = n;

        }

        else {
            Node currentNode = this.table.getNodes()[i];
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(n);
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
