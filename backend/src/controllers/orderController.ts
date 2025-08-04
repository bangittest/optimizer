import { Request, Response, NextFunction } from 'express';
import * as OrderService from '../services/orderService';
import HttpError from '../utils/HttpErrorUtils';

export const getOrderDetail = async (req: Request, res: Response, next: NextFunction) => {
  try {
  
    const order = await OrderService.getOrderDetail();
    res.success(order);
  } catch (error) {
    next(error);
  }
};
