package com.example.MyRoutine2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.MyRoutine2.databinding.ItemsFragmentBinding
import com.example.MyRoutine2.databinding.TimerFragmentBinding
import com.example.MyRoutine2.dialog.ItemEditDialogArgs
import com.example.MyRoutine2.model.ItemEntity
import com.example.MyRoutine2.viewmodel.ItemEditDialogViewModel
import com.example.MyRoutine2.viewmodel.ItemsFragmentViewModel
import com.example.MyRoutine2.viewmodel.TimerFragmentViewModel

class TimerFragment : Fragment() {

    var countingDown: Boolean = false
    val handler = Handler()
    var timeValue: Int = 0
    var pauseAfter = false

    var prevItemId:Long? = null
    var nextItemId:Long? = null


    private lateinit var binding: TimerFragmentBinding
    private lateinit var viewModel: TimerFragmentViewModel
    private val args: TimerFragmentArgs by navArgs()

//    companion object {
//        fun newInstance() = TimerFragment()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(TimerFragmentViewModel::class.java)

        viewModel.currentItem.observe(viewLifecycleOwner, {
            binding.tvName.setText(
                savedInstanceState?.getString(ITEM_NAME_KEY) ?: it.nameString
            )

            pauseAfter = it.pauseAfter
            setTimer(it.duration!!.toInt() )
        })

        viewModel.prevItem.observe(viewLifecycleOwner, {
            binding.tvPrevItem.setText(
//                savedInstanceState?.getString(ITEM_NAME_KEY_PREVIOUS) ?: it.nameString
            it?.nameString
            )

            if (it==null)
                binding.bPrevious.visibility = INVISIBLE
            else
                prevItemId = it.id

        })

        viewModel.nextItem.observe(viewLifecycleOwner, {
            binding.tvNextItem.setText(
                it?.nameString
//                savedInstanceState?.getString(ITEM_NAME_KEY_NEXT) ?: it.nameString
            )
            if (it==null)
                binding.bNext.visibility = INVISIBLE
                else
                    nextItemId = it.id
        })

        viewModel.getItemById(args.itemId)

        // return inflater.inflate(R.layout.main_fragment, container, false)
        binding = TimerFragmentBinding.inflate(inflater,container,false)


        val runnable = object : Runnable {
            override fun run() {
                // TextViewを更新
                TimeToText(timeValue).let {
                    binding.tvTime.text = it
                }

//                handler.postDelayed(this, 1000)
//                timeValue++

                handler.postDelayed(this, 1000)
                if (timeValue==0) {
                    stopCounting(this)
                } else {
                    timeValue--
                }

            }
        }

        binding.bPlayPause.setOnClickListener {
            if (countingDown) {
                stopCounting(runnable)
            } else {
                startCounting(runnable)
            }
        }

        binding.bNext.setOnClickListener {
            goNext()
        }

        binding.bPrevious.setOnClickListener {

            prevItemId?.let {
                val action = TimerFragmentDirections.actionTimerFragmentSelf(prevItemId!!)
                findNavController().navigate(action)
            }
        }

        startCounting(runnable)

        return binding.root
    }

    private fun startCounting(runnable: Runnable) {
        handler.post(runnable)
        binding.bPlayPause.text = getString(R.string.pause)
        countingDown = true
    }

    private fun stopCounting(runnable: Runnable) {
        handler.removeCallbacks(runnable)
        binding.bPlayPause.text = getString(R.string.start)
        countingDown = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(TimerFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun goNext() {
        nextItemId?.let {

            val action = TimerFragmentDirections.actionTimerFragmentSelf(nextItemId!!)
            findNavController().navigate(action)
        }
    }

    private fun setTimer( time: Int) {
        timeValue = time
        binding.tvTime.text = TimeToText(time)
    }

    private fun TimeToText(time: Int = 0): String {
        if (time == 0) {
            if (!pauseAfter)
                goNext()

            return "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            return "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }

}