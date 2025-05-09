import { useRouter } from "next/navigation";
import "./GoBackButton.scss";
import { GoBackIcon } from "@/components/icons/GoBackIcon";

export function GoBackButton() {
  const { back } = useRouter();

  return (
    <button className="go-back-button" onClick={() => back()}>
      <GoBackIcon />
    </button>
  );
}
