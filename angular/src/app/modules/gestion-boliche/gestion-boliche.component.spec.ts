import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionBolicheComponent } from './gestion-boliche.component';

describe('GestionBolicheComponent', () => {
  let component: GestionBolicheComponent;
  let fixture: ComponentFixture<GestionBolicheComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionBolicheComponent]
    });
    fixture = TestBed.createComponent(GestionBolicheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
