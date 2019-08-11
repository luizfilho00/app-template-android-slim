package com.facom.facomemfoco.domain.boundary.resources

interface StringsProvider {
    val errorTitle: String
    val errorUnknown: String
    val errorNetwork: String
    val errorUnauthorizedLoginNow: String
    val errorFacebookDeniedPermissions: String
    val errorFacebookSdk: String
    val errorLoginFields: String
    val globalDoLogin: String
    val globalTryAgain: String
    val globalOk: String
    val globalYes: String
    val globalNo: String
    val errorNotFound: String
    val errorServerInternal: String
    val errorTimeout: String
    val waitForResult: String

    val activityRecoverPassword: String
    val activityRecoverPasswordSuccess: String
    val activityInterestTagsSaved: String

    fun errorUnprocessable(errors: String): String
}