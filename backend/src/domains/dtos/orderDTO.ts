export interface OrderDTO {
  id: number;
  orderCode: string;
  customerId: number;
  locationDestinationId: number;
  locationArrivalId: number;
  weight: number;
  volume: number;
  precedence: number;
  etdDate: string; // ISO datetime string, e.g., "2025-08-04T13:00:00"
  destLat: number | null;
  destLng: number | null;
  arrivalLat: number | null;
  arrivalLng: number | null;
}
