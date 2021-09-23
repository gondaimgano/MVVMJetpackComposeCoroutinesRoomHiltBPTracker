package gondai.tutorial.mvvmcomposebptracker.screens.new_entry

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gondai.tutorial.mvvmcomposebptracker.screens.Index
import gondai.tutorial.mvvmcomposebptracker.ui.components.NumberTextField


@Composable
fun NewEntryScreen(navController: NavController, model: NewEntryViewModel= hiltViewModel()) {
    val reading by model.reading.collectAsState()
    val state by model.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = Index.NewEntry.label)

            },
                navigationIcon={
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack,contentDescription = null)
                    }
                },

                actions = {
                    IconButton(onClick = {
                       model.confirm()
                    }) {
                        Icon(Icons.Rounded.Done,contentDescription = null)

                    }

                })
        },

        ) {
        when (state) {
            is NewEntryState.Confirm -> {
                AlertDialog(onDismissRequest = { model.cancel() },
                    title = { Text("About to save reading") },
                    buttons = {
                        Row {
                            TextButton(onClick = {
                                // setDisplay(false)
                                // setReadingValue(readingValue)
                                model.submit()

                            }) {
                                Text("Ok")
                            }
                            Spacer(modifier = Modifier.padding(all = 8.dp))
                            TextButton(onClick = { model.cancel() }) {
                                Text("Cancel")
                            }
                        }
                    },

                    text = {
                        Text("Continue ?")
                    }
                )
            }
            is NewEntryState.Loading -> {
                AlertDialog(onDismissRequest = {},
                    buttons = {},
                    text = { Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.padding(all=10.dp)
                        )
                    } })
            }
            is NewEntryState.Success -> {
                val result = (state as NewEntryState.Success)
                AlertDialog(onDismissRequest = {},
                    buttons = {},
                    text = { Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(result.message?:"Saved!")
                    } })
            }
            else-> Spacer(modifier = Modifier.fillMaxWidth(0.0f))
        }


        Card (modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(all=8.dp)) {

                NumberTextField(
                    value = reading.sys?.toString()?:"",
                    func = {
                        model.onSys(it)
                    },
                    name = "Sys"
                )

                Spacer(modifier = Modifier.padding(all = 8.dp))
                NumberTextField(
                    value = reading.dia?.toString()?:"",
                    func = {
                        model.onDia(it)
                    },
                    name = "Dia"
                )
                Spacer(modifier = Modifier.padding(all = 8.dp))
                NumberTextField(
                    value = reading.pulse?.toString()?:"",
                    func = {
                        model.onPulse(it)
                    },
                    name = "Pulse"
                )
                Spacer(modifier = Modifier.padding(all = 8.dp))
                NumberTextField(
                    value = reading.weight?.toString()?:"",
                    func = {
                        model.onWeight(it)
                    },
                    name = "Weight"
                )
                Spacer(modifier = Modifier.padding(all = 8.dp))
                NumberTextField(
                    value = reading.spO2?.toString()?:"",
                    func = {
                        model.onSpO2(it)
                    },
                    name = "Sp02"
                )
                Spacer(modifier = Modifier.padding(all = 8.dp))

            }
        }
    }

}

