"use client";
import Image from "next/image";
import "./style.scss";
import hamburgerIcon from "../../assets/hamburger_icon.png";
import { useWindowWidth } from "@/hooks/useWindowWidth";
import { useEffect, useRef, useState } from "react";
import { useAuthContext } from "@/providers/AuthProvider";

const menuParts = [
  {
    name: "Home",
    path: "/ ",
  },
  // {
  //   name: "O nas",
  //   path: "/about",
  // },
  // {
  //   name: "Kontakt",
  //   path: "/contact",
  // },
  {
    name: "Usługi",
    path: "/services",
  },
  // {
  //   name: "Przedmioty",
  //   path: "/lessons",
  // },
  // {
  //   name: "Opinie",
  //   path: "/opinions",
  // },
  // {
  //   name: "Materiały",
  //   path: "/materials",
  // },
  // {
  //   name: "Rezerwuj",
  //   path: "/reservation",
  // },
];

const ITEM_PADDING = 16;
const ITEM_LINE_HEIGHT = 28;
const OPENED_OPTIONS_HEIGHT =
  (menuParts.length+1) * (ITEM_LINE_HEIGHT + ITEM_PADDING);

export function Navbar() {
  const windowWidth = useWindowWidth();
  const { currentUser } = useAuthContext();
  const [lastElement, setLastElement] = useState({
    name: "Login",
    path: "/login",
  });

  useEffect(() => {
    setLastElement(
      currentUser
        ? {
            name: "Panel",
            path: "/panel",
          }
        : {
            name: "Login",
            path: "/login",
          }
    );
  }, [currentUser]);

  return (
    <div className="navbar flex justify-between place-items-center h-28 w-full">
      <h4 className="text-2xl">MAYIT</h4>
      {windowWidth > 990 ? (
        <div className="navbar-elements">
          <Options lastElement={lastElement} />
        </div>
      ) : (
        <ExpandedOptions lastElement={lastElement} />
      )}
    </div>
  );
}

export function ExpandedOptions({ lastElement }: { lastElement: any }) {
  const navbarElementsRef = useRef<HTMLDivElement>(null);
  const handleExpand = () => {
    if (!navbarElementsRef.current) return;
    navbarElementsRef.current.style.height =
      navbarElementsRef.current.style.height === "0px"
        ? `${OPENED_OPTIONS_HEIGHT}px`
        : "0px";
  };
  return (
    <div className="navbar-icon bg-sec">
      <Image
        src={hamburgerIcon}
        alt={""}
        width={30}
        height={30}
        onClick={handleExpand}
      />
      <div
        className="navbar-elements"
        ref={navbarElementsRef}
        style={{ height: "0px" }}
      >
        <Options lastElement={lastElement} />
      </div>
    </div>
  );
}

export function Options({ lastElement }: { lastElement: any }) {
  return (
    <>
      {menuParts.map((part) => (
        <a
          key={part.name}
          href={part.path}
          className="ml-4 text-xl hover:text-gray-600 transition-colors duration-300"
        >
          {part.name}
        </a>
      ))}
      <a
        href={lastElement.path}
        className="ml-4 text-xl hover:text-gray-600 transition-colors duration-300"
      >
        {lastElement.name}
      </a>
    </>
  );
}
