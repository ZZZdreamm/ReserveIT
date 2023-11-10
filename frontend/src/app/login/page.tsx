"use client";

import "./style.scss";
import { ACCESS_TOKEN, GOOGLE_AUTH_URL } from "@/config/constants";
import Image from "next/image";
import googleLogo from "@/assets/google-logo.png";
import Link from "next/link";
import { login } from "@/api/login";
import { redirect } from "next/navigation";
import { useState } from "react";
import { Input } from "@/common/input/Input";
import { Button } from "@/common/button/Button";
import { PasswordInput } from "@/common/input/PasswordInput";
import Form from "@/common/form/Form";
import { useAuthContext } from "@/providers/AuthProvider";

export default function Login() {
  return (
    <div className="h-full-minus-nav flex flex-col items-center justify-center h-full">
      {/* <div className="login-container"> */}
      <div className="login-content">
        <h1 className="login-title">Login admin to May IT</h1>
        <SocialLogin />
        <div className="or-separator">
          <span className="or-text">OR</span>
        </div>
        <LoginForm />
        {/* <span className="signup-link">
            New user? <Link href="/signup">Sign up!</Link>
          </span> */}
      </div>
      {/* </div> */}
    </div>
  );
}

function SocialLogin() {
  return (
    <div className="social-login">
      <Link className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
        <Image style={{ objectFit: "contain" }} src={googleLogo} alt="Google" />{" "}
        Log in with Google
      </Link>
    </div>
  );
}

function LoginForm() {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });
  // const [email, setEmail] = useState("");
  // const [password, setPassword] = useState("");

  function handleInputChange(event: any) {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    setForm((form) => {
      return {
        [inputName]: inputValue,
        ...form,
      };
    });
  }

  function handleSubmit(values: any) {
    console.log(values);

    login(values)
      .then((response: any) => {
        localStorage.setItem(ACCESS_TOKEN, response.accessToken);
        redirect("/");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <Form handleOnSubmit={handleSubmit}>
      <Input name="email" placeholder={"Email"} onChange={handleInputChange} />
      <PasswordInput
        name="password"
        placeholder="Password"
        onChange={handleInputChange}
      />
      <Button className="loginButton" type="submit">
        Login
      </Button>
    </Form>
  );
}
