package com.dice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class DiceService {

    private static final String rollWithModifierMessage = "Rolling d%s. Result: %s + %s = %s";
    private static final String rollMessage = "%sd%s =  %s";
    private static final String rollDiceRegex = "\\d+[d|D|к|К]\\d+";
    private static final String numberOfRollsRegex = "\\d+(?=\\w+\\d)";
    private static final String diceToRollRegex = "(?<=[\\d.+]\\w)\\d+";


    public void parseStringToActions(String request) {
        var splitedString = request.split("[-+*/]");
    }

    public String rollDice(String[] arguments) {
        var convertedString = String.join("", arguments);
        var rollPattern = Pattern.compile(rollDiceRegex);
        var rollMatcher = rollPattern.matcher(convertedString);
        parseStringToActions(convertedString);
        rollMatcher.find();
        return roll(rollMatcher.group());
    }

    public String roll(String rollRequest) {
        var numberOfDiceMatcher = Pattern.compile(numberOfRollsRegex).matcher(rollRequest);
        var diceToRollMatcher = Pattern.compile(diceToRollRegex).matcher(rollRequest);
        numberOfDiceMatcher.find();
        diceToRollMatcher.find();
        String dicesToRoll = numberOfDiceMatcher.group();
        String diceSides = diceToRollMatcher.group();
        Integer result = 0;
        for (int counter = 0; counter < Integer.parseInt(dicesToRoll); counter++) {
            result += rollDice(Integer.parseInt(diceSides));
        }
        return String.format(rollMessage, dicesToRoll, diceSides, result);
    }

    public Integer rollDice(Integer sides) {
        var random = new Random();
        return random.nextInt(sides);
    }

    public String rollDiceWithModifier(Integer sides, Integer modifier) {
        var rollResult = rollDice(sides);
        var sum = rollResult += modifier;
        return String.format(rollWithModifierMessage, sides, rollResult, modifier, sum);
    }
}
