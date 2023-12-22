import {Table} from "../../dto/Table";
import {Node} from "../../dto/Node";

export class Draw{

  index : number = 0;
  added : boolean = false;

  nodeMap : Map<string, number[]> = new Map<string, number[]>();

  public  setAdded(test : boolean){
    this.added = test;
  }
  public setIndex(index : number){
    this.index = index;
  }

  public getNodeMap(){
    return this.nodeMap
  }


  public drawNode(context : CanvasRenderingContext2D, nodeX: number, nodeY: number, n: Node, i: number, k:number){
    if(n.next == null && i == this.index && this.added){
      setTimeout(() => {
        context.beginPath();
        context.moveTo(nodeX-40, nodeY+60);
        context.lineTo(nodeX , nodeY+60);
        context.stroke();
        context.fillStyle = 'orange'
        context.fillRect(nodeX, nodeY + 10, 100, 100);
        context.strokeRect(nodeX, nodeY + 10, 100, 100);
        context.font = "16px Arial";
        context.fillStyle= 'black';
        context.fillText(n.value, nodeX + 20, nodeY + 60);
      }, this.index * 3000 + 1500*k);
    }
    else if(this.index ==i && this.added){
      context.beginPath();
      context.moveTo(nodeX-40, nodeY+60);
      context.lineTo(nodeX , nodeY+60);
      context.stroke();
      context.strokeRect(nodeX, nodeY + 10, 100, 100);
      setTimeout(() =>{

        context.fillStyle = 'orange'
        context.fillRect(nodeX, nodeY + 10, 100, 100)
        context.strokeRect(nodeX, nodeY + 10, 100, 100);

      }, 2000*k + 1000)
      setTimeout(()=>{
        context.fillStyle = 'white'
        context.fillRect(nodeX, nodeY + 10, 100, 100)
        context.strokeRect(nodeX, nodeY + 10, 100, 100);
        context.font = "16px Arial";
        context.fillStyle= 'black';
        context.fillText(n.value, nodeX + 20, nodeY + 60);
      }, 3000*k +1000)
    }
    else {
      context.beginPath();
      context.moveTo(nodeX-40, nodeY+60);
      context.lineTo(nodeX , nodeY+60);
      context.stroke();
      context.strokeRect(nodeX, nodeY + 10, 100, 100);
      context.font = "16px Arial";
      context.fillStyle= 'black';
      context.fillText(n.value, nodeX + 20, nodeY + 60);
    }

    let key = n.value;
    let value = [nodeX, nodeX + 100, nodeY, nodeY + 110];
    this.nodeMap.set(key, value)
  }
  public drawMsalha(context : CanvasRenderingContext2D, nodeX: number, nodeY : number){
      context.beginPath();
      context.moveTo(nodeX-40, nodeY+60);
      context.lineTo(nodeX , nodeY+60);
      context.stroke();
      context.beginPath();
      context.moveTo(nodeX, nodeY+30);
      context.lineTo(nodeX , nodeY+90);
      context.stroke();
      context.moveTo(nodeX, nodeY+45);
      context.lineTo(nodeX+10 , nodeY+35);
      context.stroke();
      context.moveTo(nodeX, nodeY+60);
      context.lineTo(nodeX+10 , nodeY+50);
      context.stroke();
      context.moveTo(nodeX, nodeY+75);
      context.lineTo(nodeX+10 , nodeY+65);
      context.stroke();

  }
  public draw(context : CanvasRenderingContext2D, model : Table){
    console.log(this.index)
    context.clearRect(0, 0, context.canvas.width, context.canvas.height);
    let caseSize = 150;
    let tableSize = model.nodes.length;
    let startX = context.canvas.width/ 2 - 800;
    let startY = context.canvas.height / 6 -100 ;
    let k = 0;
    for (let i = 0; i<tableSize; i++){
      if(this.index>=i && this.added ){

        setTimeout(()=>{
          startY = startY + caseSize
          context.font = "24px Ariel"
          context.fillStyle = 'green'
          context.strokeRect(startX,startY-(tableSize+1)*caseSize,caseSize,caseSize);
          context.fillText(i.toString(),startX-20, startY + 80 -(tableSize+1)*caseSize,caseSize)
          context.fillRect(startX,startY-(tableSize+1)*caseSize,caseSize,caseSize)
        }, i*1000+1000)
        setTimeout(()=>{

          context.strokeRect(startX,startY-(tableSize+1)*caseSize,caseSize,caseSize);
          context.fillStyle = "white"

          context.fillRect(startX,startY-(tableSize+1)*caseSize,caseSize,caseSize)
        }, i*2000+1000)

      }
      context.strokeRect(startX,startY,caseSize,caseSize);
      startY = startY + caseSize
      let n : Node |null = model.nodes[i]
      let nodeX = startX + caseSize + 40;
      let nodeY = startY - caseSize;

      while(n!= null ){
        this.drawNode(context, nodeX, nodeY,n, i, k)
        nodeX+=caseSize-10  ;
        n = n.next as Node
        k++;

      }
      if(this.index == i && this.added){
        setTimeout(()=>{
          this.drawMsalha(context, nodeX, nodeY)
        },i*4000 +k*1000)
      }
      else {
        this.drawMsalha(context, nodeX, nodeY)
      }

    }
    this.added = false

  }

}
