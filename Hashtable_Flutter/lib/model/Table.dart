import 'Node.dart';

class Table {
  List<Node?> _nodes = [];

  Table(size){
    _nodes = List<Node?>.filled(size, null);
  }
  void setNodes(List<Node> nodes) => _nodes = nodes;

  List<Node?> getNodes() => _nodes;
}
