package com.example.unitconverter

import android.os.Bundle
import android.widget.ImageView.ScaleType
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Composable
fun unitConverter(){
    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meters")}
    var outputUnit by remember{ mutableStateOf("Meters")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    var conversionFactor by remember { mutableStateOf(1.00)}
    var oconversionFactor by remember { mutableStateOf(1.00)}

    fun conversion(){
        val inputDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputDouble*conversionFactor*100/oconversionFactor) /100
        outputValue = String.format("%.2f", result)
    }

    Column (

        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){//main column
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineLarge
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {/*here goes what should happen when the value of out line text field changes*/
            inputValue=it
            conversion()
        },
            label = { Text(text = "Enter the value")}
            )
Spacer(modifier = Modifier.height(20.dp))
        Row {//row
            //input box
Box {
    //input button
    Button(onClick = { iExpanded=true }) {
        Text(text = inputUnit)
        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown-Icon")
    }
    DropdownMenu(expanded =iExpanded, onDismissRequest = {iExpanded=false }) {
        DropdownMenuItem(
            text = { Text(text = "Centimeters") },
            onClick = {
                iExpanded=false
                inputUnit="Centimeters"
                conversionFactor=0.01
                conversion()
            })
        DropdownMenuItem(
            text = { Text(text = "Meters") },
            onClick = {
                iExpanded=false
                inputUnit="Meters"
                conversionFactor=1.0
                conversion()
            })
        DropdownMenuItem(
            text = { Text(text = "Feet") },
            onClick = {
                iExpanded=false
                inputUnit="Feet"
                conversionFactor=0.3048
                conversion()
            })
        DropdownMenuItem(
            text = { Text(text = "Millimeters") },
            onClick = {
                iExpanded=false
                inputUnit="Millimeters"
                conversionFactor= 0.001
                conversion()
            })

    }
}
            //output box
            Box{
                Button(onClick = { oExpanded=true }, modifier = Modifier.padding(start = 30.dp)) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown-Icon")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {  oExpanded=false
                            outputUnit="Centimeters"
                            oconversionFactor=0.01
                            conversion() })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Meters"
                            oconversionFactor=1.0
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oconversionFactor=0.3048
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimeters"
                            oconversionFactor=0.001
                            conversion()
                        })
                }
            }

}
Spacer(modifier = Modifier.height(16.dp))
        Text("Results : ${outputValue} ${outputUnit}" ,
            style = MaterialTheme.typography.headlineMedium
            )


        }
        

    }





@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}
