package db.project.ghorbanian.models

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType.TIMESTAMP

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable<U> {
    @CreatedBy
    protected var createdBy: U? = null
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected var createdDate: Date? = null
    @LastModifiedBy
    protected var lastModifiedBy: U? = null
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected var lastModifiedDate: Date? = null // getters and setter here
}