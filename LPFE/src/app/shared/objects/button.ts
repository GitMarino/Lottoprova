import { Observable } from "rxjs";

export class Button {
    name!: string;
    action?: () => void
    identifier?: string;
    disabled?: Observable<boolean>;
}