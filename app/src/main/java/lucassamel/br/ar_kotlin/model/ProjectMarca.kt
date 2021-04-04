package lucassamel.br.ar_kotlin.model

class ProjectMarca(
    var nome: String? = null,
    var codigo: String? = null,
    var modelo: ProjectModelo? = null
    ){
    override fun toString(): String {
        return "$nome"
    }
}
