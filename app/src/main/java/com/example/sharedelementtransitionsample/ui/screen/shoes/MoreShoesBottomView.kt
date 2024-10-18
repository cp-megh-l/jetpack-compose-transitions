package com.example.sharedelementtransitionsample.ui.screen.shoes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharedelementtransitionsample.model.shoes.Shoe

@Composable
fun MoreShoesBottomView(shoesList: List<Shoe>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "More Shoes",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "More Shoes"
            )
        }
    }
    MoreShoesListView(shoesList = shoesList.asReversed())
}

@Composable
fun MoreShoesListView(shoesList: List<Shoe>) {
    LazyRow(contentPadding = PaddingValues(16.dp)) {
        items(shoesList.size) { index ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillParentMaxWidth(0.5f)
                    .clip(MaterialTheme.shapes.medium)
                    .border(1.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
                    .clickable { /*TODO*/ },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Image(
                    painter = painterResource(id = shoesList[index].image),
                    contentDescription = "Shoe Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Text(text = shoesList[index].name, style = MaterialTheme.typography.titleMedium)

                Text(
                    text = "$" + shoesList[index].details.price.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}