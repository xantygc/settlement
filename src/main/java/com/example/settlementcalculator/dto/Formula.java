package com.example.settlementcalculator.dto;

import java.util.List;

public record Formula(String formula, List<Variable> variables){}
