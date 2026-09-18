package com.example.listycity3
import androidx.compose.runtime.mutableStateListOf

class CityRepository {
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    fun addCity(city: City){
        _cities.add(city)
    }
    fun editCities(oldCity: City, newCity: City){
        println(oldCity.name)
        println(oldCity.province)


        val removed: Boolean =_cities.removeIf{ city ->
            city.name == oldCity.name && city.province == oldCity.province}

        if (removed) {
            _cities.add(newCity)
        }
    }
}