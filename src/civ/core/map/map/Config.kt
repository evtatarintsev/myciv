package civ.core.map.map

enum class LandMass {
    SMALL {
        override fun toString(): String = "Small"
    },
    NORMAL {
        override fun toString(): String = "Normal"
    },
    LARGE {
        override fun toString(): String = "Large"
    }
}

enum class Temperature {
    COOL {
        override fun toString(): String = "Cool"
    },
    TEMPERATE {
        override fun toString(): String = "Temperate"
    },
    WARM {
        override fun toString(): String = "Warm"
    }
}

enum class Climate {
    ARID {
        override fun toString(): String = "Arid"
    },
    NORMAL {
        override fun toString(): String = "Normal"
    },
    WET {
        override fun toString(): String = "Wet"
    }
}

enum class Age {
    AGE_0 {
        override fun toString(): String = "3 billion years"
    },
    AGE_1 {
        override fun toString(): String = "4 billion years"
    },
    AGE_2 {
        override fun toString(): String = "5 billion years"
    },
}

data class Size(val width: Int, val height: Int)

data class Config(
    val size: Size,
    val landMass: LandMass,
    val temperature: Temperature,
    val climate: Climate,
    val age: Age,
)

val DefaultConfig = Config(
    size = Size(600, 400),
    landMass = LandMass.NORMAL,
    temperature = Temperature.TEMPERATE,
    climate = Climate.NORMAL,
    age = Age.AGE_1,
)
