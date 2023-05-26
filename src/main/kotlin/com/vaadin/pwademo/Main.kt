package com.vaadin.pwademo

import com.github.mvysny.vaadinboot.VaadinBoot

/**
 * Run this function to launch your app in Embedded Jetty.
 */
fun main(vararg args: String) {
    VaadinBoot().withArgs(args).run()
}
