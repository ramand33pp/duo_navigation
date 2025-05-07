This is a simple Android native app built with Jetpack Compose, demonstrating how to implement a Navigation Drawer that opens and closes using Compose's ModalNavigationDrawer.

 # Features✨
Modern Android app using Jetpack Compose

Navigation Drawer implementation

Open/Close drawer using UI controls

Clean, minimal UI structure

Written in Kotlin

# Tech Stack 🛠️
Kotlin

Jetpack Compose

Material3 Design Components

## for duo navigation
add ScaleX, scaleY  as per your need

modifier = Modifier
                .graphicsLayer(
                    translationX = translationX.toFloat(),
                    scaleY = scale,
                    scaleX = scale
                )
