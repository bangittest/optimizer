export interface Order {
  id: string;
  distance: number; // km
  etd: number; // timestamp
  priority: number; // 1 - 5
  weight: number; // kg
  driverId?: string;
}

export interface Driver {
  id: string;
  assignedOrders: number;
}

export interface CriteriaContext {
  order: Order;
  drivers: Driver[];
}
