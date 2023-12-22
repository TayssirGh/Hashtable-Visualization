package tn.usousse.eniso.ia1.stage.example.presentation.controller;

import tn.usousse.eniso.ia1.stage.example.model.Table;
import tn.usousse.eniso.ia1.stage.example.presentation.view.HashtableDrawComponent;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Presentation {
    private int size;


    public void showPresentation() {
        JFrame f = new JFrame();
        f.setTitle("SWING");
        Service service = new Service(new Table(10));
        HashtableDrawComponent drawComponent = new HashtableDrawComponent();
        drawComponent.setModel(service.getTable());
//        creating the header
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("file");
        JMenu help = new JMenu("help");
        JMenuItem about = new JMenuItem("about");
        JMenuItem n = new JMenuItem("size ");
        JMenuItem addName = new JMenuItem("add");
        menu.add(n);
        menu.add(addName);
        help.add(about);
        menuBar.add(menu);
        menuBar.add(help);
        f.setJMenuBar(menuBar);
        f.add(drawComponent);
        f.setVisible(true);


//            creating the dialogs
        n.addActionListener(e -> {
            String sizeText = JOptionPane.showInputDialog(f, "Size: ");
            if (sizeText != null) {
                try {
                    this.size = Integer.parseInt(sizeText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Aib Alik", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Table table = new Table(size);
                service.setTable(table);
                drawComponent.setModel(table);

            }
        });
        about.addActionListener(e -> JOptionPane.showMessageDialog(f, "Tayssir"));
        addName.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(f, "Name");
            if (name != null) {
                service.add(name);
                int index = service.hash(name);
                drawComponent.setIndex(index);
                drawComponent.setModel(drawComponent.getModel());
            }
        });
        drawComponent.mouseAction();
        drawComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String delName = drawComponent.getDelName();
                if (e.getX()<=drawComponent.getxMax() && e.getX()>= drawComponent.getxMin()&& e.getY()>=drawComponent.getyMin() && e.getY()<=drawComponent.getyMax()){
                    if (delName != null) {
                        if (drawComponent.isConfirmation()) {
                            service.remove(delName);
                            drawComponent.setModel(drawComponent.getModel());
                            drawComponent.repaint();
                        }
                    }
                }

            }
        });

        f.pack();
        f.setSize(700, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        System.out.println();
    }
}
