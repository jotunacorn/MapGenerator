package se.afsandeberg.kotlin.mapgenerator

import javafx.animation.Interpolator
import javafx.scene.paint.Color
import javafx.util.Duration
import tornadofx.*

class MainView : View("Map Generator") {
    override val root = stackpane {
        group {
            val quad = quadcurve {
                startX = 0.0
                startY = 150.0
                endX = 150.0
                endY = 150.0
                controlX = 75.0
                controlY = 0.0
                fill = Color.BURLYWOOD
            }
            timeline {
                keyframe(Duration.seconds(5.0)) {
                    keyvalue(quad.startXProperty(), 75.0, interpolator = Interpolator.EASE_BOTH)
                    keyvalue(quad.endXProperty(), 75.0, interpolator = Interpolator.EASE_BOTH)
                    isAutoReverse = true
                    cycleCount = 4
                }
            }
        }
    }
}
