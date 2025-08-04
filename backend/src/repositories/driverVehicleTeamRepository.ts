import { QueryTypes } from 'sequelize';
import sequelize from '../config/db';
import { DriverVehicleTeamDTO } from '../domains/dtos/driverVehicleTeamDTO';

export async function getDriverVehicleTeams(): Promise<DriverVehicleTeamDTO[]> {
  const sql = `
    SELECT driver_id, vehicle_team_id
    FROM driver_vehicle_team
  `;

  const rows = await sequelize.query(sql, { type: QueryTypes.SELECT });

  return (rows as any[]).map((row): DriverVehicleTeamDTO => ({
    driverId: row.driver_id,
    vehicleTeamId: row.vehicle_team_id,
  }));
}