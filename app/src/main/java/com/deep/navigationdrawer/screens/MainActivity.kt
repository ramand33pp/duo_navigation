package com.deep.navigationdrawer.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deep.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawerTheme {
                NavigationDrawer()

            }
        }
    }
}


@Composable
fun NavigationDrawer() {

    val scope = rememberCoroutineScope()

    //Screen Size IN PX
    val displayMetrics = LocalContext.current.resources.displayMetrics

    //Screen Size IN DP
    val localConfig = LocalConfiguration.current

    //Drawer Width
    val drawerWidth = localConfig.screenWidthDp / 2

    //Drawer offset
    var currentOffset: Float

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    //Navigation Drawer
    ModalNavigationDrawer(drawerState = drawerState.apply {
        currentOffset = this.currentOffset
    }, scrimColor = Color.Transparent, gesturesEnabled = true,
        //Side drawer Content
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(drawerWidth.dp)
                    .fillMaxHeight()
            ) {
                SidebarUI()
            }
        }) {
        val translationX = if (currentOffset + drawerWidth < -1) 0.0
        else currentOffset + (displayMetrics.density * drawerWidth)
        Scaffold(
            modifier = Modifier
                .graphicsLayer(
                    translationX = translationX.toFloat(),
                )
                .padding(all = 5.dp)
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Image(
                    contentDescription = "drawer_icon",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clickable {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    imageVector = Icons.Default.Menu
                )

                OutlinedButton(onClick = {
                    /* nothing here */
                }, content = {
                    Text(if (!drawerState.isOpen) "Drawer Closed" else "Drawer Opened")

                })


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {

    NavigationDrawer()

}
