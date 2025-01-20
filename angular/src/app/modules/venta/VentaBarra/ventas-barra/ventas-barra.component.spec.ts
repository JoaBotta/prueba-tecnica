import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentasBarraComponent } from './ventas-barra.component';

describe('VentasBarraComponent', () => {
  let component: VentasBarraComponent;
  let fixture: ComponentFixture<VentasBarraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VentasBarraComponent]
    });
    fixture = TestBed.createComponent(VentasBarraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
