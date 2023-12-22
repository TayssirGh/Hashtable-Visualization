
import 'package:demo/model/Table.dart' as t;
import 'package:demo/service/Service.dart';

class AppController {

  t.Table _model = t.Table(10) ;
  Service _service = Service(t.Table(10));

  AppController();

  Service getService() => _service;

  void setService(Service service) => _service = service;

  t.Table getModel() => _model;

  t.Table setModel(t.Table table) => _model = table;
}
