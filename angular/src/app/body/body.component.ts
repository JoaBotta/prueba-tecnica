import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent {

  @Input() collapsed = true;
  @Input() screenWidth = 0;
  getBodyClass(): string {
    let styleClass = '';
    if (this.screenWidth <= 768 && this.screenWidth > 0) {
      // En pantallas pequeñas, el body debería ocupar todo el ancho si el sidenav está oculto
      styleClass = this.collapsed ? 'body-md-screen' : 'body-full-width';
    } else {
      // En pantallas grandes, manejar el colapso
      styleClass = this.collapsed ? 'body-trimmed' : 'body';
    }
    return styleClass;
  }
  

}
