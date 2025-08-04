import express from 'express';


import customerRoute from './customerRoute';
import OrderRoute from './orderRoute';

const router = express.Router();

// Mount các route API
router.use('/customers', customerRoute);
router.use('/orders', OrderRoute);

export default router;
