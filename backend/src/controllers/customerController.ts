import { Request, Response, NextFunction } from 'express';
import { getAllCustomers } from '../services/customerService';
import HttpError from '../utils/HttpErrorUtils';

export async function getAllCustomersController(req: Request, res: Response, next: NextFunction) {
  try {
    const customers = await getAllCustomers();

    if (!customers.length) {
      throw new HttpError('Không có khách hàng nào', 400, 'NO_CUSTOMERS_FOUND');
    }

    return res.success(customers);
  } catch (error) {
    next(error);
  }
}