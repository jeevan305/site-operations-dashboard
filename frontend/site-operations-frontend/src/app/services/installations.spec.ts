import { TestBed } from '@angular/core/testing';
import { Installations } from './installations';

describe('Installations', () => {
  let service: Installations;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Installations);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
