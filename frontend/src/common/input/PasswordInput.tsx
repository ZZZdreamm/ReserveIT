import { useState } from "react";
import { Input, InputProps } from "./Input";
import "./style.scss";
import openEye from "../../assets/eye-open.png";
import closedEye from "../../assets/eye-closed.png";
import Image from "next/image";

interface Props extends Omit<InputProps, "inputType"> {}

export function PasswordInput({
  name,
  placeholder = "Password",
  onChange,
  required = true,
}: Props) {
  const [showPassword, setShowPassword] = useState(false);
  return (
    <div className="passwordInput">
      <Input
        name={name}
        placeholder={placeholder}
        onChange={onChange}
        required={required}
        inputType={showPassword ? "text" : "password"}
      />
      <div
        className="passwordInput-show"
        onClick={() => setShowPassword((showPassword) => !showPassword)}
      >
        <Image src={showPassword ? openEye : closedEye} alt="Show password" className="h-full w-full"/>
      </div>
    </div>
  );
}
