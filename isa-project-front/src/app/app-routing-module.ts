import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AllHotelsComponent } from './all-hotels/all-hotels.component';
import { AllRentACarsComponent } from './all-rent-a-cars/all-rent-a-cars.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login',component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'hotels', component: AllHotelsComponent},
  {path: 'rentACar', component: AllRentACarsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
