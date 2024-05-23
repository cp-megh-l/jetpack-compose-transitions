package com.example.sharedelementtransitionsample.ui.screen.shoes

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.sharedelementtransitionsample.data.Constants
import com.example.sharedelementtransitionsample.model.Shoe
import kotlin.math.absoluteValue

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun LazyItemScope.ShoesListView(
    navController: NavController,
    shoesList: List<Shoe>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val pagerState = rememberPagerState(pageCount = { shoesList.size })

    HorizontalPager(state = pagerState, modifier = Modifier.padding(vertical = 8.dp)) { page ->
        val currentPageOffset =
            (pagerState.currentPage + pagerState.currentPageOffsetFraction - page).coerceIn(-1f, 1f)


        val shoeRotation = lerp(-45f, 0f, 1f - currentPageOffset)
        val shoeTranslationX = lerp(150f, 0f, 1f - currentPageOffset)
        val shoesAlpha = lerp(0f, 1f, 1f - currentPageOffset)
        val shoesOffsetX = lerp(30f, 0f, 1f - currentPageOffset)

        val pageOffset = (
                (pagerState.currentPage - page) + pagerState
                    .currentPageOffsetFraction
                )
        val cardAlpha = lerp(
            start = 0.4f,
            stop = 1f,
            1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
        )
        val cardRotationY = lerp(
            start = 0f,
            stop = 40f,
            pageOffset.coerceIn(-1f, 1f),
        )
        val cardScale = lerp(
            start = 0.5f,
            stop = 1f,
            1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
        )

        ShoeItemView(
            shoe = shoesList[page],
            cardScale = cardScale,
            shoeRotation = shoeRotation,
            shoeTranslationX = shoeTranslationX,
            shoesAlpha = shoesAlpha,
            shoesOffsetX = shoesOffsetX,
            cardAlpha = cardAlpha,
            cardRotationY = cardRotationY,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            page = page
        ) {
            navController.navigate("shoe_detail/${page}")
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun LazyItemScope.ShoeItemView(
    shoe: Shoe,
    cardScale: Float,
    shoeRotation: Float,
    shoeTranslationX: Float,
    shoesAlpha: Float,
    shoesOffsetX: Float,
    cardAlpha: Float,
    cardRotationY: Float,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    page: Int,
    onClick: () -> Unit
) {
    with(sharedTransitionScope) {

        val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
            keyframes {
                durationMillis = 1000
                initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                targetBounds at 1000
            }
        }

        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        rotationY = cardRotationY
                        alpha = cardAlpha
                        cameraDistance = 8 * density
                        scaleX = cardScale
                        scaleY = cardScale
                    }
                    .aspectRatio(0.8f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(shoe.color)
                        .sharedElement(
                            rememberSharedContentState(key = "${Constants.KEY_BACKGROUND}-$page"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = boundsTransform
                        )
                )

                Text(
                    text = shoe.name,
                    style =
                    MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                        .sharedElement(
                            rememberSharedContentState(key = "${Constants.KEY_SHOE_TITLE}-$page"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = boundsTransform
                        )
                )

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .sharedElement(
                            rememberSharedContentState(key = "${Constants.KEY_FAVOURITE_ICON}-$page"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = boundsTransform
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Details",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }

            Image(
                painter = painterResource(id = shoe.image),
                contentDescription = "Shoe Image",
                modifier =
                Modifier
                    .fillParentMaxWidth()
                    .zIndex(1f)
                    .graphicsLayer {
                        rotationZ = shoeRotation
                        translationX = shoeTranslationX
                        alpha = shoesAlpha
                    }
                    .offset(x = shoesOffsetX.dp, y = 0.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "${Constants.KEY_SHOE_IMAGE}-$page"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = boundsTransform
                    )
            )
        }
    }
}

fun lerp(start: Float, stop: Float, amount: Float): Float {
    return start + (stop - start) * amount
}