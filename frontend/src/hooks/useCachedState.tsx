import { useEffect, useState } from "react";

export function useCachedState<T>(initialState: T, key: string) {
  const [state, setState] = useState<T>(() => {
    if(typeof window === "undefined") return initialState; // SSR (Server Side Rendering
    const cachedState = sessionStorage.getItem(key);
    return cachedState ? JSON.parse(cachedState, dateReviver) : initialState;
  });

  useEffect(() => {
    sessionStorage.setItem(key, JSON.stringify(state, dateReplacer));
  }, [state]);

  return { state, setState };
}

function dateReplacer(key: string, value: any) {
  if (value instanceof Date) {
    return value.toISOString(); // Serialize Date to ISO string
  }
  return value;
}

function dateReviver(key: string, value: any) {
  if (typeof value === "string") {
    const date = new Date(value);
    if (!isNaN(date.getTime())) {
      return date; // Deserialize ISO string to Date
    }
  }
  return value;
}