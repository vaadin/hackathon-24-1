window.Vaadin.Flow._vaadinAppLayoutDrawerSplitterInit = (splitter, appLayout, initialWidth, minWidth, maxWidth) => {
    const DEFAULT_APP_LAYOUT_TRANSITION = "200ms";

    let currentWidth = 0;
    if (!appLayout) {console.error("applayout not found in DOM");}
    if (!appLayout.shadowRoot) {console.error("applayout has no shadowroot");}

    let drawer = appLayout.shadowRoot.getElementById("drawer");
    if (!drawer) {console.error("drawer not obtainable from shadow");}

    let fSetWidth = (width) => {
        width = Math.max(minWidth, Math.min(maxWidth, width));
        currentWidth = width;
        let widthInPx = `${width}px`;

        requestAnimationFrame(time => {
            drawer.style.width = widthInPx;
            appLayout.style.setProperty("--_vaadin-app-layout-drawer-offset-size", widthInPx);
        });
    }

    if (initialWidth) {
        fSetWidth(initialWidth);
    }

    // let defaultAppLayoutTransition = appLayout.style.getProperty("--vaadin-app-layout-transition"); // FIXME no getProperty?

    let fMouseMoveListener;
    let fMouseUpListener;

    splitter.addEventListener("mousedown", event => {
        document.body.addEventListener("mousemove", fMouseMoveListener, {passive: true});
        document.body.addEventListener("mouseup", fMouseUpListener);

        document.body.style.setProperty("user-select", "none");
        appLayout.style.setProperty("--vaadin-app-layout-transition", "0");
    })

    fMouseMoveListener = (event) => {
        fSetWidth(event.clientX);
    };

    fMouseUpListener = (event) => {
        document.body.style.removeProperty("user-select");

        appLayout.style.setProperty("--vaadin-app-layout-transition", DEFAULT_APP_LAYOUT_TRANSITION);

        document.body.removeEventListener("mousemove", fMouseMoveListener);
        document.body.removeEventListener("mouseup", fMouseUpListener);

        splitter.parentNode.dispatchEvent(new CustomEvent("vaadin-app-layout-drawer-width-changed", {
            detail: {
                currentWidth
            }
        }));
    };
}