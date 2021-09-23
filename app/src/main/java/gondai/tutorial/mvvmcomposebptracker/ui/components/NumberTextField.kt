package gondai.tutorial.mvvmcomposebptracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun NumberTextField(value: String, name:String, func: (i: String) -> Unit) {
    OutlinedTextField(

        value,
        trailingIcon = {
            Icon(Icons.Rounded.CheckCircle, contentDescription = null)
        },

        onValueChange = {
            func(it)

        },

        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Text(name) },
        textStyle = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Medium),
    )
}