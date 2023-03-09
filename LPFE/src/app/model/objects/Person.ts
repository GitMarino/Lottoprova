import { SkillMark } from "./skill-marks";

export class Person
{   
    id?: number;
    username!: string;
    name!: string;
    surname!: string;
    skillMarkList!: SkillMark[];

}