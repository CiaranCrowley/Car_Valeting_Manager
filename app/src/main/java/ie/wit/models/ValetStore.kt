package ie.wit.models

interface ValetStore {
    fun findAll(): List<ValetModel>
    fun create(valet: ValetModel)
    fun update(valet: ValetModel)
    fun delete(valet: ValetModel)
}