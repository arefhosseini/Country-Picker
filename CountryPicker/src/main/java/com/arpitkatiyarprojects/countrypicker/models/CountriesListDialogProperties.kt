package com.arpitkatiyarprojects.countrypicker.models


/**
 * Configuration class for country list dialog.
 * @param showCountryPhoneCode Specifies whether to display the country phone code for each country.
 * @param showCountryCode Specifies whether to display the country code for each country.
 * @param headerText Text to be displayed in the header of the dialog.
 * @param searchHintText Text to be displayed in the search bar's hint.
 * @param noResultText Text to be displayed when no search results are found.
 */
data class CountriesListDialogProperties(
    val showCountryPhoneCode: Boolean = true,
    val showCountryCode: Boolean = false,
    val headerText: String? = null,
    val searchHintText: String? = null,
    val noResultText: String? = null,
)