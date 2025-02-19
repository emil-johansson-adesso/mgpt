import { Routes, RouterModule } from '@angular/router';

import { ChatComponent } from './chat';

const routes: Routes = [
    { path: '', component: AppComponent },
    { path: 'chat', component: ChatComponent },
];

export const appRoutingModule = RouterModule.forRoot(routes);
