package com.umutcansahin.unit2pathway2limonata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.umutcansahin.unit2pathway2limonata.ui.theme.Unit2Pathway2LimonataTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit2Pathway2LimonataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1)}
    var squeezeCount by remember { mutableStateOf(0)}

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(currentStep){
            1 -> {
               LemonTextAndImage(
                   textLabelResourceId = R.string.tree_description,
                   drawableResourceId = R.drawable.lemon_tree,
                   contentDescriptionResourceId = R.string.Lemon_tree,
                   onImageClick = {
                       currentStep = 2
                       squeezeCount = (2..4).random()
                   })
            }
            2-> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.tapping_description,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.Lemon,
                    onImageClick = {
                        squeezeCount --
                        if (squeezeCount == 0){
                            currentStep = 3
                        }
                    })
            }
            3-> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.drink_description,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.Glass_of_lemonade,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4-> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.empty_glass_description,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.Empty_glass,
                    onImageClick = {
                        currentStep = 1
                    })

            }
        }

    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(
            modifier = modifier.height(16.dp)
        )
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Unit2Pathway2LimonataTheme {
        LemonadeApp()
    }
}