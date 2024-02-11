package com.example.settlementcalculator.dto;

import java.util.List;

public record Calculation(String formula, List<Variable> variables)
{

}
