package com.example.sharedelementtransitionsample.ui.screen.shoes

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharedelementtransitionsample.data.shoes.ShoesConstants
import com.example.sharedelementtransitionsample.model.shoes.Shoe

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationSpecApi::class
)
@Composable
fun ShoesDetailView(
    index: Int,
    shoe: Shoe,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit = {}
) {
    with(sharedTransitionScope) {
        val textBoundsTransform = { _: Rect, _: Rect -> tween<Rect>(550) }
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = shoe.name,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.sharedElement(
                                rememberSharedContentState(key = "${ShoesConstants.KEY_SHOE_TITLE}-$index"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = textBoundsTransform
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier.sharedElement(
                                rememberSharedContentState(key = "${ShoesConstants.KEY_FAVOURITE_ICON}-$index"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = textBoundsTransform
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    modifier = Modifier,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = shoe.color
                    )
                )
            },
            containerColor = shoe.color,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
                keyframes {
                    durationMillis = 1000
                    initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                    targetBounds at 1000
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(shoe.color)
                    .sharedElement(
                        rememberSharedContentState(key = "${ShoesConstants.KEY_BACKGROUND}-$index"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = boundsTransform
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = shoe.image),
                    contentDescription = "Shoe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .sharedElement(
                            rememberSharedContentState(key = "${ShoesConstants.KEY_SHOE_IMAGE}-$index"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = boundsTransform
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    ShoeDetailTextView(text = shoe.details.description)
                    ShoeDetailTextView(text = "Size: ${shoe.details.size}")
                    ShoeDetailTextView(text = "Price: $${shoe.details.price}")
                    ShoeDetailTextView(text = "Ratings: ${shoe.details.ratings}")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ShoeDetailTextView(text = "Available Colors:")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            shoe.details.availableColors.forEach { color ->
                                Box(
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(40.dp)
                                        .border(
                                            1.dp,
                                            MaterialTheme.colorScheme.onSurface,
                                            RoundedCornerShape(4.dp)
                                        )
                                        .background(color, RoundedCornerShape(4.dp))
                                        .padding(8.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoeDetailTextView(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(16.dp)
    )
}