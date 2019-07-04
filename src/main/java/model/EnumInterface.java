package model;

public interface EnumInterface {
    String getName();

    default String getNameLowerCase() {
        return getName().toLowerCase();
    }

    Enum[] getValues();

    String findName(String line) throws WrongInputException;
    //public List<String> getListName();
}
