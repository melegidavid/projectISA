import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from './avio-companies.service';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable } from '../../../node_modules/rxjs';

@Component({
  selector: 'app-all-avio-companies',
  templateUrl: './all-avio-companies.component.html',
  styleUrls: ['./all-avio-companies.component.css'],
  providers: [AvioCompaniesService]
})
export class AllAvioCompaniesComponent implements OnInit {

  avioCompanies: AvioCompany[] = [];
  
  constructor( private avioCompanyService: AvioCompaniesService) { }

  ngOnInit() {
    this.getAvioCompanies().subscribe(data => {
      this.avioCompanies = data;
    });
  }

  public getAvioCompanies(): Observable<AvioCompany[]> {
    return this.avioCompanyService.getAvioCompanies();
  }

}
