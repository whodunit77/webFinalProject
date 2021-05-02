package db.project.ghorbanian.models

import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "register_user")
class RegisterUser (
        val name :String,
        val password : String,
        val phone : String
): BaseEntity()