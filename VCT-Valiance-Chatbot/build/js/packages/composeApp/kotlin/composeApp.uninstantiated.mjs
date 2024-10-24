
export async function instantiate(imports={}, runInitializer=true) {
    const cachedJsObjects = new WeakMap();
    // ref must be non-null
    function getCachedJsObject(ref, ifNotCached) {
        if (typeof ref !== 'object' && typeof ref !== 'function') return ifNotCached;
        const cached = cachedJsObjects.get(ref);
        if (cached !== void 0) return cached;
        cachedJsObjects.set(ref, ifNotCached);
        return ifNotCached;
    }

    const _ref_Li9qcy9hd3MtYXBpLmpz_ = imports['./js/aws-api.js'];
    const _ref_Li9qcy9jb29raWUtaGFuZGxlci5qcw_ = imports['./js/cookie-handler.js'];
    const _ref_Li9qcy9maXJlYmFzZS1hcGkuanM_ = imports['./js/firebase-api.js'];
    const _ref_Li9qcy9qcy11dGlscy5qcw_ = imports['./js/js-utils.js'];
    const _ref_Li9za2lrby5tanM_ = imports['./skiko.mjs'];
    
    const js_code = {
        'kotlin.captureStackTrace' : () => new Error().stack,
        'kotlin.wasm.internal.stringLength' : (x) => x.length,
        'kotlin.wasm.internal.jsExportStringToWasm' : (src, srcOffset, srcLength, dstAddr) => { 
            const mem16 = new Uint16Array(wasmExports.memory.buffer, dstAddr, srcLength);
            let arrayIndex = 0;
            let srcIndex = srcOffset;
            while (arrayIndex < srcLength) {
                mem16.set([src.charCodeAt(srcIndex)], arrayIndex);
                srcIndex++;
                arrayIndex++;
            }     
             },
        'kotlin.wasm.internal.importStringFromWasm' : (address, length, prefix) => { 
            const mem16 = new Uint16Array(wasmExports.memory.buffer, address, length);
            const str = String.fromCharCode.apply(null, mem16);
            return (prefix == null) ? str : prefix + str;
             },
        'kotlin.wasm.internal.getJsEmptyString' : () => '',
        'kotlin.wasm.internal.externrefToString' : (ref) => String(ref),
        'kotlin.wasm.internal.externrefEquals' : (lhs, rhs) => lhs === rhs,
        'kotlin.wasm.internal.externrefHashCode' : 
        (() => {
        const dataView = new DataView(new ArrayBuffer(8));
        function numberHashCode(obj) {
            if ((obj | 0) === obj) {
                return obj | 0;
            } else {
                dataView.setFloat64(0, obj, true);
                return (dataView.getInt32(0, true) * 31 | 0) + dataView.getInt32(4, true) | 0;
            }
        }
        
        const hashCodes = new WeakMap();
        function getObjectHashCode(obj) {
            const res = hashCodes.get(obj);
            if (res === undefined) {
                const POW_2_32 = 4294967296;
                const hash = (Math.random() * POW_2_32) | 0;
                hashCodes.set(obj, hash);
                return hash;
            }
            return res;
        }
        
        function getStringHashCode(str) {
            var hash = 0;
            for (var i = 0; i < str.length; i++) {
                var code  = str.charCodeAt(i);
                hash  = (hash * 31 + code) | 0;
            }
            return hash;
        }
        
        return (obj) => {
            if (obj == null) {
                return 0;
            }
            switch (typeof obj) {
                case "object":
                case "function":
                    return getObjectHashCode(obj);
                case "number":
                    return numberHashCode(obj);
                case "boolean":
                    return obj ? 1231 : 1237;
                default:
                    return getStringHashCode(String(obj)); 
            }
        }
        })(),
        'kotlin.wasm.internal.isNullish' : (ref) => ref == null,
        'kotlin.wasm.internal.intToExternref' : (x) => x,
        'kotlin.wasm.internal.getJsTrue' : () => true,
        'kotlin.wasm.internal.getJsFalse' : () => false,
        'kotlin.wasm.internal.getCachedJsObject_$external_fun' : (p0, p1) => getCachedJsObject(p0, p1),
        'kotlin.js.jsCatch' : (f) => { 
            let result = null;
            try { 
                f();
            } catch (e) {
               result = e;
            }
            return result;
             },
        'kotlin.js.__convertKotlinClosureToJsClosure_(()->Unit)' : (f) => getCachedJsObject(f, () => wasmExports['__callFunction_(()->Unit)'](f, )),
        'kotlin.js.jsThrow' : (e) => { throw e; },
        'kotlin.io.printError' : (error) => console.error(error),
        'kotlin.io.printlnImpl' : (message) => console.log(message),
        'kotlin.js.jsArrayGet' : (array, index) => array[index],
        'kotlin.js.length_$external_prop_getter' : (_this) => _this.length,
        'kotlin.js.__convertKotlinClosureToJsClosure_((Js?)->Js?)' : (f) => getCachedJsObject(f, (p0) => wasmExports['__callFunction_((Js?)->Js?)'](f, p0)),
        'kotlin.js.then_$external_fun' : (_this, p0, p1) => _this.then(p0, p1),
        'kotlin.js.__convertKotlinClosureToJsClosure_((Js)->Js?)' : (f) => getCachedJsObject(f, (p0) => wasmExports['__callFunction_((Js)->Js?)'](f, p0)),
        'kotlin.random.initialSeed' : () => ((Math.random() * Math.pow(2, 32)) | 0),
        'kotlin.wasm.internal.getJsClassName' : (jsKlass) => jsKlass.name,
        'kotlin.wasm.internal.getConstructor' : (obj) => obj.constructor,
        'kotlinx.browser.window_$external_prop_getter' : () => window,
        'kotlinx.browser.document_$external_prop_getter' : () => document,
        'org.w3c.dom.length_$external_prop_getter' : (_this) => _this.length,
        'org.w3c.dom.item_$external_fun' : (_this, p0) => _this.item(p0),
        'org.khronos.webgl.Int8Array_$external_fun' : (p0, p1, p2, isDefault0, isDefault1) => new Int8Array(p0, isDefault0 ? undefined : p1, isDefault1 ? undefined : p2, ),
        'org.khronos.webgl.length_$external_prop_getter' : (_this) => _this.length,
        'org.w3c.dom.clipboard.clipboardData_$external_prop_getter' : (_this) => _this.clipboardData,
        'org.w3c.dom.clipboard.ClipboardEvent_$external_class_instanceof' : (x) => x instanceof ClipboardEvent,
        'org.w3c.dom.css.cursor_$external_prop_setter' : (_this, v) => _this.cursor = v,
        'org.w3c.dom.css.height_$external_prop_setter' : (_this, v) => _this.height = v,
        'org.w3c.dom.css.left_$external_prop_setter' : (_this, v) => _this.left = v,
        'org.w3c.dom.css.top_$external_prop_setter' : (_this, v) => _this.top = v,
        'org.w3c.dom.css.width_$external_prop_setter' : (_this, v) => _this.width = v,
        'org.w3c.dom.css.setProperty_$external_fun' : (_this, p0, p1, p2, isDefault0) => _this.setProperty(p0, p1, isDefault0 ? undefined : p2, ),
        'org.w3c.dom.css.style_$external_prop_getter' : (_this) => _this.style,
        'org.w3c.dom.encryptedmedia.__convertKotlinClosureToJsClosure_((Js)->Unit)' : (f) => getCachedJsObject(f, (p0) => wasmExports['__callFunction_((Js)->Unit)'](f, p0)),
        'org.w3c.dom.events.KeyboardEventInit_js_code' : (key, code, location, repeat, isComposing, ctrlKey, shiftKey, altKey, metaKey, modifierAltGraph, modifierCapsLock, modifierFn, modifierFnLock, modifierHyper, modifierNumLock, modifierScrollLock, modifierSuper, modifierSymbol, modifierSymbolLock, view, detail, bubbles, cancelable, composed) => { return { key, code, location, repeat, isComposing, ctrlKey, shiftKey, altKey, metaKey, modifierAltGraph, modifierCapsLock, modifierFn, modifierFnLock, modifierHyper, modifierNumLock, modifierScrollLock, modifierSuper, modifierSymbol, modifierSymbolLock, view, detail, bubbles, cancelable, composed }; },
        'org.w3c.dom.events.addEventListener_$external_fun' : (_this, p0, p1, p2) => _this.addEventListener(p0, p1, p2),
        'org.w3c.dom.events.addEventListener_$external_fun_1' : (_this, p0, p1) => _this.addEventListener(p0, p1),
        'org.w3c.dom.events.addEventListener_$external_fun_2' : (_this, p0, p1) => _this.addEventListener(p0, p1),
        'org.w3c.dom.events.removeEventListener_$external_fun' : (_this, p0, p1) => _this.removeEventListener(p0, p1),
        'org.w3c.dom.events.removeEventListener_$external_fun_1' : (_this, p0, p1) => _this.removeEventListener(p0, p1),
        'org.w3c.dom.events.type_$external_prop_getter' : (_this) => _this.type,
        'org.w3c.dom.events.stopPropagation_$external_fun' : (_this, ) => _this.stopPropagation(),
        'org.w3c.dom.events.preventDefault_$external_fun' : (_this, ) => _this.preventDefault(),
        'org.w3c.dom.events.Event_$external_class_instanceof' : (x) => x instanceof Event,
        'org.w3c.dom.events.ctrlKey_$external_prop_getter' : (_this) => _this.ctrlKey,
        'org.w3c.dom.events.shiftKey_$external_prop_getter' : (_this) => _this.shiftKey,
        'org.w3c.dom.events.altKey_$external_prop_getter' : (_this) => _this.altKey,
        'org.w3c.dom.events.metaKey_$external_prop_getter' : (_this) => _this.metaKey,
        'org.w3c.dom.events.button_$external_prop_getter' : (_this) => _this.button,
        'org.w3c.dom.events.buttons_$external_prop_getter' : (_this) => _this.buttons,
        'org.w3c.dom.events.offsetX_$external_prop_getter' : (_this) => _this.offsetX,
        'org.w3c.dom.events.offsetY_$external_prop_getter' : (_this) => _this.offsetY,
        'org.w3c.dom.events.MouseEvent_$external_class_instanceof' : (x) => x instanceof MouseEvent,
        'org.w3c.dom.events.KeyboardEvent_$external_fun' : (p0, p1, isDefault0) => new KeyboardEvent(p0, isDefault0 ? undefined : p1, ),
        'org.w3c.dom.events.key_$external_prop_getter' : (_this) => _this.key,
        'org.w3c.dom.events.location_$external_prop_getter' : (_this) => _this.location,
        'org.w3c.dom.events.ctrlKey_$external_prop_getter_1' : (_this) => _this.ctrlKey,
        'org.w3c.dom.events.shiftKey_$external_prop_getter_1' : (_this) => _this.shiftKey,
        'org.w3c.dom.events.altKey_$external_prop_getter_1' : (_this) => _this.altKey,
        'org.w3c.dom.events.metaKey_$external_prop_getter_1' : (_this) => _this.metaKey,
        'org.w3c.dom.events.keyCode_$external_prop_getter' : (_this) => _this.keyCode,
        'org.w3c.dom.events.DOM_KEY_LOCATION_RIGHT_$external_prop_getter' : (_this) => _this.DOM_KEY_LOCATION_RIGHT,
        'org.w3c.dom.events.Companion_$external_object_getInstance' : () => KeyboardEvent,
        'org.w3c.dom.events.KeyboardEvent_$external_class_instanceof' : (x) => x instanceof KeyboardEvent,
        'org.w3c.dom.events.deltaX_$external_prop_getter' : (_this) => _this.deltaX,
        'org.w3c.dom.events.deltaY_$external_prop_getter' : (_this) => _this.deltaY,
        'org.w3c.dom.events.WheelEvent_$external_class_instanceof' : (x) => x instanceof WheelEvent,
        'org.w3c.dom.AddEventListenerOptions_js_code' : (passive, once, capture) => { return { passive, once, capture }; },
        'org.w3c.dom.navigator_$external_prop_getter' : (_this) => _this.navigator,
        'org.w3c.dom.devicePixelRatio_$external_prop_getter' : (_this) => _this.devicePixelRatio,
        'org.w3c.dom.requestAnimationFrame_$external_fun' : (_this, p0) => _this.requestAnimationFrame(p0),
        'org.w3c.dom.__convertKotlinClosureToJsClosure_((Double)->Unit)' : (f) => getCachedJsObject(f, (p0) => wasmExports['__callFunction_((Double)->Unit)'](f, p0)),
        'org.w3c.dom.matchMedia_$external_fun' : (_this, p0) => _this.matchMedia(p0),
        'org.w3c.dom.matches_$external_prop_getter' : (_this) => _this.matches,
        'org.w3c.dom.addListener_$external_fun' : (_this, p0) => _this.addListener(p0),
        'org.w3c.dom.focus_$external_fun' : (_this, ) => _this.focus(),
        'org.w3c.dom.blur_$external_fun' : (_this, ) => _this.blur(),
        'org.w3c.dom.documentElement_$external_prop_getter' : (_this) => _this.documentElement,
        'org.w3c.dom.body_$external_prop_getter' : (_this) => _this.body,
        'org.w3c.dom.createElement_$external_fun' : (_this, p0, p1, isDefault0) => _this.createElement(p0, isDefault0 ? undefined : p1, ),
        'org.w3c.dom.hasFocus_$external_fun' : (_this, ) => _this.hasFocus(),
        'org.w3c.dom.clearTimeout_$external_fun' : (_this, p0, isDefault0) => _this.clearTimeout(isDefault0 ? undefined : p0, ),
        'org.w3c.dom.fetch_$external_fun' : (_this, p0, p1, isDefault0) => _this.fetch(p0, isDefault0 ? undefined : p1, ),
        'org.w3c.dom.namespaceURI_$external_prop_getter' : (_this) => _this.namespaceURI,
        'org.w3c.dom.localName_$external_prop_getter' : (_this) => _this.localName,
        'org.w3c.dom.clientWidth_$external_prop_getter' : (_this) => _this.clientWidth,
        'org.w3c.dom.clientHeight_$external_prop_getter' : (_this) => _this.clientHeight,
        'org.w3c.dom.getAttribute_$external_fun' : (_this, p0) => _this.getAttribute(p0),
        'org.w3c.dom.getAttributeNS_$external_fun' : (_this, p0, p1) => _this.getAttributeNS(p0, p1),
        'org.w3c.dom.setAttribute_$external_fun' : (_this, p0, p1) => _this.setAttribute(p0, p1),
        'org.w3c.dom.getBoundingClientRect_$external_fun' : (_this, ) => _this.getBoundingClientRect(),
        'org.w3c.dom.Element_$external_class_instanceof' : (x) => x instanceof Element,
        'org.w3c.dom.language_$external_prop_getter' : (_this) => _this.language,
        'org.w3c.dom.nodeName_$external_prop_getter' : (_this) => _this.nodeName,
        'org.w3c.dom.childNodes_$external_prop_getter' : (_this) => _this.childNodes,
        'org.w3c.dom.lookupPrefix_$external_fun' : (_this, p0) => _this.lookupPrefix(p0),
        'org.w3c.dom.appendChild_$external_fun' : (_this, p0) => _this.appendChild(p0),
        'org.w3c.dom.item_$external_fun_1' : (_this, p0) => _this.item(p0),
        'org.w3c.dom.identifier_$external_prop_getter' : (_this) => _this.identifier,
        'org.w3c.dom.clientX_$external_prop_getter' : (_this) => _this.clientX,
        'org.w3c.dom.clientY_$external_prop_getter' : (_this) => _this.clientY,
        'org.w3c.dom.top_$external_prop_getter' : (_this) => _this.top,
        'org.w3c.dom.left_$external_prop_getter' : (_this) => _this.left,
        'org.w3c.dom.remove_$external_fun' : (_this, ) => _this.remove(),
        'org.w3c.dom.getData_$external_fun' : (_this, p0) => _this.getData(p0),
        'org.w3c.dom.setData_$external_fun' : (_this, p0, p1) => _this.setData(p0, p1),
        'org.w3c.dom.width_$external_prop_setter' : (_this, v) => _this.width = v,
        'org.w3c.dom.height_$external_prop_setter' : (_this, v) => _this.height = v,
        'org.w3c.dom.HTMLCanvasElement_$external_class_instanceof' : (x) => x instanceof HTMLCanvasElement,
        'org.w3c.dom.changedTouches_$external_prop_getter' : (_this) => _this.changedTouches,
        'org.w3c.dom.TouchEvent_$external_class_instanceof' : (x) => x instanceof TouchEvent,
        'org.w3c.dom.matches_$external_prop_getter_1' : (_this) => _this.matches,
        'org.w3c.dom.MediaQueryListEvent_$external_class_instanceof' : (x) => x instanceof MediaQueryListEvent,
        'org.w3c.dom.value_$external_prop_setter' : (_this, v) => _this.value = v,
        'org.w3c.dom.setSelectionRange_$external_fun' : (_this, p0, p1, p2, isDefault0) => _this.setSelectionRange(p0, p1, isDefault0 ? undefined : p2, ),
        'org.w3c.dom.HTMLTextAreaElement_$external_class_instanceof' : (x) => x instanceof HTMLTextAreaElement,
        'org.w3c.dom.parsing.DOMParser_$external_fun' : () => new DOMParser(),
        'org.w3c.dom.parsing.parseFromString_$external_fun' : (_this, p0, p1) => _this.parseFromString(p0, p1),
        'org.w3c.fetch.ok_$external_prop_getter' : (_this) => _this.ok,
        'org.w3c.fetch.blob_$external_fun' : (_this, ) => _this.blob(),
        'org.w3c.performance.performance_$external_prop_getter' : (_this) => _this.performance,
        'org.w3c.performance.now_$external_fun' : (_this, ) => _this.now(),
        'kotlinx.coroutines.tryGetProcess' : () => (typeof(process) !== 'undefined' && typeof(process.nextTick) === 'function') ? process : null,
        'kotlinx.coroutines.tryGetWindow' : () => (typeof(window) !== 'undefined' && window != null && typeof(window.addEventListener) === 'function') ? window : null,
        'kotlinx.coroutines.nextTick_$external_fun' : (_this, p0) => _this.nextTick(p0),
        'kotlinx.coroutines.error_$external_fun' : (_this, p0) => _this.error(p0),
        'kotlinx.coroutines.console_$external_prop_getter' : () => console,
        'kotlinx.coroutines.createScheduleMessagePoster' : (process) => () => Promise.resolve(0).then(process),
        'kotlinx.coroutines.__callJsClosure_(()->Unit)' : (f, ) => f(),
        'kotlinx.coroutines.createRescheduleMessagePoster' : (window) => () => window.postMessage('dispatchCoroutine', '*'),
        'kotlinx.coroutines.subscribeToWindowMessages' : (window, process) => {
            const handler = (event) => {
                if (event.source == window && event.data == 'dispatchCoroutine') {
                    event.stopPropagation();
                    process();
                }
            }
            window.addEventListener('message', handler, true);
        },
        'kotlinx.coroutines.setTimeout' : (window, handler, timeout) => window.setTimeout(handler, timeout),
        'kotlinx.coroutines.clearTimeout' : (handle) => { if (typeof clearTimeout !== 'undefined') clearTimeout(handle); },
        'kotlinx.coroutines.setTimeout_$external_fun' : (p0, p1) => setTimeout(p0, p1),
        'org.jetbrains.skiko.w3c.language_$external_prop_getter' : (_this) => _this.language,
        'org.jetbrains.skiko.w3c.clipboard_$external_prop_getter' : (_this) => _this.clipboard,
        'org.jetbrains.skiko.w3c.userAgent_$external_prop_getter' : (_this) => _this.userAgent,
        'org.jetbrains.skiko.w3c.navigator_$external_prop_getter' : (_this) => _this.navigator,
        'org.jetbrains.skiko.w3c.performance_$external_prop_getter' : (_this) => _this.performance,
        'org.jetbrains.skiko.w3c.requestAnimationFrame_$external_fun' : (_this, p0) => _this.requestAnimationFrame(p0),
        'org.jetbrains.skiko.w3c.window_$external_object_getInstance' : () => window,
        'org.jetbrains.skiko.w3c.writeText_$external_fun' : (_this, p0) => _this.writeText(p0),
        'org.jetbrains.skiko.w3c.now_$external_fun' : (_this, ) => _this.now(),
        'org.jetbrains.skiko.w3c.width_$external_prop_getter' : (_this) => _this.width,
        'org.jetbrains.skiko.w3c.height_$external_prop_getter' : (_this) => _this.height,
        'org.jetbrains.skiko.w3c.HTMLCanvasElement_$external_class_instanceof' : (x) => x instanceof HTMLCanvasElement,
        'org.jetbrains.skia.impl.FinalizationRegistry_$external_fun' : (p0) => new FinalizationRegistry(p0),
        'org.jetbrains.skia.impl.register_$external_fun' : (_this, p0, p1) => _this.register(p0, p1),
        'org.jetbrains.skia.impl.unregister_$external_fun' : (_this, p0) => _this.unregister(p0),
        'org.jetbrains.skia.impl._releaseLocalCallbackScope_$external_fun' : () => _ref_Li9za2lrby5tanM_._releaseLocalCallbackScope(),
        'org.jetbrains.skiko.getNavigatorInfo' : () => navigator.userAgentData ? navigator.userAgentData.platform : navigator.platform,
        'org.jetbrains.skiko.wasm.createContext_$external_fun' : (_this, p0, p1) => _this.createContext(p0, p1),
        'org.jetbrains.skiko.wasm.makeContextCurrent_$external_fun' : (_this, p0) => _this.makeContextCurrent(p0),
        'org.jetbrains.skiko.wasm.GL_$external_object_getInstance' : () => _ref_Li9za2lrby5tanM_.GL,
        'org.jetbrains.skiko.wasm.createDefaultContextAttributes' : () => {
            return {
                alpha: 1,
                depth: 1,
                stencil: 8,
                antialias: 0,
                premultipliedAlpha: 1,
                preserveDrawingBuffer: 0,
                preferLowPowerToHighPerformance: 0,
                failIfMajorPerformanceCaveat: 0,
                enableExtensionsByDefault: 1,
                explicitSwapControl: 0,
                renderViaOffscreenBackBuffer: 0,
                majorVersion: 2,
            }
        }
        ,
        'androidx.compose.ui.text.intl.getUserPreferredLanguagesAsArray' : () => window.navigator.languages,
        'androidx.compose.ui.text.intl.parseLanguageTagToIntlLocale' : (languageTag) => new Intl.Locale(languageTag),
        'androidx.compose.ui.text.intl.language_$external_prop_getter' : (_this) => _this.language,
        'androidx.compose.ui.text.intl.region_$external_prop_getter' : (_this) => _this.region,
        'androidx.compose.ui.text.intl.baseName_$external_prop_getter' : (_this) => _this.baseName,
        'androidx.compose.ui.window.isMatchMediaSupported' : () => window.matchMedia != undefined,
        'androidx.compose.ui.platform.inputType_$external_prop_getter' : (_this) => _this.inputType,
        'androidx.compose.ui.platform.data_$external_prop_getter' : (_this) => _this.data,
        'androidx.compose.ui.platform.keyCode_$external_prop_setter' : (_this, v) => _this.keyCode = v,
        'androidx.compose.ui.window.force_$external_prop_getter' : (_this) => _this.force,
        'androidx.compose.foundation.text.EventListener' : (handler) => (event) => { handler(event) },
        'org.jetbrains.compose.resources.Locale_$external_fun' : (p0) => new Intl.Locale(p0),
        'org.jetbrains.compose.resources.language_$external_prop_getter' : (_this) => _this.language,
        'org.jetbrains.compose.resources.region_$external_prop_getter' : (_this) => _this.region,
        'org.jetbrains.compose.resources.jsExportBlobAsArrayBuffer' : (blob) => blob.arrayBuffer(),
        'org.jetbrains.compose.resources.jsExportInt8ArrayToWasm' :  (src, size, dstAddr) => {
                const mem8 = new Int8Array(wasmExports.memory.buffer, dstAddr, size);
                mem8.set(src);
            }
        ,
        'org.xephyrous.com.JSInterop.teamDataToJSON_$external_fun' : (_this, p0, p1) => _this.teamDataToJSON(p0, p1),
        'org.xephyrous.com.JSInterop.extractJSONObject_$external_fun' : (_this, p0) => _this.extractJSONObject(p0),
        'org.xephyrous.com.JSInterop.extractJSONArray_$external_fun' : (_this, p0, p1) => _this.extractJSONArray(p0, p1),
        'org.xephyrous.com.JSInterop.cacheJSON_$external_fun' : (_this, p0) => _this.cacheJSON(p0),
        'org.xephyrous.com.JSInterop.clearJSONCache_$external_fun' : (_this, ) => _this.clearJSONCache(),
        'org.xephyrous.com.JSInterop.JSUtils_$external_object_getInstance' : () => _ref_Li9qcy9qcy11dGlscy5qcw_,
        'org.xephyrous.com.JSInterop.initializeFirebase_$external_fun' : (_this, ) => _this.initializeFirebase(),
        'org.xephyrous.com.JSInterop.createUser_$external_fun' : (_this, ) => _this.createUser(),
        'org.xephyrous.com.JSInterop.calculateSessionUUID_$external_fun' : (_this, ) => _this.calculateSessionUUID(),
        'org.xephyrous.com.JSInterop.setSessionUUID_$external_fun' : (_this, p0) => _this.setSessionUUID(p0),
        'org.xephyrous.com.JSInterop.addMessage_$external_fun' : (_this, p0, p1) => _this.addMessage(p0, p1),
        'org.xephyrous.com.JSInterop.addTeam_$external_fun' : (_this, p0) => _this.addTeam(p0),
        'org.xephyrous.com.JSInterop.getTeamNames_$external_fun' : (_this, ) => _this.getTeamNames(),
        'org.xephyrous.com.JSInterop.getTeamUUIDs_$external_fun' : (_this, ) => _this.getTeamUUIDs(),
        'org.xephyrous.com.JSInterop.getTeamByUUID_$external_fun' : (_this, p0) => _this.getTeamByUUID(p0),
        'org.xephyrous.com.JSInterop.getMessages_$external_fun' : (_this, ) => _this.getMessages(),
        'org.xephyrous.com.JSInterop.debug_$external_fun' : (_this, p0) => _this.debug(p0),
        'org.xephyrous.com.JSInterop.JSFirebase_$external_object_getInstance' : () => _ref_Li9qcy9maXJlYmFzZS1hcGkuanM_,
        'org.xephyrous.com.JSInterop.InvokeModel_$external_fun' : (_this, p0) => _this.InvokeModel(p0),
        'org.xephyrous.com.JSInterop.InvokeRAG_$external_fun' : (_this, p0) => _this.InvokeRAG(p0),
        'org.xephyrous.com.JSInterop.JSBedrockRuntime_$external_object_getInstance' : () => _ref_Li9qcy9hd3MtYXBpLmpz_,
        'org.xephyrous.com.JSInterop.addCookie_$external_fun' : (_this, p0, p1) => _this.addCookie(p0, p1),
        'org.xephyrous.com.JSInterop.getCookie_$external_fun' : (_this, p0) => _this.getCookie(p0),
        'org.xephyrous.com.JSInterop.JSCookieHandler_$external_object_getInstance' : () => _ref_Li9qcy9jb29raWUtaGFuZGxlci5qcw_
    }
    
    // Placed here to give access to it from externals (js_code)
    let wasmInstance;
    let require; 
    let wasmExports;

    const isNodeJs = (typeof process !== 'undefined') && (process.release.name === 'node');
    const isDeno = !isNodeJs && (typeof Deno !== 'undefined')
    const isStandaloneJsVM =
        !isDeno && !isNodeJs && (
            typeof d8 !== 'undefined' // V8
            || typeof inIon !== 'undefined' // SpiderMonkey
            || typeof jscOptions !== 'undefined' // JavaScriptCore
        );
    const isBrowser = !isNodeJs && !isDeno && !isStandaloneJsVM && (typeof window !== 'undefined' || typeof self !== 'undefined');
    
    if (!isNodeJs && !isDeno && !isStandaloneJsVM && !isBrowser) {
      throw "Supported JS engine not detected";
    }
    
    const wasmFilePath = './composeApp.wasm';
    const importObject = {
        js_code,
        './skiko.mjs': imports['./skiko.mjs'],

    };
    
    try {
      if (isNodeJs) {
        const module = await import(/* webpackIgnore: true */'node:module');
        const importMeta = import.meta;
        require = module.default.createRequire(importMeta.url);
        const fs = require('fs');
        const url = require('url');
        const filepath = import.meta.resolve(wasmFilePath);
        const wasmBuffer = fs.readFileSync(url.fileURLToPath(filepath));
        const wasmModule = new WebAssembly.Module(wasmBuffer);
        wasmInstance = new WebAssembly.Instance(wasmModule, importObject);
      }
      
      if (isDeno) {
        const path = await import(/* webpackIgnore: true */'https://deno.land/std/path/mod.ts');
        const binary = Deno.readFileSync(path.fromFileUrl(import.meta.resolve(wasmFilePath)));
        const module = await WebAssembly.compile(binary);
        wasmInstance = await WebAssembly.instantiate(module, importObject);
      }
      
      if (isStandaloneJsVM) {
        const wasmBuffer = read(wasmFilePath, 'binary');
        const wasmModule = new WebAssembly.Module(wasmBuffer);
        wasmInstance = new WebAssembly.Instance(wasmModule, importObject);
      }
      
      if (isBrowser) {
        wasmInstance = (await WebAssembly.instantiateStreaming(fetch(wasmFilePath), importObject)).instance;
      }
    } catch (e) {
      if (e instanceof WebAssembly.CompileError) {
        let text = `Please make sure that your runtime environment supports the latest version of Wasm GC and Exception-Handling proposals.
For more information, see https://kotl.in/wasm-help
`;
        if (isBrowser) {
          console.error(text);
        } else {
          const t = "\n" + text;
          if (typeof console !== "undefined" && console.log !== void 0) 
            console.log(t);
          else 
            print(t);
        }
      }
      throw e;
    }
    
    wasmExports = wasmInstance.exports;
    if (runInitializer) {
        wasmExports._initialize();
    }

    return { instance: wasmInstance,  exports: wasmExports };
}
