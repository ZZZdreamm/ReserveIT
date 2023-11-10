import { useEffect, useState } from "react";
import { useAuthContext } from "@/providers/AuthProvider";
import { redirect } from "next/navigation";

const login = "/login?redirected=true";

export const withPrivateRoute = (WrappedComponent: any) => {
  const hocComponent = ({ ...props }) => {
    const { mounted, authenticated, loading } = useAuthContext();
    useEffect(() => {
      if (mounted && !authenticated) {
        redirect(login);
      }
    }, [authenticated, loading]);
    return <>{!loading ? <WrappedComponent {...props} /> : <div>Loading...</div>}</>;
  };

  return hocComponent;
};
