"use client";

import { withPrivateRoute } from "@/common/withPrivateRoute";
import { useEffect } from "react";

const Admin = () => {
  useEffect(() => {
    console.log("Creator mounted");
    return () => {
      console.log("Creator unmounted");
    };
  }, []);
  return <>test</>;
};

export default withPrivateRoute(Admin);
