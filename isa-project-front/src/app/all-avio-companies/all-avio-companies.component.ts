import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from './avio-companies.service';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable } from '../../../node_modules/rxjs';
import { UserService } from '../user.service';

@Component({
  selector: 'app-all-avio-companies',
  templateUrl: './all-avio-companies.component.html',
  styleUrls: ['./all-avio-companies.component.css'],
  providers: [AvioCompaniesService]
})
export class AllAvioCompaniesComponent implements OnInit {

  len: number;
  username: string;
  avioCompanies: AvioCompany[] = [];
  
  constructor( private avioCompanyService: AvioCompaniesService,
    private userService: UserService) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);

    this.getAvioCompanies().subscribe(data => {
      this.avioCompanies = data;
    });
  }

  public getAvioCompanies(): Observable<AvioCompany[]> {
    return this.avioCompanyService.getAvioCompanies();
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
