import { Subject } from './subject';

export class Group {
    subject: Subject;
    id : number;
    teacherName : string;
    studentLimit : number;
    memberCount : number;
    location : string;
    timeframe : string;
}
