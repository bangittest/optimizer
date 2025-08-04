import { BaseCriteria } from "./BaseCriteria";
import { OrderDTO } from '../domains/dtos/orderDTO';
import { VehicleDTO } from '../domains/dtos/vehicleDTO';
import { DriverDTO } from '../domains/dtos/driverDTO';

export class ETDTimeCriteria extends BaseCriteria {
  constructor(weight: number) {
    super('ETD Time', weight);
  }

  calculate(order: OrderDTO, vehicle: VehicleDTO, driver: DriverDTO): number {
    const now = new Date();
    const etd = new Date(order.etdDate); 

    if (etd < now) {
      return 0; // Quá hạn
    }

    const diffMs = etd.getTime() - now.getTime();
    const diffHours = diffMs / (1000 * 60 * 60);

    // Normalize score: càng gần thì điểm càng cao (1 nếu còn 0 giờ, 0 nếu hơn 48h)
    return Math.max(0, Math.min(1, (48 - diffHours) / 48));
  }
}
