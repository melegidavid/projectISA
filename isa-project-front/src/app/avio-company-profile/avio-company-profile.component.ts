import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable } from '../../../node_modules/rxjs';

@Component({
  selector: 'app-avio-company-profile',
  templateUrl: './avio-company-profile.component.html',
  styleUrls: ['./avio-company-profile.component.css']
})
export class AvioCompanyProfileComponent implements OnInit {

  avioCompany: AvioCompany;
  sub: any;
  id: number;
  
  constructor(private avioCompaniesService: AvioCompaniesService, 
    private route: ActivatedRoute,
    private router: Router) {
      this.router = router;
    }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getAvioCompany(this.id).subscribe(data => {
      this.avioCompany = data;
    });
  }

  public getAvioCompany(id: number | string): Observable<AvioCompany> {
    return this.avioCompaniesService.getAvioCompany(id);
  }

}
