import * as OrderRepository from "../repositories/orderRepository";
import * as VehicleRepository from "../repositories/vehicleRepository";
import * as DriverVehicleTeamRepository from "../repositories/driverVehicleTeamRepository";
import * as DriverRepository from "../repositories/driverRepository";
import { getCriteriaList } from "./criteriaManager";
import axios from "axios";

export async function getOrderDetail() {
  const orders = await OrderRepository.getUnassignedOrders();
  const vehicles = await VehicleRepository.getActiveVehicles();
  const driverVehicleTeam =
    await DriverVehicleTeamRepository.getDriverVehicleTeams();
  const drivers = await DriverRepository.getActiveDrivers();
  const criteria = getCriteriaList();

  const payload = {
    criteria,
    orders,
    vehicles,
    driverVehicleTeam,
    drivers,
  };

  const response = await axios.post(
    "http://localhost:8888/api/receiveData",
    payload
  );
  return response.data;
}
