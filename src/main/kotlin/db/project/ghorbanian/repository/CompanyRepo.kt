package db.project.ghorbanian.repository

import db.project.ghorbanian.models.Company
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by mgh on Jan, 2020
 **/
interface CompanyRepo : JpaRepository<Company, Long> {
    fun findAllByParentId(parentId: Long?): List<Company>
}
