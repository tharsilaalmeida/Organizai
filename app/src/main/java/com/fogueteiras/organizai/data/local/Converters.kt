package com.fogueteiras.organizai.data.local

import androidx.room.TypeConverter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class Converters {
    @TypeConverter
    fun fromColor(color: Color): Int = color.toArgb()

    @TypeConverter
    fun toColor(colorArgb: Int): Color = Color(colorArgb)
}
