import {Node} from "./Node";

export class Table{
  private _nodes: (Node | null)[] = [];
  constructor(size : number) {
    this._nodes = new Array(size).fill(null);
  }

  get nodes(): (Node | null)[] {
    return this._nodes;
  }

  set nodes(value: Node[]) {
    this._nodes = value;
  }
}
