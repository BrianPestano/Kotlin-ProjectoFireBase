package com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.proyectofinalfirebasebrianylauren.R
import com.example.proyectofinalfirebasebrianylauren.Videojuego.infoArray

var lista = mutableStateListOf(
            infoArray("Final Fantasy XII", R.drawable.finalfantasyxii, "16/03/2006", "PS2", 92,"RPG"),
            infoArray("Grand Thef Auto San Andreas", R.drawable.gtasanandreas, "26/10/2004", "PS2, PC, XB, XB360", 95,"Accion"),
            infoArray("Grand Thef Auto V", R.drawable.gtav, "17/09/2013", "PS3/PS4/PS5, XB360/XB1/XBSS/XBSX, PC", 97,"Accion"),
            infoArray("Kindom Hearts II", R.drawable.kindomhearts, "22/12/2005", "PS2", 87,"Aventura"),
            infoArray("Marvel's Guardians of the Galaxy", R.drawable.marvelsgotg, "27/10/2021", "PS5, PC, XBSS, XBSX", 80,"Accion"),
            infoArray("Marvel's Spider-Man", R.drawable.marvelspiderman, "07/09/2018", "PS4/PS5, PC", 87,"Accion"),
            infoArray("Minecraft", R.drawable.minecraft, "18/11/2011", "PS3/PS4/PS5, PC, XB360/XB1/XBSS/XBSX, NSwitch, Android, IOS", 82,"Aventura"),
            infoArray("Super Mario Galaxy", R.drawable.smgalaxy, "01/11/2007", "Wii", 97,"Aventura"),
            infoArray("Wakfu", R.drawable.wakfu, "29/02/2012", "PC", 76,"MMO"),
            infoArray("Watch Dogs 2", R.drawable.watchdogs2, "15/11/2016", "PS4, PC, XB1", 82,"RPG")
        )
