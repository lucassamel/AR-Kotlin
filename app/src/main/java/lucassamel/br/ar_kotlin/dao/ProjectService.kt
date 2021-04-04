package lucassamel.br.ar_kotlin.dao

import lucassamel.br.ar_kotlin.model.ProjectMarca
import retrofit2.http.GET

interface ProjectService {

    @GET("carros/marcas")
    suspend fun all(): List<ProjectMarca>


}