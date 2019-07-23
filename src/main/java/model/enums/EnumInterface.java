package model.enums;

import model.exception.WrongInputException;

public interface EnumInterface {
    String getName();

    default String getNameLowerCase() {
        return getName().toLowerCase();
    }

    Enum[] getValues();

    String findName(String line) throws WrongInputException;
}
