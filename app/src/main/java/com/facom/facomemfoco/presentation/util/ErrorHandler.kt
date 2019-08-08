package com.facom.facomemfoco.presentation.util

import com.facom.facomemfoco.data.remote.client.RequestException
import com.facom.facomemfoco.presentation.util.resources.LoginAction
import com.facom.facomemfoco.presentation.util.viewmodels.DialogData
import com.facom.facomemfoco.presentation.util.viewmodels.Placeholder

class ErrorHandler constructor(
        private val strings: com.facom.facomemfoco.domain.boundary.resources.StringsProvider,
        private val logger: com.facom.facomemfoco.domain.boundary.resources.Logger,
        private val loginAction: LoginAction
) {

    fun handleError(throwable: Throwable, tryAgainAction: (() -> Unit)? = null): Placeholder {
        throwable.printStackTrace()
        return if (throwable is RequestException) {
            handleRequestException(throwable, tryAgainAction)
        } else {
            unexpectedErrorData(tryAgainAction)
        }
    }

    private fun handleRequestException(exception: RequestException, tryAgainAction: (() -> Unit)? = null): Placeholder {
        return when {
            exception.isUnprocessableEntity() -> unProcessableEntityErrorData(exception.errorMessage, tryAgainAction)
            exception.isTimeOutException() -> timeoutErrorData(tryAgainAction)
            exception.isNetworkError() -> httpErrorData(strings.errorNetwork, tryAgainAction)
            exception.isUnauthorizedError() -> unauthorizedErrorData(exception.errorMessage)
            exception.isHttpError() -> when (RequestException.HttpError.getErrorForCode(exception.errorCode)) {
                RequestException.HttpError.NOT_FOUND -> httpErrorData(strings.errorNotFound, tryAgainAction)
                RequestException.HttpError.TIMEOUT -> timeoutErrorData(tryAgainAction)
                RequestException.HttpError.INTERNAL_SERVER_ERROR -> httpErrorData(strings.errorServerInternal, tryAgainAction)
                else -> httpErrorData(
                        exception.errorMessage
                                ?: exception.message
                                ?: strings.errorUnknown, null
                )
            }
            else -> unexpectedErrorData(tryAgainAction)
        }
    }

    private fun unauthorizedErrorData(errorMessage: String?): Placeholder {
        return Placeholder.Action(errorMessage, strings.globalDoLogin, loginAction::execute)
    }

    private fun unProcessableEntityErrorData(errorMessage: String?, tryAgainAction: (() -> Unit)?): Placeholder {
        return tryAgainPlaceholder(errorMessage, tryAgainAction)
    }

    private fun httpErrorData(message: String, tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(message, tryAgainAction)
    }

    private fun timeoutErrorData(tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(strings.errorTimeout, tryAgainAction)
    }

    private fun unexpectedErrorData(tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(strings.errorUnknown, tryAgainAction)
    }

    private fun tryAgainPlaceholder(errorMessage: String?, tryAgainAction: (() -> Unit)?): Placeholder {
        return Placeholder.Action(errorMessage, strings.globalTryAgain, tryAgainAction)
    }

    fun getPlaceholder(throwable: Throwable, retryAction: (() -> Unit)?): Placeholder {
        logger.e(throwable)
        return if (throwable is RequestException) {
            handleError(throwable, retryAction)
        } else {
            getUnknownErrorPlaceholder()
        }
    }

    private fun getUnknownErrorPlaceholder(): Placeholder {
        return Placeholder.Message(getUnknownErrorMessage())
    }

    private fun getUnknownErrorMessage(): String {
        return strings.errorUnknown
    }

    fun getDialogData(
            throwable: Throwable, retryAction: (() -> Unit)?, onDismiss: (() -> Unit)? = null
    ): DialogData {
        val data = getPlaceholder(throwable, retryAction)
        return if (data.message == null) {
            DialogData.error(strings, getUnknownErrorMessage(), onDismiss = onDismiss)
        } else {
            DialogData.error(strings, data.message, data.buttonText, data.buttonAction)
        }
    }
}
