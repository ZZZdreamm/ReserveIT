import { TeachingOptions } from "./enums/TeachingOption";

export interface Teacher {
  id: number;
  name: string;
  email: string;
  age: number;
  description: string;
  image: string;
  subject: string;
  teachingOptions: TeachingOptions[];
}
