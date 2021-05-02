package db.project.ghorbanian.config



import org.springframework.data.annotation.CreatedDate

import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType.TIMESTAMP


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable<U> : Serializable {

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected var createdDate: LocalDateTime? = null

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected var lastModifiedDate: LocalDateTime? = null


    companion object {

        private const val serialVersionUID = 1L
    }
}
