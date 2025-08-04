import express from 'express';
import cors from 'cors';
import routes from './routes';
import responseHandler from './middlewares/responseHandlerMiddleware';
import { errorHandler } from './middlewares/errorHandlerMiddleware';
import { loggerMiddleware } from './middlewares/loggerMiddleware';
import { callOptimizerAPI } from './services/optimizerService';
// import redis from './config/redis'

const app = express();

app.use(cors({
  origin: process.env.CLIENT_URL || '*',
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization'],
}));

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(loggerMiddleware);
// Middleware custom response
app.use(responseHandler);

// API routes
app.use('/api/v1', routes);

// Error handler cuối cùng
app.use(errorHandler);







// async function main() {
//   await redis.set('example_key', 'Hello Redis via .env');
//   const value = await redis.get('example_key');
//   console.log('Redis value:', value);
// }

// main();

export default app;