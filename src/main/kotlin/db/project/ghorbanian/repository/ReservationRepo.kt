package db.project.ghorbanian.repository

import db.project.ghorbanian.models.RegisterDoctor
import db.project.ghorbanian.models.Reservation
import org.springframework.data.jpa.repository.JpaRepository



interface ReservationRepo : JpaRepository<Reservation, Long> {
//    fun findOneByName(name : String): RegisterDoctor?
//    fun findOneByPhone(phone : String):Register?
    fun findByDoctorAndDay(doctor: RegisterDoctor , day :String):List<Reservation>
}