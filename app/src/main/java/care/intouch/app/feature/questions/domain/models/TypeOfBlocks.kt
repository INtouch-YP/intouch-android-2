package care.intouch.app.feature.questions.domain.models

enum class TypeOfBlocks {
    OPEN,
    SINGLE,
    MULTIPLE,
    RANGE,
    TEXT,
    IMAGE,
    UNDEFINED  // сделал на случай если новый тип добавят
}