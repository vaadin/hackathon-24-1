/**
 * NOTICE: this is an auto-generated file
 *
 * This file has been generated for `pnpm install` task.
 * It is used to pin client side dependencies.
 * This file will be overwritten on every run.
 */

const fs = require('fs');

const versions = {"@vaadin/bundles":"24.1.0-beta2","@vaadin/a11y-base":"24.1.0-beta2","@vaadin/accordion":"24.1.0-beta2","@vaadin/app-layout":"24.1.0-beta2","@vaadin/avatar":"24.1.0-beta2","@vaadin/avatar-group":"24.1.0-beta2","@vaadin/button":"24.1.0-beta2","@vaadin/checkbox":"24.1.0-beta2","@vaadin/checkbox-group":"24.1.0-beta2","@vaadin/combo-box":"24.1.0-beta2","@vaadin/component-base":"24.1.0-beta2","@vaadin/confirm-dialog":"24.1.0-beta2","@vaadin/context-menu":"24.1.0-beta2","@vaadin/custom-field":"24.1.0-beta2","@vaadin/date-picker":"24.1.0-beta2","@vaadin/date-time-picker":"24.1.0-beta2","@vaadin/details":"24.1.0-beta2","@vaadin/dialog":"24.1.0-beta2","@vaadin/email-field":"24.1.0-beta2","@vaadin/field-base":"24.1.0-beta2","@vaadin/field-highlighter":"24.1.0-beta2","@vaadin/form-layout":"24.1.0-beta2","@vaadin/grid":"24.1.0-beta2","@vaadin/horizontal-layout":"24.1.0-beta2","@vaadin/icon":"24.1.0-beta2","@vaadin/icons":"24.1.0-beta2","@vaadin/input-container":"24.1.0-beta2","@vaadin/integer-field":"24.1.0-beta2","@vaadin/item":"24.1.0-beta2","@vaadin/list-box":"24.1.0-beta2","@vaadin/lit-renderer":"24.1.0-beta2","@vaadin/login":"24.1.0-beta2","@vaadin/menu-bar":"24.1.0-beta2","@vaadin/message-input":"24.1.0-beta2","@vaadin/message-list":"24.1.0-beta2","@vaadin/multi-select-combo-box":"24.1.0-beta2","@vaadin/notification":"24.1.0-beta2","@vaadin/number-field":"24.1.0-beta2","@vaadin/overlay":"24.1.0-beta2","@vaadin/password-field":"24.1.0-beta2","@vaadin/polymer-legacy-adapter":"24.1.0-beta2","@vaadin/progress-bar":"24.1.0-beta2","@vaadin/radio-group":"24.1.0-beta2","@vaadin/scroller":"24.1.0-beta2","@vaadin/select":"24.1.0-beta2","@vaadin/side-nav":"24.1.0-beta2","@vaadin/split-layout":"24.1.0-beta2","@vaadin/tabs":"24.1.0-beta2","@vaadin/tabsheet":"24.1.0-beta2","@vaadin/text-area":"24.1.0-beta2","@vaadin/text-field":"24.1.0-beta2","@vaadin/time-picker":"24.1.0-beta2","@vaadin/tooltip":"24.1.0-beta2","@vaadin/upload":"24.1.0-beta2","@vaadin/vaadin-development-mode-detector":"2.0.6","@vaadin/vaadin-lumo-styles":"24.1.0-beta2","@vaadin/vaadin-material-styles":"24.1.0-beta2","@vaadin/router":"1.7.5","@vaadin/vaadin-usage-statistics":"2.1.2","@vaadin/vertical-layout":"24.1.0-beta2","@vaadin/virtual-list":"24.1.0-beta2","@vaadin/board":"24.1.0-beta2","@vaadin/charts":"24.1.0-beta2","@vaadin/cookie-consent":"24.1.0-beta2","@vaadin/crud":"24.1.0-beta2","@vaadin/grid-pro":"24.1.0-beta2","@vaadin/map":"24.1.0-beta2","@vaadin/rich-text-editor":"24.1.0-beta2","@polymer/polymer":"3.5.1","@vaadin/common-frontend":"0.0.18","@vaadin/vaadin-themable-mixin":"24.1.0-beta2","construct-style-sheets-polyfill":"3.1.0","date-fns":"2.29.3","lit":"2.7.4","mobile-drag-drop":"2.3.0-rc.2","proj4":"2.9.0","@rollup/plugin-replace":"5.0.2","@rollup/pluginutils":"5.0.2","async":"3.2.2","glob":"7.2.3","mkdirp":"1.0.4","rollup-plugin-brotli":"3.1.0","rollup-plugin-visualizer":"5.9.0","strip-css-comments":"5.0.0","transform-ast":"2.4.4","typescript":"5.0.4","vite":"4.3.8","vite-plugin-checker":"0.5.5","workbox-build":"6.5.4","workbox-core":"6.5.4","workbox-precaching":"6.5.4"};

module.exports = {
  hooks: {
    readPackage
  }
};

function readPackage(pkg) {
  const { dependencies } = pkg;

  if (dependencies) {
    for (let k in versions) {
      if (dependencies[k] && dependencies[k] !== versions[k]) {
        pkg.dependencies[k] = versions[k];
      }
    }
  }

  // Forcing chokidar version for now until new babel version is available
  // check out https://github.com/babel/babel/issues/11488
  if (pkg.dependencies.chokidar) {
    pkg.dependencies.chokidar = '^3.4.0';
  }

  return pkg;
}
