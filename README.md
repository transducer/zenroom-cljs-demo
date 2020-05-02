# zenroom-example

A [Reagent](reagent-project.github.io/) application designed to work with the Zenroom npm package.

## Development Mode

### Run application:

    npm i
    shadow-cljs watch app

shadow-cljs will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:8280](http://localhost:8280).

### Start Cider from Emacs:

Refer to the [shadow-cljs Emacs / CIDER documentation](https://shadow-cljs.github.io/docs/UsersGuide.html#cider).

## Production Build

Run

    shadow-cljs release app

Compiled frontend will be available in `resources/public`.
