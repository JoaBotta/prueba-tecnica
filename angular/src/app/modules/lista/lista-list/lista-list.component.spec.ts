import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaListComponent } from './lista-list.component';

describe('ListaListComponent', () => {
  let component: ListaListComponent;
  let fixture: ComponentFixture<ListaListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaListComponent]
    });
    fixture = TestBed.createComponent(ListaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
