package com.example.gunluk.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gunluk.R
import com.example.gunluk.viewmodel.MainScreenVM
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("ConfigurationScreenWidthHeight")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainScreenVM = hiltViewModel(), navController: NavController) {

    val allData = viewModel.diaries.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var dailyText by remember { mutableStateOf("") }
    var selectedRating by remember { mutableIntStateOf(3) }
    var settingMenuShow by remember { mutableStateOf(false) }

    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formattedDate = today.format(formatter)

    Column(modifier = Modifier.fillMaxSize()) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Günlük",
                fontSize = 36.sp,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0x33FFFFFF),
                        shape = CircleShape
                    )
                    .clickable { settingMenuShow = !settingMenuShow },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier
                )

                SettingsMenu(
                    navController = navController,
                    expanded = settingMenuShow,
                    onDismiss = { settingMenuShow = false }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            contentAlignment = Alignment.BottomCenter,

            ){
            FloatingActionButton(
                onClick = { showDialog = true },
                shape = CircleShape,
                containerColor =  Color(0xff9c6644),
                contentColor = Color.White,
                modifier = Modifier.size(82.dp)
            ){
                Icon(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = "",
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    }

    if (showDialog){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val dialogHeight = screenHeight * 0.80f

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .heightIn(max = dialogHeight),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xffD9B89C), Color(0xffFFE5C4)),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF896645)
                            )
                            Row (verticalAlignment = Alignment.CenterVertically){

                                ModernDropdownMenu(
                                    selectedValue = selectedRating,
                                    onValueSelected = { newValue ->
                                        selectedRating = newValue
                                    }
                                )

                                TextButton(
                                    onClick = {
                                        showDialog = false
                                        dailyText = ""
                                   },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color(0xff5C3A21),
                                        containerColor = Color.Transparent
                                    )
                                ) {
                                    Text("Kaydetme")
                                }
                                Spacer(modifier = Modifier.width(3.dp))
                                TextButton(
                                    onClick = { /*kaydet*/ },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color(0xff5C3A21),
                                        containerColor = Color.Transparent
                                    )
                                ) {
                                    Text("Kaydet")
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                        ) {
                            TextField(
                                value = dailyText,
                                onValueChange = {dailyText = it},
                                placeholder = {Text("Yazın...")},
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedTextColor = Color(0xFF82482A),
                                    unfocusedTextColor = Color(0xFF82482A)
                                ),
                                modifier = Modifier.fillMaxSize(),
                                singleLine = false
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ModernDropdownMenu(
    selectedValue: Int = 3,
    onValueSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var currentValue by remember { mutableIntStateOf(selectedValue) }

    Box {

        Row(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFB14A18),
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = currentValue.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xff5C3A21)
            )

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = Color(0xFF995028),
                modifier = Modifier.size(16.dp)
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface,)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    RoundedCornerShape(12.dp)
                )
        ) {

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                (1..5).forEach { number ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (number == currentValue) {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                } else {
                                    Color.Transparent
                                },
                                shape = CircleShape
                            )
                            .border(
                                width = if (number == currentValue) 2.dp else 1.dp,
                                color = if (number == currentValue) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                },
                                shape = CircleShape
                            )
                            .clickable {
                                currentValue = number
                                onValueSelected(number)
                                expanded = false
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = number.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (number == currentValue) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                            fontWeight = if (number == currentValue) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsMenu(
    navController: NavController,
    expanded: Boolean,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "İstatistikler",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // İstatistikler sayfasına git
                        onDismiss()
                    }
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Ayarlar",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Ayarlar sayfasına git
                        onDismiss()
                    }
                    .padding(vertical = 8.dp)
            )
        }
    }
}
