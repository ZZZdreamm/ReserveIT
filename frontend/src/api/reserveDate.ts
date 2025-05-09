import { axiosBase } from "@/config/requestsConfig";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export async function reserveDate(subjectDate: SubjectDateDto) {
    const response = await axiosBase.post("/reserveDate", subjectDate);
    return response;
}