package com.hongstudio.dependencyinjection

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

interface Car

data class SamsungCar @Inject constructor(
    val engine: Engine,
    val tire: Tire
): Car

interface Engine {
    fun print()
}

class GasolineEngine : Engine {
    override fun print() {
        println("GasolineEngine")
    }
}

class ElectricEngine : Engine {
    override fun print() {
        println("ElectricEngine")
    }
}

@Singleton
@Component(modules = [
    EngineModule::class,
    TireModule::class,
    CarModule::class
])
interface CarComponent {
    fun getCar(): SamsungCar
}

interface Tire

class NormalTire : Tire

class SuperTire : Tire

@Module
class EngineModule {
    @Singleton
    @Provides
    fun provideEngine(): Engine {
        return GasolineEngine()
    }
}

@Module
class TireModule {
    @Singleton
    @Provides
    fun provideTire(): Tire {
        return NormalTire()
    }
}

@Module
abstract class CarModule {
    @Binds
    abstract fun bindsCar(car: SamsungCar): Car
}

fun main() {
    val carComponent = DaggerCarComponent.create()

    (1..10).forEach { _ ->
        val car = carComponent.getCar()
        print(car)
        car.engine.print()
    }
}
