# zenroom-example

A [re-frame](https://github.com/Day8/re-frame) application designed to ... well, that part is up to you.

## Development Mode

### Start Cider from Emacs:

Refer to the [shadow-cljs Emacs / CIDER documentation](https://shadow-cljs.github.io/docs/UsersGuide.html#cider).

### Run application:

```
lein clean
npm i
```

As per [Make (L) with Zenroom and Javascript part 3](https://www.dyne.org/using-zenroom-with-javascript-react-part3/) 

cp node_modules/zenroom/dist/lib/zenroom.wasm resources/public

And in node_modules/zenroom/dist/lib/zenroom.js change

```
var wasmBinaryFile = 'zenroom.wasm';
if (!isDataURI(wasmBinaryFile)) {
  wasmBinaryFile = locateFile(wasmBinaryFile);
}
```

to this

```
var wasmBinaryFile = '/zenroom.wasm';
if (!isDataURI(wasmBinaryFile)) {
  // wasmBinaryFile = locateFile(wasmBinaryFile);
}
```

Then run

```
lein dev
```

shadow-cljs will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:8280](http://localhost:8280).

## Production Build

