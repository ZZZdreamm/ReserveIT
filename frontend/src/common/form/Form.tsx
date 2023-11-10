import {
  useForm,
  SubmitHandler,
  FormProvider,
  DefaultValues,
} from "react-hook-form";

interface FormProps {
  children: React.ReactNode;
  handleOnSubmit?: (values: any) => void;
  centered?: boolean;
  //   defaultValues?: T;
}

export default function Form({
  children,
  handleOnSubmit = () => {},
  centered = true,
}: FormProps) {
  const methods = useForm({});

  const { handleSubmit } = methods;
  const onSubmit: SubmitHandler<any> = (values) => handleOnSubmit(values);

  return (
    <FormProvider {...methods}>
      <form
        className={
          centered ? `flex flex-col place-items-center justify-center gap-4` : ""
        }
        onSubmit={handleSubmit(onSubmit)}
      >
        {children}
      </form>
    </FormProvider>
  );
}
