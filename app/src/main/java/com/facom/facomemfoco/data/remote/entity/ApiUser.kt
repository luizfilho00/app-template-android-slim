package com.facom.facomemfoco.data.remote.entity

import com.facom.facomemfoco.data.remote.mapper.Mapper
import com.facom.facomemfoco.domain.entity.User
import com.google.gson.annotations.SerializedName

data class ApiUser(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("username") val username: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("avatar") val avatar: ApiImage?,
        @SerializedName("phone") val phone: String?,
        @SerializedName("token") val token: String?
) {
    object ApiUserToUserMapper : Mapper<ApiUser, User>() {
        override fun transform(t: ApiUser) = User(
                id = t.id,
                name = t.name,
                username = t.username,
                phone = t.phone,
                email = t.email,
                token = t.token,
                avatarUrl = t.avatar?.medium,
                avatarThumbUrl = t.avatar?.thumb
        )
    }
}
