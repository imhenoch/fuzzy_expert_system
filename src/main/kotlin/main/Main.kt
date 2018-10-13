package main

import di.DaggerFuzzyComponent

fun main(args: Array<String>) {
    val component = DaggerFuzzyComponent.builder().build()
    component.parentWindow().isVisible = true
}