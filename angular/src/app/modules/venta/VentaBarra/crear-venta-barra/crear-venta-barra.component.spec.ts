import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearVentaBarraComponent } from './crear-venta-barra.component';

describe('CrearVentaBarraComponent', () => {
  let component: CrearVentaBarraComponent;
  let fixture: ComponentFixture<CrearVentaBarraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrearVentaBarraComponent]
    });
    fixture = TestBed.createComponent(CrearVentaBarraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
