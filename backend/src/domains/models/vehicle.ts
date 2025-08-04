import { DataTypes, Model, Optional } from 'sequelize';
import sequelize from '../../config/db';

interface VehicleAttributes {
  id: number;
  group_id?: number | null;
  gps_company_id?: number | null;
  gps_id?: string | null;
  reg_no: string;
  stamp_number?: string | null;
  vehicle_plate?: string | null;
  type: string;
  latitude?: string | null;
  longitude?: string | null;
  current_location?: string | null;
  volume?: number | null;
  weight?: number | null;
  length?: number | null;
  height?: number | null;
  width?: number | null;
  status: string;
  active: string;
  repair_distance?: number | null;
  repair_date?: Date | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  is_subcontract: boolean;
  fixed_route: string;
  direction?: string | null;
  speed?: string | null;
  temperature?: number | null;
  contractor_id?: number | null;
  secondary_temperature?: number | null;
  tertiary_temperature?: number | null;
  goods_position: string;
  is_camera: boolean;
  camera_id: string;
  device_id: string;
  plate_number?: string | null;
  department_id?: number | null;
  is_passenger_transfer: boolean;
  seat_quantity?: number | null;
  seats?: string | null;
  partner_vehicle_id?: number | null;
  container_id?: number | null;
  trailer_id?: number | null;
  group_fee_id?: number | null;
}

interface VehicleCreationAttributes extends Optional<
  VehicleAttributes,
  | 'id'
  | 'group_id'
  | 'gps_company_id'
  | 'gps_id'
  | 'stamp_number'
  | 'vehicle_plate'
  | 'latitude'
  | 'longitude'
  | 'current_location'
  | 'volume'
  | 'weight'
  | 'length'
  | 'height'
  | 'width'
  | 'repair_distance'
  | 'repair_date'
  | 'upd_id'
  | 'upd_date'
  | 'direction'
  | 'speed'
  | 'temperature'
  | 'contractor_id'
  | 'secondary_temperature'
  | 'tertiary_temperature'
  | 'plate_number'
  | 'department_id'
  | 'seat_quantity'
  | 'seats'
  | 'partner_vehicle_id'
  | 'container_id'
  | 'trailer_id'
  | 'group_fee_id'
> {}

class Vehicle extends Model<VehicleAttributes, VehicleCreationAttributes> implements VehicleAttributes {
  public id!: number;
  public group_id!: number | null;
  public gps_company_id!: number | null;
  public gps_id!: string | null;
  public reg_no!: string;
  public stamp_number!: string | null;
  public vehicle_plate!: string | null;
  public type!: string;
  public latitude!: string | null;
  public longitude!: string | null;
  public current_location!: string | null;
  public volume!: number | null;
  public weight!: number | null;
  public length!: number | null;
  public height!: number | null;
  public width!: number | null;
  public status!: string;
  public active!: string;
  public repair_distance!: number | null;
  public repair_date!: Date | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public is_subcontract!: boolean;
  public fixed_route!: string;
  public direction!: string | null;
  public speed!: string | null;
  public temperature!: number | null;
  public contractor_id!: number | null;
  public secondary_temperature!: number | null;
  public tertiary_temperature!: number | null;
  public goods_position!: string;
  public is_camera!: boolean;
  public camera_id!: string;
  public device_id!: string;
  public plate_number!: string | null;
  public department_id!: number | null;
  public is_passenger_transfer!: boolean;
  public seat_quantity!: number | null;
  public seats!: string | null;
  public partner_vehicle_id!: number | null;
  public container_id!: number | null;
  public trailer_id!: number | null;
  public group_fee_id!: number | null;
}

Vehicle.init({
  id: {
    type: DataTypes.INTEGER.UNSIGNED,
    autoIncrement: true,
    primaryKey: true,
  },
  group_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  gps_company_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  gps_id: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  reg_no: {
    type: DataTypes.CHAR(20),
    allowNull: false,
  },
  stamp_number: {
    type: DataTypes.STRING(15),
    allowNull: true,
  },
  vehicle_plate: {
    type: DataTypes.STRING(15),
    allowNull: true,
  },
  type: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '1',
  },
  latitude: {
    type: DataTypes.CHAR(10),
    allowNull: true,
  },
  longitude: {
    type: DataTypes.CHAR(10),
    allowNull: true,
  },
  current_location: {
    type: DataTypes.CHAR(255),
    allowNull: true,
  },
  volume: {
    type: DataTypes.DECIMAL(10, 4),
    allowNull: true,
  },
  weight: {
    type: DataTypes.DECIMAL(10, 4),
    allowNull: true,
  },
  length: {
    type: DataTypes.DECIMAL(10, 4),
    allowNull: true,
  },
  height: {
    type: DataTypes.DECIMAL(10, 4),
    allowNull: true,
  },
  width: {
    type: DataTypes.DECIMAL(10, 4),
    allowNull: true,
  },
  status: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '1',
  },
  active: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '1',
  },
  repair_distance: {
    type: DataTypes.DECIMAL(18, 4),
    allowNull: true,
  },
  repair_date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  ins_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  upd_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  ins_date: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: DataTypes.NOW,
  },
  upd_date: {
    type: DataTypes.DATE,
    allowNull: true,
  },
  del_flag: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '0',
  },
  is_subcontract: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
    defaultValue: false,
  },
  fixed_route: {
    type: DataTypes.CHAR(255),
    allowNull: false,
    defaultValue: '0',
  },
  direction: {
    type: DataTypes.CHAR(10),
    allowNull: true,
  },
  speed: {
    type: DataTypes.CHAR(4),
    allowNull: true,
  },
  temperature: {
    type: DataTypes.DECIMAL(8, 2),
    allowNull: true,
  },
  contractor_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  secondary_temperature: {
    type: DataTypes.DECIMAL(8, 2),
    allowNull: true,
  },
  tertiary_temperature: {
    type: DataTypes.DECIMAL(8, 2),
    allowNull: true,
  },
  goods_position: {
    type: DataTypes.STRING(10),
    allowNull: false,
    defaultValue: '1',
  },
  is_camera: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
  },
  camera_id: {
    type: DataTypes.STRING(255),
    allowNull: false,
  },
  device_id: {
    type: DataTypes.STRING(255),
    allowNull: false,
  },
  plate_number: {
    type: DataTypes.STRING(20),
    allowNull: true,
  },
  department_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  is_passenger_transfer: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
    defaultValue: false,
  },
  seat_quantity: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  seats: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  partner_vehicle_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  container_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  trailer_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  group_fee_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
}, {
  sequelize,
  tableName: 'vehicle',
  timestamps: false,
  charset: 'utf8mb3',
  collate: 'utf8mb3_unicode_ci',
  indexes: [
    {
      name: 'vehicle_reg_no_index',
      fields: ['reg_no'],
    },
  ],
});

export default Vehicle;
