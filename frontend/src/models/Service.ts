import { StaticImageData } from "next/image";

export interface Service {
  id: number;
  name: string;
  description: string;
  price: number;
  image: string | StaticImageData;
}
