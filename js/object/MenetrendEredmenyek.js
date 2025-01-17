import {Talalat} from "./Talalat.js"

class MenetrendEredmenyek {
    constructor(data) {
        this.napkiiras = data.napkiiras;
        this.date_got = data.date_got;
        this.daytype_got = data.daytype_got;
        this.apache_hostname = data.apache_hostname;
        this.used_params = data.used_params;
        this.talalatok = {};
        for (const key in data.talalatok) {
            this.talalatok[key] = new Talalat(data.talalatok[key]);
        }
    }
}

export { MenetrendEredmenyek };