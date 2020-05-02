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

### shadow-cljs editor integration:

Refer to the [shadow-cljs Editor Integration documentation](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration).

- [shadow-cljs Emacs / CIDER](https://shadow-cljs.github.io/docs/UsersGuide.html#cider)
- [Visual Studio Code with Calva](https://github.com/BetterThanTomorrow/calva/blob/master/docs/readthedocs/source/jack-in-guide.md)
- [IntelliJ or Eclipse with Cursive](https://cursive-ide.com/) 
- [Vim with Fireplace](https://github.com/tpope/vim-fireplace).

## Production Build

Run

```shell
shadow-cljs release app
```

Compiled frontend will be available in `resources/public`.
