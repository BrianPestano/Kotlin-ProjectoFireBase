package PantallaDetalles

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
fun pantallaDetalles(navController: NavController, pokemon: String?) {
    val detallesPokemon = obtenerDetallesPokemon(pokemon)

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
        // Detalles del Pokémon
        detallesPokemon?.let { pokemon ->
            // Muestra la imagen del Pokémon
            Image(
                painter = painterResource(id = pokemon.imagenes),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterHorizontally)
            )
            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))
            // Muestra el nombre del Pokémon
            Text(
                text = pokemon.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .align(Alignment.CenterHorizontally)
            )
            // Muestra la ruta y texto de la Pokédex
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Muestra la ruta del Pokémon en negrita
                Text(
                    text = "Ruta: ${pokemon.ruta}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                // Espaciador
                Spacer(modifier = Modifier.height(15.dp))
                // Muestra el texto de la Pokédex en negrita
                Text(
                    text = "Pokédex: ${pokemon.textoPokedex}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))
        } ?: run {
            // Muestra un mensaje si no se encuentran detalles del Pokémon
            Text(
                text = "No se encontraron detalles para el Pokémon seleccionado.",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
data class DetallesPokemon(
    val nombre: String,
    val imagenes: Int,
    val ruta: String,
    val textoPokedex: String
)
fun obtenerDetallesPokemon(nombrePokemon: String?): DetallesPokemon? {
    return when (nombrePokemon) {
        "Bulbasaur" -> DetallesPokemon(
            "Bulbasaur",
            R.drawable.bulbasaur,
            "Ruta 20",
            "Bulbasaur es un Pokémon cuadrúpedo de color verde, posee manchas de una tonalidad más oscura del mismo color con distintas formas geométricas. Su cabeza representa cerca de un tercio de su cuerpo. En su frente se ubican tres manchas que pueden cambiar de posición, forma o lugar dependiendo del ejemplar. Tiene como orejas muñones pequeños y puntiagudos. Sus ojos son grandes y de color rojo. Sus patas son cortas y posee tres garras en cada una. Este Pokémon tiene plantado un bulbo en el lomo desde que nace, esta semilla crece y se desarrolla a lo largo del ciclo de vida de Bulbasaur a medida que suceden sus evoluciones. El bulbo absorbe y almacena la energía solar que Bulbasaur necesita para hacer florecer el bulbo de su lomo y evolucionar en Ivysaur. Dicen que cuanta más luz consuma la semilla, más rápido crecerá y brotará, por lo que es muy común ver a este Pokémon tumbado echándose una siesta en lugares donde los rayos del sol lleguen a plenitud. Por otro lado, gracias a los nutrientes que el bulbo almacena, puede pasar varios días sin comer. Su cuerpo según a palabras de Ken Sugimori y Junichi Masuda en una entrevista, está basado en un anfibio (sapo o rana), al igual que toda su línea evolutiva.3\n" +
                    "\n" +
                    "El bulbo de Bulbasaur le ayuda a defenderse de los enemigos, y desde él puede disparar ataques tales como rayo solar y drenadoras entre otros movimientos.\n" +
                    "\n" +
                    "No es muy raro encontrarlo en jardines y zonas cercanas a fuentes de agua. Se los puede atraer con el aroma de las flores. Según el anime, una vez al año, cuando estos Pokémon están listos para evolucionar suelen reunirse en grandes cantidades en un Jardín Misterioso mientras hacen un ritual a la luz de la luna junto a un gran Venusaur.\n"
        )
        "Charmander" -> DetallesPokemon(
            "Charmander",
            R.drawable.charmander,
            "Ruta 24",
            "Charmander es un pequeño monstruo bípedo parecido a un lagarto. Sus características de fuego son resaltadas por su color de piel anaranjado y su cola, cuya punta está envuelta en llamas. Charmander y sus evoluciones, Charmeleon y Charizard, tienen una pequeña llama en la punta de sus colas desde que nacen. La intensidad con la que ésta arde es un indicador del estado de salud y emocional de este Pokémon: si la llama arde con mucha fuerza, indica que está completamente sano, y si arde muy levemente, significa que se encuentra débil. El Pokémon podría morir si la llama de su cola se apaga. Cuando son bebés aún no están familiarizados con el fuego, pudiendo llegar a quemarse a sí mismos.\n" +
                    "\n" +
                    "Viven en grupos, cuidando las llamas de sus colas entre sí. Prefieren los lugares silenciosos y rocosos. En estos lugares si se presta mucha atención, se pueden llegar a escuchar el tenue chisporroteo de su llama. Los lugares secos y cálidos son mejores para ellos, por lo que frecuentemente son encontrados en cuevas o en las cercanías de volcanes y montañas. En la lluvia es fácil reconocerlos por el vapor que emana de su cola, la cual seguirá ardiendo aunque se moje un poco. Al dormir usan su cola para calentarse.\n"
        )
        "Squirtle" -> DetallesPokemon(
            "Squirtle",
            R.drawable.squirtle,
            "Ruta 23",
            "Squirtle tiene forma de una tortuga semiacuática de una tonalidad azulada, su caparazón es color café, las placas periféricas de color blanco y finalmente su plastrón de una tonalidad crema, posee una cola con la punta enrollada, además de tres dedos en cada una de sus extremidades, una boca con una punta en forma de pico característico de las tortugas y unos grandes ojos de tonalidad rojiza.\n" +
                    "\n" +
                    "Al nacer su espalda se va hinchando hasta formarse un caparazón, al principio es blando y elástico, si lo golpeas este rebotará, pero conforme pasa el tiempo se irá endureciendo para resistir los ataques de cualquier amenaza, ocultándose dentro de él cuando siente peligro, al estar escondido puede lanzar una enorme presión de agua desde su interior cuando tiene la oportunidad. Igualmente retrae su cabeza y extremidades mientras duerme para sentirse seguro, a veces se puede llegar a ver como se mece su caparazón entre sueños. Su caparazón no solo le sirve de protección únicamente, con su forma redondeada y las hendiduras que posee, le sirven para reducir su resistencia en el agua y así poder nadar a enormes velocidades. Además de lanzar con gran precisión chorros de agua a presión por la boca, también puede lanzar espuma y usar su duro caparazón para atacar. Siempre se lo ve cerca del agua, ya sea dulce o salada.\n"
        )
        "Chikorita" -> DetallesPokemon(
            "Chikorita",
            R.drawable.chikorita,
            "Ruta 29",
            "Chikorita es un Pokémon cuadrúpedo con forma de saurópodo de tonos verdes con una gran hoja en su cabeza. De su cuello sobresalen varios pinchos pequeños formando una especie de collar, en el anime puede verse que se pueden expandir para formar lianas. Tiene dos grandes ojos carmesí y una pequeña boca. Su cuerpo es muy corto en comparación con su cabeza y en la parte de atrás sobresale una pequeña cola.\n" +
                    "\n" +
                    "De su gran hoja desprende un dulce aroma, con ella es capaz de medir la humedad y temperatura del ambiente para localizar los sitios cálidos. Una vez que detecta ese lugar idóneo para echarse, dormirá plácidamente con el rostro lleno de satisfacción de haber hallado el sitio perfecto. Es muy dócil, comúnmente se le ve echado en los campos y praderas abiertas donde puede recibir los rayos directos del sol y así poder absorberlos. Cuando combate gira y agita su gran hoja para impresionar y mantener a raya a su rival, para después liberar una fragancia relajante y apaciguar el encuentro, creando un ambiente agradable lleno de paz. Es un Pokémon agradable, se lo puede ver trotando llevando su gran hoja al viento.\n"
        )
        "Cyndaquil" -> DetallesPokemon(
            "Cyndaquil",
            R.drawable.cyndaquil,
            "Ruta 30",
            "Cyndaquil es un Pokémon tímido y pequeño que recuerda a un equidna. Su piel es azulada en la parte superior de su cuerpo, y de color crema en la parte inferior. De su lomo puede expulsar llamas a través de cuatro grandes orificios.\n" +
                    "\n" +
                    "Suele vérselo acurrucado en forma de bola, si se enfada o se asusta, lanzará las llamas por su lomo para poder protegerse, dicho fuego expulsado es infernal e intimidará a sus rivales. Sin embargo, si este Pokémon se encuentra cansado durante está situación, solo conseguirá echar unas chispas que no llegan a cuajar en una combustión completa. Su naturaleza tímida se ve contrastada con el inmenso poder ígneo que posee en su interior. En el anime se ha mostrado que algunos Cyndaquil previamente necesitan entrar en calor durante el combate para poder expulsar sus llamas apropiadamente, aunque con algo de entrenamiento pueden resolver este problema.\n" +
                    "\n" +
                    "Se lo suele ver en las praderas, donde con su carácter tranquilo evitará meterse con otros Pokémon.\n"
        )
        "Totodile" -> DetallesPokemon(
            "Totodile",
            R.drawable.totodile,
            "Ruta 32",
            "La apariencia de Totodile está basada en un reptil acuático como la cría de un cocodrilo o un lagarto. Tiene un pequeño cuerpo de color azul con una banda amarilla que cruza su pecho. En su espalda y su cola tiene cuatro puntas rojas. Este Pokémon bípedo tiene una gran y prominente quijada, llena de dientes filosos.\n" +
                    "\n" +
                    "Es de un carácter violento. Sus fauces están muy desarrolladas con las cuales es capaz de romper cualquier cosa. Además de que gusta clavarlas en todo lo que se mueve a su alrededor, por lo que sus entrenadores deben de tener cuidado y no darle la espalda, ya que hasta ellos pueden ser su objetivo. A veces piensa que solo esta dando unos pequeños mordiscos cuando en realidad se encuentra ocasionando heridas bastante considerables. Al dormir, lo hace con un ojo abierto, pues solo la mitad de su cerebro se encuentra descansando, a esto se le conoce como sueño unihemisférico. Esto lo hace para siempre estar alerta frente a posibles ataques enemigos.\n"
        )
        "Treecko" -> DetallesPokemon(
            "Treecko",
            R.drawable.treecko,
            "Ruta 101",
            "Treecko está basado en un geco. Es de tonalidad verde, aunque cuando se desplaza sobre superficies verticales en cuatro patas, cuando camina por tierra firme lo hace en dos. Posee una gran cola de un tono verde más oscuro, la cual parece estar dividida en dos partes.\n" +
                    "\n" +
                    "Tiene unos ganchos pequeños en las plantas de los pies con los que puede escalar superficies verticales. Se dice que incluso pueden caminar sobre vidrio y techos sin caerse. Es carismático, tranquilo y con gran capacidad de autocontrol. Si algún rival se le queda mirando, también él le devolverá la misma mirada sin concederle a su rival ningún centímetro de espacio. Suele usar su cola como un miembro más a la hora de trepar por los árboles y esta llega a poseer una fuerza asombrosa con la que ataca a sus oponentes. Con su cola además puede detectar la humedad y puede prever el tiempo que va a hacer.\n" +
                    "\n" +
                    "Este Pokémon tiende a vivir en bosques y selvas, pero también puede vivir en praderas. Hacen su nido en un árbol gigante, sienten mucho respeto por el árbol en el que nacieron, ya que lo cuidan y protegen con todas sus fuerzas. Por eso se le conoce como el protector de los árboles.\n"
        )
        "Torchic" -> DetallesPokemon(
            "Torchic",
            R.drawable.torchic,
            "Ruta 103",
            "Torchic está basado en un pollito. Torchic está cubierto por una suave capa de plumas con tonos anaranjados. Sus alas son inútiles para volar debido a su pequeño tamaño.\n" +
                    "\n" +
                    "Este Pokémon tiene un saco interno en su estómago en el que hay fuego ardiendo todo el tiempo, lo que le permite arrojar bolas de fuego en combate a una temperatura cercana a los 1000 °C. Esta bolsa calienta todo su cuerpo y, por ello, Torchic es caliente al tacto. En las noches cuando va a dormir coloca su cabeza entre el plumaje de su espalda. Torchic es algo inseguro y miedoso, por lo que no se separa de su entrenador. Si lo abrazan arderá con fervor. Siempre va detrás de él con sus pasitos inseguros y alerta por si ocurre algo fuera de lo habitual. Se lo suele ver picoteando el suelo en busca de guijarros, los cuales traga y llena su estómago, esto le ayuda a mejorar su digestión y a elevar la temperatura de sus llamas.\n"
        )
        "Mudkip" -> DetallesPokemon(
            "Mudkip",
            R.drawable.mudkip,
            "Ruta 102",
            "Mudkip está basado en un pez del fango y en un ajolote.\n" +
                    "\n" +
                    "La aleta en la cabeza de Mudkip actúa como un radar altamente sensible. Esto le permite sentir los movimientos del agua y el aire, con lo que puede determinar qué es lo que ocurre a su alrededor sin necesidad de usar sus ojos. Esta aleta de la cabeza también le sirve para detectar las corrientes del agua y la proximidad de algún depredador o enemigo. Cuando está en el agua, respira utilizando las puntas de sus mejillas, ya que realmente son branquias. A pesar de ser pequeño, este Pokémon tiene mucha fuerza. En tierra, Mudkip puede levantar rocas grandes y haciendo palanca con sus patas. Duerme enterrándose en el lodo blando de las orillas de lagos y ríos. Su larga cola, que es más bien una especie de aleta, le provee con una propulsión y dirección muy potentes en el agua, haciendo que sea muy veloz al nadar. Cuando tiene que enfrentarse a un oponente muy fuerte o en situaciones límite en los combates, Mudkip desata una fuerza asombrosa con la que puede destruir rocas varias veces mayores que su propio cuerpo.\n"
        )
        "Turtwig" -> DetallesPokemon(
            "Turtwig",
            R.drawable.turtwig,
            "Ruta 201",
            "Turtwig está basado tanto en una tortuga terrestre como en un brote. Turtwig es un pequeño Pokémon cuadrúpedo. Su cuerpo es principalmente verde claro, aunque sus patas y mandíbula inferior son amarillas y su caparazón es marrón, con una franja negra.\n" +
                    "\n" +
                    "En su cabeza crece un tallo con una rama y de esta salen dos hojas. Su caparazón está constituido completamente de tierra y limo y es ligeramente húmedo y cálido. Si el Pokémon está sano, este suele estar húmedo y endurecerse cuando bebe agua. Generalmente habita en zonas cercanas a ríos o lagos, ya que necesita agua para mantener verdes las hojas de su cabeza, de lo contrario, al pasar sed, estas se marchitan. Además, puede realizar la fotosíntesis en todo su cuerpo, usando a los rayos del sol para obtener oxígeno y vitalidad, por lo que no debe preocuparse por conseguir otros nutrientes.\n"
        )
        "Chimchar" -> DetallesPokemon(
            "Chimchar",
            R.drawable.chimchar,
            "Ruta 202",
            "Chimchar está basado en un bebé mono. Lo demuestran sus grandes orejas y su pequeño hocico. Tiene un copete en forma de llama en la parte superior de su cabeza. El pelaje de su cuerpo es de un color naranja pálido.\n" +
                    "\n" +
                    "Es un Pokémon que está lleno de energía y entusiasmo. La gente de antaño le temía, pues lo confundía con un espíritu maligno conocido como \"culinterna\". Es capaz de producir una llama en su cola quemando el gas en su estómago, esta llama no puede ser extinguida ni por la lluvia. A diferencia de otros Pokémon de tipo fuego donde esa llama es permanente, Chimchar la apaga cuando va a dormir para no provocar ningún incendio. Cuando está débil, el fuego de su cola arde con menos intensidad, disminuyendo notoriamente. Posee una gran agilidad, especialmente en los árboles, pudiendo trepar rápidamente por el tronco y saltar de rama en rama impulsándose con ayuda de sus brazos. Vive en las montañas donde se le puede observar escalando los acantilados escarpados con facilidad.\n"
        )
        "Piplup" -> DetallesPokemon(
            "Piplup",
            R.drawable.piplup,
            "Ruta 203",
            "Piplup está basado en una cría de pingüino emperador. Sus colores son un celeste y azul intenso y tiene dos botones blancos colocados en su vientre. Este aspecto parece indicar que tiene influencia de un pingüino azul.\n" +
                    "\n" +
                    "A pesar de su aspecto tierno e infantil, se trata de un Pokémon muy orgulloso. Este orgullo hace que odie que la gente le de comida a modo de ofrenda y que lo cuiden. Como no aprecia el apoyo ni de su entrenador, le cuesta obtener una confianza hacía él. En tierra firme no se le da muy bien el caminar por ella, haciendo que se tropiece y caiga mucho, pero su naturaleza orgullosa le hace levantarse sacando el pecho como si nada hubiese ocurrido. En cambio en el agua se desplaza como un nadador experto, pudiendo bucear por más de diez minutos mientras busca su comida.\n" +
                    "\n" +
                    "Habita en las costas frías de las regiones nórdicas más septentrionales con otros miembros de su familia evolutiva. También se le puede ver sobre témpanos de hielo. Debido a estos climas, posee un grueso plumón que lo protege del frío.\n"
        )
        // Teselia
        "Snivy" -> DetallesPokemon(
            "Snivy",
            R.drawable.snivy,
            "Ruta 1",
            "Su cuerpo es predominantemente de color verde. Sobre sus brazos cuenta con dos estructuras curvadas de un color amarillo más vivo que el de la franja que recorre su cuerpo.\n" +
                    "\n" +
                    "Su cola, en forma de hoja, es la encargada de realizar la fotosíntesis. Si se queda sin energía, esta se dobla hacia abajo. Se dice que cuando son salvajes, también tienen la capacidad de adaptarse mejor al medio que les rodea, siendo esto naturalmente en bosques o selvas muy densas, aunque cuando es entrenado, se transforma en pedante y altanero. Se dice que si cree que su entrenador no es digno de él, lo abandonará. Los Snivy son muy elegantes y fuertes, por lo que no soportan perder bajo ningún concepto. Cuando le baña el sol, sus movimientos se vuelven mucho más veloces. Puede resultar ser un Pokémon muy orgulloso, pero cuando se le entrena bien se convierte en un Pokémon muy fiel.\n"
        )
        "Tepig" -> DetallesPokemon(
            "Tepig",
            R.drawable.tepig,
            "Ruta 17",
            "Tiene ojos grandes, la cabeza de color negro y amarillo, y grandes orejas. Su cola tiene forma de espiral con una esfera roja al final. En la parte trasera de su lomo, su pelaje es negro, mientras que el resto de su cuerpo es anaranjado y en sus patas delanteras tiene unas pezuñas negras. En la parte negra de su pelo se forma una figura como la del calzón de un luchador que le envuelve las patas traseras y contrasta la parte naranja.\n" +
                    "\n" +
                    "La esfera roja de su cola absorbe los rayos solares convirtiéndolos en pura energía que pasa al centro de su cuerpo, fortaleciendo de ese modo sus ataques de tipo fuego. Cuando usa ataques de fuego, la esfera de su cola empieza a brillar con una gran intensidad. Al evolucionar esta esfera roja desaparece de su cola, pasando a estar en el centro de su cuerpo donde antes se acumulaba toda la energía absorbida, así se van fortaleciendo aún mucho más sus ataques.\n" +
                    "\n" +
                    "Asa bayas con sus llamas y se las come bien tostadas. Cuando se resfría, las llamas que expulsa de su nariz se convierten en humo negro, fácil de confundir con humo de hogueras descontroladas. A veces se emociona demasiado y termina churruscando las bayas que intenta asar.\n"
        )
        "Oshawott" -> DetallesPokemon(
            "Oshawott",
            R.drawable.oshawott,
            "Ruta 19",
            "Oshawott tiene una cabeza grande, blanca y esférica, con orejas pequeñas en forma cónica de color azul oscuro. Los ojos de Oshawott son grandes, oscuros y de forma ovoide. Su nariz es de color marrón de forma ovalada. Oshawott también posee manchas a los lados de su cara, haciendo referencia a las barbas cortas que poseen las nutrias jóvenes. También posee un extraño collar alrededor de su cuello, que recuerda a pequeñas burbujas. El torso de Oshawott es de color azul claro y posee una especie de adhesivo que mantiene su vieira de color amarillo pálido pegada en el centro. Esta vieira se puede quitar y es utilizada con la versatilidad de una espada. Sus pequeños brazos redondeados son de color blanco, y sus pies, que, a diferencia de sus brazos, son de color azul oscuro, el mismo que sus orejas y su cola. Oshawott también posee una cola plana y larga, que le permite nadar a mayor velocidad. Suele nadar de espaldas, usando solo las patas traseras y la cola.\n" +
                    "\n" +
                    "Lucha con la vieira de su vientre, usándola para bloquear ataques y luego contraatacar. Esta vieira está hecha del mismo material que sus garras y se regenera en caso de romperse. Oshawott también usa esta vieira para cortar bayas que estén muy duras.\n"
        )
        // Kalos
        "Chespin" -> DetallesPokemon(
            "Chespin",
            R.drawable.chespin,
            "Ruta 2",
            "Chespin está basado en un erizo o puercoespín y en una castaña. Tiene el cuerpo marrón y una robusta coraza de color verde con varios pinchos alrededor que le cubre la cabeza y la espalda, simulando la cápsula espinosa de las castañas, conocida como erizo. Posee grandes garras blancas y notorias en sus patas inferiores e incisivos prominentes. Su cola acaba en punta y es de color naranja, al igual que su nariz.\n" +
                    "\n" +
                    "Debido a su naturaleza curiosa, se mete en líos a menudo; sin embargo Chespin es un Pokémon optimista que no suele preocuparse por los problemas, lo que da muestra de su naturaleza amigable y gentil. Chespin puede cargar energía y usarla para convertir sus suaves púas en unas muy duras y afiladas, con las que puede atravesar rocas. En cuanto a su coraza, es muestra de que Chespin destaca en defensa, pues su gran resistencia le puede proteger de grandes impactos.\n"
        )
        "Fennekin" -> DetallesPokemon(
            "Fennekin",
            R.drawable.fennekin,
            "Ruta 3",
            "Fennekin está basado en un fénec, un mamífero carnívoro de la familia de los zorros, que habita en el desierto del Sáhara. Tiene el cuerpo de color amarillo, con la zona del hocico y unos bigotes color blanco. Sus ojos son alargados y de color anaranjado, al igual que el pelaje que sale de sus orejas, que recuerda a un par de llamas, así como el final de su cola.\n" +
                    "\n" +
                    "A este Pokémon le encanta mordisquear ramitas. Intimida a sus enemigos emanando un aire abrasador por las orejas que alcanzan unas temperaturas superiores a los 200°C, aunque en el anime se menciona que alcanzan unas temperaturas superiores a los 400°C.\n"
        )
        "Froakie" -> DetallesPokemon(
            "Froakie",
            R.drawable.froakie,
            "Ruta 4",
            "Froakie está basado en una rana coquí, que del huevo nace como una rana desarrollada completamente. Froakie posee un cuerpo de color celeste con una franja azul oscura entre sus ojos de color amarillo y pupilas negras. Tiene una especie de espuma de color blanco en su nariz y a su vez éste también rodea su cuello y llega hasta la espalda. Tiene una coloración blanca en sus manos que se asemejan a guantes.\n" +
                    "\n" +
                    "Las burbujas que tiene en el pecho y en la espalda le protegen de los ataques rivales. Froakie es tan ligero como fuerte y puede saltar muy alto. A veces parece que siempre está distraído, pero, en realidad, vigila con mucha atención a todo lo que le rodea. Al evolucionar, pierde esos dedos tan anchos de las manos y las patas para ser más ágil. Su espalda y cuello están llenas de burbujas espumosas que pueden agrandarse hasta cubrirle toda la cara, principalmente cuando se siente bien.\n"
        )
        // Alola
        "Rowlet" -> DetallesPokemon(
            "Rowlet",
            R.drawable.rowlet,
            "Ruta 1",
            "Rowlet está basado en una cría de lechuza. El Pokémon se caracteriza por tener cuerpo redondo y patas cortas. Su plumaje es de color beige con un disco facial blanco, al igual que su parte inferior. Tiene grandes ojos negros y entre estos un pico, del cual la parte superior es blanca y la inferior tiene un tono anaranjado. Dos hojas brotan de su pecho, posicionadas de tal forma que se asemejan a una pajarita. Sus patas tienen dos dedos orientados hacia adelante y uno hacia atrás. Hojas adicionales forman su cola y se alinean en el interior de sus alas.\n" +
                    "\n" +
                    "Se trata de un Pokémon nocturno, por lo que pasa gran parte del día durmiendo, almacenando energía a través de la fotosíntesis. Surca los cielos con sigilo, acercándose a sus adversarios sin hacer el menor ruido y sin que lo detecten para asestarles luego potentes patadas. También puede atacar a distancia gracias a las hojas de sus alas, afiladas como cuchillas. Este movimiento conocido como follaje es su principal ataque. Rowlet puede girar el cuello casi 180° para inspeccionar sus alrededores, con lo que es capaz de ver lo que tiene justo a su espalda. En los combates, suele girar la cabeza para mirar a su entrenador directamente a la espera de instrucciones. El Pokémon suele tener una personalidad inocente y amable. Rowlet se relaja mucho en espacios estrechos y oscuros.\n"
        )
        "Litten" -> DetallesPokemon(
            "Litten",
            R.drawable.litten,
            "Ruta 2",
            "Litten está basado en un gato pequeño. Su pelaje es negro con franjas rojas alrededor de cada una de sus patas. El color rojo también está presente en la parte inferior de su cara y en sus bigotes. Sus ojos tienen una tonalidad amarillenta y sus pupilas son rojas. En la punta de su cola destaca un mechón negro con cuatro puntas más.\n" +
                    "\n" +
                    "Su pelaje es muy graso y altamente inflamable. Se dice que el abrigo que cubre su piel puede crecer hasta dos veces al año. Litten se lame para acicalarse y luego prende las bolas de pelo que se forman en su estómago para atacar a sus adversarios con bolas de fuego. Cuando a Litten le llega la hora de cambiar su pelaje, le prende fuego y se produce un estallido de llamas monumental. Eriza el pelo de su lomo para intimidar a sus enemigos. Litten es un Pokémon lógico y apasionado. Este Pokémon no pierde nunca la calma y jamás muestra sus emociones. También se destaca por ser algo solitario.\n"
        )
        "Popplio" -> DetallesPokemon(
            "Popplio",
            R.drawable.popplio,
            "Ruta 3",
            "Popplio está basado en un león marino de la familia de los pinnípedos y en un payaso de circo. Su piel es de color azul oscuro. Alrededor del cuello posee una membrana de color azul claro que se asemeja a una gorguera, la cual, junto a con su hocico blanco y nariz roja, le dan la apariencia similar a la de un león marino de circo. El Pokémon tiene cuatro aletas, las delanteras son más grandes que las traseras, y estas tienen marcas blancas que separan sus dedos.\n" +
                    "\n" +
                    "Popplio es capaz de crear globos de agua por la nariz y utilizarlos como parte de sus distintas estrategias y ataques en los combates. Este Pokémon se mueve con más soltura en el agua que en tierra firme y puede nadar a velocidades que superan los 40 km/h. Cuando está fuera del agua, aprovecha la elasticidad de sus globos para ejecutar saltos y otras acrobacias.\n"
        )
        // Galar
        "Grookey" -> DetallesPokemon(
            "Grookey",
            R.drawable.grookey,
            "Ruta 1",
            "Grookey está basado en un macaco coronado o en un mono ardilla. El pelaje de la mayor parte de su cuerpo es verde (sirviendo para sintetizar la luz solar), con el hocico, los brazos y las patas de color naranja, cosas que lo asemejan al segundo primate antes mencionado. Posee unos ojos grandes, y el pelaje de alrededor de sus ojos es de un tono amarillo; además tiene las orejas y la cola marrones. De la parte superior de su cabeza sobresalen un par de hojas, atravesadas por un pequeño palo, que alguna vez fue una rama del bosque donde vivía.\n" +
                    "\n" +
                    "Este Pokémon suele ser bastante travieso y siente curiosidad por todo lo que lo rodea. Grookey puede retirar el pequeño palo de su cabeza y usarlo a modo de herramienta o instrumento (específicamente, una baqueta). Este palo, ahora baqueta, adquirió poderes especiales al estar expuesto a la energía que emana del cuerpo de este Pokémon. Las flores y hojas marchitas recuperan su color cuando Grookey golpea con su baqueta cerca de ellas. Estos también alegran a las plantas a su alrededor, llenándolas de vida.\n" +
                    "\n" +
                    "Grookey ama la música y los sitios con una altura elevada, como las ramas de los árboles. Es común verlo realizando ritmos con su baqueta o bailando.\n" +
                    "\n" +
                    "En combate, Grookey utiliza su baqueta para pelear y golpear a sus oponentes. Este aumenta su entusiasmo según la velocidad de sus golpes. Contra más rápido golpee, mayor será su entusiasmo.\n"
        )
        "Scorbunny" -> DetallesPokemon(
            "Scorbunny",
            R.drawable.scorbunny,
            "Ruta 2",
            "Scorbunny está basado en un conejo. Tiene el cuerpo de color mayormente blanco, una pequeña nariz rosada y unos ojos rojizos, mostrando también zonas rojizas en las puntas de las patas, en sus largas orejas y en el cuello. También tiene el interior de las orejas de un tono anaranjado, al igual que en su entrecejo y en la parte inferior de sus patas, siendo en estas dos últimas zonas donde empieza a irradiar calor cuando se prepara para combatir. Se ve que tiene un pelaje algo desaliñado en las orejas y los mofletes.\n" +
                    "\n" +
                    "Este Pokémon posee una gran energía ígnea en el interior de su pecho, que le proporciona grandes capacidades físicas al elevar su temperatura corporal cuando corre. Al correr, la energía ígnea se dispersa por todo su cuerpo, dándole aún más fuerza y velocidad, lo que puede meterlo en líos ya que puede corretear sin prestar atención a donde va. Debido a que las almohadillas de las plantas de sus pies y la nariz alcanzan altas temperaturas, es normal que a veces el suelo que pisa se queme, aunque esto también le sirve para causar quemaduras a sus rivales cuando los golpea con las patas, se dice que estás plantas de sus pies pueden atraer la buena suerte.\n"
        )
        "Sobble" -> DetallesPokemon(
            "Sobble",
            R.drawable.sobble,
            "Ruta 3",
            "Sobble está basado en un camaleón. Tiene el cuerpo de color azul, con tonos más claros en el estómago y en la parte frontal de su cara, y más oscuros en sus mofletes y en el centro de su cola enroscada. Posee unas extremidades muy pequeñas y una aleta sobre su cabeza de color amarillo.\n" +
                    "\n" +
                    "Ataca desde lejos con su destacable talento para disparar chorros de agua, tanto para disminuir el gasto de energía como para pasar desapercibido. Al tocar el agua, las marcas y el color del cuerpo de Sobble cambian, lo que le permite mimetizarse con el entorno. Como es un poco tímido, en cuanto se siente avergonzado o se pone nervioso, segrega agua como si de sudor se tratase para camuflarse. Las lágrimas de Sobble son capaces de provocar el llanto en aquellos que están a su alrededor, y se dice que su efecto es equivalente al de 100 cebollas juntas. Si se siente en peligro, se pondrá a llorar y a esparcir sus lágrimas para provocar un llanto incontrolable a todo aquel que se acerque, y aprovechará esa distracción para escapar.\n"
        )
        else -> null
    }
}