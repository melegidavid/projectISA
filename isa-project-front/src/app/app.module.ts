import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AppRoutingModule } from './app-routing-module';
import { AllHotelsComponent } from './all-hotels/all-hotels.component';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { AllRentACarsComponent } from './all-rent-a-cars/all-rent-a-cars.component';
import { RentCarProfileComponent } from './rent-car-profile/rent-car-profile.component';
import { RentCarSearchComponent } from './rent-car-search/rent-car-search.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RentCarVehicleComponent } from './rent-car-vehicle/rent-car-vehicle.component';
import { RentCarEditComponent } from './rent-car-edit/rent-car-edit.component';
import { UserService} from './user.service'
import { FormsModule } from '@angular/forms';
import { AllAvioCompaniesComponent } from './all-avio-companies/all-avio-companies.component';
import { HotelProfileComponent } from './hotel-profile/hotel-profile.component';
import { AvioCompanyProfileComponent } from './avio-company-profile/avio-company-profile.component';
import { SearchUserFirstNamePipe } from './pipes/search-user-first-name.pipe';
import { AdminComponent } from './admin/admin.component';
import { ActivateProfileComponent } from './activate-profile/activate-profile.component';
import { AdminAvioCompanyComponent } from './admin-avio-company/admin-avio-company.component';
import { AdminAvioCompanyService } from './admin-avio-company/admin-avio-company.service';
import { HotelPipePipe } from './pipes/hotel-pipe.pipe';
import { HotelMenuItemReservationComponent } from './hotel-menu-item-reservation/hotel-menu-item-reservation.component';
import { UserHistoryComponent } from './home/user-history/user-history.component';
import { FromLocationPipe } from'./pipes/from-location.pipe';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { HotelEditComponent } from './hotel-edit/hotel-edit.component';
import { AvioFlightSeatsComponent } from './avio-flight-seats/avio-flight-seats.component';
import { InviteFriendsComponent } from './invite-friends/invite-friends.component';
import { FinalReservationComponent } from './final-reservation/final-reservation.component';
import { AdminRentCarProfileComponent } from './admin-rent-car-profile/admin-rent-car-profile.component';
import { AdminHotelProfileComponent } from './admin-hotel-profile/admin-hotel-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AllHotelsComponent,
    AllRentACarsComponent,
    RentCarProfileComponent,
    ActivateProfileComponent,
    UserHistoryComponent,
    RentCarSearchComponent,
    UserProfileComponent,
    RentCarVehicleComponent,
    RentCarEditComponent,
    AllAvioCompaniesComponent,
    HotelProfileComponent,
    AvioCompanyProfileComponent,
    SearchUserFirstNamePipe,
    AdminComponent,
    ActivateProfileComponent,
    AdminAvioCompanyComponent,
    FromLocationPipe,
    ActivateProfileComponent,
    HotelPipePipe,
    HotelMenuItemReservationComponent,
    ChangePasswordComponent,
    HotelMenuItemReservationComponent,
    HotelEditComponent,
    AvioFlightSeatsComponent,
    InviteFriendsComponent,
    FinalReservationComponent,
    AdminHotelProfileComponent,
    AdminRentCarProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserModule
  ],
  providers: [
    UserService,
    AdminAvioCompanyService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
