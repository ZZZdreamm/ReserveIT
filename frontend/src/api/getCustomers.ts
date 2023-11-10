import { axiosBase } from "@/config/requestsConfig";

export async function getCustomers() {
  const response = await axiosBase.get("/customers");
  return response.data;
}
