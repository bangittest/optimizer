import { OrderDTO } from "../domains/dtos/orderDTO";
import { VehicleDTO } from "../domains/dtos/vehicleDTO";
import { DriverDTO } from "../domains/dtos/driverDTO";

export abstract class BaseCriteria {
  protected name: string;
  protected weight: number;

  constructor(name: string, weight: number) {
    this.name = name;
    this.weight = weight;
  }

  abstract calculate(
    order: OrderDTO,
    vehicle: VehicleDTO,
    driver: DriverDTO
  ): number;

  getName(): string {
    return this.name;
  }

  public setWeight(newWeight: number): void {
    this.weight = newWeight;
  }

  public getWeight(): number {
    return this.weight;
  }
}
