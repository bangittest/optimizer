import { DataTypes, Model, Optional } from 'sequelize';
import sequelize from '../../config/db';

interface LocationAttributes {
  id: number;
  title?: string | null;
  code?: string | null;
  code_of_customer?: string | null;
  address?: string | null;
  full_address?: string | null;
  address_auto_code?: string | null;
  province_id?: string | null;
  district_id?: string | null;
  ward_id?: string | null;
  latitude?: string | null;
  longitude?: string | null;
  customer_id?: number | null;
  location_type_id?: number | null;
  limited_day?: number | null;
  location_group_id?: number | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  is_transit_location?: number | null;
  is_shift_fee?: number | null;
  version: number;
  country_id: string;
}

type LocationCreationAttributes = Optional<
  LocationAttributes,
  | 'id'
  | 'title'
  | 'code'
  | 'code_of_customer'
  | 'address'
  | 'full_address'
  | 'address_auto_code'
  | 'province_id'
  | 'district_id'
  | 'ward_id'
  | 'latitude'
  | 'longitude'
  | 'customer_id'
  | 'location_type_id'
  | 'limited_day'
  | 'location_group_id'
  | 'upd_id'
  | 'upd_date'
  | 'is_transit_location'
  | 'is_shift_fee'
>;

class Location
  extends Model<LocationAttributes, LocationCreationAttributes>
  implements LocationAttributes
{
  public id!: number;
  public title!: string | null;
  public code!: string | null;
  public code_of_customer!: string | null;
  public address!: string | null;
  public full_address!: string | null;
  public address_auto_code!: string | null;
  public province_id!: string | null;
  public district_id!: string | null;
  public ward_id!: string | null;
  public latitude!: string | null;
  public longitude!: string | null;
  public customer_id!: number | null;
  public location_type_id!: number | null;
  public limited_day!: number | null;
  public location_group_id!: number | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public is_transit_location!: number | null;
  public is_shift_fee!: number | null;
  public version!: number;
  public country_id!: string;
}

Location.init(
  {
    id: {
      type: DataTypes.INTEGER.UNSIGNED,
      autoIncrement: true,
      primaryKey: true
    },
    title: {
      type: DataTypes.STRING,
      allowNull: true
    },
    code: {
      type: DataTypes.STRING(50),
      allowNull: true
    },
    code_of_customer: {
      type: DataTypes.STRING(50),
      allowNull: true
    },
    address: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    full_address: {
      type: DataTypes.STRING(500),
      allowNull: true
    },
    address_auto_code: {
      type: DataTypes.STRING(100),
      allowNull: true
    },
    province_id: {
      type: DataTypes.STRING(5),
      allowNull: true
    },
    district_id: {
      type: DataTypes.STRING(5),
      allowNull: true
    },
    ward_id: {
      type: DataTypes.STRING(5),
      allowNull: true
    },
    latitude: {
      type: DataTypes.CHAR(50),
      allowNull: true
    },
    longitude: {
      type: DataTypes.CHAR(50),
      allowNull: true
    },
    customer_id: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    location_type_id: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    limited_day: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true
    },
    location_group_id: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    ins_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      comment: 'Created by column'
    },
    upd_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
      comment: 'Updated by column'
    },
    ins_date: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: DataTypes.NOW,
      comment: 'Created at column'
    },
    upd_date: {
      type: DataTypes.DATE,
      allowNull: true,
      comment: 'Updated at column'
    },
    del_flag: {
      type: DataTypes.CHAR,
      allowNull: false,
      defaultValue: '0',
      comment: 'Delete flag column'
    },
    is_transit_location: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: 0,
      comment: 'Điểm trung chuyển'
    },
    is_shift_fee: {
      type: DataTypes.TINYINT,
      allowNull: true
    },
    version: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: 2
    },
    country_id: {
      type: DataTypes.STRING(5),
      allowNull: false,
      defaultValue: 'VN'
    }
  },
  {
    sequelize,
    tableName: 'locations',
    timestamps: false,
    charset: 'utf8mb3',
    collate: 'utf8mb3_unicode_ci'
  }
);

export default Location;
