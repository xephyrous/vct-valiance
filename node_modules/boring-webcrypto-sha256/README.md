# boring-webcrypto-sha256

A quick (< 10 lines) version of MDNs [function to get the SHA256 hash of a string](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/digest#converting_a_digest_to_a_hex_string) 

## Quick reminder that I am a strange person on the internet

Read the code, check it's correct, compare it to MDN version, make sure the built version is the same, and compare with the Linux command line to ensure you get the same output:

```
echo -n 'someInput' | sha256sum
```

## Usage

```
npm i boring-webcrypto-sha256
```

Then just: 

```
import { getSHA256Hash } from "./sha256";
```

and

```
const hash = await getSHA256Hash('someInput')
```

That's all.
