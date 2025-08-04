import { findAllActiveCustomers } from '../repositories/customerRepository';
import { toCustomerResponseDTO } from '../mappers/customerMapper';
import { CustomerResponseDTO } from '../domains/dtos/response/customerResponseDTO';

export async function getAllCustomers(): Promise<CustomerResponseDTO[]> {
  const data = await findAllActiveCustomers();
  return data.map(toCustomerResponseDTO);
}