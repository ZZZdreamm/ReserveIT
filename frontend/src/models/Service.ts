import { StaticImageData } from "next/image";

export interface Service {
  id: number;
  name: string;
  price: number;
  description: string;
  image: string | StaticImageData;
}
