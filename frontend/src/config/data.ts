import service1 from "../assets/service_1.jpg";
import service2 from "../assets/service_2.jpg";
import service3 from "../assets/service_3.webp";
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
