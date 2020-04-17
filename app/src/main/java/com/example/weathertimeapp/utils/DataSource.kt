package com.example.weathertimeapp.utils

import com.example.weathertimeapp.mvp.entities.Weather

class DataSource {

    companion object {

        fun createDataSet(): ArrayList<Weather> {
            val list = ArrayList<Weather>()
            list.add(
                Weather(
                    "16/04/2020",
                    "17°C",
                    "11°C",
                    "https://raw.githubusercontent.com/MarcosCGlobant/CalculatorOnboarding/master/app" +
                            "/app/src/main/java/com/globant/calculator/android/utils/images" +
                            "/PinClipart.com_grey-cloud-clip-art_1881847.webp",
                    "overcast clouds"
                )
            )
            list.add(
                Weather(
                    "17/04/2020",
                    "20°C",
                    "5°C",
                    "https://raw.githubusercontent.com/MarcosCGlobant/CalculatorOnboarding/master/app" +
                            "/app/src/main/java/com/globant/calculator/android/utils/images" +
                            "/PinClipart.com_mary-poppins-clipart_42574.webp",
                    "sky is clear"
                )
            )

            list.add(
                Weather(
                    "18/04/2020",
                    "18°C",
                    "7°C",
                    "https://raw.githubusercontent.com/MarcosCGlobant/CalculatorOnboarding/master/app" +
                            "/app/src/main/java/com/globant/calculator/android/utils/images" +
                            "/PinClipart.com_sunny-weather-clipart_249560.webp",
                    "few clouds"
                )
            )
            list.add(
                Weather(
                    "19/04/2020",
                    "17°C",
                    "3°C",
                    "https://raw.githubusercontent.com/MarcosCGlobant/CalculatorOnboarding/master/app" +
                            "/app/src/main/java/com/globant/calculator/android/utils/images" +
                            "/PinClipart.com_sun-and-clouds-clipart_52787.webp",
                    "broken clouds"
                )
            )
            list.add(
                Weather(
                    "20/04/2020",
                    "15°C",
                    "10°C",
                    "https://raw.githubusercontent.com/MarcosCGlobant/CalculatorOnboarding/master/app/" +
                            "app/src/main/java/com/globant/calculator/android/utils/images" +
                            "/PinClipart.com_clipart-sonne_304053.webp",
                    "heavy intensity rain"
                )
            )
            return list
        }
    }
}