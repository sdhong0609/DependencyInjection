package com.hongstudio.dependencyinjection.example03

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class Student {
    @Inject
    lateinit var name: String
}

class Teacher {
    lateinit var name: String

    @Inject
    fun setTeacherName(name : String){
        this.name = name
    }
}

@Module
class PersonModule {
    @Provides
    fun providesString() = "Example3 Name"
}

@Component(modules = [PersonModule::class])
interface SchoolComponent{
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}

fun main(){
    val personComponent : SchoolComponent = DaggerSchoolComponent.create()

    val student = Student()
    personComponent.injectStudent(student)
    println(student.name) // Example3 name

    val teacher = Teacher()
    personComponent.injectTeacher(teacher)
    println(teacher.name) // Example3 Name
}
