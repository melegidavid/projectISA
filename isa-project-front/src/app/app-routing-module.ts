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
import { AdminComponent } from './admin/admin.component';
import { ActivateProfileComponent } from './activate-profile/activate-profile.component';
import { AdminAvioCompanyComponent } from './admin-avio-company/admin-avio-company.component';
import { HotelMenuItemReservationComponent } from './hotel-menu-item-reservation/hotel-menu-item-reservation.component';
import { HotelEditComponent } from './hotel-edit/hotel-edit.component';
import { AvioFlightSeatsComponent } from './avio-flight-seats/avio-flight-seats.component';
import { InviteFriendsComponent } from './invite-friends/invite-friends.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'auth/login',component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: 'hotels', component: AllHotelsComponent },
  { path: 'hotels/:id', component: HotelProfileComponent},
  { path: 'hotels/:id/menu_reservation', component: HotelMenuItemReservationComponent},
  { path: 'hotels/edit/:id', component: HotelEditComponent},
  {
    path: 'rentACar', 
    component: AllRentACarsComponent, 
    children : [
      //{path: ':id', component: RentCarProfileComponent},
      {path: 'search', component: RentCarSearchComponent},
      {path: 'admin', component: UserProfileComponent},
      //{path: 'vehicles', component: RentCarVehicleComponent},
      {path: 'edit/:id', component: RentCarEditComponent}
  ]},
  { path: 'rentACar/:id', component: RentCarProfileComponent},
  { path: 'avioCompanies', component: AllAvioCompaniesComponent},
  { path: 'avioCompanies/:id', component: AvioCompanyProfileComponent},
  { path: 'flight/:id/seats', component: AvioFlightSeatsComponent}, //id je oznaka id leta
  { path: 'invite-friends', component: InviteFriendsComponent},
  { path: 'users/:username', component: UserProfileComponent}, 
  { path: 'admin', component: AdminComponent},
  { path: 'admin-avio-company', component: AdminAvioCompanyComponent },
  { path: 'auth/activate/:id', component: ActivateProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
