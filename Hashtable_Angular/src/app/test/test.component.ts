import { Component } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
})
export class TestComponent {
  visible: boolean = false;

  showDialog() {
    this.visible = true;
  }

}
