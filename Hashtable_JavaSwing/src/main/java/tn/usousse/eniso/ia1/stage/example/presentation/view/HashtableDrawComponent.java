package tn.usousse.eniso.ia1.stage.example.presentation.view;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HashtableDrawComponent extends JComponent {
    private String delName;
    int nodeY;
    int xMax;
    int xMin;
    int yMax;
    int yMin;
    boolean confirmation = false;

    public boolean isConfirmation() {
        return confirmation;
    }

    public int getxMax() {
        return xMax;
    }

    public int getxMin() {
        return xMin;
    }

    public int getyMax() {
        return yMax;
    }

    public int getyMin() {
        return yMin;
    }

    public HashtableDrawComponent() {

    }
    public void mouseAction(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int tableSize = model.getNodes().length;
                int caseSize = 100;
                int startX = (getWidth() - caseSize * 2 - 100) / 2;
                int startY = (getHeight() - caseSize * tableSize) / 2;

                for (int i = 0; i < tableSize; i++) {
                    int yNode = startY + i * caseSize;
                    Node node = model.getNodes()[i];

                    int nodeX = startX + caseSize + 40;
                    while (node != null) {
                        int nodeY = yNode + caseSize - 75;
                        xMin = nodeX;
                        xMax = nodeX + caseSize - 20;
                        yMax = nodeY + caseSize - 40;
                        yMin = nodeY;
                        if (x >= xMin && x <= xMax && y >= nodeY && y <= yMax) {
                            String name = node.getValue();
                            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to remove this node ? " );
                            if (confirm == JOptionPane.YES_OPTION) {
                                confirmation = true;
                                delName = name;
                            }
                            return;
                        }

                        nodeX += caseSize + 20;
                        node = node.getNext();
                    }
                }
            }
        });
    }


    public String getDelName() {
        return delName;
    }

    private Table model = new Table(10);



    public Table getModel() {

        return model;
    }

    public void setModel(Table model) {
        this.model = model;
        //SwingUtilities.invokeLater(()->repaint());
        SwingUtilities.invokeLater(this::repaint);
    }




    public void setIndex(int index) {

    }
    private void drawNode(Graphics2D g2d, int nodeX , int nodeY, int caseSize, Node n){
        g2d.drawLine(nodeX,nodeY+25,nodeX - 40,nodeY+25);
        g2d.drawRect(nodeX, nodeY, caseSize - 20, caseSize - 40);
        g2d.drawString(n.getValue(), nodeX + 25, nodeY + caseSize - 20);
        g2d.drawLine(nodeX+caseSize-17,nodeY+25,nodeX +caseSize +20,nodeY+25);
    }
    private void drawMsalha(Graphics2D g2d,int lineY, int nodeX){
        g2d.drawLine(nodeX, lineY + 20, nodeX, lineY - 20);
        g2d.drawLine(nodeX, lineY + 10, nodeX + 10, lineY + 20);
        g2d.drawLine(nodeX, lineY - 10, nodeX+ 10, lineY);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        g2d.setPaint(Color.WHITE);
        g2d.fill(getBounds());

        int tableSize = model.getNodes().length;
        System.out.println("table size : " + tableSize);

        int caseSize = 100;
        int tableHeight = caseSize * tableSize;

        int startX = (getWidth() - caseSize*2-100) / 2;
        int startY = (getHeight() - tableHeight) / 2;
        for (int i = 0; i < tableSize; i++) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            int y = startY + i * caseSize;

            g2d.drawRect(startX, y, caseSize, caseSize);
            Node n = model.getNodes()[i];
            boolean test = false;
            int nodeX = startX + caseSize + 40;


            while (n != null) {
                test = true;
                nodeY = y + caseSize - 75;
                drawNode(g2d, nodeX,nodeY,caseSize,n);
                nodeX += caseSize + 20;
                n = n.getNext();
            }

            if (test){
                int lineY = y + caseSize / 2;
                drawMsalha(g2d,lineY,nodeX);
            }


        }

    }
}
