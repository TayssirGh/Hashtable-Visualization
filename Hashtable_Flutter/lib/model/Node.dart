class Node {
  String value;
  Node? next;

  Node(this.value, [this.next]);

  Node? getNext() {
    return next;
  }

  void setNext(Node? next) {
    this.next = next;
  }

  String getValue() {
    return value;
  }

  void setValue(String value) {
    this.value = value;
  }
}
