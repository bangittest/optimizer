import { BaseCriteria } from "../criteria/BaseCriteria";
import { ETDTimeCriteria } from "../criteria/ETDCriteria";
import { DriverBalanceCriteria } from "../criteria/DriverBalanceCriteria";
import { OrderPrecedenceCriteria } from '../criteria/OrderPrecedenceCriteria';
import { VehicleLoadCriteria } from "../criteria/VehicleLoadCriteria ";
import { VehicleUtilizationCriteria } from "../criteria/VehicleUtilizationCriteria";

/**
 * Quản lý danh sách các tiêu chí đánh giá
 */
export class CriteriaManager {
  private criteriaList: BaseCriteria[] = [];

  constructor() {
    this.initializeDefaultCriteria();
  }

  /**
   * Khởi tạo các tiêu chí mặc định
   */
  private initializeDefaultCriteria(): void {
    this.criteriaList.push(new ETDTimeCriteria(0.2));
    this.criteriaList.push(new DriverBalanceCriteria(0.2, new Map()));
    this.criteriaList.push(new OrderPrecedenceCriteria(0.1));
    this.criteriaList.push(new VehicleLoadCriteria(0.05));
    this.criteriaList.push(new VehicleUtilizationCriteria(0.1, new Map()));
  }

  /**
   * Thêm tiêu chí vào danh sách
   * @param criteria Tiêu chí mới
   */
  addCriteria(criteria: BaseCriteria): void {
    this.criteriaList.push(criteria);
  }

  /**
   * Lấy danh sách tiêu chí hiện tại
   */
  public getCriteriaList(): BaseCriteria[] {
    return [...this.criteriaList];
  }

  /**
   *  Export static helper để gọi nhanh như CriteriaManager.get()
   */
  static get(): BaseCriteria[] {
    return new CriteriaManager().getCriteriaList();
  }
}

export function getCriteriaList(): BaseCriteria[] {
  return new CriteriaManager().getCriteriaList();
}
