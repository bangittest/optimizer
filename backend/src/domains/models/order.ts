import { DataTypes, Model, Optional } from "sequelize";
import sequelize from "../../config/db";

interface OrderAttributes {
  id: number;
  order_code?: string | null;
  order_no?: string | null;
  model_no?: string | null;
  vin_no?: string | null;
  order_purchasing_no?: string | null;
  bill_no?: string | null;
  status?: string | null;
  secondary_driver_id?: number | null;
  primary_driver_id?: number | null;
  vehicle_id?: number | null;
  route_id?: number | null;
  order_customer_id?: number | null;
  order_date?: Date | null;
  contract_no?: string | null;
  customer_id?: number | null;
  customer_name?: string | null;
  customer_mobile_no?: string | null;
  ETD_date?: Date | null;
  ETD_time?: string | null; 
  ETD_date_reality?: Date | null;
  ETD_time_reality?: string | null;
  location_destination_id?: number | null;
  contact_name_destination?: string | null;
  contact_mobile_no_destination?: string | null;
  contact_email_destination?: string | null;
  loading_destination?: number | null;
  loading_destination_fee?: string | null; 
  informative_destination?: string | null;
  ETA_date?: Date | null;
  ETA_time?: string | null;
  ETA_date_reality?: Date | null;
  ETA_time_reality?: string | null;
  location_arrival_id?: number | null;
  contact_name_arrival?: string | null;
  contact_mobile_no_arrival?: string | null;
  contact_email_arrival?: string | null;
  loading_arrival?: number | null;
  loading_arrival_fee?: string | null;
  informative_arrival?: string | null;
  good_details?: string | null;
  is_insured_goods?: boolean | null;
  amount?: string | null;
  cod_amount?: string | null;
  commission_amount?: string | null;
  commission_value?: string | null;
  commission_type?: number | null;
  quantity?: string | null;
  volume?: string | null;
  weight?: string | null;
  precedence?: number | null;
  note?: string | null;
  description?: string | null;
  remark?: string | null;
  bill_print_url?: string | null;
  draft?: boolean | null;
  gps_distance: string; // not null, decimal
  order_review_id?: number | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  currency_id?: number | null;
  cod_currency_id?: number | null;
  is_merge_item?: string | null;
  source_create: number;
  quantity_order_customer?: string | null;
  weight_order_customer?: string | null;
  volume_order_customer?: string | null;
  number_of_delivery_points?: string | null;
  number_of_arrival_points?: string | null;
  is_lock?: string | null;
  destination_location_correct: string;
  arrival_location_correct: string;
  subcontractor_name?: string | null;
  trailer_id?: number | null;
  sub_vehicle_group?: string | null;
  sub_driver_name?: string | null;
  sub_mobile_no?: string | null;
  destination_time?: Date | null;
  arrival_time?: Date | null;
  goods_group_id?: number | null;
  expected_vehicle_group_id?: number | null;
  road_id?: number | null;
  final_amount?: string | null;
  container_id?: number | null;
  shipping_line_id?: number | null;
  container_expired_date?: Date | null;
  road_type_id?: number | null;
  total_cost_amount?: string | null;
  contractor_id?: number | null;
  project_id?: number | null;
}

interface OrderCreationAttributes
  extends Optional<
    OrderAttributes,
    | "id"
    | "gps_distance"
    | "ins_id"
    | "ins_date"
    | "del_flag"
    | "source_create"
    | "destination_location_correct"
    | "arrival_location_correct"
  > {}

export class Order
  extends Model<OrderAttributes, OrderCreationAttributes>
  implements OrderAttributes
{
  public id!: number;
  public order_code!: string | null;
  public order_no!: string | null;
  public model_no!: string | null;
  public vin_no!: string | null;
  public order_purchasing_no!: string | null;
  public bill_no!: string | null;
  public status!: string | null;
  public secondary_driver_id!: number | null;
  public primary_driver_id!: number | null;
  public vehicle_id!: number | null;
  public route_id!: number | null;
  public order_customer_id!: number | null;
  public order_date!: Date | null;
  public contract_no!: string | null;
  public customer_id!: number | null;
  public customer_name!: string | null;
  public customer_mobile_no!: string | null;
  public ETD_date!: Date | null;
  public ETD_time!: string | null;
  public ETD_date_reality!: Date | null;
  public ETD_time_reality!: string | null;
  public location_destination_id!: number | null;
  public contact_name_destination!: string | null;
  public contact_mobile_no_destination!: string | null;
  public contact_email_destination!: string | null;
  public loading_destination!: number | null;
  public loading_destination_fee!: string | null;
  public informative_destination!: string | null;
  public ETA_date!: Date | null;
  public ETA_time!: string | null;
  public ETA_date_reality!: Date | null;
  public ETA_time_reality!: string | null;
  public location_arrival_id!: number | null;
  public contact_name_arrival!: string | null;
  public contact_mobile_no_arrival!: string | null;
  public contact_email_arrival!: string | null;
  public loading_arrival!: number | null;
  public loading_arrival_fee!: string | null;
  public informative_arrival!: string | null;
  public good_details!: string | null;
  public is_insured_goods!: boolean | null;
  public amount!: string | null;
  public cod_amount!: string | null;
  public commission_amount!: string | null;
  public commission_value!: string | null;
  public commission_type!: number | null;
  public quantity!: string | null;
  public volume!: string | null;
  public weight!: string | null;
  public precedence!: number | null;
  public note!: string | null;
  public description!: string | null;
  public remark!: string | null;
  public bill_print_url!: string | null;
  public draft!: boolean | null;
  public gps_distance!: string;
  public order_review_id!: number | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public currency_id!: number | null;
  public cod_currency_id!: number | null;
  public is_merge_item!: string | null;
  public source_create!: number;
  public quantity_order_customer!: string | null;
  public weight_order_customer!: string | null;
  public volume_order_customer!: string | null;
  public number_of_delivery_points!: string | null;
  public number_of_arrival_points!: string | null;
  public is_lock!: string | null;
  public destination_location_correct!: string;
  public arrival_location_correct!: string;
  public subcontractor_name!: string | null;
  public trailer_id!: number | null;
  public sub_vehicle_group!: string | null;
  public sub_driver_name!: string | null;
  public sub_mobile_no!: string | null;
  public destination_time!: Date | null;
  public arrival_time!: Date | null;
  public goods_group_id!: number | null;
  public expected_vehicle_group_id!: number | null;
  public road_id!: number | null;
  public final_amount!: string | null;
  public container_id!: number | null;
  public shipping_line_id!: number | null;
  public container_expired_date!: Date | null;
  public road_type_id!: number | null;
  public total_cost_amount!: string | null;
  public contractor_id!: number | null;
  public project_id!: number | null;
}

