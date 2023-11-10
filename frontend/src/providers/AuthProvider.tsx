import { getCurrentUser } from "@/api/getCurrentUser";
import { createContext, useContext, useMemo, useState } from "react";
import { useCurrentUser } from "../hooks/useCurrentUser";

interface AuthProps {
  mounted: boolean;
  authenticated: boolean;
  currentUser: any;
  setAuthenticated: (value: boolean) => void;
  setCurrentUser: (value: any) => void;
  loading: boolean;
}

interface Props {
  children: React.ReactNode;
}

const AuthContext = createContext<AuthProps>({
  mounted: false,
  authenticated: false,
  currentUser: null,
  setAuthenticated: () => {},
  setCurrentUser: () => {},
  loading: true,
});

export function AuthProvider({ children }: Props) {
  const {
    mounted,
    currentUser,
    authenticated,
    loading,
    setAuthenticated,
    setCurrentUser,
  } = useCurrentUser();

  const states = useMemo(
    () => ({
      mounted,
      authenticated,
      currentUser,
      loading,
      setAuthenticated,
      setCurrentUser,
    }),
    [
      mounted,
      currentUser,
      authenticated,
      loading,
      setAuthenticated,
      setCurrentUser,
    ]
  );

  return <AuthContext.Provider value={states}>{children}</AuthContext.Provider>;
}

export const useAuthContext = () => useContext(AuthContext);
