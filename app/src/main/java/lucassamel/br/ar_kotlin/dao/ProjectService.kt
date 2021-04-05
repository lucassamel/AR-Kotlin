package lucassamel.br.ar_kotlin.dao

import lucassamel.br.ar_kotlin.model.ProjectMarca
import lucassamel.br.ar_kotlin.model.ProjectModelo
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectService {

    @GET("carros/marcas")
    suspend fun all(): List<ProjectMarca>

    @GET("carros/marcas/{codigo}/modelos")
    suspend fun modelos(
        @Path("codigo") codigo: Long
    ):ProjectModelo


}