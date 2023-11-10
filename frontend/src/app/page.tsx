"use client";

import Link from "next/link";
import { useAuthContext } from "../providers/AuthProvider";
import { FirstView } from "@/components/firstView/FirstView";
import { InformationBlock } from "@/components/secondView/InformationBlock";
import { ServicesSection } from "@/components/servicesSection/ServicesSection";
import { TutorsView } from "@/components/tutorsView/TutorsView";
import { ContactView } from "@/components/contactView/ContactView";

export default function Home() {
  const { authenticated, loading, setAuthenticated, setCurrentUser } =
    useAuthContext();

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    setAuthenticated(false);
    setCurrentUser(null);
  };

  if (loading) {
    return <div>Loading...</div>;
  }
  return (
    <>
      <FirstView />
      <InformationBlock />
      <ServicesSection />
      <TutorsView />
      <ContactView />
      {/* {authenticated ? (
        <div>
          <button onClick={handleLogout}>Log out</button>
        </div>
      ) : (
        <Link href="/login">Login</Link>
      )} */}
    </>
  );
}
