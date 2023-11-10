import { axiosBase } from "@/config/requestsConfig";

export async function getService(id: number) {
  const response = await axiosBase.get(`/services/${id}`);
  return response.data;
}
