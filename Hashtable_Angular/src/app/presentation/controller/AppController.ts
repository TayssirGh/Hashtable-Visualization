import {Table} from "../../dto/Table";
import {Service} from "../../service/Service";

export class AppController{
  private _model: Table = new Table(10);
  private _service : Service = new Service(this._model);
  constructor() {
  }


  get model(): Table {
    return this._model;
  }

  set model(value: Table) {
    this._model = value;
  }

  get service(): Service {
    return this._service;
  }

  set service(value: Service) {
    this._service = value;
  }
}
