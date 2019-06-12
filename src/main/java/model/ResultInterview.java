package model;

public enum ResultInterview implements EnumInterface {
    TRAINING("приглашен на треннинг"),
    SPECIFICITY_OF_WORK("специфика работы"),
    MONEY("деньги"),
    SCHEDULE("график работы"),
    LOCATION("расположение"),
    FOUND_ANOTHER_JOB("нашел другую работу"),
    OUR_INITIATIVE("отказ, наша инициатива"),
    RESULT_OF_TESTING("результат тестирования"),
    NO_EXPLANATION("нет объяснения");

    private String name;

    ResultInterview(String name) {
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
    public String findName(String line) throws WrongInputExeption {
        String result = null;
        for (ResultInterview one : values()) {
            if (one.getName().contains(line.trim())) {
                if (result != null) {
                    throw new WrongInputExeption(Constants.WRONG_FORMAT_ENUM + " нашел - " + result + ", " + one.getName());
                }
                result = one.getName();
            }
        }
        return result;
    }
}
