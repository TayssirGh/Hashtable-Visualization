import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {PresentationComponent} from "./presentation.component";
@NgModule({
  imports: [
    RouterModule.forChild([
      {path : '', component :PresentationComponent }
    ])],
  exports :[RouterModule]
})
export class PresentationRoutingModule{}
