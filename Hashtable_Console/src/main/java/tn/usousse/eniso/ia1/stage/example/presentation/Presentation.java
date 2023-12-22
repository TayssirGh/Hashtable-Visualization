package tn.usousse.eniso.ia1.stage.example.presentation;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import java.util.Scanner;

public class Presentation {
    public Presentation() {
    }

    public void console(){
        Scanner sc = new Scanner(System.in);
        System.out.print(">size : ");
    int  size = sc.nextInt();
        sc.nextLine();
    Table table = new Table(size);
        while (true){

        Service service = new Service(table);
        System.out.print(">");
        String c = sc.nextLine();

        if(c.equals("list")){
            Table hashtable = service.list();
            Node[] nodes = hashtable.getNodes();
            for(int i = 0; i<nodes.length; i++){
                Node linkedList = nodes[i];
                System.out.print ("Index " + i + ": ");
                while(linkedList !=null){
                    System.out.print(linkedList.getValue() + "--->" );
                    linkedList = linkedList.getNext();
                }
                System.out.print("null");
                System.out.println();
            }
        }
        else{
            String [] params = c.split(" ");
            String command = params[0];
            String name = params[1];
            switch (command){
                case "add":

                    boolean t1 = service.add(name);
                    System.out.println(t1);
                    break;
                case "remove":

                    t1 = service.remove(name);
                    System.out.println(t1);
                    break;
                case "find":

                    t1 = service.exists(name, table);
                    System.out.println(t1);
                    break;
                case "hash":

                    System.out.println(service.hash(name));
                    break;
                case "break":
                    break;
                default:
                    System.out.println("invalid command");
            }
        }


    }
}}
