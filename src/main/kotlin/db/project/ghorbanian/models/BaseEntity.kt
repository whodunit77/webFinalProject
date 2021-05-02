package db.project.ghorbanian.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import java.io.Serializable

/**
 * Base super class for other entities. It provides a [id] for entities.
 */
//@MappedSuperclass
//open class BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long =-1L
//}
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
//@Immutable
abstract class BaseEntity : Serializable{

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    var dbId = -1L



    val isPersisted: Boolean
        @JsonIgnore
        get() = dbId > 0L

//    abstract fun generateOldKey(): String

    companion object {

        private const val serialVersionUID = 1L
    }
}
