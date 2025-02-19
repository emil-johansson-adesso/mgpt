import { Routes, RouterModule } from '@angular/router';

import { ChatComponent } from './chat';

const routes: Routes = [
    { path: '', component: AppComponent },
    { path: ''chat', component: ChatComponent }

    // otherwise redirect to home
    // { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
