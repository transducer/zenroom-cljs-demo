# zenroom-cljs-demo

A [Reagent](reagent-project.github.io/) application designed to work with the [Zenroom](https://zenroom.org/) [npm package](https://www.npmjs.com/package/zenroom) and [shadow-cljs](https://shadow-cljs.org/).

## Development Mode

### Run application:

```shell
# npm
npm instal

# yarn
yarn

shadow-cljs watch app
```

shadow-cljs will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:8280](http://localhost:8280).

### Start Cider from Emacs:

Refer to the [shadow-cljs Emacs / CIDER documentation](https://shadow-cljs.github.io/docs/UsersGuide.html#cider).

## Production Build

Run

```shell
shadow-cljs release app
```

Compiled frontend will be available in `resources/public`.
