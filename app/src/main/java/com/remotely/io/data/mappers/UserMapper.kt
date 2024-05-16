package com.remotely.io.data.mappers

import com.remotely.io.data.models.db.User
import com.remotely.io.domain.models.DomainUser
import com.remotely.io.utils.getRandomUUID

class UserMapper {
    fun mapToDomain(user: User): DomainUser {
        return DomainUser(
            name = user.name,
            profilePicture = user.profilePicture,
            location = user.location
        )
    }

    fun mapToDataModel(user: DomainUser): User {
        return User(
            userId = getRandomUUID(),
            name = user.name,
            profilePicture = user.profilePicture,
            location = user.location
        )
    }
}