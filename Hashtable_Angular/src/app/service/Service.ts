import {Table} from "../dto/Table";
import {Node} from  "../dto/Node"
export class Service{
  private _table: Table;
  constructor(t: Table) {
    this._table = t;
  }

  get table(): Table {
    return this._table;
  }

  set table(value: Table) {
    this._table = value;
  }
  public exists(value: string, table: Table): boolean {
    const index: number = this.hash(value);
    let node: Node | null = table.nodes[index];

    while (node !== null) {
      if (node.value === value) {
        return true;
      }
      node = node.next;
    }

    return false;
  }
  public hash(value: string): number {
    let index: number = 0;

    for (let j = 0; j < value.length; j++) {
      index += value.charCodeAt(j);
      index = index * 31;
    }

    return Math.abs(index % this._table.nodes.length);
  }
  public add(value: string): boolean{
    if(this.exists(value,this._table)){
      return false;
    }
    const index: number = this.hash(value);
    const node: Node = new Node(value);
    if (this.table.nodes[index] == null) {
      this.table.nodes[index] = node;
    }
    else {
      let currentNode: Node |null = this.table.nodes[index] as Node;
      while (currentNode.next != null) {
        currentNode = currentNode.next;
      }
      currentNode.next = node;
    }
    return true
  }
  public remove(value: string): boolean {
    const i: number = this.hash(value);
    if (this._table.nodes[i] == null) {
      return false;
    }
    let p: Node | null = this.table.nodes[i];
    let q: Node | null = null;
    while (p !== null) {
      if (value === p.value) {
        if (q === null) {
          this._table.nodes[i] = <Node>p.next;
        } else {
          q.next = p.next;
        }
        return true;
      }
      q = p;
      p = p.next;
    }

    return false;
  }

}
