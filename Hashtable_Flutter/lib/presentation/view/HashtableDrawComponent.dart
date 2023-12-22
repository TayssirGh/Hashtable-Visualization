import 'package:demo/model/Table.dart' as t;
import 'package:flutter/material.dart';

import '../../model/Node.dart';

class HashtableDrawComponent extends CustomPainter {
  int _index = 0;

  Map<String, List<double>> _nodeMap = {};

  t.Table _model = t.Table(10);

  HashtableDrawComponent();

  t.Table getModel() => _model;

  t.Table setModel(t.Table model) => _model = model;

  void setIndex(index) => _index = _index;

  Map<String, List<double>> getMap() => _nodeMap;

  void setNodeMap(Map<String, List<double>> nodeMap) => _nodeMap = nodeMap;

  void drawNode(
      Canvas canvas, double nodeX, double nodeY, double caseSize, Node n) {
    Paint linePaint = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX - 40, nodeY + 30),
      Offset(nodeX, nodeY + 30),
      linePaint,
    );

    Rect nodeRect = Rect.fromLTRB(
      nodeX,
      nodeY,
      nodeX + caseSize - 20,
      nodeY + caseSize - 40,
    );

    Paint nodePaint = Paint()
      ..color = Colors.white
      ..style = PaintingStyle.fill;
    canvas.drawRect(nodeRect, nodePaint);
    final strokePaint = Paint()
      ..color = Colors.black
      ..style = PaintingStyle.stroke
      ..strokeWidth = 2;
    canvas.drawRect(nodeRect, strokePaint);

    TextSpan textSpan = TextSpan(
      text: n.value.toString(),
      style: const TextStyle(color: Colors.black),
    );
    TextPainter textPainter = TextPainter(
      text: textSpan,
      textDirection: TextDirection.ltr,
    );
    textPainter.layout();
    double textX = nodeX + 10;
    double textY = nodeY + 20;
    Offset textOffset = Offset(textX, textY);
    textPainter.paint(canvas, textOffset);
    Paint connectionLinePaint = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX + caseSize - 20, nodeY + 30),
      Offset(nodeX + caseSize + 20, nodeY + 30),
      connectionLinePaint,
    );
    _nodeMap[n.value.toString()] = [
      nodeX,
      nodeY,
      nodeX + caseSize - 20,
      nodeY + caseSize - 40
    ];

  }

  void drawMsalha(Canvas canvas, double nodeX, double lineY) {
    Paint linePaint = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX, lineY + 30),
      Offset(nodeX, lineY - 20),
      linePaint,
    );
    Paint line1 = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX, lineY - 5),
      Offset(nodeX + 10, lineY - 15),
      line1,
    );
    Paint line2 = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX, lineY + 5),
      Offset(nodeX + 10, lineY - 5),
      line2,
    );
    Paint line3 = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX, lineY + 15),
      Offset(nodeX + 10, lineY + 5),
      line3,
    );
    Paint line4 = Paint()
      ..color = Colors.black
      ..strokeWidth = 3;

    canvas.drawLine(
      Offset(nodeX, lineY + 25),
      Offset(nodeX + 10, lineY + 15),
      line4,
    );
  }

  @override
  void paint(Canvas canvas, Size size) {
    int tableSize = _model.getNodes().length;
    double caseSize = 100;
    double tableHeight = caseSize * tableSize;
    double startX = (size.width / 2) - 250;
    double startY = ((size.height - tableHeight) / 2);
    for (int i = 0; i < tableSize; i++) {
      final paint = Paint()
        ..color = Colors.white
        ..style = PaintingStyle.fill;
      double y = startY + i * caseSize;
      Rect rect = Rect.fromLTRB(startX, y, startX + caseSize, y + caseSize);

      canvas.drawRect(rect, paint);
      final strokePaint = Paint()
        ..color = Colors.black
        ..style = PaintingStyle.stroke
        ..strokeWidth = 2;
      canvas.drawRect(rect, strokePaint);
      double nodeX = startX + caseSize + 40;
      Node? n = _model.getNodes()[i];
      while (n != null) {
        double nodeY = y + caseSize - 75;
        drawNode(canvas, nodeX, nodeY, caseSize, n);
        n = n.getNext();
        nodeX += caseSize + 20;
      }
      double lineY = y + caseSize / 2;
      drawMsalha(canvas, nodeX, lineY);
    }
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return true;
  }
}
