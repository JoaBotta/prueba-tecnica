import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BolicheFormComponent } from './boliche-form.component';

describe('BolicheFormComponent', () => {
  let component: BolicheFormComponent;
  let fixture: ComponentFixture<BolicheFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BolicheFormComponent]
    });
    fixture = TestBed.createComponent(BolicheFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
