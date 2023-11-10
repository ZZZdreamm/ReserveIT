import Image from "next/image";
import tutor1 from "../../assets/tutors1.jpg";
import tutor2 from "../../assets/tutors2.jpg";
import tutor3 from "../../assets/tutors3.jpg";
import "./style.scss";
import { Button } from "@/common/button/Button";

const tutors = [
  { id: 1, image: tutor1, name: "Anna Zaburska" },
  { id: 2, image: tutor2, name: "Marek Andzej" },
  { id: 3, image: tutor3, name: "Jarosław Grzęda" },
];

export function TutorsView() {
  const handleMoreInfoClick = (tutorId: number) => {};

  return (
    <section className="flex flex-col place-items-center">
      <div className="flex flex-col place-items-center justify-center text-center gap-4 w-full mb-24">
        <h3 className="phoneSmallerFont">USATYSFAKCJONOWANI KLIENCI</h3>
        <h6>Rekomendacje</h6>
        <span className="tightLongDescription">
          Najbardziej satysfakcjonującą częścią naszej pracy są pozytywne
          opinie, które otrzymujemy od uczniów i ich rodziców. Jeśli
          zastanawiasz się, jak to jest brać udział w jednym z naszych kursów,
          spójrz na poniższe opinie.
        </span>
      </div>
      <article className="flex flex-col gap-16 place-items-center justify-center tightLongDescription">
        {tutors.map((tutor, index) => (
          <div
            key={index}
            className="flex flex-col place-items-center justify-center text-center gap-4 longDescription"
          >
            <Image src={tutor.image} alt="tutor" className="w-full" />
            <h6>{tutor.name}</h6>
            <span className="mb-4">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
              vestibulum, nunc sed pellentesque aliquet, ex massa aliquam elit,
              quis eleifend eros velit quis dolor. Sed nec ultricies nisi.
              Nullam id nisl non augue aliquam ultricies. Nulla facilisi. Nulla
              facilisi. Sed vitae nisl sit amet nunc aliquet ultricies. Aliquam
              erat volutpat. Nulla facilisi. Nulla facilisi. Sed vitae nisl sit
              amet nunc aliquet ultricies. Aliquam erat volutpat.
            </span>
            <Button onClick={() => handleMoreInfoClick(tutor.id)}>
              Więcej informacji
            </Button>
          </div>
        ))}
      </article>
    </section>
  );
}
