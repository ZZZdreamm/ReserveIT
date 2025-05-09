"use client";

import Image from "next/image";
import "./style.scss";
import Link from "next/link";
import { services } from "@/config/data";
import { useCachedState } from "@/hooks/useCachedState";
import { TeachingOption } from "@/models/enums/TeachingOption";
import { SubjectDateDto } from "@/models/SubjectDateDto";

function Services() {
  const { setState: setSubjectDate } =
    useCachedState<SubjectDateDto | null>(null, "subjectDate");

const setTeachingOption = (teachingOption: TeachingOption) => {
    setSubjectDate((prevDate) => {
      if (prevDate) {
        return { ...prevDate, teachingOption };
      }
      return {
        id: 0,
        subjectName: "",
        price: 0,
        date: new Date(),
        teacherId: 0,
        teacherName: "",
        duration: 0,
        teachingOption,
      };
    });
  }

  return (
    <section className="flex flex-col place-items-center">
      <h4 className="mb-4">USŁUGI KOREPETYCJI</h4>
      <span className="text-sm mb-16">Skupiamy się na wynikach</span>
      <article className="flex flex-col gap-12">
        {services.map((service) => (
          <Link
            href={`/services/${service.id}?part=subject`}
            className="w-100-phone-100"
            onClick={()=> setTeachingOption(service.id)}
          >
            <div
              key={service.id}
              className="service flex w-full text-center phoneColumn cursor-pointer"
            >
              <div className="w-50-phone-100 flex flex-col justify-center place-items-center p-4 gap-4 bg-sec">
                <h6>{service.name.toUpperCase()}</h6>
                <span className="tightLongDescription text-center mb-6 text-sm">
                  {service.description}
                </span>
              </div>
              <Image
                src={service.image}
                alt=""
                className="w-50-phone-100 h-full"
              />
            </div>
          </Link>
        ))}
      </article>
    </section>
  );
}

export default Services;
