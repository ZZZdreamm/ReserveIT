import Image from "next/image";
import "./style.scss";
import home_2 from "../../assets/home_2.webp";

export function InformationBlock() {
  return (
    <section className="information">
      <article className="flex w-full bg-cover place-items-center justify-center relative">
        <div className="information-textContainer flex place-items-center justify-center flex-col z-10">
          <h3>INFORMACJE</h3>
          <h6 className="mb-4">Kim jesteśmy?</h6>
          <span className="longDescription text-center">
            Od 2000 roku nasz elitarny zespół korepetytorów pomaga uczniom
            maksymalnie wykorzystać posiadany potencjał i doskonalić się w
            wybranej przez siebie dziedzinie. Dzięki zindywidualizowanemu i
            bezstresowemu podejściu udoskonaliliśmy metodę nauczania, która
            przynosi prawdziwe rezultaty. Nie wierzysz? Przewiń dalej i odkryj,
            jak korepetytor może odmienić Twoje życie.
          </span>
        </div>
        <Image
          src={home_2}
          alt={""}
          className="absolute top-0 left-0 w-full h-full opacity-50"
        />
      </article>
    </section>
  );
}
