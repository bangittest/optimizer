import express from 'express';
import * as OrderController from '../controllers/orderController';

const router = express.Router();

router.get('', OrderController.getOrderDetail);

export default router;
