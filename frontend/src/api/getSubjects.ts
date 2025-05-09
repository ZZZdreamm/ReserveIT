import { axiosBase } from "@/config/requestsConfig";
import { MockedDaysHours } from "@/mocks/MockedDaysHours";
import { MockedSubjects } from "@/mocks/MockedSubjects";

export async function getSubjects() {
  // const response = await axiosBase.post<SubjectDate[]>('/subjects/subjects');
  // return response.data;
  return MockedSubjects;
}
