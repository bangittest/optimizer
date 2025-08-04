import { QueryTypes } from "sequelize";
import sequelize from "../config/db";
import { OrderDTO } from "../domains/dtos/orderDTO";
import { toNumberOrZero } from "../utils/numberUtils";
import dayjs from "dayjs";

export async function getUnassignedOrders(): Promise<OrderDTO[]> {
  const sql = `
    SELECT 
      o.id, o.order_code, o.customer_id, o.location_destination_id, 
      o.location_arrival_id, o.weight, o.volume, o.precedence,
      CONCAT(DATE_FORMAT(o.ETD_date, '%Y-%m-%d'), 'T', TIME_FORMAT(o.ETD_time, '%H:%i:%s')) AS etd,
      ld.latitude AS dest_lat, ld.longitude AS dest_lng,
      la.latitude AS arrival_lat, la.longitude AS arrival_lng
    FROM orders o
    LEFT JOIN locations ld ON o.location_destination_id = ld.id
    LEFT JOIN locations la ON o.location_arrival_id = la.id
    WHERE o.vehicle_id IS NULL 
      AND o.primary_driver_id IS NULL 
      AND o.del_flag = '0' 
      AND o.status = 2
  `;

  const rows = await sequelize.query(sql, { type: QueryTypes.SELECT });

  return (rows as any[]).map(
    (row): OrderDTO => ({
      id: row.id,
      orderCode: row.order_code,
      customerId: row.customer_id,
      locationDestinationId: row.location_destination_id,
      locationArrivalId: row.location_arrival_id,
      weight: row.weight,
      volume: row.volume,
      precedence: row.precedence,
      etdDate: row.etd
        ? dayjs(row.etd).format("YYYY-MM-DDTHH:mm:ss")
        : dayjs().format("YYYY-MM-DDTHH:mm:ss"),
      destLat: toNumberOrZero(row.dest_lat),
      destLng: toNumberOrZero(row.dest_lng),
      arrivalLat: toNumberOrZero(row.arrival_lat),
      arrivalLng: toNumberOrZero(row.arrival_lng),
    })
  );
}
