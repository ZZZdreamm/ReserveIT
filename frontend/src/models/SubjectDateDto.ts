import { TeachingOption } from "./enums/TeachingOption";

export interface SubjectDateDto {
  id: number;
  subjectName: string;
  price: number;
  date: Date;
  teacherId: number;
  teacherName: string;
  duration: number;
  teachingOption: TeachingOption;
}