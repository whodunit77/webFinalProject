package db.project.ghorbanian.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.FetchMode
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*


@Entity
@Table(name = "register_doctor")
class RegisterDoctor (
        val name :String,
//        @JsonIgnore
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "doctor", orphanRemoval = true, fetch = FetchType.EAGER)//fetch = FetchType.EAGER
//        @LazyCollection(LazyCollectionOption.FALSE)
        var comments : MutableSet<Comments> = mutableSetOf(),
//        @JsonIgnore
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "doctor", orphanRemoval = true, fetch = FetchType.EAGER)// fetch = FetchType.EAGER
//        @LazyCollection(LazyCollectionOption.FALSE)
        var reservations : MutableSet<Reservation> = mutableSetOf(),
        val password : String,
        val spec : String,
        @JsonIgnore
        val average : Int = 0,
        val number : String,
        val onlinePay : String,
        val experience : String ,
        val address : String,
        val phone : String,
//        @ElementCollection
        var days :String //: MutableList<String> = mutableListOf()

): BaseEntity()