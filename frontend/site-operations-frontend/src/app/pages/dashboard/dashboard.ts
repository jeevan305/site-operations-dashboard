import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import {
  DashboardService,
  DashboardSummary
} from '../../services/dashboard';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  private dashboardService = inject(DashboardService);
  private cdr = inject(ChangeDetectorRef);

  summary: DashboardSummary | null = null;
  loading = true;
  error = '';

  ngOnInit(): void {
    this.dashboardService.getSummary().subscribe({
      next: (data) => {
        console.log('Dashboard data:', data);

        this.summary = data;
        this.loading = false;

        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Dashboard API error:', error);

        this.error = 'Unable to load dashboard data.';
        this.loading = false;

        this.cdr.detectChanges();
      }
    });
  }
}