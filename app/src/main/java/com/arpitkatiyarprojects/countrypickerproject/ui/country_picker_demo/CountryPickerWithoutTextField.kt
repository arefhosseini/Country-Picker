package com.arpitkatiyarprojects.countrypickerproject.ui.country_picker_demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arpitkatiyarprojects.countrypicker.CountryPicker
import com.arpitkatiyarprojects.countrypicker.enums.CountryListDisplayType
import com.arpitkatiyarprojects.countrypicker.models.CountriesListDialogDisplayProperties
import com.arpitkatiyarprojects.countrypicker.models.CountryDetails
import com.arpitkatiyarprojects.countrypicker.models.CountryPickerDialogTextStyles
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryDisplayProperties
import com.arpitkatiyarprojects.countrypickerproject.ui.common.CountriesListDialogSettings
import com.arpitkatiyarprojects.countrypickerproject.ui.common.CountryDetailsSectionRow
import com.arpitkatiyarprojects.countrypickerproject.ui.common.SelectedCountrySettings
import com.arpitkatiyarprojects.countrypickerproject.ui.common.SpacerHeight16
import com.arpitkatiyarprojects.countrypickerproject.ui.common.SpacerHeight8
import com.arpitkatiyarprojects.countrypickerproject.ui.common.TextSwitchRow
import com.arpitkatiyarprojects.countrypickerproject.ui.common.TitleSettingsComposable

@Composable
fun CountryPickerWithoutTextField() {
    var selectedCountryDisplayProperties by remember {
        mutableStateOf(SelectedCountryDisplayProperties(flagShape = RoundedCornerShape(4.dp)))
    }

    var countriesListDialogDisplayProperties by remember {
        mutableStateOf(
            CountriesListDialogDisplayProperties(
                flagShape = RoundedCornerShape(6.dp),
                textStyles = CountryPickerDialogTextStyles(
                    titleTextStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    searchBarHintTextStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    noSearchedCountryAvailableTextStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            )
        )
    }

    var selectedCountryState by remember {
        mutableStateOf<CountryDetails?>(null)
    }

    var showCountryListInBottomSheet by remember {
        mutableStateOf(false)
    }

    val openCountrySelectionList = remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            SpacerHeight16()
            CountryPicker(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                selectedCountryDisplayProperties = selectedCountryDisplayProperties,
                countriesListDialogDisplayProperties = countriesListDialogDisplayProperties,
                openCountrySelectionList = openCountrySelectionList,
                countryListDisplayType = if (showCountryListInBottomSheet) CountryListDisplayType.BottomSheet else CountryListDisplayType.Dialog,
            ) {
                selectedCountryState = it
            }
            CountryDetailsSectionRow(selectedCountryState)
            TitleSettingsComposable("Selected Country Settings:- ") {
                SelectedCountrySettings(selectedCountryDisplayProperties) {
                    selectedCountryDisplayProperties = it
                }
            }
            SpacerHeight8()
            TitleSettingsComposable("Countries List Dialog Settings:- ") {
                CountriesListDialogSettings(countriesListDialogDisplayProperties) {
                    countriesListDialogDisplayProperties = it
                }
            }
            SpacerHeight8()
            TextSwitchRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Show Country List in BottomSheet",
                isSwitchEnabled = showCountryListInBottomSheet
            ) {
                showCountryListInBottomSheet = it
            }
            SpacerHeight8()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable {
                        openCountrySelectionList.value = true
                    },
                text = "Open Country Selection List",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

