import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentaEntradaOnlineComponent } from './venta-entrada-online.component';

describe('VentaEntradaOnlineComponent', () => {
  let component: VentaEntradaOnlineComponent;
  let fixture: ComponentFixture<VentaEntradaOnlineComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VentaEntradaOnlineComponent]
    });
    fixture = TestBed.createComponent(VentaEntradaOnlineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
