package db.project.ghorbanian.repository

import db.project.ghorbanian.models.Comments
import db.project.ghorbanian.models.RegisterDoctor
import org.springframework.data.jpa.repository.JpaRepository


interface CommentRepo : JpaRepository<Comments, Long> {
//    fun findOneByName(name : String): RegisterDoctor?
//    fun findOneByPhone(phone : String):Register?
}