package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.PublicationModelPost
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EndpointPublication {
    @GET("/api/publication")
    suspend fun publicationsGet() : List<PublicationModelGet>

    @POST("api/publication/pointo")
    suspend fun publicationsPost(
        @Body new_publication : PublicationModelPost
    ) : Response<Void>
}