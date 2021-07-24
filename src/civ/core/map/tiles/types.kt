package civ.core.map.tiles

enum class Type {
    HILL {
        override val boseFood = 1
        override val baseMove = 1
        override val baseProduction = 2
        override fun toString(): String  = "Hill"
    },
    PLAIN {
        override val boseFood = 2
        override val baseMove = 2
        override val baseProduction = 1
        override fun toString(): String = "Plain"
    },
    MARSH {
        override val boseFood = 1
        override val baseMove = 1
        override val baseProduction = 1
        override fun toString(): String = "Marsh"
    },
    RIVER {
        override val boseFood = 2
        override val baseMove = 1
        override val baseProduction = 1
        override fun toString(): String = "River"
    },
    FOREST {
        override val boseFood = 2
        override val baseMove = 1
        override val baseProduction = 2
        override fun toString(): String = "Forest"
    };
    abstract val boseFood: Int
    abstract val baseMove: Int
    abstract val baseProduction: Int
}
