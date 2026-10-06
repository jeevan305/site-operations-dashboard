import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Sites } from './pages/sites/sites';
import { Installations } from './pages/installations/installations';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'sites', component: Sites },
  { path: 'installations', component: Installations },
  { path: '**', redirectTo: 'dashboard' }
];