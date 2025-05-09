import { getDaysHours } from "@/api/getDaysHours";
import { useQuery } from "react-query";
import "./ChooseTimeAndTutor.scss";
import { useCachedState } from "@/hooks/useCachedState";
import { useParams, useRouter } from "next/navigation";
import { Button } from "@/common/button/Button";
import { FORM_NAME } from "./constants";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export function ChooseTimeAndTutor() {
  const { id } = useParams();
  const { push } = useRouter();
  const dateDay = sessionStorage.getItem("startDate");
  const { data: daysHours } = useQuery("daysHours", () =>
    getDaysHours(new Date(dateDay || ""))
  );

  const { state: subjectDate, setState: setSubjectDate } =
    useCachedState<SubjectDateDto | null>(null, FORM_NAME);

  const saveHour = () => {
    push(`/services/${id}?part=finalization`);
  };

  const setHoursAndTutorforDate = (
    hours: number,
    minutes: number,
    teacherId: number,
    teacherName: string,
    price: number,
    duration: number
  ) => {
    setSubjectDate((prevDate) => {
      if (prevDate) {
        return {
          ...prevDate,
          date: new Date(prevDate.date.setHours(hours, minutes)),
          teacherId,
          teacherName,
          price,
          duration,
        };
      }
      return null;
    });
  };

  return (
    <div className="choose-date">
      <h2>Choose your hour</h2>
      <div className="choose-date__container">
        {daysHours?.map((dayHour) => {
          return (
            <div
              className="choose-date__element"
              key={dayHour.id}
              onClick={() => {
                setHoursAndTutorforDate(
                  dayHour.date.getHours(),
                  dayHour.date.getMinutes(),
                  dayHour.teacherId,
                  dayHour.teacherName,
                  dayHour.price,
                  dayHour.duration
                );
              }}
              style={{
                backgroundColor:
                  dayHour.date.getHours() === subjectDate?.date.getHours() &&
                  dayHour.date.getMinutes() ===
                    subjectDate?.date?.getMinutes() &&
                  dayHour.teacherId === subjectDate?.teacherId &&
                  dayHour.teacherName === subjectDate?.teacherName
                    ? "var(--hover-color)"
                    : "var(--button-color",
              }}
            >
              <span>
                {dayHour.date.getHours()}:
                {String(dayHour.date.getMinutes()).padStart(2, "0")}
              </span>
              <span>{dayHour.teacherName}</span>
              <span> {dayHour.price} PLN</span>
              <span>{dayHour.duration} min</span>
            </div>
          );
        })}
      </div>
      <Button onClick={saveHour}>Choose Hour</Button>
    </div>
  );
}
