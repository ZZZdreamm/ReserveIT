"use client";

import { ThemeProvider } from "next-themes";
import { AuthProvider } from "./AuthProvider";
import { QueryClientProviderr } from "./QueryClientProvider";
import { PayPalScriptProvider } from "@paypal/react-paypal-js";
import { paypalOptions } from "@/config/paypalConfig";

interface Props {
  children: React.ReactNode;
}

export function Providers({ children }: Props) {
  return (
    <ThemeProvider>
      <QueryClientProviderr>
        <PayPalScriptProvider options={paypalOptions}>
          <AuthProvider>{children}</AuthProvider>
        </PayPalScriptProvider>
      </QueryClientProviderr>
    </ThemeProvider>
  );
}
