package com.example.sharedelementtransitionsample.ui.screen.movies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toIntSize
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.sharedelementtransitionsample.data.movies.MovieUtils.movies
import com.example.sharedelementtransitionsample.ui.screen.shoes.lerp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import kotlin.math.absoluteValue

@Composable
fun MoviePager(paddingValues: PaddingValues) {
    val backgroundPagerState = rememberPagerState(pageCount = { movies.size })
    val movieCardPagerState = rememberPagerState(pageCount = { movies.size })

    val scrollingFollowingPair by remember {
        derivedStateOf {
            when {
                backgroundPagerState.isScrollInProgress -> backgroundPagerState to movieCardPagerState
                movieCardPagerState.isScrollInProgress -> movieCardPagerState to backgroundPagerState
                else -> null
            }
        }
    }

    LaunchedEffect(scrollingFollowingPair) {
        scrollingFollowingPair?.let { (scrollingState, followingState) ->
            snapshotFlow { scrollingState.currentPage + scrollingState.currentPageOffsetFraction }
                .collect { pagePart ->
                    val (page, offset) = BigDecimal.valueOf(pagePart.toDouble())
                        .divideAndRemainder(BigDecimal.ONE)
                        .let { it[0].toInt() to it[1].toFloat() }

                    followingState.requestScrollToPage(page, offset)
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.TopCenter
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = backgroundPagerState
        ) { currentPage ->
            val currentPageOffset = calculatePageOffset(movieCardPagerState, currentPage)
            val translationX = lerp(30f, 0f, 1f - currentPageOffset)

            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(movies[currentPage].url),
                    contentDescription = movies[currentPage].title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { this.translationX = translationX }
                )
            }
        }

        GradientOverlay()

        MovieCardsPager(movieCardPagerState)
    }
}

@Composable
private fun MovieCardsPager(state: PagerState) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = state,
        verticalAlignment = Alignment.Bottom
    ) { currentPage ->
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(currentPage) {
            loadImageBitmap(context, coroutineScope, movies[currentPage].url) {
                imageBitmap = it.asImageBitmap()
            }
        }

        val currentPageOffset = calculatePageOffset(state, currentPage)
        MovieCard(currentPage, imageBitmap, currentPageOffset)
    }
}

@Composable
private fun MovieCard(currentPage: Int, imageBitmap: ImageBitmap?, currentPageOffset: Float) {
    val cardTranslationX = lerp(100f, 0f, 1f - currentPageOffset)
    val cardScaleX = lerp(0.8f, 1f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .graphicsLayer {
                scaleX = cardScaleX
                translationX = cardTranslationX
            }
            .background(Color.Black, shape = MaterialTheme.shapes.large)
    ) {
        imageBitmap?.let {
            ParallaxImage(imageBitmap, currentPageOffset)
        }
        MovieCardOverlay(currentPage, currentPageOffset)
    }
}

@Composable
private fun ParallaxImage(imageBitmap: ImageBitmap, currentPageOffset: Float) {
    val drawSize = IntSize(imageBitmap.width, imageBitmap.height)
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val parallaxOffset = currentPageOffset * screenWidth * 2f

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.large)
            .border(2.dp, Color.White, MaterialTheme.shapes.large)
            .graphicsLayer { translationX = lerp(10f, 0f, 1f - currentPageOffset) }
    ) {
        translate(left = parallaxOffset) {
            drawImage(
                image = imageBitmap,
                srcSize = drawSize,
                dstSize = size.toIntSize(),
            )
        }
    }
}

@Composable
private fun BoxScope.MovieCardOverlay(currentPage: Int, currentPageOffset: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(MaterialTheme.shapes.large)
            .background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                    startY = 500f,
                    endY = 1000f
                )
            )
    )

    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .offset(y = -(20).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val titleTranslationX = lerp(30f, 0f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))
        val titleAlpha = lerp(0f, 1f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))

        Text(
            text = movies[currentPage].title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .graphicsLayer {
                    translationX = titleTranslationX
                    alpha = titleAlpha
                }
        )

        val descriptionTranslationX =
            lerp(150f, 0f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))
        val descriptionAlpha = lerp(0f, 1f, 1f - currentPageOffset.absoluteValue.coerceIn(0f, 1f))

        Text(
            text = movies[currentPage].description,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .graphicsLayer {
                    translationX = descriptionTranslationX
                    alpha = descriptionAlpha
                }
        )

        Box(
            modifier = Modifier
                .padding(16.dp)
                .graphicsLayer {
                    translationX = titleTranslationX
                    alpha = titleAlpha
                }
                .background(Color.White, shape = MaterialTheme.shapes.medium)
        ) {
            Text(
                text = "Watch Now",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun GradientOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                    startY = 0f,
                    endY = 500f
                )
            )
    )
}

fun calculatePageOffset(state: PagerState, currentPage: Int): Float {
    return (state.currentPage + state.currentPageOffsetFraction - currentPage).coerceIn(-1f, 1f)
}

fun loadImageBitmap(
    context: Context,
    scope: CoroutineScope,
    imageUrl: String,
    onBitmapLoaded: (Bitmap) -> Unit
) {
    scope.launch(Dispatchers.IO) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        when (val result = loader.execute(request)) {
            is SuccessResult -> {
                (result.drawable as? BitmapDrawable)?.bitmap?.let {
                    onBitmapLoaded(it)
                }
            }

            is ErrorResult -> Log.e("TAG", "Error loading image: ${result.throwable}")
        }
    }
}