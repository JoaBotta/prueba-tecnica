import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImpresoraTermicaService {
  imprimirRecibo(texto: string): void {
    const ventana = window.open('', '_blank', 'width=300,height=400');
    if (ventana) {
      ventana.document.write(`<pre>${texto}</pre>`);
      ventana.document.close();
      ventana.print();
      ventana.close();
    } else {
      console.error('No se pudo abrir la ventana de impresión.');
    }
  }
}
