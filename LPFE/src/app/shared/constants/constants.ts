//HTTP CALLS
export const BACKEND_ENDPOINT = "/api"; //due to Cors error we use a proxy
export const STANDARD_JSON_HEADERS = {'Content-type': 'application/json','Accept': 'application/json',};

    //AREA
export const AREA = "/area";

    //PERSON
export const PERSON = "/person";
export const CV = "/cv";

    //SKILL
export const SKILL = "/skill";
export const SKILLS = "/skills";

    //TOPIC
export const TOPIC = "/topic";

    //OTHER
export const SEARCH = "/search";

//POPUP
    
    //SUCCESS
export const SUCCESS_TITLE = "Completato!";
export const SUCCESS_BODY = "Il salvataggio è avvenuto con successo";

    //ERROR
export const ERROR_TITLE = "Qualcosa è andato storto!";
export const ERROR_BODY = "Non è stato possibile effettuare il salvataggio";