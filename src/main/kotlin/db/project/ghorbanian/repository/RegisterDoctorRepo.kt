package db.project.ghorbanian.repository

import db.project.ghorbanian.models.RegisterDoctor
import db.project.ghorbanian.models.RegisterUser
import org.springframework.data.jpa.repository.JpaRepository




interface RegisterDoctorRepo : JpaRepository<RegisterDoctor, Long> {
    fun findOneByName(name : String): RegisterDoctor?
    fun findBySpec(specId : String) : List<RegisterDoctor>

    fun findByNameStartsWith(namePart : String) :List<RegisterDoctor>
//    fun findOneByPhone(phone : String):Register?
}