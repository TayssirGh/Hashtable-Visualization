import 'dart:js_interop';

import 'package:demo/presentation/controller/AppController.dart';
import 'package:demo/presentation/view/HashtableDrawComponent.dart';
import 'package:demo/service/Service.dart';
import 'package:flutter/material.dart';
import 'package:demo/model/Table.dart' as t;

class Presentation extends StatefulWidget {
  const Presentation({super.key});

  @override
  State<Presentation> createState() => _PresentationState();
}

class _PresentationState extends State<Presentation> {
  TextEditingController nameController = TextEditingController();
  TextEditingController sizeController = TextEditingController();
  String? sizeText;
  String? name;
  int? size = 0;
  HashtableDrawComponent drawComponent = HashtableDrawComponent();
  Service service = Service(t.Table(10));
  AppController controller = AppController();
  bool paint = false;
  GlobalKey _customPaintKey = GlobalKey();
  String? deleteName;
  Map<String, List<double>> nodeMap = {};
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(

        appBar: AppBar(
          title: const Text("Hashtable",
          style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),),
          backgroundColor: Colors.orangeAccent[100],
          actions: [
            PopupMenuButton<int>(
                itemBuilder: (context) => [
                  const PopupMenuItem(
                    value: 1,
                    child: Row(
                      children: [
                        SizedBox(
                          width: 10,
                        ),
                        Text(
                          "New",
                          style: TextStyle(fontSize: 18),
                        )
                      ],
                    ),
                  ),
                  const PopupMenuItem(
                    value: 2,
                    child: Row(
                      children: [
                        SizedBox(
                          width: 10,
                        ),
                        Text(
                          "add",
                          style: TextStyle(fontSize: 18),
                        )
                      ],
                    ),
                  ),

                ],
                onSelected: (value) {
                  if (value == 1) {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return AlertDialog(
                          // title: const Text('Enter the size'),
                          content: TextField(
                            controller: sizeController,
                            decoration: const InputDecoration(
                              border: InputBorder.none,
                              labelText: "Enter the size",
                            ),
                            onChanged: (value) {
                              setState(() {
                                sizeText = value;
                              });
                            },
                          ),
                          actions: [
                            MaterialButton(
                              textColor: Colors.black,
                              onPressed: () {
                                try {
                                  size = int.parse(sizeController.text);
                                  t.Table table = t.Table(size);

                                  controller.getService().setTable(table);

                                  drawComponent.setModel(table);
                                  drawComponent.setNodeMap({});
                                  setState(() {

                                    paint = size.isDefinedAndNotNull;
                                    _customPaintKey = GlobalKey();
                                  });
                                  Navigator.pop(context);
                                } catch (e) {
                                  showDialog(
                                    context: context,
                                    builder: (BuildContext context) {
                                      return const AlertDialog(
                                        content: Text(
                                          "SIZE !!",
                                          style: TextStyle(fontSize: 24),
                                        ),
                                      );
                                    },
                                  );
                                }
                              },
                              child: const Text('ACCEPT'),
                            ),
                          ],
                        );
                      },
                    );
                  }
                  if (value == 2) {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return AlertDialog(
                          // title: const Text('Enter the size'),
                          content: TextField(
                            controller: nameController,
                            decoration: const InputDecoration(
                              border: InputBorder.none,
                              labelText: "Enter the name",
                            ),
                            onChanged: (value) {
                              setState(() {
                                name = value;

                                // print(name);
                              });
                            },
                          ),
                          actions: [
                            MaterialButton(
                              color: Colors.orangeAccent[50],
                              textColor: Colors.black,
                              onPressed: () {
                                if (size == 0) {
                                  showDialog(
                                    context: context,
                                    builder: (BuildContext context) {
                                      return const AlertDialog(
                                        content: Text(
                                          "Enter the size first !",
                                          style: TextStyle(fontSize: 24),
                                        ),
                                      );
                                    },
                                  );
                                } else {
                                  // service.add(nameController.text);

                                  controller.getService().add(nameController.text);
                                  drawComponent.setModel(controller.getService().getTable());
                                  setState(() {
                                    paint = size.isDefinedAndNotNull;
                                    _customPaintKey = GlobalKey();
                                  });

                                  Navigator.pop(context);
                                }
                              },
                              child: const Text('ACCEPT'),
                            ),
                          ],
                        );
                      },
                    );
                  }
                },
                child: const Icon(Icons.menu_open,
                size: 40,)),
          ],
        ),
        body:
            SizedBox(
              height: MediaQuery.of(context).size.height,
              width: MediaQuery.of(context).size.width,

              child: GestureDetector(
                onTapDown: (TapDownDetails details) {
                  double xClick = details.globalPosition.dx;
                  double yClick = details.globalPosition.dy;
                  nodeMap = drawComponent.getMap();
                  for (var entry in nodeMap.entries) {
                    String nodeName = entry.key;
                    double nodeX = entry.value[0];
                    double nodeY = entry.value[1];
                    double nodeWidth = entry.value[2] - nodeX;
                    double nodeHeight = entry.value[3] - nodeY;
                      if (xClick >= nodeX && xClick <= nodeX + nodeWidth &&
                          yClick >= nodeY +54 && yClick <= nodeY+nodeHeight +53 ) {
                        deleteName = nodeName;
                      }
                    }
                    if(deleteName !=null){
                      showDialog(
                        context: context,
                        builder: (BuildContext context) {
                          return AlertDialog(
                            title: const Text(
                              "Confirmation",
                              style: TextStyle(fontSize: 24),
                            ),
                            content: RichText(
                              text: TextSpan(
                                style: const TextStyle(fontSize: 16, color: Colors.black),
                                children: [
                                  const TextSpan(text: "Are you sure you want to delete "),
                                  TextSpan(
                                    text: deleteName!,
                                    style: const TextStyle(fontWeight: FontWeight.bold ),
                                  ),
                                  const TextSpan(text: " ?"),
                                ],
                              ),
                            ),
                            actions: <Widget>[
                              TextButton(
                                child: const Text(
                                  "Cancel",
                                  style: TextStyle(fontSize: 16),
                                ),
                                onPressed: () {
                                  Navigator.of(context).pop(false);
                                },
                              ),
                              TextButton(
                                child: const Text(
                                  "Confirm",
                                  style: TextStyle(fontSize: 16),
                                ),
                                onPressed: () {
                                  controller.getService().remove(deleteName!);
                                  drawComponent.setModel(controller.getService().getTable());
                                  nodeMap.removeWhere((key, value) => key == deleteName);
                                  setState(() {
                                    paint = size.isDefinedAndNotNull;
                                    _customPaintKey = GlobalKey();
                                    nodeMap = drawComponent.getMap();
                                    print("new node :  $nodeMap");
                                  });
                                  Navigator.of(context).pop(true);
                                  deleteName = null;
                                },
                              ),
                            ],
                          );
                        },
                      );

                    }



                },
                  child: CustomPaint(
                  key: _customPaintKey,
                  painter: paint? drawComponent : null ,
                ),
              ),
            ),

      ),

    );
  }
}
