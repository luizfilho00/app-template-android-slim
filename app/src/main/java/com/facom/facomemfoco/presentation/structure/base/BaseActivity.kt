package com.facom.facomemfoco.presentation.structure.base

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.facom.facomemfoco.presentation.structure.navigation.NavData
import com.facom.facomemfoco.presentation.structure.navigation.Navigator
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.observeEvent
import com.facom.facomemfoco.presentation.util.extensions.shortToast
import com.facom.facomemfoco.presentation.util.extensions.showDialog
import com.facom.facomemfoco.presentation.util.viewmodels.DialogData

abstract class BaseActivity : AppCompatActivity() {

    abstract val sl: ServiceLocator
    abstract val baseViewModel: BaseViewModel

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeUi()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    open fun subscribeUi() {
        baseViewModel.dialog.observeEvent(this, ::onNextDialog)
        baseViewModel.goTo.observeEvent(this, ::onNextNavigation)
        baseViewModel.toast.observeEvent(this, ::onNextToast)
    }

    private fun onNextToast(text: String?) {
        text?.let {
            shortToast(it)
        }
    }

    private fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { showDialog(it) }
    }

    private fun onNextNavigation(navData: NavData?) {
        navData?.let {
            Navigator.goTo(this, it)
        }
    }
}