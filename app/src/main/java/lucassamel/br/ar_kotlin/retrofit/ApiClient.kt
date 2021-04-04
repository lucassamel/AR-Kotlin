package lucassamel.br.ar_kotlin.retrofit

import lucassamel.br.ar_kotlin.dao.ProjectService
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

        private var instance: Retrofit? = null
        private fun getRetrofit(): Retrofit{
            if (instance == null)
                instance = Builder()
                    .baseUrl("https://parallelum.com.br/fipe/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return instance as Retrofit

        }

        fun getProjectService() : ProjectService {
            return getRetrofit().create(ProjectService::class.java)
        }

    }
