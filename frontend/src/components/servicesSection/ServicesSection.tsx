import { services } from "@/app/services/page";
import "./style.scss";
import Image from "next/image";



export function ServicesSection() {
  return (
    <section
      className="flex flex-col text-center"
      style={{ minHeight: "50vh" }}
    >
      <h4 className="mb-12">Nasze usługi</h4>
      <article className="phoneColumn w-full gap-8 flex">
        {services.map((service, index) => (
          <div
            key={index}
            className="flex flex-col place-items-center w-full gap-4"
          >
            <Image
              src={service.image}
              alt=""
              className="w-full cursor-pointer"
            />
            <h5 className="text-left w-full ml-4 cursor-pointer">
              {service.name}
            </h5>
          </div>
        ))}
      </article>
    </section>
  );
}
