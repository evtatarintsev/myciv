
import civ.core.map.generator.SimpleMapGenerator
import civ.core.map.map.*
import civ.ui.html.HtmlMapPresenter
import java.util.*

fun main() {
    val mapPresenter = HtmlMapPresenter()
    val config = Config(
        size = Size(140, 70),
        landMass = LandMass.LARGE,
        temperature = Temperature.TEMPERATE,
        climate = Climate.NORMAL,
        age = Age.AGE_1,
    )
    val map = SimpleMapGenerator(Date().time).generate(config)
    mapPresenter.print(map)
}
