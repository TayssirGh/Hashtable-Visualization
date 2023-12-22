import '../model/Node.dart';
import '../model/Table.dart';

class Service {
  Table _table;

  Service(this._table);

  Table getTable() => _table;

  void setTable(Table table) => _table = table;

  int hashF(String value) {
    int index = 0;
    for (int i = 0; i < value.length; i++) {
      index += value.codeUnitAt(i);
      index = index * 31;
    }
    index = (index/31) as int;
    return (index % _table.getNodes().length).abs();
  }

  bool exists(String value, Table table) {
    int index = hashF(value);
    Node? node = table.getNodes()[index];
    while (node != null) {
      if (node.getValue().compareTo(value) == 0) {
        return true;
      }
      node = node.getNext();
    }
    return false;
  }

  bool add(String value) {
    if (exists(value, _table)) {
      return false;
    }

    int index = hashF(value);
    Node node = Node(value);

    if (_table.getNodes()[index] == null) {
      _table.getNodes()[index] = node;
    } else {
      Node? currentNode = _table.getNodes()[index];
      while (currentNode?.getNext() != null) {
        currentNode = currentNode?.getNext();
      }
      currentNode?.setNext(node);
    }

    return true;
  }

  bool remove(String value) {
    int i = hashF(value);
    if (_table.getNodes()[i] == null) {
      return false;
    }
    Node? p = _table.getNodes()[i];
    Node? q;
    while (p != null) {
      if (value.compareTo(p.getValue()) == 0) {
        if (q == null) {
          _table.getNodes()[i] = p.getNext();
        } else {
          q.setNext(p.getNext());
        }
        return true;
      }
      q = p;
      p = p.getNext();
    }

    return false;
  }

  Table list() {
    Table list = Table(_table.getNodes());
    for (int i = 0; i < _table.getNodes().length; i++) {
      Node? linkedList = _table.getNodes()[i];
      list.getNodes()[i] = linkedList;
    }
    return list;
  }
}
