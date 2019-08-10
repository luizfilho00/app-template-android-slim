package com.facom.facomemfoco.presentation.structure.base

import android.app.Dialog
import androidx.fragment.app.Fragment
import com.facom.facomemfoco.presentation.structure.navigation.NavData
import com.facom.facomemfoco.presentation.structure.navigation.Navigator
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.showDialog
import com.facom.facomemfoco.presentation.util.viewmodels.DialogData

abstract class BaseFragment : Fragment() {

    abstract val sl: ServiceLocator
    private var dialog: Dialog? = null

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            dialog?.dismiss()
            dialog = activity?.showDialog(it)
        }
    }

    open fun onGoTo(navData: NavData?) {
        navData?.let {
            Navigator.goTo(activity, it)
        }
    }
}