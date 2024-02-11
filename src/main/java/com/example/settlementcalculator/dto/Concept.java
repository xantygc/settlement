package com.example.settlementcalculator.dto;

import java.util.List;

public record Concept(String name, String formula, List<Variable> variables)
{
}
