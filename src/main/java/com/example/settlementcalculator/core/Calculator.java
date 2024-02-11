package com.example.settlementcalculator.core;

import com.example.settlementcalculator.dto.Variable;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Calculator
{
    public Double calculate(String formula, List<Variable> variables)
    {
        ExpressionBuilder builder = new ExpressionBuilder(formula);
        builder.variables(variables.stream().map(Variable::name).collect(Collectors.toSet()));
        Expression expression = builder.build();
        expression.setVariables(variables.stream().collect(Collectors.toMap(Variable::name, variable -> {
            if(variable.value()==null)
            {
                return this.calculate(variable.formula().formula(), variable.formula().variables());
            }
            return variable.value();
        })));
        return expression.evaluate();
    }
}
