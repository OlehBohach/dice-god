package com.dice.service.dice_action;

import com.dice.service.enums.ModifierTypeEnum;
import lombok.Data;

@Data
public class AddModifierDiceAction {
    private String firstArgument;
    private String action;
    private String secondArgument;
}
