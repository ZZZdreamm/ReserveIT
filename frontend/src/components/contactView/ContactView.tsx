import Image from "next/image";
import home_2 from "../../assets/home_2.webp";
import { useWindowWidth } from "@/hooks/useWindowWidth";
import { GoogleMapComponent } from "../googleMap/GoogleMap";

export function ContactView() {
  const windowWidth = useWindowWidth();
  return (
    <section className="flex flex-col">
      <article className="w-full flex tabletColumn">
        <div className="w-full flex flex-col place-items-center justify-center gap-6 bg-sec p-6 text-center">
          <h3>Skontaktuj się</h3>
          <span>Aleje Jerozolimskie 52</span>
          <span>kmultan1234@gmail.com</span>
          <span>+48 784 598 277</span>
        </div>
        {windowWidth > 768 && <Image src={home_2} alt="" className="w-full" />}
      </article>
      <article className="w-full" style={{ height: "50vh" }}>
        <GoogleMapComponent />
      </article>
    </section>
  );
}
