export interface DriverDTO {
  id: number;
  code: string;
  fullName: string;
  vehicleTeamId: number | null;
  workingStatus: string;
  currentOrdersCount: number;
}