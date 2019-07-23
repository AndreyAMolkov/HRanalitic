package model.enums;

import model.constats.Constants;
import model.exception.WrongInputException;

public enum ResultOfAdaptation implements EnumInterface {
    PASSED("успешно"),
    OT_PASSED("не устроен"),
    NEWER_COME("не пришел совсем"),
    DID_NOT_COME_ON_2_DAY("не пришел на 2 день"),
    DID_NOT_COME_ON_3_DAY("не пришел на 3 день"),
    DID_NOT_PASS_QUALIFICATION("не сдал атестацию"),
    DID_NOT_COME_ON_EMPLOYMENT("не пришел на трудоустройство");

    private String name;

    ResultOfAdaptation(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Enum[] getValues() {
        return values();
    }

    @Override
    public String findName(String line) throws WrongInputException {
        String result = null;
        for (ResultOfAdaptation one : values()) {
            if (one.getName().contains(line.trim())) {
                if (result != null) {
                    throw new WrongInputException(Constants.WRONG_FORMAT_ENUM + " нашел - " + result + ", " + one.getName());
                }
                result = one.getName();
            }
        }
        return result;
    }
}
