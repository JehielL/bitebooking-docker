import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Restaurant } from '../Interfaces/restaurant.model';
import { RestaurantType } from '../Interfaces/restaurantType.model';
import { RouterLink } from '@angular/router';
import { combineLatest, delay, switchMap, timer } from 'rxjs';


@Component({
  selector: 'app-zone',
  standalone: true,
  templateUrl: './zone.component.html',
  imports: [RouterLink],
  styleUrls: ['./zone.component.css']
})
export class ZoneComponent implements OnInit {
  restaurants: Restaurant[] = [];
  restaurantsByPostalCode: { [postalCode: string]: Restaurant[] } = {};
  uniquePostalCodes: string[] = [];
  restaurantType = RestaurantType;
  showSpinner = true;

  constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {
    window.scrollTo(0, 0); 
    this.loadRestaurants();
  }

  loadRestaurants(): void {
    const apiUrl = 'http://localhost:8080/restaurant';
    timer(500).pipe(
      switchMap(() => this.httpClient.get<Restaurant[]>(apiUrl))).subscribe(restaurants => {
      this.restaurants = restaurants;
      this.showSpinner = false;
      this.groupRestaurantsByPostalCode();
    });
  }

  groupRestaurantsByPostalCode(): void {
    this.restaurantsByPostalCode = {};
    
    this.restaurants.forEach(restaurant => {
      if (!this.restaurantsByPostalCode[restaurant.postalCode]) {
        this.restaurantsByPostalCode[restaurant.postalCode] = [];
      }
      this.restaurantsByPostalCode[restaurant.postalCode].push(restaurant);
    });
    
    this.uniquePostalCodes = Object.keys(this.restaurantsByPostalCode);
  }
  getRestaurantType(type?: RestaurantType): string {
    if (type === undefined) return 'No especificado';
    const typeAsString: string = RestaurantType[type as unknown as keyof typeof RestaurantType];
    return typeAsString;
  }
}
