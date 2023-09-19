package ru.example.scout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.example.scout.databinding.FragmentCalculatorBinding

@AndroidEntryPoint
class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Calculate.setOnClickListener() {
            var RemainderTank = binding.tRemainderTank.text?.ifEmpty { "0" }.toString()//остаток в опрыскивателе
            var ConsumptionRate = binding.tConsumptionRate.text?.ifEmpty { "0" }.toString()//норма расхода на Га
            var NeedsProcessed = binding.tNeedsProcessed.text?.ifEmpty { "0" }.toString()//нужно обработать
            var VolumeTank = binding.tVolumeTank.text?.ifEmpty { "0" }.toString()// Объем бака

            var NeedRefuel = CalculationOfDrugs().CalculationRefueling(//нужно дозаправить л.
                ConsumptionRate.toInt(),
                NeedsProcessed.toInt(),
                RemainderTank.toInt()
            )
            NeedRefuel = minOf(NeedRefuel,VolumeTank.toInt())

            var Refueling = CalculationOfDrugs().RefuelSprinklersHa(//можно обработать дозаправкой
                ConsumptionRate.toInt(),
                NeedRefuel
            )
            binding.tNeedRefuel.setText(NeedRefuel.toString())
            binding.tRefueling.setText(Refueling.toString())
        }
    }
}
