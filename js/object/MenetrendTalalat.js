import {MenetrendEredmenyek} from "./MenetrendEredmenyek.js";

class MenetrendTalalat {
    constructor(data) {
        this.status = data.status;
        this.results = new MenetrendEredmenyek(data.results);
    }
}

export {MenetrendTalalat}
