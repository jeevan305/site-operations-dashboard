import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import { FormsModule } from '@angular/forms';
import { SitesService, Site } from '../../services/sites';

@Component({
  selector: 'app-sites',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sites.html',
  styleUrl: './sites.css'
})
export class Sites implements OnInit {

  private sitesService = inject(SitesService);
  private cdr = inject(ChangeDetectorRef);

  sites: Site[] = [];

  searchName = '';
  selectedStatus = '';

  loading = false;
  error = '';

  showForm = false;
  saving = false;
  formError = '';

  newSite = {
    name: '',
    location: '',
    status: 'ACTIVE',
    createdBy: 1
  };

  editingSiteId: number | null = null;

  editSite = {
    name: '',
    location: '',
    status: 'ACTIVE',
    createdBy: 1
  };

  ngOnInit(): void {
    this.loadSites();
  }

  loadSites(): void {
    this.loading = true;
    this.error = '';

    this.sitesService.getSites(
      this.searchName,
      this.selectedStatus
    ).subscribe({
      next: (data: Site[]) => {
        console.log('Sites response:', data);

        this.sites = data;
        this.loading = false;

        this.cdr.detectChanges();
      },

      error: (error) => {
        console.error('Sites API error:', error);

        this.error = 'Unable to load sites.';
        this.loading = false;

        this.cdr.detectChanges();
      }
    });
  }

  searchSites(): void {
    this.loadSites();
  }

  clearFilters(): void {
    this.searchName = '';
    this.selectedStatus = '';

    this.loadSites();
  }

  addSite(): void {
    this.formError = '';

    if (
      !this.newSite.name.trim() ||
      !this.newSite.location.trim()
    ) {
      this.formError = 'Site name and location are required.';
      return;
    }

    this.saving = true;

    this.sitesService.createSite(this.newSite).subscribe({
      next: (site: Site) => {
        console.log('Site created:', site);

        this.saving = false;
        this.showForm = false;

        this.newSite = {
          name: '',
          location: '',
          status: 'ACTIVE',
          createdBy: 1
        };

        this.cdr.detectChanges();

        this.loadSites();
      },

      error: (error) => {
        console.error('Create site error:', error);

        this.formError = 'Unable to create site.';
        this.saving = false;

        this.cdr.detectChanges();
      }
    });
  }

  startEdit(site: Site): void {
    this.editingSiteId = site.id;

    this.editSite = {
      name: site.name,
      location: site.location,
      status: site.status,
      createdBy: site.createdBy ?? 1
    };

    this.cdr.detectChanges();
  }

  updateSite(): void {
    if (this.editingSiteId === null) {
      return;
    }

    this.saving = true;

    this.sitesService.updateSite(
      this.editingSiteId,
      this.editSite
    ).subscribe({
      next: (site: Site) => {
        console.log('Site updated:', site);

        this.saving = false;
        this.editingSiteId = null;

        this.cdr.detectChanges();

        this.loadSites();
      },

      error: (error) => {
        console.error('Update site error:', error);

        this.saving = false;

        this.cdr.detectChanges();
      }
    });
  }

  deleteSite(id: number): void {
    if (!confirm('Are you sure you want to delete this site?')) {
      return;
    }

    this.sitesService.deleteSite(id).subscribe({
      next: () => {
        console.log('Site deleted:', id);

        this.loadSites();
      },

      error: (error) => {
        console.error('Delete site error:', error);

        this.cdr.detectChanges();
      }
    });
  }
}