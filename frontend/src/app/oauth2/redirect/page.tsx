"use client";

import { ACCESS_TOKEN } from "@/config/constants";
import { redirect } from "next/navigation";
import { useEffect } from "react";

export default function OAuth2RedirectHandler() {
  function getUrlParameter(name: string) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");

    var results = regex.exec(window.location.search);
    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  }

  useEffect(() => {
    const token = getUrlParameter("token");
    const error = getUrlParameter("error");
    if (token) {
      localStorage.setItem(ACCESS_TOKEN, token);
      redirect("/");
    } else {
      redirect("/login");
    }
  }, []);

  return null;
}
