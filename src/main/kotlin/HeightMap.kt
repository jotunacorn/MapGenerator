package se.afsandeberg.kotlin.mapgenerator

import java.util.*

class HeightMap(private val width: Int, private val height: Int) {
    private val adjacentWeight = 1.5
    private val fillChunks = 6
    private val waterLevel = 0.4
    private val numberOfRidges = 2
    private val random = Random()
    val map: Array<Array<Double?>> = Array(width, { arrayOfNulls<Double>(height) })
    var maxValue = -2.0
    var minValue = 2.0

    init {
        fillMap()
        initializeMap()
        addRidges()
        findMinMax()
        //addWater()
        findMinMax()
    }

    private fun fillMap() {
        val chunkHeights: Array<Double> = Array(fillChunks * fillChunks, { random.nextDouble() })
        for (i in 0 until width) {
            for (j in 0 until height) {
                val index = ((i * fillChunks) / width) * fillChunks + ((j * fillChunks) / width)
                map[i][j] = chunkHeights[index]
            }
        }
    }

    private fun initializeMap() {
        for (i in 0 until width) {
            for (j in 0 until height) {
                val adjacent = getAdjacent(j, i)
                var average = 0.0
                for (value in adjacent) {
                    if (value != null) {
                        average += value
                    }
                }
                average *= adjacentWeight
                average += random.nextGaussian()
                average /= adjacent.size * adjacentWeight + 1
                map[i][j] = average
            }
        }
    }


    private fun getAdjacent(j: Int, i: Int): MutableList<Double?> {
        val adjacent: MutableList<Double?> = mutableListOf()
        for (k in width - 1..width + 1) {
            if (k >= 0 && k < width - 1 && j > 0) {
                adjacent.add(map[k][j - 1])
            }
        }
        for (k in width - 1..width + 1) {
            if (k >= 0 && k < width - 1 && j < height - 1) {
                adjacent.add(map[k][j + 1])
            }
        }
        if (i > 0) {
            adjacent.add(map[i - 1][j])
        }
        if (i < width - 1) {
            adjacent.add(map[i + 1][j])
        }
        return adjacent
    }

    private fun addRidges() {

    }

    private fun addWater() {
        val waterHeight = (maxValue + minValue) * waterLevel + minValue
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (map[i][j] != null && map[i][j]!! < waterHeight) {
                    map[i][j] = null
                }
            }
        }
    }

    private fun findMinMax() {
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (map[i][j] != null && map[i][j]!! > maxValue) {
                    maxValue = map[i][j]!!
                }
                if (map[i][j] != null && map[i][j]!! < minValue) {
                    minValue = map[i][j]!!
                }
            }
        }
    }
}