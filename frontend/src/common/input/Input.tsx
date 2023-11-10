import { useFormContext } from "react-hook-form";
import "./style.scss";
// import { ErrorMessage } from "@hookform/error-message";

export interface InputProps {
  name: string;
  inputType?: "text" | "password";
  placeholder?: string;
  onChange: (event: any) => void;
  required?: boolean;
}

export function Input({
  name,
  placeholder,
  onChange,
  inputType = "text",
  required = true,
}: InputProps) {
  const {
    register,
    formState: { errors },
  } = useFormContext();
  return (
    <div
      className="inputContainer"
      {...register(name, {
        required: required,
        // validate:
      })}
    >
      <input
        name={name}
        type={inputType}
        placeholder={placeholder}
        onChange={onChange}
        required={required}
      />
      {/* <ErrorMessage
        errors={errors}
        name={name}
        render={({ message }) => (
          <ErrorMessageContainer>
            <Text fontSize={10} color="red" lineHeight={132}>
              {message}
            </Text>
          </ErrorMessageContainer>
        )}
      /> */}
    </div>
  );
}
