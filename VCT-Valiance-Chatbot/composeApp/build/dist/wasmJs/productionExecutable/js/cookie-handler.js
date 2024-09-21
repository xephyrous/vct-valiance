/**
 * Sets a browser cookie, with the maximum expiration date
 * @param name The name of the cookie to set
 * @param value The value of the cookie to set
 * @param expires When the cookie expires, defaults to maximum value (epoch)
 * @returns {any} nothing (undefined) if successful, null if not
 */
function addCookie(
    name, value,
    expires = 2147483647
) {
    try {
        document.cookie = `${name}=${value}; expires=${expires}; path=/`
    } catch (_) { return null; }
}

/**
 * Removes a browser cookie by setting its expiration date to The first (0) epoch time
 * @param name The name of the cookie to remove
 * @returns {any} nothing (undefined) if successful, null if not
 */
function removeCookie(name) {
    try {
        document.cookie = `${name}=;expires=Thu, 01 Jan 1970 00:00:01 GMT`;
    } catch (_) { return null }
}

/**
 * Gets a browser cookie by name
 * @param name THe name of the cookie to get
 * @returns {string} The value of the cookie
 */
function getCookie(name)  {
    let fullName = name + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(fullName.length, c.length);
        }
    }

    return "";
}

export {
    addCookie,
    getCookie,
    removeCookie
}