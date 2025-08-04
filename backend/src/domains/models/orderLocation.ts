import { DataTypes, Model, Optional } from 'sequelize';
import sequelize from '../../config/db'; // đường dẫn config db của bạn

interface OrderLocationAttributes {
  id: number;
  order_id: number;
  location_id: number;
  type?: number | null;
  date?: Date | null;
  date_reality?: Date | null;
  time?: string | null;
  time_reality?: string | null;
  note?: string | null;
  ins_id: number;
  upd_id?: number | null;
  ins_date: Date;
  upd_date?: Date | null;
  del_flag: string;
  contact_name: string;
  contact_mobile_no: string;
}

interface OrderLocationCreationAttributes extends Optional<OrderLocationAttributes, 'id' | 'type' | 'date' | 'date_reality' | 'time' | 'time_reality' | 'note' | 'upd_id' | 'upd_date'> {}

class OrderLocation extends Model<OrderLocationAttributes, OrderLocationCreationAttributes> implements OrderLocationAttributes {
  public id!: number;
  public order_id!: number;
  public location_id!: number;
  public type!: number | null;
  public date!: Date | null;
  public date_reality!: Date | null;
  public time!: string | null;
  public time_reality!: string | null;
  public note!: string | null;
  public ins_id!: number;
  public upd_id!: number | null;
  public ins_date!: Date;
  public upd_date!: Date | null;
  public del_flag!: string;
  public contact_name!: string;
  public contact_mobile_no!: string;
}

OrderLocation.init({
  id: {
    type: DataTypes.INTEGER.UNSIGNED,
    autoIncrement: true,
    primaryKey: true,
  },
  order_id: {
    type: DataTypes.INTEGER.UNSIGNED,
    allowNull: false,
  },
  location_id: {
    type: DataTypes.INTEGER.UNSIGNED,
    allowNull: false,
  },
  type: {
    type: DataTypes.SMALLINT,
    allowNull: true,
  },
  date: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  date_reality: {
    type: DataTypes.DATEONLY,
    allowNull: true,
  },
  time: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  time_reality: {
    type: DataTypes.TIME,
    allowNull: true,
  },
  note: {
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
  contact_name: {
    type: DataTypes.STRING(255),
    allowNull: false,
  },
  contact_mobile_no: {
    type: DataTypes.STRING(20),
    allowNull: false,
  },
}, {
  sequelize,
  tableName: 'order_locations',
  timestamps: false,
  charset: 'utf8mb3',
  collate: 'utf8mb3_unicode_ci',
});

export default OrderLocation;
