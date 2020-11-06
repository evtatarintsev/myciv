package civ.core.models.units


enum class UnitType{
    TANK {
        override fun toString(): String {
            return "Tank"
        }
    },
    PHALANX {
        override fun toString(): String {
            return "Phalanx"
        }
    }
}
