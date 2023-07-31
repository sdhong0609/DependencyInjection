package com.hongstudio.dependencyinjection.example04

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

@Component(modules = [PersonModule::class])
interface SchoolComponent {
    fun injectStudent(student: Student)
    fun injectTeacher(teacher: Teacher)
}

@Module
class PersonModule {
    @Provides
    @Named("Student")
    fun providesStudentName() = "StudentA"

    @Provides
    @Named("Teacher")
    fun providesTeacherName() = "TeacherB"
}

class Student {
    @Inject
    @Named("Student")
    lateinit var name: String
}

class Teacher {
    @Inject
    @Named("Teacher")
    lateinit var name: String
}
