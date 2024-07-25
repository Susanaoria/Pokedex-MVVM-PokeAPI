package com.example.pokedex.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Clase de datos que representa un Pokémon
data class Pokemon(
    @Expose @SerializedName("id") val id: Int, // ID del Pokémon
    @Expose @SerializedName("name") val name: String, // Nombre del Pokémon
    @Expose @SerializedName("base_experience") val baseExperience: Int, // Experiencia base del Pokémon
    @Expose @SerializedName("height") val height: Int, // Altura del Pokémon
    @Expose @SerializedName("is_default") val isDefault: Boolean, // Indica si es el Pokémon predeterminado
    @Expose @SerializedName("order") val order: Int, // Orden del Pokémon
    @Expose @SerializedName("weight") val weight: Int, // Peso del Pokémon
    @Expose @SerializedName("sprites") val sprites: Sprites, // Sprites del Pokémon
    @Expose @SerializedName("abilities") val abilities: List<Ability>, // Habilidades del Pokémon
    @Expose @SerializedName("forms") val forms: List<Form>, // Formas del Pokémon
    @Expose @SerializedName("game_indices") val gameIndices: List<GameIndex>, // Índices de juego del Pokémon
    @Expose @SerializedName("held_items") val heldItems: List<HeldItem>, // Objetos sostenidos por el Pokémon
    @Expose @SerializedName("location_area_encounters") val locationAreaEncounters: String, // Encuentros en áreas del Pokémon
    @Expose @SerializedName("moves") val moves: List<Move>, // Movimientos del Pokémon
    @Expose @SerializedName("species") val species: Species, // Especie del Pokémon
    @Expose @SerializedName("stats") val stats: List<Stat>, // Estadísticas del Pokémon
    @Expose @SerializedName("types") val types: List<Type>, // Tipos del Pokémon
    @Expose @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>, // Entradas de texto de sabor del Pokémon
    // Propiedad añadida para las entradas de texto de sabor en español
    var spanishFlavorTextEntries: List<String> = emptyList()
)

// Clase de datos que representa una entrada de texto de sabor
data class FlavorTextEntry(
    @Expose @SerializedName("flavor_text") val flavorText: String, // Texto de sabor
    @Expose @SerializedName("language") val language: Language // Idioma del texto de sabor
)

// Clase de datos que representa un idioma
data class Language(
    @Expose @SerializedName("name") val name: String, // Nombre del idioma
    @Expose @SerializedName("url") val url: String // URL del idioma
)

// Clase de datos que representa los sprites de un Pokémon
data class Sprites(
    @Expose @SerializedName("back_default") val backDefault: String?, // Sprite trasero por defecto
    @Expose @SerializedName("back_female") val backFemale: String?, // Sprite trasero femenino
    @Expose @SerializedName("back_shiny") val backShiny: String?, // Sprite trasero brillante
    @Expose @SerializedName("back_shiny_female") val backShinyFemale: String?, // Sprite trasero femenino brillante
    @Expose @SerializedName("front_default") val frontDefault: String?, // Sprite delantero por defecto
    @Expose @SerializedName("front_female") val frontFemale: String?, // Sprite delantero femenino
    @Expose @SerializedName("front_shiny") val frontShiny: String?, // Sprite delantero brillante
    @Expose @SerializedName("front_shiny_female") val frontShinyFemale: String? // Sprite delantero femenino brillante
)

// Clase de datos que representa una habilidad de un Pokémon
data class Ability(
    @Expose @SerializedName("ability") val ability: AbilityInfo, // Información de la habilidad
    @Expose @SerializedName("is_hidden") val isHidden: Boolean, // Indica si la habilidad está oculta
    @Expose @SerializedName("slot") val slot: Int // Ranura de la habilidad
)

// Clase de datos que representa un resultado de Pokémon
data class PokeResult(
    @Expose @SerializedName("name") val name: String, // Nombre del Pokémon
    @Expose @SerializedName("url") val url: String // URL del Pokémon
)

