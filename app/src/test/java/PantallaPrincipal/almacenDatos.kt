package PantallaPrincipal

import Videojuego.infoArray
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.proyectofinalfirebasebrianylauren.R

var lista =
    mutableStateListOf(
        infoArray("Bulbasaur", R.drawable.bulbasaur, "20", "Kanto", 10),
        infoArray("Charmander", R.drawable.charmander, "24", "Kanto", 12),
        infoArray("Squirtle", R.drawable.squirtle, "26", "Kanto", 13),
        infoArray("Chikorita", R.drawable.chikorita, "40", "Johto", 8),
        infoArray("Cyndaquil", R.drawable.cyndaquil, "43", "Johto", 7),
        infoArray("Totodile", R.drawable.totodile, "46", "Johto", 6),
        infoArray("Treecko", R.drawable.treecko, "103", "Hoenn", 5),
        infoArray("Torchic", R.drawable.torchic, "106", "Hoenn", 11),
        infoArray("Mudkip", R.drawable.mudkip, "108", "Hoenn", 3),
        infoArray("Turtwig", R.drawable.turtwig, "202", "Sinnoh", 1),
        infoArray("Chimchar", R.drawable.chimchar, "203", "Sinnoh", 2),
        infoArray("Piplup", R.drawable.piplup, "212", "Sinnoh", 4),
        infoArray("Snivy", R.drawable.snivy, "14", "Teselia", 9),
        infoArray("Tepig", R.drawable.tepig, "15", "Teselia", 10),
        infoArray("Oshawott", R.drawable.oshawott, "13", "Teselia", 7),
        infoArray("Chespin", R.drawable.chespin, "16", "Kalos", 4),
        infoArray("Fennekin", R.drawable.fennekin, "10", "Kalos", 8),
        infoArray("Froakie", R.drawable.froakie, "1", "Kalos", 11),
        infoArray("Rowlet", R.drawable.rowlet, "16", "Alola", 4),
        infoArray("Litten", R.drawable.litten, "10", "Alola", 8),
        infoArray("Popplio", R.drawable.popplio, "1", "Alola", 11),
    )
