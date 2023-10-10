package com.example.project4
import com.example.project4.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var billAmount: Double = 0.0
    private var tipAmount: Double = 15.0/100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.base.text = "Bill"
        binding.tipPercent.text = "15%"
        binding.displayTip.text = ""
        binding.displayTotal.text = ""
        binding.numberSlider.progress = 15
        setContentView(binding.root)
        billListener()
        slideListener()
    }

    private fun billListener() {
        binding.billAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 0) {
                    binding.displayTip.text = ""
                    binding.displayTotal.text = ""
                } else {
                    billAmount = p0.toString().toDouble()
                    calculate()
                }
            }

        })
    }

    private fun slideListener() {
        binding.numberSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tipPercent.text = "$p1%"
                tipAmount = p1.toDouble()/100
                calculate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun calculate() {
        var tip = (billAmount * tipAmount)
        var total = ((billAmount * tipAmount) + billAmount)

        binding.displayTip.text = String.format("%.2f", tip)
        binding.displayTotal.text = String.format("%.2f", total)
    }
}