package ie.wit.models

interface ValetStore {
    fun findAll(): List<ValetModel>
    fun findById(id: Long): ValetModel?
    //fun findDate(date: String): ValetModel?    ///////   Causes error in ValetFragment:  Lines 59 & 103; 'parameter_name' is not used and can be renamed to "_"  Error is somehow related to this function
    fun create(valet: ValetModel)
    //fun update(valet: ValetModel)
    //fun delete(valet: ValetModel)
}