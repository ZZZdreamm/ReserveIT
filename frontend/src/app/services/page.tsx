import Image from "next/image";
import "./style.scss";
import Link from "next/link";
import service1 from "../../assets/service_1.jpg";
import service2 from "../../assets/service_2.jpg";
import service3 from "../../assets/service_3.webp";
import { Service } from "@/models/Service";

export const services: Service[] = [
  {
    id: 1,
    price: 50,
    image: service1,
    name: "Korepetycje online",
    description:
      "Korepetycje online to element naszej działalności, który dostosowujemy do indywidualnych potrzeb każdego z naszych uczniów. Profesjonalne umiejętności nauczycieli oraz Twoje zaangażowanie w pracę to gwarancja sukcesu w nauce.",
  },
  {
    id: 2,
    price: 50,
    image: service2,
    name: "Przygotowanie do egzaminów",
    description:
      "Tworzymy plan i program odpowiedni dla każdego ucznia. Działamy w sposób kompleksowy i oparty na współpracy. Dostosowując się do różnych osobowości i konkretnych potrzeb związanych z nauką, zawsze znajdujemy to, co działa najlepiej.",
  },
  {
    id: 3,
    price: 50,
    image: service3,
    name: "Nauczanie w domu",
    description:
      "Nasz program nauczania i sesje edukacyjne skupiają się wokół umiejętności i doświadczeń każdego ucznia. Identyfikujemy i eliminujemy luki w nauce, aby dać uczniom pewność swojej wiedzy.",
  },
];

export default function Services() {
  return (
    <section className="flex flex-col place-items-center">
      <h4 className="mb-4">USŁUGI KOREPETYCJI</h4>
      <span className="text-sm mb-16">Skupiamy się na wynikach</span>
      <article className="flex flex-col gap-12">
        {services.map((service) => (
          <div className="service flex w-full text-center phoneColumn cursor-pointer">
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
