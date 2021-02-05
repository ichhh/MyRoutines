package com.example.MyRoutine2.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.MyRoutine2.*
import com.example.MyRoutine2.databinding.ItemEditDialogBinding
import com.example.MyRoutine2.viewmodel.ItemEditDialogViewModel
import com.example.MyRoutine2.viewmodel.ScoreDialogViewModel


class ScoreDialog : DialogFragment() {

    private lateinit var viewModel: ScoreDialogViewModel
    private val args: ItemEditDialogArgs by navArgs()
    private lateinit var binding: ItemEditDialogBinding





}

//onAttach
//onCreate
//onCreateDialog
//onCreateView
//onActivityCreated
//onStart
//onResume
