import { useEffect, useState } from "react";

export function useWindowWidth() {
  const [windowWidth, setWindowWidth] = useState(1100);
  useEffect(() => {
    window.addEventListener("resize", () => {
      setWindowWidth(window.innerWidth);
    });
    if(window){
      setWindowWidth(window.innerWidth);
    }
  }, [windowWidth]);

  return windowWidth;
}
