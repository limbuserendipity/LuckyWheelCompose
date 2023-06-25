package net.limbuserendipity.luckywheelcompose.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.logic.Inventory
import net.limbuserendipity.luckywheelcompose.ui.local.LocalContentPadding
import net.limbuserendipity.luckywheelcompose.ui.local.LocalContentSpace


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowInventory(
    inventory: Inventory<Item>,
    modifier : Modifier = Modifier
){

    val itemModifier = Modifier
        .size(42.dp, 104.dp)
        .clickable { }

    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = 3
    ) {
        inventory.items.forEach { item ->
            InventoryItem(
                item = item,
                color = MaterialTheme.colors.surface.copy(alpha = 0.4f),
                border = BorderStroke(1.dp,MaterialTheme.colors.surface),
                modifier = itemModifier
            )
        }
    }
}

@Composable
fun GridInventory(
    inventory: Inventory<Item>,
){

    val itemModifier = Modifier
        .size(42.dp, 104.dp)
        .clickable { }

    val stateItems = remember{
        inventory.items.toMutableStateList()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = LocalContentPadding.current.large,
        modifier = Modifier.fillMaxWidth()
    ){
        items(inventory.items){ item ->
            InventoryItem(
                item = item,
                color = MaterialTheme.colors.surface.copy(alpha = 0.4f),
                border = BorderStroke(1.dp,MaterialTheme.colors.surface),
                modifier = itemModifier
            )
        }
    }

}

@Composable
fun InventoryItem(
    item : Item,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    paddingValues: PaddingValues = LocalContentPadding.current.medium
){
    Surface(
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        modifier = Modifier.padding(paddingValues)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = item.emoji,
                style = MaterialTheme.typography.h4,
            )

            Spacer(modifier = Modifier.size(LocalContentSpace.current.small))

            Text(
                text = "${item.count}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}