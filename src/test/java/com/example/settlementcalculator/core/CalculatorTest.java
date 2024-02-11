package com.example.settlementcalculator.core;

import com.example.settlementcalculator.dto.Concept;
import com.example.settlementcalculator.dto.Formula;
import com.example.settlementcalculator.dto.Variable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{

    @Test
    void calculeNestedFormulas()
    {
        List<Variable> variables = new ArrayList<>();
        List<Variable> variablesFormula = new ArrayList<>();
        Variable priceFormulaVariable = new Variable("P", 10.00, null);
        variablesFormula.add(priceFormulaVariable);
        Formula priceFormula = new Formula("P*1", variablesFormula);

        Variable price = new Variable("P", null, priceFormula);
        Variable quantity = new Variable("Q", 10.00, null);
        variables.add(price);
        variables.add(quantity);
        Concept concept = new Concept("energía", "P*Q", variables);

        List<Formula> formulas = concept.variables().stream()
                .filter(variable -> {return variable.value()==null;})
                .map(Variable::formula)
                .toList();

        Calculator calculator = new Calculator();
        assertEquals(100.00, calculator.calculate(concept.formula(), variables));
    }

    @Test
    void calculeBasic()
    {
        List<Variable> variables = new ArrayList<>();
        Variable price = new Variable("P", 10.00, null);
        Variable quantity = new Variable("Q", 10.00, null);
        variables.add(price);
        variables.add(quantity);
        Concept concept = new Concept("energía", "P*Q", variables);
        Calculator calculator = new Calculator();
        assertEquals(100.00, calculator.calculate(concept.formula(), variables));
    }
}