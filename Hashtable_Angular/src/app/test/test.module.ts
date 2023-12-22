import {NgModule} from "@angular/core";
import {MenubarModule} from "primeng/menubar";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {TestRoutingModule} from "./test-routing.module";
import {TestComponent} from "./test.component";

@NgModule({
  imports: [
    TestRoutingModule,
    ButtonModule,
    InputTextModule,
    DialogModule
  ],
  exports: [
    TestComponent
  ],

  declarations: [TestComponent]
})
export class TestModule{}
