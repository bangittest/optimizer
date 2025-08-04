import { BaseCriteria } from './BaseCriteria';
import { OrderDTO } from '../domains/dtos/orderDTO';
import { VehicleDTO } from '../domains/dtos/vehicleDTO';
import { DriverDTO } from '../domains/dtos/driverDTO';

/**
 * Tiêu chí phân bổ đều tài xế (Driver Balance Criteria)
 * - Ưu tiên các tài xế có ít đơn hàng hơn
 * - Điểm số càng cao nếu số lượng đơn của tài xế càng thấp
 */
export class DriverBalanceCriteria extends BaseCriteria {
  // Bản đồ lưu số đơn hàng hiện tại của từng tài xế theo driverId
  private readonly driverOrderCounts: Map<number, number>;

  /**
   * Constructor
   * @param weight Trọng số của tiêu chí
   * @param driverOrderCounts Map<driverId, số đơn đang có>
   */
  constructor(weight: number, driverOrderCounts: Map<number, number>) {
    super('Driver Balance', weight);
    this.driverOrderCounts = driverOrderCounts ?? new Map<number, number>();
  }

  /**
   * Tính điểm cho tiêu chí Driver Balance
   * @param order Đơn hàng
   * @param vehicle Xe
   * @param driver Tài xế
   * @returns Điểm số (0 đến 1): càng ít đơn thì điểm càng cao
   */
  calculate(order: OrderDTO, vehicle: VehicleDTO, driver: DriverDTO): number {
    if (!driver || typeof driver.id !== 'number') return 0;

    const currentCount = this.driverOrderCounts.get(driver.id) ?? 0;

    const driverValues = Array.from(this.driverOrderCounts.values());

    const maxCount = driverValues.length > 0
      ? Math.max(...driverValues, 1)
      : 1; // fallback nếu map rỗng

    const score = 1 - currentCount / maxCount;

    return Math.max(0, score);
  }
}
