package gondai.tutorial.mvvmcomposebptracker.screens.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import gondai.tutorial.mvvmcomposebptracker.screens.Index


@Composable
fun HistoryScreen(navController: NavController, model: HistoryViewModel = hiltViewModel()) {
    val state by model.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(Index.History.label)
            },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Index.NewEntry.route) {
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Rounded.AddCircle, contentDescription = null)
                    }

                })
        },


        ) {

        when (state) {
            is HistoryState.Loading ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            is HistoryState.Success -> {
                val result = (state as HistoryState.Success)
                if (result.readings.isEmpty())
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Please make your very first reading.")
                    }
                else
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state is HistoryState.Success)
                            items((state as HistoryState.Success).readings) { reading ->
                                CustomListTile(reading = reading)
                            }
                    }
            }
            is HistoryState.Failed -> {
                val result = (state as HistoryState.Failed)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(result.message!!)
                }
            }
        }
    }

}

@Composable
fun CustomListTile(reading: Reading) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f)
            .padding(all = 10.dp)
    ) {
        val (leading, trailing, title) = createRefs()
        Text(
            style = TextStyle.Default.copy(
                color = androidx.compose.ui.graphics.Color.Red,
                fontWeight = FontWeight.W500,
            ),
            text = "${reading.sys}",
            modifier = Modifier
                .constrainAs(leading) {
                    //top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .scale(1.5f)
        )

        Text("/ ${reading.dia}",
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(leading.end)
                    top.linkTo(leading.top)
                }
                .padding(start = 10.dp)
        )

       Column (modifier = Modifier.constrainAs(trailing) {
           end.linkTo(parent.end)
           top.linkTo(parent.top)
           bottom.linkTo(parent.bottom)
       },
           horizontalAlignment=Alignment.End,
       ){
           Text("${reading.dateCreated}",
           style = TextStyle.Default.copy(fontSize = 12.sp,fontWeight = FontWeight.W500))
           Row(
               modifier = Modifier
                   .fillMaxWidth(0.35f),
               horizontalArrangement = Arrangement.SpaceBetween,


           ) {
               Column(
                   modifier = Modifier.fillMaxHeight(0.89f),
                   verticalArrangement = Arrangement.SpaceBetween,
               ) {
                   Text("Pulse", style = TextStyle.Default.copy(fontSize = 11.sp))
                   Text("${reading.pulse}", style = TextStyle.Default.copy(fontSize = 11.sp))
               }
               Column(
                   modifier = Modifier.fillMaxHeight(0.89f),
                   verticalArrangement = Arrangement.SpaceBetween,
               ){
                   Text("Weight", style = TextStyle.Default.copy(fontSize = 11.sp))
                   Text("${reading.weight?:"-"}", style = TextStyle.Default.copy(fontSize = 11.sp))
               }
               Column(
                   modifier = Modifier.fillMaxHeight(0.89f),
                   verticalArrangement = Arrangement.SpaceBetween,
               ){
                   Text("SpO2", style = TextStyle.Default.copy(fontSize = 11.sp))
                   Text("${reading.spO2?:"-"}", style = TextStyle.Default.copy(fontSize = 11.sp))
               }
           }
       }

    }
}