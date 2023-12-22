import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router';


@NgModule({
  imports: [RouterModule.forRoot([
    {path : '', loadChildren: () => import('./presentation/presentation.module').then(m =>m.PresentationModule)},
    {path : 'test', loadChildren: () => import('./test/test.module').then(m =>m.TestModule)}
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
