import { DataTypes, Model } from "sequelize";
import sequelize from "../../config/db";

interface CustomerAttributes {
  id: number;
  user_id: number;
  avatar_id?: number | null;
  customer_code: string;
  active: string;
  mobile_no?: string | null;
  identity_no?: string | null;
  full_name: string;
  delegate?: string | null;
  tax_code?: string | null;
  type?: string | null;
  address?: string | null;
  current_address?: string | null;
  birth_date?: Date | null;
  sex?: string | null;
  full_name_accent?: string | null;
  standard_mobile_no?: string | null;
  note?: string | null;
  province_id?: string | null;
  district_id?: string | null;
  ward_id?: string | null;
  longitude?: string | null;
  latitude?: string | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  account_bank?: string | null;
  bank_name?: string | null;
}

class Customer extends Model<CustomerAttributes> implements CustomerAttributes {
  public id!: number;
  public user_id!: number;
  public avatar_id!: number | null;
  public customer_code!: string;
  public active!: string;
  public mobile_no!: string | null;
  public identity_no!: string | null;
  public full_name!: string;
  public delegate!: string | null;
  public tax_code!: string | null;
  public type!: string | null;
  public address!: string | null;
  public current_address!: string | null;
  public birth_date!: Date | null;
  public sex!: string | null;
  public full_name_accent!: string | null;
  public standard_mobile_no!: string | null;
  public note!: string | null;
  public province_id!: string | null;
  public district_id!: string | null;
  public ward_id!: string | null;
  public longitude!: string | null;
  public latitude!: string | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public account_bank!: string | null;
  public bank_name!: string | null;
}

Customer.init(
  {
    id: {
      type: DataTypes.INTEGER.UNSIGNED,
      primaryKey: true,
      autoIncrement: false,
    }, // autoIncrement false nếu DB có sẵn tự tăng
    user_id: { type: DataTypes.INTEGER, allowNull: false },
    avatar_id: { type: DataTypes.INTEGER, allowNull: true },
    customer_code: { type: DataTypes.STRING(50), allowNull: false },
    active: { type: DataTypes.CHAR(1), allowNull: false, defaultValue: "1" },
    mobile_no: { type: DataTypes.CHAR(20), allowNull: true },
    identity_no: { type: DataTypes.STRING(20), allowNull: true },
    full_name: { type: DataTypes.STRING(250), allowNull: false },
    delegate: { type: DataTypes.STRING(255), allowNull: true },
    tax_code: { type: DataTypes.STRING(255), allowNull: true },
    type: { type: DataTypes.STRING(255), allowNull: true },
    address: { type: DataTypes.STRING(255), allowNull: true },
    current_address: { type: DataTypes.STRING(255), allowNull: true },
    birth_date: { type: DataTypes.DATEONLY, allowNull: true },
    sex: { type: DataTypes.CHAR(10), allowNull: true },
    full_name_accent: { type: DataTypes.STRING(250), allowNull: true },
    standard_mobile_no: { type: DataTypes.CHAR(20), allowNull: true },
    note: { type: DataTypes.TEXT, allowNull: true },
    province_id: { type: DataTypes.STRING(5), allowNull: true },
    district_id: { type: DataTypes.STRING(5), allowNull: true },
    ward_id: { type: DataTypes.STRING(5), allowNull: true },
    longitude: { type: DataTypes.CHAR(50), allowNull: true },
    latitude: { type: DataTypes.CHAR(50), allowNull: true },
    ins_id: { type: DataTypes.INTEGER, allowNull: false },
    upd_id: { type: DataTypes.INTEGER, allowNull: true },
    ins_date: { type: DataTypes.DATE, allowNull: false },
    upd_date: { type: DataTypes.DATE, allowNull: true },
    del_flag: { type: DataTypes.CHAR(1), allowNull: false, defaultValue: "0" },
    account_bank: { type: DataTypes.STRING(20), allowNull: true },
    bank_name: { type: DataTypes.STRING(255), allowNull: true },
  },
  {
    sequelize,
    tableName: "customer",
    timestamps: false,
    freezeTableName: true,
  }
);

export default Customer;
