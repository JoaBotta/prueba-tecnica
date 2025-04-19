import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradaOnlineListComponent } from './entrada-online-list.component';

describe('EntradaOnlineListComponent', () => {
  let component: EntradaOnlineListComponent;
  let fixture: ComponentFixture<EntradaOnlineListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradaOnlineListComponent]
    });
    fixture = TestBed.createComponent(EntradaOnlineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
