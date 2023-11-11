"use client";

import { useEffect } from "react";

export default function Admin(){
  useEffect(() => {
    console.log("Creator mounted");
    return () => {
      console.log("Creator unmounted");
    };
  }, []);
  return <>test</>;
};

