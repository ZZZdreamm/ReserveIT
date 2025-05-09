import { axiosBase } from "@/config/requestsConfig";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export async function getFreeDates(){
    const response = await axiosBase.get<SubjectDateDto[]>('/subjects/freeDates');
    return response.data;
}