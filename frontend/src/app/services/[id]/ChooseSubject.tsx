import { getSubjects } from "@/api/getSubjects";
import { useCachedState } from "@/hooks/useCachedState";
import { Subject } from "@/models/Subject";
import { useParams, useRouter } from "next/navigation";
import { useQuery } from "react-query";
import "./ChooseSubject.scss";
import Image from "next/image";
import math from "../../../assets/math.jpg";
import { Button } from "@/common/button/Button";
import { FORM_NAME } from "./constants";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export function ChooseSubject() {
  const { id } = useParams();
  const { replace, push } = useRouter();
  const { state: subjectDate, setState: setSubjectDate } =
    useCachedState<SubjectDateDto | null>(null, FORM_NAME);
  const { data: subjects } = useQuery("subjects", getSubjects);

  const saveSubject = () => {
    push(`/services/${id}?part=day`);
  };

  const setSubject = (subject: Subject) => {
    setSubjectDate((prevDate) => {
      if (prevDate) {
        return { ...prevDate, subjectName: subject.name };
      }
      return {
        id: 0,
        subjectName: subject.name,
        price: 0,
        date: new Date(),
        teacherId: 0,
        teacherName: "",
        duration: 0,
        teachingOption: 0,
      };
    });
  };

  return (
    <div className="choose-subject">
      <div className="choose-subject__container">
        {subjects?.map((subject) => (
          <div
            className="choose-subject__element"
            key={subject.id}
            onClick={() => {
              setSubject(subject);
            }}
            style={{
              backgroundColor:
                subject.name === subjectDate?.subjectName
                  ? "var(--hover-color)"
                  : "var(--button-color",
            }}
          >
            <Image src={math} alt={subject.name} width={100} height={100} />
            {subject.name}
          </div>
        ))}
      </div>
      <Button onClick={saveSubject}>Choose Subject</Button>
    </div>
  );
}