Order.init(
  {
    id: {
      type: DataTypes.INTEGER.UNSIGNED,
      autoIncrement: true,
      primaryKey: true,
    },
    order_code: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    order_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    model_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    vin_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    order_purchasing_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    bill_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    status: {
      type: DataTypes.STRING(30),
      allowNull: true,
    },
    secondary_driver_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    primary_driver_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    vehicle_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    route_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    order_customer_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    order_date: {
      type: DataTypes.DATEONLY,
      allowNull: true,
    },
    contract_no: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    customer_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: 0,
    },
    customer_name: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    customer_mobile_no: {
      type: DataTypes.STRING(20),
      allowNull: true,
    },
    ETD_date: {
      type: DataTypes.DATEONLY,
      allowNull: true,
    },
    ETD_time: {
      type: DataTypes.TIME,
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
    location_destination_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: 0,
    },
    contact_name_destination: {
      type: DataTypes.STRING(100),
      allowNull: true,
    },
    contact_mobile_no_destination: {
      type: DataTypes.STRING(20),
      allowNull: true,
    },
    contact_email_destination: {
      type: DataTypes.STRING(250),
      allowNull: true,
    },
    loading_destination: {
      type: DataTypes.SMALLINT,
      allowNull: true,
      defaultValue: 0,
    },
    loading_destination_fee: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
      defaultValue: "0.0000",
    },
    informative_destination: {
      type: DataTypes.TEXT,
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
    location_arrival_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: 0,
    },
    contact_name_arrival: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    contact_mobile_no_arrival: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    contact_email_arrival: {
      type: DataTypes.STRING(250),
      allowNull: true,
    },
    loading_arrival: {
      type: DataTypes.SMALLINT,
      allowNull: true,
      defaultValue: 0,
    },
    loading_arrival_fee: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
      defaultValue: "0.0000",
    },
    informative_arrival: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    good_details: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    is_insured_goods: {
      type: DataTypes.BOOLEAN,
      allowNull: true,
    },
    amount: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    cod_amount: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    commission_amount: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    commission_value: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    commission_type: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    quantity: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    volume: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    weight: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    precedence: {
      type: DataTypes.SMALLINT,
      allowNull: true,
      defaultValue: 0,
    },
    note: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    description: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    remark: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    bill_print_url: {
      type: DataTypes.STRING(200),
      allowNull: true,
    },
    draft: {
      type: DataTypes.BOOLEAN,
      allowNull: true,
      defaultValue: false,
    },
    gps_distance: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: false,
      defaultValue: "0.0000",
    },
    order_review_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    ins_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      comment: "Created by column",
    },
    upd_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
      comment: "Updated by column",
    },
    ins_date: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: DataTypes.NOW,
      comment: "Created at column",
    },
    upd_date: {
      type: DataTypes.DATE,
      allowNull: true,
      comment: "Updated at column",
    },
    del_flag: {
      type: DataTypes.CHAR(1),
      allowNull: false,
      defaultValue: "0",
      comment: "Delete flag column",
    },
    currency_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    cod_currency_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    is_merge_item: {
      type: DataTypes.STRING(255),
      allowNull: true,
      defaultValue: "0",
    },
    source_create: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: 0,
    },
    quantity_order_customer: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    weight_order_customer: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    volume_order_customer: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    number_of_delivery_points: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    number_of_arrival_points: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    is_lock: {
      type: DataTypes.STRING(255),
      allowNull: true,
      defaultValue: "0",
    },
    destination_location_correct: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: "1",
    },
    arrival_location_correct: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: "1",
    },
    subcontractor_name: {
      type: DataTypes.STRING(500),
      allowNull: true,
    },
    trailer_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    sub_vehicle_group: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    sub_driver_name: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    sub_mobile_no: {
      type: DataTypes.STRING(255),
      allowNull: true,
    },
    destination_time: {
      type: DataTypes.DATE,
      allowNull: true,
    },
    arrival_time: {
      type: DataTypes.DATE,
      allowNull: true,
    },
    goods_group_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    expected_vehicle_group_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    road_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    final_amount: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    container_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    shipping_line_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    container_expired_date: {
      type: DataTypes.DATE,
      allowNull: true,
    },
    road_type_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    total_cost_amount: {
      type: DataTypes.DECIMAL(18, 4),
      allowNull: true,
    },
    contractor_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    project_id: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
  },
  {
    sequelize,
    tableName: "orders",
    timestamps: false,
  }
);

export default Order;
