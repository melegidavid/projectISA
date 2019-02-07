import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { RentACarService } from '../all-rent-a-cars/rent-a-car.service';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { RentACar } from '../dto/rent-a-car.model';
import { Router} from '@angular/router';
import { Vehicle } from '../dto/vehicle.model';
import { RentACarBranch } from '../dto/rent-a-car-branch.model';
import { Address } from '../dto/address.model';



@Component({
  selector: 'app-rent-car-edit',
  templateUrl: './rent-car-edit.component.html',
  styleUrls: ['./rent-car-edit.component.css']
})
export class RentCarEditComponent implements OnInit {

  private sub: any;
  id: number;

  rentCar : RentACar = new RentACar();
  vehicles: Vehicle[] = [];
  branches: RentACarBranch[] = [];
  vehiclesToRemove : Vehicle[] = [];
  branchesToRemove : RentACarBranch[] = [];
  vehicleToAdd : Vehicle = new Vehicle();
  branchToAdd : RentACarBranch = new RentACarBranch();
  vehicleToUpdate : Vehicle = new Vehicle();
  branchToUpdate : RentACarBranch = new RentACarBranch();

  showAddDiv : boolean = false;
  showUpdateDiv : boolean = false;

  showAddBranchDiv : boolean = false;
  showUpdateBranchDiv : boolean = false;


  constructor(private userService: UserService, private rentCarService: RentACarService,
    private route: ActivatedRoute, private router : Router) { }

  ngOnInit() {

    this.rentCar.name = "";
    this.rentCar.description = "";
    
    this.restoreAddVehicle();
    this.restoreBranch(this.branchToAdd);
    this.restoreBranch(this.branchToUpdate);

    this.sub = this.route.params.subscribe(params => { 
       this.id = + params['id'];
    });

    this.rentCarService.getRentACar(this.id).subscribe(data => {
      this.rentCar = data;
    });

    this.rentCarService.getVehicles(this.id).subscribe(data => {
      this.vehicles = data;
    });

    this.rentCarService.getRentACarBranches(this.id).subscribe(data => {
      this.branches = data;
    });

    
  }

  toggleAdd() {
    this.showAddDiv = !this.showAddDiv;
    this.restoreAddVehicle();
  }

  toggleAddBranch() {
    this.showAddBranchDiv = !this.showAddBranchDiv;
    this.restoreBranch(this.branchToAdd);
  }

  toggleUpdate(id : number) {
    this.showUpdateDiv = !this.showUpdateDiv;
    if(this.showUpdateDiv == true) {
      this.rentCarService.getVehicle(id).subscribe(data => {
        this.vehicleToUpdate = data;
      });
    }
  }

  toggleUpdateBranch(id : number) {
    this.showUpdateBranchDiv = !this.showUpdateBranchDiv;
    if(this.showUpdateBranchDiv == true) {
      this.rentCarService.getBranch(id).subscribe(data => {
        this.branchToUpdate = data;
      });
    }
  }

  restoreAddVehicle() {
    this.vehicleToAdd.mark = "";
    this.vehicleToAdd.model = "";
    this.vehicleToAdd.name = "";
    this.vehicleToAdd.type = "";
    this.vehicleToAdd.price = 0;
    //probaj bez nule
    this.vehicleToAdd.seatsNumber = 0;
    this.vehicleToAdd.yearProduced = 0;
    
  }

  restoreBranch(branch : RentACarBranch) {
    branch.name = "";
    branch.id = 0;

    branch.addressDTO = new Address();
    branch.addressDTO.id = 0;
    branch.addressDTO.country = "";
    branch.addressDTO.city = "";
    branch.addressDTO.street = "";
    branch.addressDTO.number = 0;
    branch.addressDTO.postalCode = 0;

    branch.rentCarDTO = new RentACar();
    branch.rentCarDTO.id = this.id;
  }

  addVehicle() {
    //validacija jos
    
    this.vehicleToAdd.rentCar = new RentACar();
    this.vehicleToAdd.rentCar.id = this.rentCar.id; 
    
    this.rentCarService.addVehicle(this.vehicleToAdd).subscribe(data => {
      this.restoreAddVehicle();
      this.showAddDiv = !this.showAddDiv;
    });
  
  }

  addBranch() {
    //validacija jos
    this.branchToAdd.rentCarDTO = new RentACar(); 
    this.branchToAdd.rentCarDTO.id = this.rentCar.id; 

    this.rentCarService.addBranch(this.branchToAdd).subscribe(data => {
      
      this.rentCarService.getRentACarBranches(this.id).subscribe(data => {
        this.branches = data;
        this.showAddBranchDiv = !this.showAddBranchDiv;
        this.restoreBranch(this.branchToAdd);
      });
      
    });
  
  }

  updateVehicle() {
    this.rentCarService.updateVehicle(this.vehicleToUpdate).subscribe(data => {
      
      //osvezi vozila
      this.rentCarService.getVehicles(this.id).subscribe(data => {
        this.vehicles = data;
        this.showUpdateDiv = !this.showUpdateDiv; 
      });

    });
  }

  updateBranch() {
    this.rentCarService.updateBranch(this.branchToUpdate).subscribe(data => {
      
      this.rentCarService.getRentACarBranches(this.id).subscribe(data => {
        this.branches = data;
        this.showUpdateBranchDiv = !this.showUpdateBranchDiv;
      });

    });
  }

  logOut() {
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

  onSubmit() {
    this.doUpdateRentCar(); 
    this.removeVehicles();
    this.removeBranches();
    alert('Uspesno izvrsene promene!');


    this.router.navigate(['/rentACar/' + this.id]);
  }

  doUpdateRentCar() {
       this.rentCarService.updateRentACar(this.rentCar);
  }

  hideVehicle(idVehicle :any) {
    
    console.log('Prosledjeno ' + idVehicle);

    for(let x of this.vehicles) {
      if(x.id == idVehicle) {
        const index = this.vehicles.indexOf(x, 0);
        if (index > -1) {
           this.vehiclesToRemove.push(x);
           this.vehicles.splice(index, 1);
           break;
        }
      }
    }
  }

  hideBranch(idBranch :any) {
    
    console.log('Prosledjeno ' + idBranch);

    for(let x of this.branches) {
      if(x.id == idBranch) {
        const index = this.branches.indexOf(x, 0);
        if (index > -1) {
           this.branchesToRemove.push(x);
           this.branches.splice(index, 1);
           console.log(this.branches);
           console.log(this.branchesToRemove);  
           break;
        }
      }
    }
  }

  removeVehicles() {
    for(let v of this.vehiclesToRemove) {
      this.rentCarService.removeVehicle(v.id);
    }
  }

  removeBranches() {
    for(let b of this.branchesToRemove) {
      this.rentCarService.removeBranch(b.id);
    }
  }

}
