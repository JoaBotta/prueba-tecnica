import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BolicheComponent } from './boliche.component';

describe('BolicheComponent', () => {
  let component: BolicheComponent;
  let fixture: ComponentFixture<BolicheComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BolicheComponent]
    });
    fixture = TestBed.createComponent(BolicheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
