package db.project.ghorbanian.models

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Base super class for other entities. It provides a [id] for entities.
 */
@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val isPersisted: Boolean
        get() = id != null
}
