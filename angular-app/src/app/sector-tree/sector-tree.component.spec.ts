import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { SectorTreeComponent } from './sector-tree.component';

describe('SectorTreeComponent', () => {
  let component: SectorTreeComponent;
  let fixture: ComponentFixture<SectorTreeComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ SectorTreeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
