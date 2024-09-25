package org.xephyrous.com.Utils

import androidx.compose.ui.graphics.Color
import vctvaliancechatbot.composeapp.generated.resources.*
import vctvaliancechatbot.composeapp.generated.resources.Res
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

val agentMap = mapOf(
    "astra" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Astra_icon),
    "breach" to arrayOf(Res.drawable.InitiatorClassSymbol, Res.drawable.Breach_icon),
    "brimstone" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Brimstone_icon),
    "chamber" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Chamber_icon),
    "clove" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Clove_icon),
    "cypher" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Cypher_icon),
    "deadlock" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Deadlock_icon),
    "gekko" to arrayOf(Res.drawable.InitiatorClassSymbol, Res.drawable.Gekko_icon),
    "harbor" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Harbor_icon),
    "iso" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Iso_icon),
    "jett" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Jett_icon),
    "kay/o" to arrayOf(Res.drawable.InitiatorClassSymbol, Res.drawable.KAYO_icon),
    "killjoy" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Killjoy_icon),
    "neon" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Neon_icon),
    "omen" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Omen_icon),
    "phoenix" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Phoenix_icon),
    "raze" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Raze_icon),
    "reyna" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Reyna_icon),
    "sage" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Sage_icon),
    "skye" to arrayOf(Res.drawable.InitiatorClassSymbol, Res.drawable.Skye_icon),
    "sova" to arrayOf(Res.drawable.InitiatorClassSymbol, Res.drawable.Sova_icon),
    "viper" to arrayOf(Res.drawable.ControllerClassSymbol, Res.drawable.Viper_icon),
    "vyse" to arrayOf(Res.drawable.SentinelClassSymbol, Res.drawable.Vyse_icon),
    "yoru" to arrayOf(Res.drawable.DuelistClassSymbol, Res.drawable.Yoru_icon),
)