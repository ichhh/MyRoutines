package com.example.MyRoutine2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MyRoutine2.databinding.TimerFragmentBinding
import com.example.MyRoutine2.viewmodel.TimerFragmentViewModel


class TimerFragment : Fragment() {

    var countingDown: Boolean = false
    val handler = Handler()
    var timeValue: Int = 0


    private lateinit var binding: TimerFragmentBinding

    companion object {
        fun newInstance() = TimerFragment()
    }

    private lateinit var viewModel: TimerFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {



        // return inflater.inflate(R.layout.main_fragment, container, false)
        binding = TimerFragmentBinding.inflate(inflater,container,false)

        setTimer(1000)

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
                    handler.removeCallbacks(this)
                    binding.bPlayPause.text = "START"
                    countingDown = false
                } else {
                    timeValue--
                }

            }
        }



        binding.bPlayPause.setOnClickListener(View.OnClickListener {
            if (countingDown) {
                handler.removeCallbacks(runnable)
                binding.bPlayPause.text = "PAUSE"
                countingDown = false
            } else {
                handler.post(runnable)
                binding.bPlayPause.text = "START"
                countingDown = true
            }
        })




        return binding.root
    }

    private fun setTimer( time: Int) {
        timeValue = time
        binding.tvTime.text = TimeToText(time)
    }

    private fun TimeToText(time: Int = 0): String {
        if (time == 0) {
            return "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            return "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimerFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}