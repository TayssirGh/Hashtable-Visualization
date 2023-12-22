import {NgModule} from "@angular/core";
import {PresentationRoutingModule} from "./presentation-routing.module";
import {MenubarModule} from "primeng/menubar";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {PresentationComponent} from "./presentation.component";

@NgModule({
  imports: [
    PresentationRoutingModule,
    MenubarModule,
    ButtonModule,
    InputTextModule,
    DialogModule,
    FormsModule,
    ButtonModule,
    NgIf
  ],
    exports: [
        PresentationComponent,
        PresentationComponent
    ],

  declarations: [PresentationComponent,
  PresentationComponent]
})
export class PresentationModule{}
