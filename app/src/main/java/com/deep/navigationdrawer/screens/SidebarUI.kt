package com.deep.navigationdrawer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val selected: Boolean = false
)

@Composable
fun SidebarUI() {
    val menuItems = remember {
        listOf(
            MenuItem("Home", Icons.Default.Home, true),
            MenuItem("My Events", Icons.Default.Add),
            MenuItem("Tasks", Icons.Default.Search),
            MenuItem("Invite Friends", Icons.Default.Person),
            MenuItem("Settings", Icons.Default.Settings),
            MenuItem("About", Icons.Default.Info)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // User Profile Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "RD",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    "RDeep",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Navigation Menu
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            menuItems.forEach { item ->
                NavigationMenuItem(item)
            }
        }

        // Sign Out Button (positioned at bottom)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = { /* Handle sign out */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Sign Out",
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Sign Out",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun NavigationMenuItem(item: MenuItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (item.selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            },
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = if (item.selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            },
            fontWeight = if (item.selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}