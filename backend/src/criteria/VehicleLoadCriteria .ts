// src/criteria/VehicleLoadCriteria.ts

import { BaseCriteria } from './BaseCriteria';
import { OrderDTO } from '../domains/dtos/orderDTO';
import { VehicleDTO } from '../domains/dtos/vehicleDTO';
import { DriverDTO } from '../domains/dtos/driverDTO';

/**
 * Tiêu chí về khả năng tải của xe (Vehicle Load Criteria)
 * - Ưu tiên xe có mức sử dụng tải trọng & thể tích hợp lý (~80%)
 * - Loại bỏ xe không đủ sức chở
 */
export class VehicleLoadCriteria extends BaseCriteria {
  /**
   * Constructor
   * @param weight Trọng số của tiêu chí
   */
  constructor(weight: number) {
    super('Vehicle Load', weight);
  }

  /**
   * Tính điểm tiêu chí dựa trên mức độ sử dụng xe
   * @param order Đơn hàng
   * @param vehicle Xe
   * @param driver Tài xế
   * @returns Điểm số (0 đến 1): càng gần 80% sức chứa thì điểm càng cao
   */
  calculate(order: OrderDTO, vehicle: VehicleDTO, driver: DriverDTO): number {
    // Kiểm tra null hoặc undefined để tránh lỗi runtime
    if (!order || !vehicle) {
      return 0;
    }

    if (
      order.weight == null ||
      order.volume == null ||
      vehicle.weight == null ||
      vehicle.volume == null ||
      vehicle.weight === 0 ||
      vehicle.volume === 0
    ) {
      return 0; 
    }

    // Tính tỷ lệ tải trọng và thể tích đơn hàng so với xe
    const weightRatio = order.weight / vehicle.weight;
    const volumeRatio = order.volume / vehicle.volume;

    // Nếu đơn hàng vượt quá khả năng chở của xe → không hợp lệ
    if (weightRatio > 1 || volumeRatio > 1) {
      return 0;
    }

    // Tính mức độ sử dụng của xe (chọn tỷ lệ lớn hơn giữa tải trọng và thể tích)
    const utilizationScore = Math.max(weightRatio, volumeRatio);

    // Ưu tiên mức sử dụng gần 80% → tối ưu hiệu quả sử dụng
    const score = 1 - Math.abs(0.8 - utilizationScore);

    // Trả về điểm từ 0 đến 1
    return Math.max(0, score);
  }
}
