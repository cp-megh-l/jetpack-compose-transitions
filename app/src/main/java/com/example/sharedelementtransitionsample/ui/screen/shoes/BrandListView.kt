package com.example.sharedelementtransitionsample.ui.screen.shoes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sharedelementtransitionsample.model.shoes.Brand

@Composable
fun BrandsListView(brandsList: List<Brand>, onBrandSelected: (Brand) -> Unit = {}) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    LazyRow(contentPadding = PaddingValues(16.dp)) {
        items(brandsList.size) { index ->
            BrandItemView(brand = brandsList[index], isSelected = index == selectedIndex) {
                selectedIndex = index
                onBrandSelected(brandsList[index])
            }
        }
    }
}

@Composable
fun BrandItemView(brand: Brand, isSelected: Boolean, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
            .clickable { onClick() }) {
        Text(
            text = brand.name,
            style = if (isSelected) {
                MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}