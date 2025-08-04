import { QueryTypes } from "sequelize";
import sequelize from "../config/db";
import { DriverDTO } from "../domains/dtos/driverDTO";

export async function getActiveDrivers(): Promise<DriverDTO[]> {
  const sql = `
    SELECT 
      d.id, d.code, d.full_name, d.vehicle_team_id, d.working_status,
      COUNT(o.id) AS current_orders_count
    FROM drivers d
     JOIN orders o 
      ON d.id = o.primary_driver_id  
    WHERE d.active = '1' AND d.del_flag = '0'
    GROUP BY d.id, d.code, d.full_name, d.vehicle_team_id, d.working_status
  `;

  const rows = await sequelize.query(sql, { type: QueryTypes.SELECT });

  return (rows as any[]).map(
    (row): DriverDTO => ({
      id: row.id,
      code: row.code,
      fullName: row.full_name,
      vehicleTeamId: row.vehicle_team_id,
      workingStatus:
        row.working_status === null ||
        row.working_status === undefined ||
        row.working_status === ""
          ? 1
          : row.working_status,
      currentOrdersCount: Number(row.current_orders_count),
    })
  );
}
