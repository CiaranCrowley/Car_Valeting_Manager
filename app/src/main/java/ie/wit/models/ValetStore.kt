package ie.wit.models

interface ValetStore {
    fun findAll(): List<ValetModel>
    fun findById(id: Long): ValetModel?
    fun create(donation: ValetModel)
    //fun update(donation: ValetModel)
    //fun delete(donation: ValetModel)
}