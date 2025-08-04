import { DataTypes, Model, Optional } from 'sequelize';
import sequelize from '../../config/db';

interface DriverAttributes {
  id: number;
  code?: string | null;
  user_id: number;
  active: string;
  mobile_no?: string | null;
  identity_no?: string | null;
  full_name: string;
  avatar_id?: string | null;
  address?: string | null;
  current_address?: string | null;
  birth_date?: Date | null;
  sex?: string | null;
  full_name_accent?: string | null;
  standard_mobile_no?: string | null;
  note?: string | null;
  working_status?: number | null;
  hometown?: string | null;
  vehicle_team_id?: number | null;
  experience_drive?: number | null;
  experience_work?: number | null;
  work_date?: Date | null;
  driver_vehicle_id?: number | null;
  vehicle_old?: string | null;
  evaluate?: string | null;
  rank?: string | null;
  work_description?: string | null;
  id_no?: string | null;
  driver_license?: string | null;
  driver_license_number?: string | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
}

interface DriverCreationAttributes extends Optional<
  DriverAttributes,
  | 'id'
  | 'code'
  | 'mobile_no'
  | 'identity_no'
  | 'avatar_id'
  | 'address'
  | 'current_address'
  | 'birth_date'
  | 'sex'
  | 'full_name_accent'
  | 'standard_mobile_no'
  | 'note'
  | 'working_status'
  | 'hometown'
  | 'vehicle_team_id'
  | 'experience_drive'
  | 'experience_work'
  | 'work_date'
  | 'driver_vehicle_id'
  | 'vehicle_old'
  | 'evaluate'
  | 'rank'
  | 'work_description'
  | 'id_no'
  | 'driver_license'
  | 'driver_license_number'
  | 'upd_id'
  | 'upd_date'
> {}

class Driver extends Model<DriverAttributes, DriverCreationAttributes> implements DriverAttributes {
  public id!: number;
  public code!: string | null;
  public user_id!: number;
  public active!: string;
  public mobile_no!: string | null;
  public identity_no!: string | null;
  public full_name!: string;
  public avatar_id!: string | null;
  public address!: string | null;
  public current_address!: string | null;
  public birth_date!: Date | null;
  public sex!: string | null;
  public full_name_accent!: string | null;
  public standard_mobile_no!: string | null;
  public note!: string | null;
  public working_status!: number | null;
  public hometown!: string | null;
  public vehicle_team_id!: number | null;
  public experience_drive!: number | null;
  public experience_work!: number | null;
  public work_date!: Date | null;
  public driver_vehicle_id!: number | null;
  public vehicle_old!: string | null;
  public evaluate!: string | null;
  public rank!: string | null;
  public work_description!: string | null;
  public id_no!: string | null;
  public driver_license!: string | null;
  public driver_license_number!: string | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
}

Driver.init({
  id: {
    type: DataTypes.INTEGER.UNSIGNED,
    autoIncrement: true,
    primaryKey: true,
  },
  code: {
    type: DataTypes.STRING(50),
    allowNull: true,
  },
  user_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  active: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '1',
  },
  mobile_no: {
    type: DataTypes.CHAR(20),
    allowNull: true,
  },
  identity_no: {
    type: DataTypes.STRING(20),
    allowNull: true,
  },
  full_name: {
    type: DataTypes.STRING(250),
    allowNull: false,
  },
  avatar_id: {
    type: DataTypes.STRING(100),
    allowNull: true,
  },
  address: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  current_address: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  birth_date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  sex: {
    type: DataTypes.CHAR(10),
    allowNull: true,
  },
  full_name_accent: {
    type: DataTypes.STRING(250),
    allowNull: true,
  },
  standard_mobile_no: {
    type: DataTypes.CHAR(20),
    allowNull: true,
  },
  note: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  working_status: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  hometown: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  vehicle_team_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  experience_drive: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  experience_work: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  work_date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  driver_vehicle_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  vehicle_old: {
    type: DataTypes.STRING(500),
    allowNull: true,
  },
  evaluate: {
    type: DataTypes.STRING(1000),
    allowNull: true,
  },
  rank: {
    type: DataTypes.STRING(500),
    allowNull: true,
  },
  work_description: {
    type: DataTypes.STRING(1000),
    allowNull: true,
  },
  id_no: {
    type: DataTypes.STRING(20),
    allowNull: true,
  },
  driver_license: {
    type: DataTypes.STRING(100),
    allowNull: true,
  },
  driver_license_number: {
    type: DataTypes.TEXT,
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
}, {
  sequelize,
  tableName: 'drivers',
  timestamps: false,
  charset: 'utf8mb3',
  collate: 'utf8mb3_unicode_ci',
});

export default Driver;
