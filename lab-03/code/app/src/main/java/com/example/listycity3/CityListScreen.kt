package com.example.listycity3

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun CityListScreen(
    cities: List<City>,
    modifier: Modifier = Modifier,
    onAddCity: (City) -> Unit,
    onEditCity: (City, City) -> Unit
) {
    var oldCityName by remember{mutableStateOf(" ")}
    var oldProvinceName by remember{mutableStateOf(" ")}

    var newCityName by remember {mutableStateOf(" ")}
    var newProvinceName by remember{mutableStateOf(" ")}

    var showEditCityFields by remember{mutableStateOf(false)}
    var showAddCityFields by remember{mutableStateOf(false)}

    Column(modifier = modifier){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    if (showEditCityFields){
                        showEditCityFields = false
                    }
                    showAddCityFields = !showAddCityFields
                }
            ){
                Text("+")
            }

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    if (showAddCityFields){
                        showAddCityFields = false
                    }
                    showEditCityFields = !showEditCityFields

                }
            ){
                Text("%")
            }
        }

        if (showEditCityFields) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            ) {
                OutlinedTextField(
                    value = oldCityName,
                    onValueChange = { oldCityName = it },
                    label = { Text("Old City") },
                    modifier = modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = oldProvinceName,
                    onValueChange = { oldProvinceName = it },
                    label = { Text("Old Province") },
                    modifier = modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        if (showAddCityFields || showEditCityFields){
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("New City") },
                    modifier = modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("New Province") },
                    modifier = modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Row(
                modifier = modifier
            ){
                Button(
                    onClick = {
                        if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                            if (!showEditCityFields) {
                                onAddCity(
                                    City(name = newCityName, province = newProvinceName)
                                )

                                newCityName = ""; newProvinceName = ""

                                showAddCityFields = false

                            } else {
                                onEditCity(
                                    City(
                                        name = oldCityName, province = oldProvinceName
                                    ),
                                    City(
                                        name = newCityName, province = newProvinceName
                                    )
                                )

                                oldCityName = ""; oldProvinceName = ""
                                showEditCityFields = false
                            }
                        }
                    },

                    modifier = modifier
                ) {
                    var text = "Add City"

                    if (showEditCityFields){
                        text = "Edit City"
                    }

                    Text(
                        text
                    )
                }
            }
        }

        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(cities) { index, city ->
                CityRow(city = city)

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }

}

@Composable
fun CityRow(city: City) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}



/*
@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),

            onAddCity = {}
        )
    }
}
*/