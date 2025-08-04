import { DataTypes, Model } from 'sequelize';
import sequelize from '../../config/db';

interface DriverVehicleTeamAttributes {
  driver_id: number;
  vehicle_team_id: number;
}

class DriverVehicleTeam extends Model<DriverVehicleTeamAttributes> implements DriverVehicleTeamAttributes {
  public driver_id!: number;
  public vehicle_team_id!: number;
}

DriverVehicleTeam.init({
  driver_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: 0,
    primaryKey: true,
  },
  vehicle_team_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: 0,
    primaryKey: true,
  },
}, {
  sequelize,
  tableName: 'driver_vehicle_team',
  timestamps: false,
  charset: 'utf8mb3',
  collate: 'utf8mb3_unicode_ci',
});

export default DriverVehicleTeam;
