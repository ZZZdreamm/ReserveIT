import { axiosBase } from "@/config/requestsConfig";

export function login(loginRequest: any){
    return axiosBase.post("/auth/login", loginRequest);
}