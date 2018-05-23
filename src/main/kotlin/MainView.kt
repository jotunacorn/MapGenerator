package se.afsandeberg.kotlin.mapgenerator

import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle
import tornadofx.*


class MainView : View("Map Generator") {

    private val screenHeight = 768.0
    private val screenWidth = 1024.0
    private val pointsX = 54
    private val pointsY = 54

    var gridWidth = screenWidth / pointsX
    var gridHeight = screenHeight / pointsY

    override val root = stackpane {
        group {
            // initialize playfield
            val heightMap = HeightMap(pointsX, pointsY)
            for (i in 0 until pointsX) {
                for (j in 0 until pointsY) {
                    var value = heightMap.map[i][j]
                    if (value == null) {
                        value = 0.0
                    }
                    value += Math.abs(heightMap.minValue)

                    value /= heightMap.maxValue + Math.abs(heightMap.minValue)
                    // create node
                    val node = RectangleNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, value)

                    // add node to group
                    add(node)

                }
            }
        }
    }

    class RectangleNode(x: Double, y: Double, width: Double, height: Double, value: Double) : StackPane() {

        init {
            // create rectangle
            val rectangle = Rectangle(width, height)
            val color = c(value, 0.0, 0.0)
            rectangle.stroke = color
            rectangle.fill = color

            // create label
            //val label = Label(name)

            // set position
            translateX = x
            translateY = y

            children.add(rectangle)

        }

    }
}
