package com.example.tugasraihanputrawiramdani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tugasraihanputrawiramdani.ui.theme.TugasraihanputrawiramdaniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasraihanputrawiramdaniTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { MyTopAppBar(navController) },
                    bottomBar = { MyBottomNavigation(navController) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(text = when (currentRoute) {
                "home" -> "Petani & Alat Pertanian"
                "crops" -> "Alat Pertanian"
                "about" -> "About"
                "detail/{itemName}" -> "Detail Informasi"
                else -> "Aplikasi Pertanian"
            }, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            if (currentRoute?.startsWith("detail") == true) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))  // Green color
    )
}

@Composable
fun MyBottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Crops,
        BottomNavItem.About
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            NavigationBarItem(
                icon = {},
                label = { Text(text = it.title, fontSize = 14.sp, fontWeight = FontWeight.Bold) },
                selected = currentRoute == it.screen_route,
                onClick = {
                    navController.navigate(it.screen_route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(var title: String, var screen_route: String, var icon: Int) {
    object Home : BottomNavItem("I", "home", R.drawable.ic_launcher_foreground)
    object Crops : BottomNavItem("II", "crops", R.drawable.ic_launcher_foreground)
    object About : BottomNavItem("III", "about", R.drawable.ic_launcher_foreground)
}

@Composable
fun HomeScreen(navController: NavHostController) {
    AgricultureContent(
        navController = navController,
        crops = listOf("windah", "arap", "upi", "alfahri", "manca", "tyson", "shroud", "hansen", "jefri", "fadil"),
        tools = listOf("Traktor", "Cangkul", "Irigasi", "Hand Sprayer", "Sekop", "Gunting Rumput", "Mesin Pemotong Padi", "Penggiling Padi", "Penyemprotan Pestisida", "Kontainer Penyimpanan")
    )
}

@Composable
fun CropsScreen(navController: NavHostController) {
    CropGrid(
        navController = navController,
        crops = listOf("Traktor", "Cangkul", "Irigasi", "Hand Sprayer", "Sekop", "Gunting Rumput", "Mesin Pemotong Padi", "Penggiling Padi", "Penyemprotan Pestisida", "Kontainer Penyimpanan")
    )
}

@Composable
fun AboutScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .height(300.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Profile Details
            Text(
                text = "Profil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Raihan Putra Wiramdani\n" +
                        "raihanpw53@gmail.com\n" +
                        "Politeknik Negeri Sriwijaya\n" +
                        "Teknik Komputer",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Versi: 10010001110011000 (masih beta cuy)\nPengembang: Raihan Putra Wiramdani",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("crops") { CropsScreen(navController) }
        composable("about") { AboutScreen() }
        composable("detail/{itemName}") { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName")
            DetailScreen(itemName = itemName ?: "Unknown")
        }
    }
}

@Composable
fun DetailScreen(itemName: String) {
    val descriptionMap = mapOf(
        "windah" to "youtuber bocil kematian.",
        "arap" to "gamers ganteng idaman",
        "upi" to "MIAWWWWWWWWWWW",
        "alfahri" to "peminat nomor 1 vtuber abu-abu.",
        "manca" to "youtuber lucu gara gara text.",
        "tyson" to "thats my name.",
        "shroud" to "professional gamer and streamer known for his mechanical abilities in games.",
        "hansen" to "haiyaa lu olang aa.",
        "jefri" to "absurdités de l’existence.",
        "fadil" to "halo gays.",
        "Traktor" to "Traktor adalah mesin yang digunakan untuk mempermudah pekerjaan pertanian, seperti membajak tanah, menggali lubang, dan menanam benih. Dengan tenaga mesin, traktor mengurangi ketergantungan pada tenaga manusia dan mempercepat proses pertanian.",
        "Cangkul" to "Cangkul adalah alat pertanian tradisional yang digunakan untuk menggali, mengolah, atau meratakan tanah. Cangkul sering digunakan untuk menyiangi rumput, menggali parit, dan membalik tanah agar lebih gembur.",
        "Irigasi" to "Irigasi adalah sistem pengairan yang digunakan untuk menyediakan air secara terkontrol untuk tanaman. Dengan irigasi, petani dapat memastikan tanaman mendapatkan air yang cukup meskipun dalam kondisi cuaca yang tidak mendukung.",
        "Hand Sprayer" to "Hand sprayer adalah alat penyemprot yang digunakan untuk menyemprotkan pestisida, herbisida, atau pupuk cair ke tanaman. Alat ini berbentuk portable dan mudah digunakan untuk pengendalian hama dan penyakit tanaman.",
        "Sekop" to "Sekop adalah alat dengan daun lebar yang digunakan untuk menggali atau memindahkan tanah, pasir, atau material lainnya. Sekop sangat berguna dalam kegiatan pengolahan tanah, seperti menggali lubang atau memindahkan tanaman.",
        "Gunting Rumput" to "Gunting rumput adalah alat yang digunakan untuk memotong rumput atau tanaman kecil lainnya dengan mudah. Gunting rumput sangat efisien untuk membersihkan lahan pertanian atau pekarangan dari tumbuhan liar.",
        "Mesin Pemotong Padi" to "Mesin pemotong padi, atau combine harvester, digunakan untuk memanen padi secara efisien. Mesin ini dapat memotong, merontokkan, dan mengumpulkan padi dalam satu langkah, menghemat waktu dan tenaga kerja dalam proses panen.",
        "Penggiling Padi" to "Penggiling padi adalah alat yang digunakan untuk menggiling padi menjadi beras. Proses penggilingan ini menghilangkan lapisan kulit luar padi untuk menghasilkan beras yang siap konsumsi.",
        "Penyemprotan Pestisida" to "Penyemprotan pestisida adalah proses penggunaan pestisida (bahan kimia untuk mengendalikan hama dan penyakit tanaman) dengan alat penyemprot. Alat ini digunakan untuk menjaga kesehatan tanaman dengan membasmi hama atau penyakit yang dapat merusak tanaman.",
        "Kontainer Penyimpanan" to "Kontainer penyimpanan adalah wadah yang digunakan untuk menyimpan hasil pertanian, seperti beras, biji-bijian, atau hasil panen lainnya. Kontainer ini dirancang untuk menjaga kualitas produk agar tetap segar dan terhindar dari kerusakan selama proses penyimpanan atau distribusi.",
    )
    val imageRes = imageMap[itemName] ?: R.drawable.default_image
    val description = descriptionMap[itemName] ?: "Deskripsi tidak tersedia."

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = itemName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = itemName,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(16.dp)
            )

            Text(
                text = "Deskripsi: $description",
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun AgricultureContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    crops: List<String>,
    tools: List<String>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(25.dp)
    ) {
        Column(modifier.fillMaxSize()) {
            Text(text = "Petani", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            LazyRow {
                items(items = crops) { crop ->
                    RowItem(modifier = modifier, name = crop, navController = navController)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = " Alat Pertanian",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(items = tools) { tool ->
                    ColumnItem(modifier = modifier, name = tool, navController = navController)
                }
            }
        }
    }
}

@Composable
fun CropGrid(
    navController: NavHostController,
    crops: List<String>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(crops) { crop ->
            GridItem(name = crop, navController = navController)
        }
    }
}

@Composable
fun ToolGrid(
    navController: NavHostController,
    tools: List<String>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tools) { tool ->
            GridItem(name = tool, navController = navController)
        }
    }
}

val imageMap = mapOf(
    "windah" to R.drawable.windah,
    "arap" to R.drawable.arap,
    "upi" to R.drawable.upi,
    "alfahri" to R.drawable.alfahri,
    "manca" to R.drawable.manca,
    "tyson" to R.drawable.tyson,
    "shroud" to R.drawable.shroud,
    "hansen" to R.drawable.hansen,
    "jefri" to R.drawable.jefri,
    "fadil" to R.drawable.fadil,
    "Traktor" to R.drawable.traktor,
    "Cangkul" to R.drawable.cangkul,
    "Irigasi" to R.drawable.irigasi,
    "Hand Sprayer" to R.drawable.handsprayer,
    "Sekop" to R.drawable.sekop,
    "Gunting Rumput" to R.drawable.untingrumput,
    "Mesin Pemotong Padi" to R.drawable.emotongpadi,
    "Penggiling Padi" to R.drawable.enggilingpadi,
    "Penyemprotan Pestisida" to R.drawable.enyemprotanestisida,
    "Kontainer Penyimpanan" to R.drawable.ontainerpenyimpanan,
)

@Composable
fun GridItem(name: String, navController: NavHostController) {
    val imageRes = imageMap[name] ?: R.drawable.default_image

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { navController.navigate("detail/$name") },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Menampilkan gambar
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .padding(10.dp)
            )
            // Menampilkan nama item di bawah gambar
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ColumnItem(modifier: Modifier, name: String, navController: NavHostController) {
    val imageRes = imageMap[name] ?: R.drawable.default_image
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(6f)
            .clickable { navController.navigate("detail/$name") },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .height(35.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.Transparent)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun RowItem(modifier: Modifier, name: String, navController: NavHostController) {
    val imageRes = imageMap[name] ?: R.drawable.default_image
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(60.dp)
            .aspectRatio(1.5f)
            .clickable { navController.navigate("detail/$name") },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun Pr() {
    val crops = listOf("windah", "arap", "upi", "alfahri", "manca", "tyson", "shroud", "hansen", "jefri", "fadil")
    val tools = listOf("Traktor", "Cangkul", "Irigasi", "Hand Sprayer", "Sekop", "Gunting Rumput", "Mesin Pemotong Padi", "Penggiling Padi", "Penyemprotan Pestisida", "Kontainer Penyimpanan")
    val navController = rememberNavController()
    AgricultureContent(navController = navController, crops = crops, tools = tools)
}
