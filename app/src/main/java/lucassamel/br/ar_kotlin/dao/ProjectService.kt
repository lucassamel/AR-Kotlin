package lucassamel.br.ar_kotlin.dao

import lucassamel.br.ar_kotlin.model.ProjectMarca
import retrofit2.http.GET

interface ProjectService {

    @GET("fipe/api/v1/carros/marcas")
    fun all(): List<ProjectMarca>
}