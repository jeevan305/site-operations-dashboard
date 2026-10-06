import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Site {
  id: number;
  name: string;
  location: string;
  status: string;
  createdBy: number | null;
  createdByName: string | null;
}

export interface SiteRequest {
  name: string;
  location: string;
  status: string;
  createdBy: number;
}

@Injectable({
  providedIn: 'root'
})
export class SitesService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/sites';

  getSites(name?: string, status?: string): Observable<Site[]> {
    let url = this.apiUrl;

    const params: string[] = [];

    if (name) {
      params.push(`name=${encodeURIComponent(name)}`);
    }

    if (status) {
      params.push(`status=${encodeURIComponent(status)}`);
    }

    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    return this.http.get<Site[]>(url);
  }

  createSite(site: SiteRequest): Observable<Site> {
    return this.http.post<Site>(this.apiUrl, site);
  }

  updateSite(id: number, site: SiteRequest): Observable<Site> {
    return this.http.put<Site>(`${this.apiUrl}/${id}`, site);
  }

  deleteSite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}