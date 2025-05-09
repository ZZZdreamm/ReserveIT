import { PayPalButtons } from "@paypal/react-paypal-js";
import "./Payment.scss";
import { useRouter } from "next/navigation";
import { Button } from "@/common/button/Button";
import { FORM_NAME } from "./constants";
import { reserveDate } from "@/api/reserveDate";
import { useCachedState } from "@/hooks/useCachedState";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export function Payment() {
  const { push } = useRouter();
  const { state: subjectDate } = useCachedState<SubjectDateDto | null>(
    null,
    FORM_NAME
  );

  const handlePayment = async () => {
    if (!subjectDate) return;
    const response = await reserveDate(subjectDate);
    if (response.status !== 200) return;
    sessionStorage.removeItem(FORM_NAME);
    push(`/services/purchasedView`);
  };
  return (
    <div className="flex justify-center gap-4 items-center">
      <PayPalButtons />
      <Button className="paypal-buttons" onClick={handlePayment}>
        Fake payment button
      </Button>
    </div>
  );
}
