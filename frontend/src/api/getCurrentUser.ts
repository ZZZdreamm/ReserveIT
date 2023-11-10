import { ACCESS_TOKEN, API_BASE_URL } from "@/config/constants";
import { axiosBase } from "@/config/requestsConfig";

export function getCurrentUser() {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }

  return axiosBase.get("/user/me", {
    headers: {
      Authorization: "Bearer " + localStorage.getItem(ACCESS_TOKEN),
    },
  });
}
