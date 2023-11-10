import React from "react";
import "./style.scss";

interface Props extends React.HTMLAttributes<HTMLButtonElement> {
  children: React.ReactNode;
  type?: "button" | "submit" | "reset";
  onClick?: () => void;
}

export function Button({
  children,
  type = "button",
  onClick = () => {},
}: Props) {
  return (
    <button type={type} className="myButton h-12 pl-2 pr-2" onClick={onClick}>
      {children}
    </button>
  );
}