// Clase de datos que representa la información de una habilidad
data class AbilityInfo(
    @Expose @SerializedName("name") val name: String, // Nombre de la habilidad
    @Expose @SerializedName("url") val url: String // URL de la habilidad
)

// Clase de datos que representa una forma de un Pokémon
data class Form(
    @Expose @SerializedName("name") val name: String, // Nombre de la forma
    @Expose @SerializedName("url") val url: String // URL de la forma
)

// Clase de datos que representa un índice de juego de un Pokémon
data class GameIndex(
    @Expose @SerializedName("game_index") val gameIndex: Int, // Índice de juego
    @Expose @SerializedName("version") val version: Version // Versión del juego
)

// Clase de datos que representa una versión de un juego
data class Version(
    @Expose @SerializedName("name") val name: String, // Nombre de la versión
    @Expose @SerializedName("url") val url: String // URL de la versión
)

// Clase de datos que representa un objeto sostenido por un Pokémon
data class HeldItem(
    @Expose @SerializedName("item") val item: Item, // Objeto
    @Expose @SerializedName("version_details") val versionDetails: List<VersionDetail> // Detalles de la versión
)

// Clase de datos que representa un objeto
data class Item(
    @Expose @SerializedName("name") val name: String, // Nombre del objeto
    @Expose @SerializedName("url") val url: String // URL del objeto
)

// Clase de datos que representa los detalles de una versión
data class VersionDetail(
    @Expose @SerializedName("rarity") val rarity: Int, // Rareza del objeto en la versión
    @Expose @SerializedName("version") val version: Version // Versión del juego
)

// Clase de datos que representa un movimiento de un Pokémon
data class Move(
    @Expose @SerializedName("move") val move: MoveInfo, // Información del movimiento
    @Expose @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail> // Detalles del grupo de la versión
)

// Clase de datos que representa la información de un movimiento
data class MoveInfo(
    @Expose @SerializedName("name") val name: String, // Nombre del movimiento
    @Expose @SerializedName("url") val url: String // URL del movimiento
)

// Clase de datos que representa los detalles del grupo de una versión
data class VersionGroupDetail(
    @Expose @SerializedName("level_learned_at") val levelLearnedAt: Int, // Nivel al que se aprende el movimiento
    @Expose @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod, // Método de aprendizaje del movimiento
    @Expose @SerializedName("version_group") val versionGroup: VersionGroup // Grupo de la versión
)

// Clase de datos que representa un método de aprendizaje de un movimiento
data class MoveLearnMethod(
    @Expose @SerializedName("name") val name: String, // Nombre del método de aprendizaje
    @Expose @SerializedName("url") val url: String // URL del método de aprendizaje
)

// Clase de datos que representa un grupo de versiones
data class VersionGroup(
    @Expose @SerializedName("name") val name: String, // Nombre del grupo de versiones
    @Expose @SerializedName("url") val url: String // URL del grupo de versiones
)

// Clase de datos que representa una especie de Pokémon
data class Species(
    @Expose @SerializedName("name") val name: String, // Nombre de la especie
    @Expose @SerializedName("url") val url: String // URL de la especie
)

// Clase de datos que representa una estadística de un Pokémon
data class Stat(
    @Expose @SerializedName("base_stat") val baseStat: Int, // Valor base de la estadística
    @Expose @SerializedName("effort") val effort: Int, // Esfuerzo requerido para la estadística
    @Expose @SerializedName("stat") val stat: StatInfo // Información de la estadística
)

// Clase de datos que representa la información de una estadística
data class StatInfo(
    @Expose @SerializedName("name") val name: String, // Nombre de la estadística
    @Expose @SerializedName("url") val url: String // URL de la estadística
)

// Clase de datos que representa un tipo de Pokémon
data class Type(
    @Expose @SerializedName("slot") val slot: Int, // Ranura del tipo
    @Expose @SerializedName("type") val type: TypeInfo // Información del tipo
)

// Clase de datos que representa la información de un tipo de Pokémon
data class TypeInfo(
    @Expose @SerializedName("name") val name: String, // Nombre del tipo
    @Expose @SerializedName("url") val url: String // URL del tipo
)
