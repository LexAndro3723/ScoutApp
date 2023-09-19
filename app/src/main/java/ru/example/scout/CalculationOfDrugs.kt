package ru.example.scout

class CalculationOfDrugs {

    fun CalculationRefueling(ConsumptionRate: Int, NeedProcessing: Int, SolutionResidue: Int): Int {
        return maxOf((ConsumptionRate * NeedProcessing - SolutionResidue), 0)
    }

    fun RefuelSprinklersHa(ConsumptionRate: Int, RefuelSprayer: Int): Double {
        if (ConsumptionRate == 0) return 0.0
        else return (RefuelSprayer / ConsumptionRate).toDouble()
    }
}



