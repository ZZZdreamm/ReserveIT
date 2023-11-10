import { axiosBase } from "@/config/requestsConfig";

export async function getAllServices(){
    const response = await axiosBase.get("/services/all");
    return response.data;
}