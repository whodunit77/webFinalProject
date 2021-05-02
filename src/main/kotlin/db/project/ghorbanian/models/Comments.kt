package db.project.ghorbanian.models

import org.codehaus.jackson.annotate.JsonIgnore;
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table



@Entity
@Table(name = "comments")
class Comments (
     @ManyToOne
     @JoinColumn(name = "doctor_db_id")
     @JsonIgnore
     var doctor: RegisterDoctor,
     var comment :String,
     var commenter : String,
     var score : String
): BaseEntity()