package se.afsandeberg.kotlin.mapgenerator

import javafx.application.Application
import tornadofx.*

fun main(args: Array<String>) {
    Application.launch(ChartApp::class.java, *args)
}

class ChartApp : App() {
    override val primaryView = MainView::class
}
