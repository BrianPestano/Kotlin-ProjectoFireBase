package com.example.proyectofinalfirebasebrianylauren.PantallaDetalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalfirebasebrianylauren.R

@Composable
fun pantallaDetalles(navController: NavController, VJ: String?) {
    val detallesVJ = obtenerDetallesVJ(VJ)

    // Composición de la pantalla de detalles
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Espaciador
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de retroceso
        IconButton(
            onClick = {
                navController.navigate("pantallaInicio")
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Detalles del Videojuego
        detallesVJ?.let { VJ ->
            // Muestra la imagen del Juego
            Image(
                painter = painterResource(id = VJ.imagenes),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterHorizontally)
            )
            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))
            // Muestra el nombre del Juego
            Text(
                text = VJ.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .align(Alignment.CenterHorizontally)
            )
            // Muestra los datalles del juegi
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Fecha de salida: ${VJ.fecha}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                // Espaciador
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Sinopsis: ${VJ.sinopsis}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))
        } ?: run {
            // Muestra un mensaje si no se encuentran detalles del Juego
            Text(
                text = "No se encontraron detalles para el videojuego seleccionado.",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
data class DetallesVJ(
    val nombre: String,
    val imagenes: Int,
    val fecha: String,
    val sinopsis: String
)
fun obtenerDetallesVJ(nombreVJ: String?): DetallesVJ? {
    return when (nombreVJ) {
        "Final Fantasy XII" -> DetallesVJ(
            "Final Fantasy XII",
            R.drawable.finalfantasyxii,
            "16/03/2006",
            "Final Fantasy XII (ファイナルファンタジーXII, Fainaru Fantajī Tueruju) es un videojuego de rol desarrollado y publicado por Square Enix en 2006/2007 para la PlayStation 2, siendo la duodécima entrega dentro la aclamada serie de videojuegos. Lanzado en el 2006, cinco años después de Final Fantasy X.\n" +
                    "\n" +
                    "Final Fantasy XII introduce cambios sustanciales en la saga: batallas que prescinden de cambio de escenario, el sistema gambit que controla automáticamente las acciones de los personajes, y el sistema de licencias que determina qué equipamiento y habilidades pueden usar los personajes. Final Fantasy XII incluye elementos de entregas anteriores, como las invocaciones, los Chocobos, y las naves voladoras. El juego ha sido aclamado universalmente con críticas muy positivas y ha sido elegido juego del año por importantes y prestigiosas revistas del sector. La secuela, Final Fantasy XII Revenant Wings, ha sido lanzada para Nintendo DS en el 2007 en Japón y el 2008 para el resto del mundo.\n" +
                    "\n" +
                    "Este juego se desarrolla en Ivalice, al igual que otros juegos de Square como Final Fantasy Tactics para PlayStation, Final Fantasy Tactics Advance para GameBoy Advance y Vagrant Story para PlayStation.\n" +
                    "\n" +
                    "El equipo que ha desarrollado todos los juegos antes comentados es el mismo, de ahí que se vean ciertas similitudes en su ambientación. Las ciudades ahora tienen un toque mediterráneo/europeo mezclado con la estética árabe, lo que da lugar a parajes llenos de gente y bazares donde la tecnología se reserva para las naves."
        )
        "Grand Thef Auto San Andreas" -> DetallesVJ(
            "Grand Thef Auto San Andreas",
            R.drawable.gtasanandreas,
            "26/10/2004",
            "Grand Theft Auto: San Andreas es un videojuego de acción-aventura de mundo abierto desarrollado por Rockstar North y publicado por Rockstar Games. Fue confirmado oficialmente por la mencionada Rockstar Games a principios de marzo de 2004, y su fecha de lanzamiento tentativa se programó para el 19 y 22 de octubre de ese año para América del Norte y Europa. Distribuido por Take-Two Interactive, San Andreas fue lanzado originalmente para PlayStation 2 el 26 de octubre de 2004, aunque posteriormente se publicaron distintas versiones, tanto para videoconsolas de sexta, séptima y octava generación, así como ordenadores, y teléfonos inteligentes.\n" +
                    "\n" +
                    "La trama del juego ocurre en San Andreas, estado ficticio que contiene a tres ciudades metropolitanas: Los Santos, San Fierro y Las Venturas; las tres basadas en las ciudades de Los Ángeles, San Francisco y Las Vegas, respectivamente, siendo el segundo mapa más extenso de la saga detrás de GTA V. Ambientado en 1992, la obra cuenta la historia de Carl Johnson “CJ”, quien decide volver a Los Santos tras cinco años de haberse ausentado en Liberty City —ciudad de otros videojuegos de la saga como GTA III, GTA Liberty City Stories o GTA IV— después de enterarse del asesinato de su madre. Al llegar, CJ intenta restablecer el poder de su antigua banda y su familia, mientras desvela poco a poco la verdad detrás de la muerte de su madre. Su trama se basa libremente en sucesos como la rivalidad entre las pandillas Bloods y Crips, la epidemia de crack, el escándalo Rampart y los disturbios de Los Ángeles de 1992.\n" +
                    "\n" +
                    "Grand Theft Auto: San Andreas está estructurado de manera similar a los títulos anteriores de la serie. El núcleo de las mecánicas de juego consiste en elementos de disparos en tercera persona, conducción y mundo abierto. Por primera vez en la serie, el jugador puede nadar, bucear, conducir un tren y trepar muros. Además, se hizo hincapié en la personalización del protagonista, añadiendo variadas estadísticas de habilidad en áreas tales como conducción, manejo de armas de fuego y atributos físicos. También hubo una mejora en el motor gráfico del juego, siendo una versión actualizada del Renderware, que también era utilizado por títulos anteriores como GTA III y Vice City. Muchos de estos mecanismos en el juego, sumado a la historia del mismo lograron una gran aceptación por parte del público, lo que provocó que la entrega fuera considerada como la más querida de GTA, el videojuego más vendido de la PlayStation 2, e incluso, el mejor y más completo videojuego de la saga.\n" +
                    "\n" +
                    "Grand Theft Auto: San Andreas recibió la aprobación casi unánime de la crítica. En el sitio web Metacritic, el juego posee una nota promedio de 95 sobre 100 para la plataforma PlayStation 2. Por su parte, las versiones de Windows y Xbox tienen una puntuación de 93 sobre 100. Asimismo, en dicha web el título se presenta como el quinto juego mejor puntuado de PlayStation 2, además de ser el videojuego más vendido de dicha consola, y el segundo con más ventas totales de la saga."
        )
        "Grand Thef Auto V" -> DetallesVJ(
            "Grand Thef Auto V",
            R.drawable.gtav,
            "17/09/2013",
            "Grand Theft Auto V (abreviado como GTA V o GTA 5) es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio escocés Rockstar North y distribuido por Rockstar Games. Este título revolucionario hizo su debut el 17 de septiembre de 2013 en las consolas Xbox 360 y PlayStation 3. Posteriormente, experimentó una reaparición el 18 de noviembre de 2014 en las consolas de nueva generación, Xbox One y PlayStation 4, con una perspectiva en primera persona. El juego luego amplió su alcance a Microsoft Windows el 14 de abril de 2015. El capítulo más reciente en su historia confirmó su llegada a Xbox Series X/S y PlayStation 5 en marzo de 2022, alardeando de impresionantes mejoras gráficas, incluido el soporte para una resolución de 8K y fluidos 120 FPS. Marca un hito significativo al ser la primera entrada importante en la serie Grand Theft Auto desde la presentación de Grand Theft Auto IV en 2008, marcando el comienzo de la \"era HD\" para la franquicia.\n" +
                    "\n" +
                    "Rockstar Games tentó a los fanáticos con los primeros detalles del juego el 25 de octubre de 2011, a través de la red social Twitter. La narrativa vibrante del juego se desarrolla en la ciudad ficticia de Los Santos y sus alrededores, tomando inspiración de Los Ángeles y la región del sur de California. Cabe destacar que Los Santos había servido como escenario para el título anterior, GTA: San Andreas. En un cambio innovador con respecto a sus predecesores, GTA V adopta un enfoque narrativo único al centrarse en tres protagonistas distintos: Michael, Trevor y Franklin, además del personaje personalizable del jugador en el modo en línea. El mundo tuvo su primera visión de Grand Theft Auto V a través de su tráiler de presentación, revelado el 2 de noviembre de 2011.\n" +
                    "\n" +
                    "Es importante destacar que GTA V ostenta la distinción de ser uno de los videojuegos más costosos jamás desarrollados, con un presupuesto de 265 millones de dólares, superando a su predecesor, GTA IV, que contó con un presupuesto de 100 millones de dólares. Grand Theft Auto V recaudó 800 millones de dólares en las primeras 24 horas de su lanzamiento, convirtiéndose en el videojuego de venta más rápida en la historia. En tan solo tres días, rompió récords al alcanzar la cifra de 1000 millones de dólares en ventas."
        )
        "Kindom Hearts II" -> DetallesVJ(
            "Kindom Hearts II",
            R.drawable.kindomhearts,
            "22/12/2005",
            "Kingdom Hearts II (キングダムハーツII) es un ARPG desarrollado por Square Enix y publicado por la misma empresa y Buena Vista Games para la consola PlayStation 2. Es la secuela directa de Kingdom Hearts: Chain of Memories. Apenas un mes después de su salida logró vender más de un millón de copias en Norteamérica, convirtiéndose en el segundo juego más vendido del año 2006.\n" +
                    "\n" +
                    "Esta secuela incorpora varias novedades, como la posibilidad de fusionarse con los miembros del equipo, permitiendo portar dos armas a la vez, así como nuevos enemigos, los Incorpóreos. Además se presentan varios mundos nuevos, que se suman a varios de los ya conocidos en Kingdom Hearts, como Coliseo del Olimpo, Atlántica, Ciudad de Halloween, o Bastión Hueco.\n" +
                    "\n" +
                    "El juego es también, hasta el momento, la única entrega de la serie Kingdom Hearts que ha sido doblado en español."
        )
        "Marvel's Guardians of the Galaxy" -> DetallesVJ(
            "Marvel's Guardians of the Galaxy",
            R.drawable.marvelsgotg,
            "27/10/2021",
            "Dale caña y surca el cosmos con una nueva versión de Marvel’s Guardians of the Galaxy. En esta aventura de acción en tercera persona encarnas a Star-Lord, un cuestionable líder muy echado para adelante que ha conseguido convencer a un estrafalario grupo de insólitos héroes para que se unan a él. Un notas (que no eres tú, clarísimamente) ha desencadenado una serie de catastróficos eventos, y solo tú puedes mantener unidos a los impredecibles Guardianes el tiempo suficiente para evitar el colapso interplanetario.\n" +
                    "\n" +
                    "Desenfunda los blásters elementales, propina palizas en equipo o da patadas voladoras con las botas propulsoras; aquí todo está permitido. Y si crees que el plan está saliendo a pedir de boca, te vas a llevar una buena sorpresa, porque las consecuencias de tus actos no dejarán que los Guardianes se duerman en los laureles.\n" +
                    "\n" +
                    "En esta historia original de Marvel’s Guardians of the Galaxy te encontrarás con poderosos seres nuevos y versiones únicas de personajes emblemáticos, que se verán envueltos en una lucha por el destino de la galaxia. Ha llegado el momento de enseñarle al universo cómo te las gastas. ¡Tú puedes! Supongo..."
        )
        "Marvel's Spider-Man" -> DetallesVJ(
            "Marvel's Spider-Man",
            R.drawable.marvelspiderman,
            "07/09/2018",
            "Marvel's Spider-Man es un videojuego de acción-aventura de mundo abierto basado en el popular superhéroe hómonimo de la editorial Marvel Comics. Fue desarrollado por Insomniac Games y publicado por Sony Interactive Entertainment en exclusiva para la consola PlayStation 4. Se trata del primer videojuego licenciado desarrollado por Insomniac. Su lanzamiento internacional se produjo el 7 de septiembre de 2018.\n" +
                    "\n" +
                    "El juego narra una historia completamente original de Spider-Man que no está ligada a películas o cómics anteriores. Cubre ambos aspectos del personaje, tanto de Peter Parker como el superhéroe, además de presentarlo en una faceta más experimentada. Durante el transcurso de la historia, Spider-Man debe hacerle frente a una gran variedad de famosos enemigos, tales como Mr. Negativo, Electro, Buitre, Rhino y Escorpión. Otro famoso personaje de los cómics que aparece en el juego es Miles Morales, un joven que porta el traje del Hombre Araña en uno de los universos donde Peter Parker fallece. Sin embargo, Insomniac confirmó que Morales no personificaría al superhéroe y que la historia del juego se centraría únicamente en Peter Parker. Stan Lee, uno de los creadores del personaje, realiza un breve cameo en el videojuego.\n" +
                    "\n" +
                    "El juego fue recibido de forma muy positiva por parte de la crítica especializada, al punto de ser considerado como uno de los mejores videojuegos de superhéroes realizados hasta la fecha. Los aspectos más elogiados fueron su jugabilidad, específicamente su sistema de combate y el desplazamiento con las telarañas, además de su nivel gráfico, la historia y la banda sonora. A nivel comercial tuvo un éxito rotundo logrando vender más de 13 millones de copias. De esa manera se convirtió en el juego de superhéroes que más rápido se ha vendido y en uno de los exclusivos de PS4 con mayores ventas.\n" +
                    "\n" +
                    "El 28 de agosto de 2019 salió a la venta una edición definitiva del videojuego llamada Marvel's Spider-Man: Game of the Year Edition, que incluía el juego completo junto con todos los contenidos descargables. Una versión remasterizada del juego, que incluye todos los contenidos descargables, fue puesta a la venta 2 años después, junto con Spider-Man: Miles Morales para PlayStation 5. Esta misma versión fue lanzada en Microsoft Windows el 12 de agosto de 2022, a cargo del estudio hermano de PlayStation Nixxes Software.\n" +
                    "\n" +
                    "En el PlayStation Showcase de 2021 se anunció una secuela del juego desarrollada por Insomniac Games y distribuida por Sony Interactive Entertainment, que estará protagonizada por Peter Parker y Miles Morales y cuyo lanzamiento está previsto para 2023 en exclusiva para PlayStation 5. Además, en este mismo evento se anunció el desarrollo de Marvel's Wolverine, un videojuego de Lobezno con el que Insomniac Games y Sony pretenden extender el universo de Marvel en las consolas PlayStation."
        )
        "Minecraft" -> DetallesVJ(
            "Minecraft",
            R.drawable.minecraft,
            "18/11/2011",
            "Minecraft es un videojuego de construcción de tipo «mundo abierto» o en inglés sandbox creado originalmente por el sueco Markus Persson (conocido comúnmente como «Notch»), que creo posteriormente Mojang Studios (actualmente parte de Microsoft). Está programado en el lenguaje de programación Java para la versión Java Edition y posteriormente desarrollado en C++ para la versión de Bedrock Edition. Fue lanzado el 17 de mayo de 2009, y después de numerosos cambios, su primera versión estable «1.0» fue publicada el 18 de noviembre de 2011.\n" +
                    "\n" +
                    "\n" +
                    "Markus Persson, el creador de Minecraft.\n" +
                    "Un mes antes del lanzamiento de su versión completa se estrenó una versión para dispositivos móviles llamada Minecraft: Pocket Edition en Android, y el 17 de noviembre del mismo año fue lanzada la misma versión para iOS, aunque posteriormente esta pasaría a ser Minecraft: Bedrock Edition. El 9 de mayo de 2012 fue lanzada la versión del juego para Xbox 360 y PS3. Todas las versiones de Minecraft reciben actualizaciones constantes desde su lanzamiento. En octubre de 2014, Minecraft lanzó su edición para PlayStation Vita, desarrollada por Mojang y 4J Studios. Esta versión presenta las mismas actualizaciones y similares características que las otras versiones de consola; además, cuenta con el sistema de venta cruzada, es decir que al comprar la versión de PlayStation 3 se obtiene también la de PlayStation Vita. A marzo de 2023 se habían vendido más de 238 millones de copias, siendo actualmente el videojuego más vendido de la historia.\n" +
                    "\n" +
                    "El 15 de septiembre de 2014, fue adquirido por la empresa Microsoft por un valor de 2500 millones de dólares estadounidenses. Este suceso provocó el alejamiento de Markus Persson de la compañía. En noviembre de 2016, Microsoft anunció el lanzamiento de la versión completa de Minecraft: Education Edition."
        )
        "Super Mario Galaxy" -> DetallesVJ(
            "Super Mario Galaxy",
            R.drawable.smgalaxy,
            "01/11/2007",
            "Super Mario Galaxy (スーパーマリオギャラクシー Sūpā Mario Gyarakushī?), también conocido como Mario Galaxy y SMG, es un videojuego de plataformas en 3D desarrollado por Nintendo EAD Tokio, dirigido por Yoshiaki Koizumi, y publicado por Nintendo para su consola Wii. El juego fue lanzado durante el mes de noviembre del año 2007 en Japón, América y Europa. Tras su estreno, se convirtió en el primer título de la serie Mario en salir para la mencionada Wii.\n" +
                    "\n" +
                    "La trama retoma los elementos clásicos que han distinguido a la serie —es decir, en la que los protagonistas intentan salvar a la princesa del malvado Bowser a través de diversos mundos, al mismo tiempo que derrotan a múltiples enemigos que complican la travesía, además de adquirir diversos objetos útiles a lo largo de su aventura, para alcanzar así el objetivo principal—; sin embargo, en esta entrega, el argumento se centra en la búsqueda galáctica que Mario y su hermano Luigi deben emprender para conseguir unos objetos llamados «Power Stars» (traducido como «Superestrellas») los cuales tienen la capacidad de otorgar energía a un artefacto inmenso llamado Planetarium del cometa, que funciona como una nave espacial, y cuyo propósito radica en poder llegar hasta el lugar donde se encuentra Bowser, ya que este tiene cautiva a la Princesa Peach.\n" +
                    "\n" +
                    "Los distintos niveles que integran el juego se conocen como «galaxias»; cada una posee planetas pequeños, polvo estelar y demás restos cósmicos. El elemento central del sistema de juego es la gravedad de los cuerpos celestes y que además proporciona la exploración total de los niveles dentro de un contexto determinado. El juego es el primero dentro de toda la saga en presentar un gameplay de manera simultánea, a pesar de que el juego tiende a enfocarse hacia un solo jugador; esto permite que un segundo jugador intervenga en la acción del juego, así como atacar a enemigos, y recolectar Star Bits.\n" +
                    "\n" +
                    "Super Mario Galaxy fue mostrado por primera vez en el evento E3 de 2006, con un sistema básico de gráficos que generaba la representativa física producida por el juego, y que además fue provisto anteriormente en una demostración. Paralelamente a la concepción original del juego a partir de «plataformas esféricas» con gravedad propia, Miyamoto concibió la idea de diseñar un nuevo juego en donde la libertad y la acción presentes en los movimientos estuvieran disponibles en todo momento; esta ocurrencia fue concebida a partir de un hámster que pertenecía a un amigo suyo. El videojuego ha sido uno de los más aclamados de todos los tiempos por parte de la crítica, así como por parte de los fanáticos de la serie, y además ha ganado numerosos premios como «Mejor juego del año». Hasta el 31 de marzo de 2008, se tuvo la certeza de que había vendido 6,1 millones de copias en todo el mundo, situándose como el cuarto juego más vendido de Wii. Debido al éxito comercial que el juego alcanzó, a finales de 2011 el título entró a la colección Player's Choice, una clasificación creada por la compañía nipona donde se enlistan diferentes juegos que han tenido un impacto dentro de la industria de los videojuegos.\n" +
                    "\n" +
                    "El 2 de junio de 2009, en el respectivo E3, se anunció la segunda parte del juego, Super Mario Galaxy 2, el cual salió a la venta a partir de mayo de 2010."
        )
        "Wakfu" -> DetallesVJ(
            "Wakfu",
            R.drawable.wakfu,
            "29/02/2012",
            "Embárcate hacia el Mundo de los Doce y sumérgete en la gran aventura de WAKFU, un juego de rol multijugador masivo en línea con un universo original, donde los combates tácticos riman con el humor (sí, sí, verás como sí rima).\n" +
                    "\n" +
                    "Asciende el Monte Zinit tras la pista de Ogrest, el ogro responsable del cataclismo que devastó el mundo, o traza tu propio camino en los archipiélagos en reconstrucción.\n" +
                    "\n" +
                    "Conviértete en guerrero, político, comerciante o artesano, y sobre todo no olvides que,\n" +
                    "\n" +
                    "¡EN WAKFU, ITODO DEPENDE DE TI!"
        )
        "Watch Dogs 2" -> DetallesVJ(
            "Watch Dogs 2",
            R.drawable.watchdogs2,
            "15/11/2016",
            "Watch Dogs 2 (estilizado como WATCH_DOGS 2) es un videojuego de mundo abierto y acción-aventura desarrollado por Ubisoft Montreal y distribuido por Ubisoft para las plataformas Microsoft Windows, PlayStation 4 y Xbox One. Es la secuela de Watch Dogs que fue lanzado a la venta el 15 de noviembre de 2016. Es de mundo abierto, sigilo y de disparos en tercera persona estando ambientado en la ciudad de San Francisco. El jugador encarna a Marcus Holloway, un hacker que se une al grupo hacktivista DedSec para descubrir y revelar al público los manejos del Sistema Operativo ctOS e investigar como el Estado y las empresas utilizan la información recolectada para controlar a la población."
        )
        else -> null
    }
}