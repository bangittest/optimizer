import { QueryTypes } from "sequelize";
import sequelize from "../config/db";
import { VehicleDTO } from "../domains/dtos/vehicleDTO";
import { toNumberOrZero } from "../utils/numberUtils";

export async function getActiveVehicles(): Promise<VehicleDTO[]> {
  const sql = `
    SELECT id, reg_no, weight, volume, latitude, longitude, status
    FROM vehicle
    WHERE status = '1' AND active = '1' AND del_flag = '0'
  `;

  const rows = await sequelize.query(sql, { type: QueryTypes.SELECT });

  return (rows as any[]).map(
    (row): VehicleDTO => ({
      id: row.id,
      regNo: row.reg_no,
      weight: row.weight,
      volume: row.volume,
      latitude: toNumberOrZero(row.latitude),
      longitude: toNumberOrZero(row.longitude),
      status: row.status,
    })
  );
}
