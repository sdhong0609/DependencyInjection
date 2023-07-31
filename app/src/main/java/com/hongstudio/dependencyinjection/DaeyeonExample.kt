package com.hongstudio.dependencyinjection

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

data class Car(
    val engine: Engine,
    val tire: Tire
)

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
    fun getCar(): Car
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
class CarModule {
    @Singleton
    @Provides
    fun provideCar(engine: Engine, tire: Tire): Car {
        return Car(engine, tire)
    }
}

fun main() {
    val carComponent = DaggerCarComponent.create()

    (1..10).forEach { _ ->
        val car = carComponent.getCar()
        print(car)
        car.engine.print()
    }
}
