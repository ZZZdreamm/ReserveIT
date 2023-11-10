import Image from "next/image";
import home1 from "../../assets/home_1.webp";
import "./style.scss";

export function FirstView() {
  return (
    <section className="firstSection bg-sec flex">
      <article className="w-full flex flex-col place-items-center justify-center text-center">
        <h1>MAYIT TO</h1>
        <h1>MY!</h1>
        <h5 className="mb-12">Lekcje prywatne w Warszawie</h5>
        <span className="longDescription">
          MayIT stara się dobrać dla każdego ucznia doświadczonego i
          profesjonalnego nauczyciela, który rozpali w nim pasję do nauki.
          Dzięki osobistemu zaangażowaniu potrafimy stosować najskuteczniejsze
          techniki nauki, aby stawiać wyzwania, motywować i wspierać naszych
          uczniów. Dołącz do nas!
        </span>
      </article>
      <Image src={home1} alt="" className="w-full" />
    </section>
  );
}
