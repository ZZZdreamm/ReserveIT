import { getCurrentUser } from "@/api/getCurrentUser";
import { ACCESS_TOKEN } from "@/config/constants";
import { axiosBase } from "@/config/requestsConfig";
import { redirect } from "next/navigation";
import { useEffect, useState } from "react";

export function useCurrentUser() {
  const [currentUser, setCurrentUser] = useState<any>(null);
  const [authenticated, setAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
    getCurrentUser()
      .then((user) => {
        setCurrentUser(user.data);
        setAuthenticated(true);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    if (!authenticated && !currentUser) return;
    axiosBase.interceptors.request.use((config) => {
      config.headers["Authorization"] = `Bearer ${localStorage.getItem(
        ACCESS_TOKEN
      )}`;
      return config;
    });
  }, [authenticated, currentUser]);

  return {
    mounted,
    currentUser,
    authenticated,
    loading,
    setAuthenticated,
    setCurrentUser,
  };
}
