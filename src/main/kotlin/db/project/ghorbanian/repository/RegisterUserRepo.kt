package db.project.ghorbanian.repository

import db.project.ghorbanian.models.RegisterUser
import org.springframework.data.jpa.repository.JpaRepository

interface RegisterUserRepo : JpaRepository<RegisterUser, Long>{
    fun findOneByName(name : String):RegisterUser?
//    fun findOneByPhone(phone : String):Register?
}