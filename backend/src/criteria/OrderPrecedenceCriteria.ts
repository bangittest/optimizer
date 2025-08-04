import { BaseCriteria } from './BaseCriteria';
import { OrderDTO } from '../domains/dtos/orderDTO';
import { VehicleDTO } from '../domains/dtos/vehicleDTO';
import { DriverDTO } from '../domains/dtos/driverDTO';

/**
 * Tiêu chí về mức độ ưu tiên đơn hàng (Order Precedence Criteria)
 * - Đơn hàng có precedence càng cao thì điểm càng lớn (chuẩn hóa 0-1)
 */
export class OrderPrecedenceCriteria extends BaseCriteria {
  /**
   * Constructor
   * @param weight Trọng số tiêu chí
   */
  constructor(weight: number) {
    super('Order Precedence', weight);
  }

  /**
   * Tính điểm dựa trên mức độ ưu tiên đơn hàng
   * @param order Đơn hàng
   * @param vehicle Xe
   * @param driver Tài xế
   * @returns Điểm từ 0 đến 1 (ưu tiên chuẩn hóa)
   */
  calculate(order: OrderDTO, vehicle: VehicleDTO, driver: DriverDTO): number {
    if (!order || order.precedence == null) {
      return 0;
    }
    // Chuẩn hóa precedence từ 1-10 thành 0-1
    return Math.max(0, Math.min(1, order.precedence / 10));
  }
}
