package com.iamtravisjsmith.starwarskmm.android.people

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamtravisjsmith.starwarskmm.entities.Person

@Composable
fun PersonRow(person: Person, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = person.name,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(
    widthDp = 400,
    showBackground = true,
)
@Composable
fun PreviewPersonRow() {
    PersonRow(Person(3, "name", "birthYear")) {

    }
}
