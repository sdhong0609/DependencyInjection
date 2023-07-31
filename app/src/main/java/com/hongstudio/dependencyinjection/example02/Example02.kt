package com.hongstudio.dependencyinjection.example02

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

data class Student @Inject constructor(
    val name: String
)

@Component(modules = [PersonModule::class])
interface PersonComponent{
    fun getString() : String
    fun getStudent() : Student
}

@Module
class PersonModule {
    @Provides
    fun providesString() = "Example2 Student"
}

fun main(){
    val personComponent : PersonComponent = DaggerPersonComponent.create()

    val string = personComponent.getString() // Example2 Student
    println(string)

    val student = personComponent.getStudent()
    println(student.name) // Example2 Student
}
