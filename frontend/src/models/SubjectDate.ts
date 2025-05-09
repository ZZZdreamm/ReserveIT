import { TeachingOption } from "./enums/TeachingOption";

export interface SubjectDate {
  id: number;
  price: number;
  date: Date;
  duration: number;
  subjectId: number;
  teacherId: number;
  teachingOption: TeachingOption;
}
