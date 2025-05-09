"use client";

import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import styled from "styled-components";
import "./react-datepicker.scss";
import { Button } from "@/common/button/Button";
import { useParams, useRouter } from "next/navigation";
import { useCachedState } from "@/hooks/useCachedState";
import { FORM_NAME } from "./constants";
import { SubjectDateDto } from "@/models/SubjectDateDto";

export interface FreeDatesProps {
  freeDates: SubjectDateDto[] | undefined;
}

export function ChooseDay({ freeDates }: FreeDatesProps) {
  const { id } = useParams();
  const { push } = useRouter();
  const { state: subjectDate, setState: setSubjectDate } =
    useCachedState<SubjectDateDto | null>(null, FORM_NAME);
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setIsOpen(true);
    });
  }, []);

  const saveDateDay = () => {
    push(`/services/${id}?part=time`);
  };

  const setDateStart = (date: Date) => {
    setSubjectDate((prevDate) => {
      if (prevDate) {
        return { ...prevDate, date };
      }
      return null
    });
  };

  return (
    <div>
      <DateContainer>
        {subjectDate?.date && (
          <>
            Wybrana data: {subjectDate.date.getMonth() + 1}/
            {subjectDate.date.getDate()}/{subjectDate.date.getFullYear()}
          </>
        )}
      </DateContainer>
      <DatePickerContainer>
        <DatePicker
          minDate={new Date()}
          selected={subjectDate?.date}
          onChange={(date) => {
            setDateStart(date as Date);
          }}
          showIcon
          onInputClick={() => setIsOpen(true)}
          open={isOpen}
          includeDates={freeDates?.map((date) => date.date)}
        />
      </DatePickerContainer>
      <Button onClick={saveDateDay}>Choose Day</Button>
    </div>
  );
}

const DatePickerContainer = styled.div`
  color: black;
  height: 65vh;
`;

const DateContainer = styled.p`
  height: 2rem;
`;
