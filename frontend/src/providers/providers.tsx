"use client";

import { ThemeProvider } from "next-themes";
import { AuthProvider } from "./AuthProvider";

interface Props {
  children: React.ReactNode;
}

export function Providers({ children }: Props) {
  return (
    <ThemeProvider>
      <AuthProvider>{children}</AuthProvider>
    </ThemeProvider>
  );
}
