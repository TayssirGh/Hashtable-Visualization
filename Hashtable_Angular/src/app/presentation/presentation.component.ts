import {Component, OnInit, Renderer2, ViewChild} from '@angular/core';
import {Table} from '../dto/Table'
import {MenuItem} from "primeng/api";
import {Draw} from "./view/Draw"
import {AppController} from "./controller/AppController";
@Component({
  selector: 'app-hashtable-draw',
  templateUrl: './presentation.component.html',

})
export class PresentationComponent implements OnInit {
  items: MenuItem[] | undefined;
  visibleCreate: boolean = false;
  visibleAdd: boolean = false;
  visibleSizeAlert: boolean = false;
  visibleNameAlert: boolean = false;
  visibleRemove : boolean = false;
  value : string = "";
  name : string = "";
  size : number = 0;
  removedValue : string = "";
  drawComponent : Draw = new Draw()
  controller : AppController = new AppController();

  constructor(private renderer: Renderer2) {}
  @ViewChild('container') container!:any;
  private model : Table = new Table(0);


  createCanvas() {
    let canvas = this.renderer.createElement('canvas');
    canvas.width = window.innerWidth - 30;
    canvas.height = window.innerHeight;
    let ctx = canvas.getContext('2d');
    this.container.nativeElement.innerHTML = '';
    this.container.nativeElement.appendChild(canvas);
    ctx.canvas.addEventListener('click', (event: MouseEvent)=>{
      let map = this.drawComponent?.getNodeMap()
      const rect = ctx.canvas.getBoundingClientRect();
      const mouseX = event.clientX - rect.left;
      const mouseY = event.clientY - rect.top;
      map?.forEach((coordinates, key) => {
        const startX = coordinates[0];
        const endX = coordinates[1];
        const startY = coordinates[2];
        const endY = coordinates[3];

        if (mouseX >= startX && mouseX <= endX && mouseY >= startY && mouseY <= endY) {
          this.visibleRemove = true;
          this.removedValue = key
        }
      });

      })
    if (this.size>0) {
      // this.draw(ctx, this.model);
      this.drawComponent.draw(ctx, this.model)

    }

  }

  ngOnInit(): void {
    this.items = [
      {
        label: 'File',
        icon: 'pi pi-fw pi-file',
        items: [
          {
            label: 'New',
            icon: 'pi pi-fw pi-plus',
            command: () => this.visibleCreate = true


          },
          {
            label: 'Add',
            icon: 'pi pi-fw pi-user',
            command: () => this.visibleAdd = true
          },
          {
            separator: true
          },
          {
            label: 'Clear',
            icon: 'pi pi-fw pi-trash',
            command: () => this.clear()
          },

        ],
      },
      {
        label: 'About',
        icon: 'pi pi-fw pi-comment'
      }
    ]

  }


  create(): void{
    try {
      this.size = parseInt(this.value)
      if(!isNaN(this.size)){
        this.model = new Table(this.size);
        this.drawComponent?.getNodeMap().clear()
        this.createCanvas()
        this.visibleCreate = false
        this.value = ""
      }
      else {
        throw new Error('Invalid input. Please enter a valid number.');
      }
    }
    catch (e) {
      console.error('Error ');
      this.visibleSizeAlert = true;
    }


  }
  clear(){
    this.model = new Table(0)
    this.drawComponent?.getNodeMap().clear()
    this.size = 0
    this.createCanvas()

  }
  add(): void{
    if(this.size == 0 ){
      this.visibleNameAlert = true
    }
    else {
      this.controller.service.table = this.model;
      this.controller.service.add(this.name)
      this.drawComponent.setIndex(this.controller.service.hash(this.name));
      this.drawComponent.setAdded(true);
      this.createCanvas()
      this.visibleAdd = false

      this.name = ""

    }

  }
  close(){
    this.value = ""
    this.name = ""
    this.visibleNameAlert = false
    this.visibleAdd = false
    this.visibleRemove = false
  }
  remove(){
    this.controller.service.remove(this.removedValue);
    this.drawComponent.getNodeMap().delete(this.removedValue)
    this.createCanvas()
    this.visibleRemove = false

  }

}
