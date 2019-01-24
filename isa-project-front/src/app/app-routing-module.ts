import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AllHotelsComponent } from './all-hotels/all-hotels.component';
import { RentCarProfileComponent } from './rent-car-profile/rent-car-profile.component';
import { RentCarSearchComponent } from './rent-car-search/rent-car-search.component';
import { RentCarVehicleComponent } from './rent-car-vehicle/rent-car-vehicle.component';
import { RentCarEditComponent } from './rent-car-edit/rent-car-edit.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AllRentACarsComponent } from './all-rent-a-cars/all-rent-a-cars.component';



const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login',component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'hotels', component: AllHotelsComponent},
  {
    path: 'rentACar', 
    component: AllRentACarsComponent, 
    children : [
      {path: 'profile', component: RentCarProfileComponent},
      {path: 'search', component: RentCarSearchComponent},
      {path: 'admin', component: UserProfileComponent},
      {path: 'vehicles', component: RentCarVehicleComponent},
      {path: 'edit', component: RentCarEditComponent}

  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
