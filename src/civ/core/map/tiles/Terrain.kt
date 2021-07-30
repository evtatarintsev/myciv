package civ.core.map.tiles

enum class Terrain {
    HILL {
        override val landForm = LandForm.LAND
        override val food = 1
        override val gold = 1
        override val movementCost = 1
        override val baseProduction = 2
        override fun toString(): String  = "Hill"
    },
    PLAIN {
        override val landForm = LandForm.LAND
        override val food = 2
        override val gold = 2
        override val movementCost = 2
        override val baseProduction = 1
        override fun toString(): String = "Plain"
    },
    MARSH {
        override val landForm = LandForm.LAND
        override val food = 1
        override val gold = 0
        override val movementCost = 1
        override val baseProduction = 1
        override fun toString(): String = "Marsh"
    },
    FOREST {
        override val landForm = LandForm.LAND
        override val food = 2
        override val gold = 1
        override val movementCost = 1
        override val baseProduction = 2
        override fun toString(): String = "Forest"
    },
    DESERT {
        override val landForm = LandForm.LAND
        override val food = 1
        override val gold = 0
        override val movementCost = 2
        override val baseProduction = 1
        override fun toString(): String = "Desert"
    },
    ICE {
        override val landForm = LandForm.WATER
        override val food = 0
        override val gold = 0
        override val movementCost = 2
        override val baseProduction = 0
        override fun toString(): String = "Water"
    },
    OCEAN {
        override val landForm = LandForm.WATER
        override val food = 0
        override val gold = 0
        override val movementCost = 2
        override val baseProduction = 0
        override fun toString(): String = "Water"
    };
    abstract val landForm: LandForm
    abstract val food: Int
    abstract val gold: Int
    abstract val movementCost: Int
    abstract val baseProduction: Int

    companion object {
        val water = values().filter { it.landForm == LandForm.WATER }
        val lands = values().filter { it.landForm == LandForm.LAND }
    }
}
