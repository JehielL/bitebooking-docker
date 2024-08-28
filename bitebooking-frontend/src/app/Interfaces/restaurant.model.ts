
import { RestaurantType } from "./restaurantType.model";

export interface Restaurant {
    id: number;
    name: string;
    phone: string;
    restaurantType: RestaurantType;
    description: string;
    openingTime: string;
    closingTime: string;
    status: boolean;
    imageUrl: string;
    city: string;
    address:  string;
    number: string;
    postalCode: string;
    averageRating: number;
    discount: number;
  }




 
