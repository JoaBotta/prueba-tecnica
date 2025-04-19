import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradaOnlineFormComponent } from './entrada-online-form.component';

describe('EntradaOnlineFormComponent', () => {
  let component: EntradaOnlineFormComponent;
  let fixture: ComponentFixture<EntradaOnlineFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradaOnlineFormComponent]
    });
    fixture = TestBed.createComponent(EntradaOnlineFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
