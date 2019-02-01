import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-activate-profile',
  templateUrl: './activate-profile.component.html',
  styleUrls: ['./activate-profile.component.css']
})
export class ActivateProfileComponent implements OnInit {

  private sub: any;
  id: number;

  constructor(private http: HttpClient,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.http.get("http://localhost:9004/users/activate/" + this.id)
    .subscribe(data => {
      
    });
    console.log(this.id);
  }

}
