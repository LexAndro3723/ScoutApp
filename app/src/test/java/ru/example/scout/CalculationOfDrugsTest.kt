package ru.example.scout

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculationOfDrugsTest {

    @Test
    fun isAdditionCorrect() {
        Assert.assertEquals(2, calculationOfDrugs?.CalculationRefueling(2, 2, 2))
    }

    //@Test(expected = IllegalArgumentException::class)
    @Test
    fun isDivisionCorrect() {
        Assert.assertEquals(0.0, calculationOfDrugs?.RefuelSprinklersHa(4, 0))
    }

    private var calculationOfDrugs: CalculationOfDrugs? = null

    @Before
    fun setUp() {
        calculationOfDrugs = CalculationOfDrugs()
    }

    @After
    fun tearDown() {
        calculationOfDrugs = null
    }

}
