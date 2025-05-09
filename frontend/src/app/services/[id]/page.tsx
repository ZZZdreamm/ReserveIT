"use client";

import { services } from "@/config/data";
import { useParams, useSearchParams } from "next/navigation";
import { ChooseDay } from "./ChooseDay";
import { ChooseTimeAndTutor } from "./ChooseTimeAndTutor";
import { useQuery } from "react-query";
import { getFreeDates } from "@/api/getFreeDates";
import { ChooseSubject } from "./ChooseSubject";
import { Finalization } from "./Finalization";
import "./page.scss";
import { Payment } from "./Payment";
import { GoBackButton } from "@/common/button/goBackButton/GoBackButton";
import { PurchasedView } from "../purchasedView";

const stages = [
  {
    name: "subject",
    title: "Choose subject",
  },
  {
    name: "day",
    title: "Choose day",
  },
  {
    name: "time",
    title: "Choose time",
  },
  {
    name: "finalization",
    title: "Finalization",
  },
  {
    name: "payment",
    title: "Payment",
  },
];

export default function ChoosenService() {
  const { id } = useParams();
  const searchParams = useSearchParams();
  const part = searchParams.get("part");
  const service = services.find(
    (service) => service.id === parseInt(id as string)
  );

  const { data: freeDates } = useQuery("freeDates", getFreeDates);

  return (
    <div className="service-hold">
      <div className="service-container__nav">
        {stages.map((stage, index) => (
          <div
            key={index}
            className={`service-container__nav__element ${
              stage.name === part ? "active" : ""
            }`}
          >
            {index + 1}. {stage.title}
          </div>
        ))}
      </div>
      <div className="service-container">
        <div className="flex justify-center w-full pl-20 pr-20">
          <div className="top-left-corner p-10">
            <GoBackButton />
          </div>
          <h2>{part?.toUpperCase()}</h2>
        </div>
        {part === "subject" && <ChooseSubject />}
        {part === "day" && <ChooseDay freeDates={freeDates} />}
        {part === "time" && <ChooseTimeAndTutor />}
        {part === "finalization" && <Finalization />}
        {part === "payment" && <Payment />}
      </div>
    </div>
  );
}
