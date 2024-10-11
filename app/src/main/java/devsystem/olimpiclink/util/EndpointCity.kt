package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.City
import retrofit2.Call
import retrofit2.http.GET

interface EndpointCity {
    @GET("api/v1/cities")
    fun citiesGet() : Call<List<City>>
}
