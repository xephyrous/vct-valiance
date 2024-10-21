
import * as _ref_Li9qcy9hd3MtYXBpLmpz_ from './js/aws-api.js';
import * as _ref_Li9qcy9jb29raWUtaGFuZGxlci5qcw_ from './js/cookie-handler.js';
import * as _ref_Li9qcy9maXJlYmFzZS1hcGkuanM_ from './js/firebase-api.js';
import * as _ref_Li9qcy9qcy11dGlscy5qcw_ from './js/js-utils.js';
import * as Li9za2lrby5tanM from './skiko.mjs';
import { instantiate } from './composeApp.uninstantiated.mjs';

const exports = (await instantiate({
    './js/aws-api.js': _ref_Li9qcy9hd3MtYXBpLmpz_,
    './js/cookie-handler.js': _ref_Li9qcy9jb29raWUtaGFuZGxlci5qcw_,
    './js/firebase-api.js': _ref_Li9qcy9maXJlYmFzZS1hcGkuanM_,
    './js/js-utils.js': _ref_Li9qcy9qcy11dGlscy5qcw_,
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

