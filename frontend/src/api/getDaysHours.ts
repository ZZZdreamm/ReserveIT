import { axiosBase } from "@/config/requestsConfig";
import { MockedDaysHours } from "@/mocks/MockedDaysHours";

export async function getDaysHours(date: Date) {
  // const response = await axiosBase.post<SubjectDate[]>('/subjects/daysHoyrs', date);
  // return response.data;
  return MockedDaysHours;
}
