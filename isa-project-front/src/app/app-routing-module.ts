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
import { HotelProfileComponent } from './hotel-profile/hotel-profile.component';
import { AllAvioCompaniesComponent } from './all-avio-companies/all-avio-companies.component';
import { AvioCompanyProfileComponent } from './avio-company-profile/avio-company-profile.component';



const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'auth/login',component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: 'hotels', component: AllHotelsComponent },
  { path: 'hotels/:id', component: HotelProfileComponent},
  {
    path: 'rentACar', 
    component: AllRentACarsComponent, 
    children : [
      {path: ':id', component: RentCarProfileComponent},
      {path: 'search', component: RentCarSearchComponent},
      {path: 'admin', component: UserProfileComponent},
      {path: 'vehicles', component: RentCarVehicleComponent},
      {path: 'edit', component: RentCarEditComponent}
  ]},
  { path: 'avioCompanies', component: AllAvioCompaniesComponent},
  { path: 'avioCompanies/:id', component: AvioCompanyProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
