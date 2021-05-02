package db.project.ghorbanian.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table



@Entity
@Table(name = "Reservation")
class Reservation (
        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "doctor_db_id")
        var doctor: RegisterDoctor,
        var day : String,

        var reserver :String
): BaseEntity()