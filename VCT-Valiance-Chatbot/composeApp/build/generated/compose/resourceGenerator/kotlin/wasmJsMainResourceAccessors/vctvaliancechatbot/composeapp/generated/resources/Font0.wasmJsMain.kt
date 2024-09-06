@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package vctvaliancechatbot.composeapp.generated.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.FontResource

private object WasmJsMainFont0 {
  public val ff_mark_black: FontResource by 
      lazy { init_ff_mark_black() }

  public val tungsten_bold: FontResource by 
      lazy { init_tungsten_bold() }

  public val valorant: FontResource by 
      lazy { init_valorant() }
}

internal val Res.font.ff_mark_black: FontResource
  get() = WasmJsMainFont0.ff_mark_black

private fun init_ff_mark_black(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ff_mark_black",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/vctvaliancechatbot.composeapp.generated.resources/font/ff_mark_black.otf", -1, -1),
    )
)

internal val Res.font.tungsten_bold: FontResource
  get() = WasmJsMainFont0.tungsten_bold

private fun init_tungsten_bold(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:tungsten_bold",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/vctvaliancechatbot.composeapp.generated.resources/font/tungsten_bold.ttf", -1, -1),
    )
)

internal val Res.font.valorant: FontResource
  get() = WasmJsMainFont0.valorant

private fun init_valorant(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:valorant",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/vctvaliancechatbot.composeapp.generated.resources/font/valorant.ttf", -1, -1),
    )
)
