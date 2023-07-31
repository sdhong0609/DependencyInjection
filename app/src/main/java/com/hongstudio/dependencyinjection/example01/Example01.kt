package com.hongstudio.dependencyinjection.example01

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Component(modules = [AAAModule::class])
interface StringComponent {
    fun injectString(student: Student)
}

@Module
class StringModule {
    @Provides
    fun providesString() = "StringA"
}

@Module
class AAAModule {
    @Provides
    fun aaa() = "aaa"
}

class Student {
    @Inject
    lateinit var name: String
}

fun main(){
    val person = Student()
    DaggerStringComponent.create().injectString(person)
    println(person.name) // aaa
}
