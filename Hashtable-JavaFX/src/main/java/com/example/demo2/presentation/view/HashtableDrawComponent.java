package com.example.demo2.presentation.view;

import com.example.demo2.model.Node;
import com.example.demo2.model.Table;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HashtableDrawComponent {
    private String delName;
    private Boolean delTest = false;
    private boolean isAdded = false;
    private int index;
    private boolean confirmed = false;
    private Table model = new Table(10);

    public HashtableDrawComponent() {

    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getDelTest() {
        return delTest;
    }

    public String getDelName() {
        return delName;
    }

    public Table getModel() {

        return model;
    }

    public void setModel(Table model) {
        this.model = model;
//        Platform.runLater(this::paintComponent);
    }

    public void setAdded(boolean added) {
        this.isAdded = added;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Rectangle drawNode(Pane pane, Node n, int nodeX, int nodeY, int caseSize) {
        Rectangle nodeRect = new Rectangle(nodeX, nodeY, caseSize - 20, caseSize - 40);
        nodeRect.setFill(Color.WHITE);
        nodeRect.setStroke(Color.BLACK);
        nodeRect.setStrokeWidth(1);
        pane.getChildren().add(nodeRect);

        Text nodeText = new Text(n.getValue());
        nodeText.setX(nodeX + 15);
        nodeText.setY(nodeY + 30);
        pane.getChildren().add(nodeText);

        Line line = new Line(nodeX, nodeY + 25, nodeX - 40, nodeY + 25);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);
        pane.getChildren().add(line);

        Line connectionLine = new Line(nodeX + caseSize - 17, nodeY + 25, nodeX + caseSize + 20, nodeY + 25);
        connectionLine.setStroke(Color.BLACK);
        connectionLine.setStrokeWidth(1);
        pane.getChildren().add(connectionLine);
        nodeRect.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                nodeRect.setFill(Color.GRAY);
                delTest = true;
                delName = n.getValue();
                System.out.println(delName);
                if (confirmed) {
                    isAdded = false;

                } else {
                    nodeRect.setFill(Color.WHITE);

                }


            }
        });


        nodeRect.setFill(Color.WHITE);
        delTest = false;
        return nodeRect;
    }

    public void drawMsalha(Pane pane, int nodeX, int lineY) {
        Line line1 = new Line(nodeX, lineY + 20, nodeX, lineY - 20);
        line1.setStroke(Color.BLACK);
        line1.setStrokeWidth(1);
        pane.getChildren().add(line1);

        Line line2 = new Line(nodeX, lineY + 10, nodeX + 12, lineY - 2);
        line2.setStroke(Color.BLACK);
        line2.setStrokeWidth(1);
        pane.getChildren().add(line2);

        Line line3 = new Line(nodeX, lineY, nodeX + 12, lineY - 12);
        line3.setStroke(Color.BLACK);
        line3.setStrokeWidth(1);
        pane.getChildren().add(line3);
        Line line4 = new Line(nodeX, lineY - 10, nodeX + 12, lineY - 22);
        line4.setStroke(Color.BLACK);
        line4.setStrokeWidth(1);
        pane.getChildren().add(line4);

    }

    public void animateGreen(Pane pane, int startX, Rectangle cell, FillTransition fillG, int y, int i) {
        Text text = new Text(String.valueOf(i));
        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds((i + 1)), event -> {
                    text.setX(startX - 50);
                    text.setY(y + 50);
                    pane.getChildren().add(text);
                    cell.setFill(Color.GREEN);
                    fillG.setFromValue(Color.GREEN);
                    fillG.setToValue(Color.WHITE);
                    fillG.setShape(cell);
                    fillG.play();


                })


        );
        timeline.play();
    }

    public void animateOrange(int i, int j, Rectangle rec) {
        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds((j + i + 1)), event -> {
                    FillTransition fillO = new FillTransition();
                    rec.setFill(Color.ORANGE);
                    fillO.setFromValue(Color.ORANGE);
                    fillO.setToValue(Color.WHITE);
                    fillO.setShape(rec);
                    fillO.play();

                })


        );
        timeline.play();
    }

    public Pane paintComponent() {
        Pane pane = new Pane();

        int tableSize = model.getNodes().length;
        int caseSize = 100;
        int tableHeight = caseSize * tableSize;

        int startX = (int) (pane.getWidth() - caseSize * 2 - 100) / 2 + 500;
        int startY = (int) (pane.getHeight() - tableHeight) / 2 + 300;
        FillTransition fillG = new FillTransition();

        for (int i = 0; i < tableSize; i++) {
            System.out.println("case : " + i);
            int y = startY + i * caseSize;
            Rectangle cell = new Rectangle(startX, y, caseSize, caseSize);
            cell.setFill(Color.WHITE);
            boolean test = false;
            cell.setStroke(Color.BLACK);
            cell.setStrokeWidth(3);
            pane.getChildren().add(cell);
            Node n = model.getNodes()[i];
            int nodeX = startX + caseSize + 40;
            Rectangle node;
            int j = 0;

            while (n != null) {
                test = true;
                int nodeY = y + caseSize - 75;
                node = drawNode(pane, n, nodeX, nodeY, caseSize);
                nodeX += caseSize + 20;
                n = n.getNext();
                j = j + 1;

                if (index == i && isAdded) {
                    animateOrange(i, j, node);

                }
            }


            if (index >= i && isAdded) {
                animateGreen(pane, startX, cell, fillG, y, i);

            }
            if (test) {
                j = j + 1;
                int lineY = y + caseSize / 2;
                int finalNodeX = nodeX;
                if (index == i) {
                    Timeline timeline;
                    if (confirmed) {
                        timeline = new Timeline(

                                new KeyFrame(Duration.seconds((j * 0.75)), event ->
                                        drawMsalha(pane, finalNodeX, lineY))

                        );
                        confirmed = false;
                    } else {
                        timeline = new Timeline(

                                new KeyFrame(Duration.seconds((i + j + 1)), event ->
                                        drawMsalha(pane, finalNodeX, lineY))

                        );

                    }
                    timeline.play();

                } else {
                    drawMsalha(pane, finalNodeX, lineY);
                }

            }


        }
        isAdded = false;
        return pane;
    }

}
