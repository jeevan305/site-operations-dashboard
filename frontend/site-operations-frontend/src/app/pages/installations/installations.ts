import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import { FormsModule } from '@angular/forms';

import {
  InstallationsService,
  Installation,
  InstallationRequest
} from '../../services/installations';

@Component({
  selector: 'app-installations',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './installations.html',
  styleUrl: './installations.css'
})
export class Installations implements OnInit {

  private installationsService = inject(InstallationsService);
  private cdr = inject(ChangeDetectorRef);

  installations: Installation[] = [];

  selectedStatus = '';
  selectedSiteId: number | null = null;

  loading = false;
  error = '';

  showForm = false;
  saving = false;
  formError = '';

  editingInstallationId: number | null = null;

  newInstallation: InstallationRequest = {
    siteId: 1,
    installationType: '',
    status: 'PLANNED',
    technicianId: 1,
    startDate: null,
    completionDate: null
  };

  editInstallation: InstallationRequest = {
    siteId: 1,
    installationType: '',
    status: 'PLANNED',
    technicianId: 1,
    startDate: null,
    completionDate: null
  };

  ngOnInit(): void {
    this.loadInstallations();
  }

  loadInstallations(): void {
    this.loading = true;
    this.error = '';

    this.installationsService.getInstallations(
      this.selectedStatus || undefined,
      this.selectedSiteId ?? undefined
    ).subscribe({
      next: (data: Installation[]) => {
        this.installations = data;
        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (error) => {
        console.error('Installations API error:', error);

        this.installations = [];
        this.error = 'Unable to load installations.';
        this.loading = false;

        this.cdr.detectChanges();
      }
    });
  }

  filterInstallations(): void {
    this.loadInstallations();
  }

  clearFilters(): void {
    this.selectedStatus = '';
    this.selectedSiteId = null;

    this.loadInstallations();
  }

  addInstallation(): void {
  this.formError = '';

  if (
    !this.newInstallation.siteId ||
    !this.newInstallation.installationType.trim()
  ) {
    this.formError =
      'Site ID and installation type are required.';
    return;
  }

  this.saving = true;

  this.installationsService
    .createInstallation(this.newInstallation)
    .subscribe({
      next: (installation: Installation) => {

        console.log(
          'Installation created:',
          installation
        );

        // Stop the saving state
        this.saving = false;

        // Close the form
        this.showForm = false;

        // Reset form
        this.newInstallation = {
          siteId: 1,
          installationType: '',
          status: 'PLANNED',
          technicianId: 1,
          startDate: null,
          completionDate: null
        };

        // Force Angular to update the UI
        this.cdr.detectChanges();

        // Reload the installation list
        this.loadInstallations();
      },

      error: (error) => {

        console.error(
          'Create installation error:',
          error
        );

        this.formError =
          error?.error?.message ||
          'Unable to create installation.';

        this.saving = false;

        this.cdr.detectChanges();
      }
    });
}

  startEdit(installation: Installation): void {
    this.editingInstallationId = installation.id;

    this.editInstallation = {
      siteId: installation.siteId,
      installationType: installation.installationType,
      status: installation.status,
      technicianId: installation.technicianId,
      startDate: installation.startDate,
      completionDate: installation.completionDate
    };

    this.formError = '';
    this.cdr.detectChanges();
  }

  updateInstallation(): void {
    if (this.editingInstallationId === null) {
      return;
    }

    this.formError = '';

    if (
      !this.editInstallation.siteId ||
      !this.editInstallation.installationType.trim()
    ) {
      this.formError =
        'Site ID and installation type are required.';
      return;
    }

    this.saving = true;

    this.installationsService
      .updateInstallation(
        this.editingInstallationId,
        this.editInstallation
      )
      .subscribe({
        next: (installation: Installation) => {
          console.log('Installation updated:', installation);

          this.saving = false;
          this.editingInstallationId = null;

          this.cdr.detectChanges();
          this.loadInstallations();
        },

        error: (error) => {
          console.error('Update installation error:', error);

          this.formError =
            error?.error?.message ||
            'Unable to update installation.';

          this.saving = false;
          this.cdr.detectChanges();
        }
      });
  }

  cancelEdit(): void {
    this.editingInstallationId = null;
    this.formError = '';
    this.cdr.detectChanges();
  }

  deleteInstallation(id: number): void {
    if (
      !confirm(
        'Are you sure you want to delete this installation?'
      )
    ) {
      return;
    }

    this.installationsService
      .deleteInstallation(id)
      .subscribe({
        next: () => {
          this.loadInstallations();
        },

        error: (error) => {
          console.error('Delete installation error:', error);

          this.error =
            error?.error?.message ||
            'Unable to delete installation.';

          this.cdr.detectChanges();
        }
      });
  }
}