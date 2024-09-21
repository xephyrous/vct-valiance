package org.xephyrous.com.Utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random


fun generateBrightPastelColorHex(): String {
    // Ensure that the RGB values are in the bright range (200–255)
    val red = Random.nextInt(200, 256)
    val green = Random.nextInt(200, 256)
    val blue = Random.nextInt(200, 256)

    // Manually convert the RGB values to hex without using String.format
    val redHex = red.toString(16).padStart(2, '0')
    val greenHex = green.toString(16).padStart(2, '0')
    val blueHex = blue.toString(16).padStart(2, '0')

    // Concatenate the hex values
    return "#$redHex$greenHex$blueHex".uppercase()
}