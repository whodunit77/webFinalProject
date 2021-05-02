package db.project.ghorbanian.models

import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "login")
class Login (
        val name :String,
        val password : String
): BaseEntity()