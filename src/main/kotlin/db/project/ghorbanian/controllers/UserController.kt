package db.project.ghorbanian.controllers

import db.project.ghorbanian.models.Login
import db.project.ghorbanian.models.RegisterUser
import db.project.ghorbanian.repository.RegisterUserRepo
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/rest/")
class UserController(
        val registerUserRepo: RegisterUserRepo
) {



    @PostMapping("get-info/{name}")// , produces = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    fun getInfo(@PathVariable name : String): RegisterUser {
        val reg = registerUserRepo.findOneByName(name)
        return reg!!
    }


    @PostMapping("register")//, consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    fun register(aRegisterUser: RegisterUser):String{
        val reg = registerUserRepo.findOneByName(aRegisterUser.name)
        println(reg)
        return if (reg == null){
            registerUserRepo.save(aRegisterUser)
            "you are registered successfully!"
        }
        else {
            "you have been registered before!"
        }
    }
    @PostMapping("login")//, consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    fun login(login: Login):String{
        println("**********")
        println(login)
        val l = registerUserRepo.findOneByName(login.name)
        return when {
            l == null -> {
                "you are not registered!"
            }
            l.password != login.password -> {
                println(l)
                "password is wrong!"
            }
            else -> {
                println(l)
                "true"
            }
        }
    }
    @PostMapping("edit-name/{name}/{newName}")
    fun editName(@PathVariable name : String , @PathVariable newName : String){
        println("#######")
        println(name)
        println(newName)
        var user = registerUserRepo.findOneByName(name)
        registerUserRepo.save(RegisterUser(newName , user!!.password , user.phone))
        registerUserRepo.delete(user!!)
        println("Done")
    }
    @PostMapping("edit-pass/{name}/{pass}")
    fun editPass(@PathVariable name : String , @PathVariable pass : String){
        println("#######")
        println(name)
        println(pass)
        var user = registerUserRepo.findOneByName(name)
        registerUserRepo.save(RegisterUser(name , pass , user!!.phone))
        registerUserRepo.delete(user!!)

    }
    @PostMapping("edit-phone/{name}/{phone}")
    fun editPhone(@PathVariable name : String , @PathVariable phone : String){
        println("#######")
        println(name)
        println(phone)
        var user = registerUserRepo.findOneByName(name)
        registerUserRepo.save(RegisterUser(name , user!!.password , phone))
        registerUserRepo.delete(user!!)
    }
}
