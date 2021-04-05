package lucassamel.br.ar_kotlin.model

open class ProjectMarca(
    var nome: String? = null,
    var codigo: Long? = null,
    ){
    override fun toString(): String {
        return "$nome"
    }
}
