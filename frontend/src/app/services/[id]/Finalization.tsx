import { useCachedState } from "@/hooks/useCachedState";
import "./Finalization.scss";
import { Button } from "@/common/button/Button";
import { useParams, useRouter } from "next/navigation";
import { FORM_NAME } from "./constants";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export function Finalization() {
  const { id } = useParams();
  const { push } = useRouter();
  const { state: subjectDate } = useCachedState<SubjectDateDto | null>(
    null,
    FORM_NAME
  );

  const goToPayment = () => {
    push(`/services/${id}?part=payment`);
  };
  return (
    <div className="finalization">
      <div className="finalization__container">
        <h3 className="mb-5">Summary</h3>
        <p>
          <b>Subject:</b> {subjectDate?.subjectName}
        </p>
        <p>
          <b>Date:</b> {subjectDate?.date.toLocaleString()}
        </p>
        <p>
          <b>Teacher:</b> {subjectDate?.teacherName}
        </p>
        <p>
          <b>Duration:</b> {subjectDate?.duration} minutes
        </p>
        <p>
          <b>Price:</b> {subjectDate?.price} PLN
        </p>
      </div>
      <Button onClick={goToPayment}>Go To Payment</Button>
    </div>
  );
}
