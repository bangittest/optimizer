import express from 'express';
import { getAllCustomersController } from '../controllers/customerController';

const router = express.Router();

router.get('/', getAllCustomersController);

export default router;
