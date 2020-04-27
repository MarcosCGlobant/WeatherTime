package com.example.weathertimeapp.data.mapper

interface BaseMapper<E, D> {

    fun transform(type: E): D

}