// src/criteria/VehicleUtilizationCriteria.ts

import { BaseCriteria } from './BaseCriteria';
import { OrderDTO } from '../domains/dtos/orderDTO';
import { VehicleDTO } from '../domains/dtos/vehicleDTO';
import { DriverDTO } from '../domains/dtos/driverDTO';

/**
 * Tiêu chí đánh giá mức độ sử dụng tải trọng của xe (Vehicle Utilization Criteria)
 * - Ưu tiên xe có mức sử dụng tải trọng từ 70% đến 90%
 * - Loại bỏ xe quá tải hoặc quá ít tải
 */
export class VehicleUtilizationCriteria extends BaseCriteria {
  private currentVehicleLoad: Map<number, number>;

  /**
   * Constructor
   * @param weight Trọng số tiêu chí
   * @param currentVehicleLoad Bản đồ vehicleId -> tải trọng hiện tại (kg)
   */
  constructor(weight: number, currentVehicleLoad?: Map<number, number>) {
    super('Vehicle Utilization', weight);
    this.currentVehicleLoad = currentVehicleLoad ?? new Map<number, number>();
  }

  /**
   * Tính điểm dựa trên mức độ tải của xe sau khi thêm đơn hàng mới
   * @param order Đơn hàng
   * @param vehicle Xe
   * @param driver Tài xế
   * @returns Điểm (0-1)
   */
  calculate(order: OrderDTO, vehicle: VehicleDTO, driver: DriverDTO): number {
    // Kiểm tra null để tránh lỗi runtime
    if (!order || !vehicle) {
      return 0;
    }
    if (order.weight == null || vehicle.weight == null || vehicle.weight === 0) {
      return 0;
    }

    // Lấy tải trọng hiện tại của xe, mặc định 0 nếu không có
    const currentLoad = this.currentVehicleLoad.get(vehicle.id) ?? 0;

    // Tính tải trọng mới nếu thêm đơn hàng này
    const newLoad = currentLoad + order.weight;

    // Tỷ lệ sử dụng tải trọng của xe
    const utilizationRatio = newLoad / vehicle.weight;

    // Điều kiện đánh giá
    if (utilizationRatio > 1.0) return 0;         // Quá tải → điểm 0
    if (utilizationRatio < 0.3) return 0.3;       // Tải quá ít → điểm thấp 0.3
    if (utilizationRatio >= 0.7 && utilizationRatio <= 0.9) return 1.0;  // Tối ưu → điểm 1

    // Ưu tiên mức sử dụng gần 80%
    return Math.max(0, 1 - Math.abs(0.8 - utilizationRatio));
  }
}
