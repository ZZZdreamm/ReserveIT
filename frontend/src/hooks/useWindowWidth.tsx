import { useEffect, useState } from "react";

export function useWindowWidth() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth ?? 0);
  useEffect(() => {
    window.addEventListener("resize", () => {
      setWindowWidth(window.innerWidth);
    });
  }, [windowWidth]);

  return windowWidth;
}
