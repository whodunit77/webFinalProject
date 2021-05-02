package db.project.ghorbanian.repository

import db.project.ghorbanian.models.Login
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepo : JpaRepository<Login, Long>{
//    fun findOneByName(name : String):Login?
}