
import * as Li9za2lrby5tanM from './skiko.mjs';
import { instantiate } from './composeApp.uninstantiated.mjs';

const exports = (await instantiate({
    './skiko.mjs': Li9za2lrby5tanM
})).exports;

export default new Proxy(exports, {
    _shownError: false,
    get(target, prop) {
        if (!this._shownError) {
            this._shownError = true;
            throw new Error("Do not use default import. Use the corresponding named import instead.")
        }
    }
});
export const {
    _initialize,
    memory
} = exports;

