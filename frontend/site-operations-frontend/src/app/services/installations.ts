import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Installation {
  id: number;
  siteId: number;
  siteName: string;
  installationType: string;
  status: string;
  technicianId: number | null;
  technicianName: string | null;
  startDate: string | null;
  completionDate: string | null;
}

export interface InstallationRequest {
  siteId: number;
  installationType: string;
  status: string;
  technicianId: number | null;
  startDate: string | null;
  completionDate: string | null;
}

@Injectable({
  providedIn: 'root'
})
export class InstallationsService {

  private http = inject(HttpClient);

  private apiUrl = `${environment.apiUrl}/installations`;

  getInstallations(
    status?: string,
    siteId?: number
  ): Observable<Installation[]> {

    let url = this.apiUrl;
    const params: string[] = [];

    if (status) {
      params.push(`status=${encodeURIComponent(status)}`);
    }

    if (siteId !== undefined && siteId !== null) {
      params.push(`siteId=${siteId}`);
    }

    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    return this.http.get<Installation[]>(url);
  }

  createInstallation(
    installation: InstallationRequest
  ): Observable<Installation> {
    return this.http.post<Installation>(
      this.apiUrl,
      installation
    );
  }

  updateInstallation(
    id: number,
    installation: InstallationRequest
  ): Observable<Installation> {
    return this.http.put<Installation>(
      `${this.apiUrl}/${id}`,
      installation
    );
  }

  deleteInstallation(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }
}