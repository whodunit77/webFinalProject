package db.project.ghorbanian.models

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "companies")
class Company(

        /**
         * Company's name.
         */
        val name: String,

        /**
         * Company's parent. A root company doesn't have any parent.
         */
        @ManyToOne
        @JoinColumn(name = "parent_id")
        val parent: Company? = null,

        /**
         * Represents the path of a company from root to current company.
         * For example 00120401 means a path from root which is 0012 -> 04 -> 01.
         */
        val zone: String,

        /**
         * When does the company created?
         */
        val createdAt: Instant = Instant.now(),

        /**
         * When does the company modified?
         */
        var modifiedAt: Instant = Instant.now()
) : BaseEntity() {

    override fun toString(): String {
        return "Company(name='$name', parent=$parent, zone='$zone', createdAt=$createdAt, modifiedAt=$modifiedAt)"
    }
}
