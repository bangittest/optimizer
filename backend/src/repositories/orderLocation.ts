import OrderLocation from '../domains/models/orderLocation';

export async function findOrderLocationsByOrderId(orderId: number) {
  return OrderLocation.findAll({
    where: {
      order_id: orderId,
      del_flag: '0',
    }
  });
}
