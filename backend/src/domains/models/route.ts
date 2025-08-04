import { DataTypes, Model } from 'sequelize';
import sequelize from '../../config/db';

interface RouteAttributes {
  id: number;
  route_code: string;
  name: string;
  route_status?: number | null;
  ETD_date?: Date | null;
  ETD_date_reality?: Date | null;
  ETD_time_reality?: string | null;
  ETD_time?: string | null;
  ETA_date?: Date | null;
  ETA_time?: string | null;
  ETA_date_reality?: Date | null;
  ETA_time_reality?: string | null;
  location_destination_id?: number | null;
  location_arrival_id?: number | null;
  gps_distance?: number | null;
  quota_id?: number | null;
  vehicle_id?: number | null;
  driver_id?: number | null;
  final_cost: number;
  admin_cost: number;
  driver_cost: number;
  price_quote_amount: number;
  payroll_amount: number;
  route_note?: string | null;
  capacity_weight_ratio?: number | null;
  capacity_volume_ratio?: number | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  is_approved?: string | null;
  approved_id?: number | null;
  approved_date?: Date | null;
  approved_note?: string | null;
  order_codes?: string | null;
  order_notes?: string | null;
  customer_ids?: string | null;
  volume: number;
  weight: number;
  quantity: number;
  total_amount: number;
  count_order: number;
  vin_nos?: string | null;
  model_nos?: string | null;
  is_lock?: string | null;
  gps_distance_reality?: number | null;
  km_des?: string | null;
  gps_distance_final?: number | null;
  goods_group_id?: number | null;
  fixed_route: string;
  recieved_tray_quantities?: number | null;
  road_id?: number | null;
  secondary_driver_id?: number | null;
  partner_route_id?: number | null;
  road_schedule_detail_id?: number | null;
  route_type: number;
  is_automatic: boolean;
  total_final_amount: number;
  quota_km?: number | null;
  is_gps_distance: boolean;
  fuel_reality?: number | null;
  advance_amount?: number | null;
  total_weight?: number | null;
  fuel_quota?: number | null;
}

class Route extends Model<RouteAttributes> implements RouteAttributes {
  public id!: number;
  public route_code!: string;
  public name!: string;
  public route_status!: number | null;
  public ETD_date!: Date | null;
  public ETD_date_reality!: Date | null;
  public ETD_time_reality!: string | null;
  public ETD_time!: string | null;
  public ETA_date!: Date | null;
  public ETA_time!: string | null;
  public ETA_date_reality!: Date | null;
  public ETA_time_reality!: string | null;
  public location_destination_id!: number | null;
  public location_arrival_id!: number | null;
  public gps_distance!: number | null;
  public quota_id!: number | null;
  public vehicle_id!: number | null;
  public driver_id!: number | null;
  public final_cost!: number;
  public admin_cost!: number;
  public driver_cost!: number;
  public price_quote_amount!: number;
  public payroll_amount!: number;
  public route_note!: string | null;
  public capacity_weight_ratio!: number | null;
  public capacity_volume_ratio!: number | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public is_approved!: string | null;
  public approved_id!: number | null;
  public approved_date!: Date | null;
  public approved_note!: string | null;
  public order_codes!: string | null;
  public order_notes!: string | null;
  public customer_ids!: string | null;
  public volume!: number;
  public weight!: number;
  public quantity!: number;
  public total_amount!: number;
  public count_order!: number;
  public vin_nos!: string | null;
  public model_nos!: string | null;
  public is_lock!: string | null;
  public gps_distance_reality!: number | null;
  public km_des!: string | null;
  public gps_distance_final!: number | null;
  public goods_group_id!: number | null;
  public fixed_route!: string;
  public recieved_tray_quantities!: number | null;
  public road_id!: number | null;
  public secondary_driver_id!: number | null;
  public partner_route_id!: number | null;
  public road_schedule_detail_id!: number | null;
  public route_type!: number;
  public is_automatic!: boolean;
  public total_final_amount!: number;
  public quota_km!: number | null;
  public is_gps_distance!: boolean;
  public fuel_reality!: number | null;
  public advance_amount!: number | null;
  public total_weight!: number | null;
  public fuel_quota!: number | null;
}

Route.init({
  id: {
    type: DataTypes.INTEGER.UNSIGNED,
    primaryKey: true,
    autoIncrement: true,
  },
  route_code: {
    type: DataTypes.STRING(255),
    allowNull: false,
  },
  name: {
    type: DataTypes.STRING(255),
    allowNull: false,
  },
  route_status: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  ETD_date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  ETD_date_reality: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  ETD_time_reality: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  ETD_time: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  ETA_date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  ETA_time: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  ETA_date_reality: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  ETA_time_reality: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  location_destination_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  location_arrival_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  gps_distance: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
    defaultValue: 0.0000,
  },
  quota_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  vehicle_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  driver_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  final_cost: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  admin_cost: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  driver_cost: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  price_quote_amount: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
  },
  payroll_amount: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
  },
  route_note: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  capacity_weight_ratio: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
  },
  capacity_volume_ratio: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
  },
  ins_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    comment: 'Created by column',
  },
  upd_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
    comment: 'Updated by column',
  },
  ins_date: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: DataTypes.NOW,
    comment: 'Created at column',
  },
  upd_date: {
    type: DataTypes.DATE,
    allowNull: true,
    comment: 'Updated at column',
  },
  del_flag: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '0',
    comment: 'Delete flag column',
  },
  is_approved: {
    type: DataTypes.CHAR,
    allowNull: true,
    defaultValue: '0',
  },
  approved_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  approved_date: {
    type: DataTypes.DATE,
    allowNull: true,
  },
  approved_note: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  order_codes: {
    type: DataTypes.STRING(500),
    allowNull: true,
  },
  order_notes: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  customer_ids: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  volume: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  weight: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  quantity: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: 0,
  },
  total_amount: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  count_order: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: 0,
  },
  vin_nos: {
    type: DataTypes.STRING(500),
    allowNull: true,
  },
  model_nos: {
    type: DataTypes.STRING(500),
    allowNull: true,
  },
  is_lock: {
    type: DataTypes.STRING(255),
    allowNull: true,
    defaultValue: '0',
  },
  gps_distance_reality: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
    defaultValue: 0.0000,
  },
  km_des: {
    type: DataTypes.STRING(255),
    allowNull: true,
  },
  gps_distance_final: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
  },
  goods_group_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  fixed_route: {
    type: DataTypes.CHAR,
    allowNull: false,
    defaultValue: '0',
    comment: 'Chuyến xe cố định',
  },
  recieved_tray_quantities: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  road_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  secondary_driver_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  partner_route_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  road_schedule_detail_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  route_type: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: 1,
  },
  is_automatic: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
    defaultValue: false,
  },
  total_final_amount: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: false,
    defaultValue: 0.0000,
  },
  quota_km: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
  },
  is_gps_distance: {
    type: DataTypes.BOOLEAN,
    allowNull: false,
    defaultValue: false,
  },
  fuel_reality: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
    defaultValue: 0.0000,
  },
  advance_amount: {
    type: DataTypes.DECIMAL(18,4),
    allowNull: true,
    comment: 'Tiền tạm ứng chuyến',
  },
  total_weight: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  fuel_quota: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
}, {
  sequelize,
  tableName: 'routes',
  timestamps: false,
  charset: 'utf8mb3',
  collate: 'utf8mb3_unicode_ci',
});

export default Route;
