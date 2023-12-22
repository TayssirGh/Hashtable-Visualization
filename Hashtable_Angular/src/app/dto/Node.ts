export class Node{
  private _value: string ;
  private _next: Node |null ;
  constructor(value: string) {
    this._value = value;
    this._next = null;
  }


  get value(): string {
    return this._value;
  }

  set value(value: string) {
    this._value = value;
  }

  get next(): Node | null {
    return this._next;
  }

  set next(value: Node | null) {
    this._next = value;
  }
}
