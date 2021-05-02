package db.project.ghorbanian.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import db.project.ghorbanian.models.Comments
import db.project.ghorbanian.models.Login
import db.project.ghorbanian.models.RegisterDoctor
import db.project.ghorbanian.models.Reservation
import db.project.ghorbanian.repository.CommentRepo
import db.project.ghorbanian.repository.RegisterDoctorRepo
import db.project.ghorbanian.repository.ReservationRepo
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.math.roundToInt


@RestController
@RequestMapping("/rest/")
class DoctorController(
        var registerDoctorRepo: RegisterDoctorRepo,
        var commentRepo: CommentRepo,
        var reservationRepo: ReservationRepo
) {


    @PostMapping("register-doctor")//, consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    fun register( registerDoctor: RegisterDoctor):String{
        val reg = registerDoctorRepo.findOneByName(registerDoctor.name)
        return if (reg == null){
            registerDoctorRepo.save(registerDoctor)
            "you are registered successfully!"
        }
        else {
            "you have been registered before!"
        }
    }
    @PostMapping("login-doctor")//, consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    fun login(login: Login):String{
        val l = registerDoctorRepo.findOneByName(login.name)
        return when {
            l == null -> {
                "you are not registered!"
            }
            l.password != login.password -> {
                "password is wrong!"
            }
            else -> {
                "true"
            }
        }
    }

    @PostMapping("get-doctor-info/{name}")
    fun getInfo(@PathVariable name : String):String{

        println(name)
        val doctor = registerDoctorRepo.findOneByName(name)
        var data = makeDoctorPersonalInfo(doctor!!)
        return data
    }

    @PostMapping("send-comment/{comment}/{doctorName}/{commenter}/{score}")
    fun sendComment(@PathVariable comment: String  , @PathVariable doctorName : String ,@PathVariable commenter : String , @PathVariable score : String){
        var doctor = registerDoctorRepo.findOneByName(doctorName)!!
        commentRepo.save(Comments(doctor , comment , commenter ,score ))
    }

    @PostMapping("reserve/{doctorName}/{day}/{reserver}")
    fun reserve( @PathVariable doctorName: String , @PathVariable day: String , @PathVariable reserver :String):String{//@PathVariable time : String
        var doctor = registerDoctorRepo.findOneByName(doctorName)!!
        return checkDate(doctor , day , reserver)
    }


    fun checkDate(doctor : RegisterDoctor , day:String , reserver: String):String{
        var days = doctor.days
        var substr = days.substring(1, days.length-1);
        var result: List<String> = substr.split(",").map { it.trim() }
        var list = reservationRepo.findByDoctorAndDay(doctor , day)
        return when {
            result[day.toInt()] == "false" -> {
                "rooze kari nist:)"
            }
            list.size > 2 -> {
                "zarfiat por ast"
            }
            else -> {
                reservationRepo.save(Reservation(doctor , day ,reserver))
                "reservation is ok!"
            }
        }

    }

    @GetMapping("get-all-doctors/{specId}")
    fun getDoctorList(@PathVariable specId : String):String{
        return makeOutput(registerDoctorRepo.findBySpec(specId))
    }
    @GetMapping("get-searched-doctors/{name}")
    fun getSearchedDoctorList(@PathVariable name : String):String{
        return makeOutput(registerDoctorRepo.findByNameStartsWith(name))
    }
    fun makeOutput(doctors : List<RegisterDoctor>):String{
        var totalObj : MutableList<Doctors> = mutableListOf()
         for (doctor in doctors) {
             var comment = ""
             var userPercent = 0.0;
             var stars = 0
             if (doctor.comments.size > 0){
                 comment =doctor.comments.toMutableList()[0].comment
                 userPercent = calculateUserPercent(doctor.comments.toMutableList())
             }
             else
                 comment = "no comments"
             var fistDate = getFirstDate(doctor.days)
             var obj = Doctors(id = doctor.dbId.toInt(), name = doctor.name, spec =specMap[doctor.spec]!!, avatar = "", stars = calculateStars(userPercent), comments = doctor.comments.size, comment_text = comment,
                     location = doctor.address, experience_years = doctor.experience.toInt(), user_percent = userPercent, first_empty_date = fistDate)
             totalObj.add(obj)
         }
      var gson = Gson()
     return gson.toJson(totalObj)
    }

    fun calculateUserPercent(comments : MutableList<Comments>):Double{
        var total = 0
        for (com in comments){
            total += com.score.toInt()
        }
        return total.toDouble()/comments.size.toDouble()
    }

    fun calculateStars(user_percent: Double):Int{
        return user_percent.roundToInt()
    }

    fun getFirstDate(days : String):String{

        var substr = days.substring(1, days.length-1);
        var result: List<String> = substr.split(",").map { it.trim() }
        for ((index, day) in result.withIndex()){
                if (day=="true") {
                    return dayMap[index.toString()]!!
                }
        }
        return ""
    }
    @GetMapping("get-one-doctor/{id}")
    fun getOneDoctor(@PathVariable id : String):String{
        var doctor = registerDoctorRepo.findById(id.toLong()).unwrap()
        return if (doctor != null)
            makeSingleOutput(doctor)
        else ""


    }

    fun <T> Optional<T>.unwrap(): T? = orElse(null)


    fun makeSingleOutput(doctor: RegisterDoctor):String{
        var substr = doctor.days.substring(1, doctor.days.length-1);
        var result: List<String> = substr.split(",").map { it.trim() }
        var comment = ""
        var commenter = ""
        var userPercent = 0.0;
        var stars = 0
        if (doctor.comments.size > 0){
            comment =doctor.comments.toMutableList()[0].comment
            userPercent = calculateUserPercent(doctor.comments.toMutableList())
            commenter = doctor.comments.toMutableList()[0].commenter
        }
        else
            comment = "no comments"
        var fistDate = getFirstDate(doctor.days)
        var obj = Doctors(id = doctor.dbId.toInt(), name = doctor.name, spec = specMap[doctor.spec]!!, avatar = "", stars = calculateStars(userPercent), comments = doctor.comments.size, comment_text = comment,
                location = doctor.address, experience_years = doctor.experience.toInt(), user_percent = userPercent, first_empty_date = fistDate , phone = doctor.phone , online_pay = doctor.onlinePay , commenter = commenter , week_days = result.toMutableList())
        var gson = Gson()
        return gson.toJson(obj)
    }
    @GetMapping("get-spec-id")
    fun getSpecId():MutableList<String>{
        var list : MutableList<String> = mutableListOf()
        for (i in specMap.keys){
            list.add(i)
        }
        return list
    }


    fun makeDoctorPersonalInfo(doctor: RegisterDoctor):String{
        var substr = doctor.days.substring(1, doctor.days.length-1);
        var result: List<String> = substr.split(",").map { it.trim() }
        var userPercent = 0.0;
        var stars = 0
        if (doctor.comments.size > 0){
            userPercent = calculateUserPercent(doctor.comments.toMutableList())
        }
        var info = DoctorInfo(name = doctor.name , id = doctor.dbId.toInt() , spec = doctor.spec ,
        avatar = "" , stars =  calculateStars(userPercent) , commentSize = doctor.comments.size ,   location = doctor.address, experience_years = doctor.experience.toInt(), user_percent = userPercent, first_empty_date = getFirstDate(doctor.days)
           ,address = doctor.address     , phone = doctor.phone , online_pay = doctor.onlinePay,number = doctor.number , week_days = result.toMutableList() ,comments = getComments(doctor.comments.toMutableList()) , reservations =getReservations(doctor.reservations.toMutableList()) )


        var gson = Gson()
        return gson.toJson(info)
    }

    fun getComments(list : MutableList<Comments>):MutableList<Comment>{
        var newList : MutableList<Comment> = mutableListOf()
        for (i in list){
            newList.add(Comment(i.commenter , i.commenter))
        }
        return newList
    }

    fun getReservations(list : MutableList<db.project.ghorbanian.models.Reservation>):MutableList<Reservation>{
        var newList : MutableList<Reservation> = mutableListOf()
        for (i in list){
            newList.add(Reservation(i.reserver ,dayMap[ i.day]!!))
        }
        return newList
    }

    data class Doctors(
             val id:Int,
             val name:String,
             val spec: String,
             val avatar :String,
             val stars : Int,
             val comments: Int,
             val commenter :String= "",
             val comment_text: String,
             val location : String,
             val experience_years: Int,
             val user_percent : Double,
             val phone :String="",
             val online_pay :String="",
             val first_empty_date:String,
             val week_days: MutableList<String> = mutableListOf("")

    )
    data class DoctorInfo(
            val id:Int,
            val name:String,
            val spec: String,
            val avatar :String,
            val stars : Int,
            val commentSize: Int,
            val comments :MutableList<Comment>,
            val reservations :MutableList<Reservation>,
            val number : String,
            val location : String,
            val experience_years: Int,
            val user_percent : Double,
            val phone :String="",
            val address : String,
            val online_pay :String="",
            val first_empty_date:String,
            val week_days: MutableList<String> = mutableListOf("")

    )
    data class Comment(

            var commenter : String,
            var comment : String
    )

    data class Reservation(
            var reserver :String ,
            var day :String
    )
    companion object{
        var specMap : MutableMap<String , String> = mutableMapOf("11" to "ارتوپدی" ,"12" to "ارولوژی" ,"13" to "پوست و مو" ,
                "14" to "زنان و زایمان" ,"15" to "داخلی" ,"16" to "گوش حلق بینی" ,"17" to "مغزو اعصاب")


        var dayMap = mapOf("0" to "شنبه" ,"1" to "یکشنبه" ,"2" to "دوشنبه" ,"3" to "سه شنبه" ,"4" to "چهار شنبه" ,"5" to "پنج شنبه" ,"6" to "جمعه" )

    }

}