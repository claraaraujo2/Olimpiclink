package devsystem.olimpiclink.model.util

import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointUser {
    @GET("api/users/login/{login_user}&{password_user}")
    fun userLogin(
        @Path("login_user") login_user : String,
        @Path("password_user") password_user : String
    ) : Call<User>

    @GET("api/users/check/{login_user}&{email_user}")
    fun emailUsernameCheck(
        @Path("login_user") login_user: String,
        @Path("email_user") email_user: String,
    ) : Call<RequestMessages>

}