package com.grey.ym.lunchtray.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.grey.ym.lunchtray.R
import com.grey.ym.lunchtray.data.DataSource
import com.grey.ym.lunchtray.model.MenuItem
import com.grey.ym.lunchtray.model.MenuItem.AccompanimentItem

@Composable
fun AccompanimentMenuScreen(
    options: List<AccompanimentItem>,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (AccompanimentItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun AccompanimentMenuPreview() {
    AccompanimentMenuScreen(
        options = DataSource.accompanimentMenuItems,
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}