package br.org.acal.resources.model

import br.org.acal.domain.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_model")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private var id: String,

    @Column(name = "username")
    private var username: String,

    @Column(name = "password")
    private var password: String,
) {

    fun toEntity(): User =
        User(id = id, username = username, password = password)
}

fun User.toModel() : UserModel = UserModel(
    id = id,
    username = username,
    password = password,
)