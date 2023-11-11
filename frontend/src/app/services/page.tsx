import Image from "next/image";
import "./style.scss";
import Link from "next/link";
import { services } from "@/config/data";


function Services() {
  return (
    <section className="flex flex-col place-items-center">
      <h4 className="mb-4">USŁUGI KOREPETYCJI</h4>
      <span className="text-sm mb-16">Skupiamy się na wynikach</span>
      <article className="flex flex-col gap-12">
        {services.map((service) => (
          <div key={service.id} className="service flex w-full text-center phoneColumn cursor-pointer">
            <div className="w-50-phone-100 flex flex-col justify-center place-items-center p-4 gap-4 bg-sec">
              <h6>{service.name.toUpperCase()}</h6>
              <span className="tightLongDescription text-center mb-6 text-sm">
                {service.description}
              </span>
            </div>
            <Link href={`/services/${service.id}`} className="w-50-phone-100">
              <Image src={service.image} alt="" className="w-full h-full" />
            </Link>
          </div>
        ))}
      </article>
    </section>
  );
}


export default Services;