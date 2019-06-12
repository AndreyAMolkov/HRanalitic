package model;

public enum AnswerOfCallConversation implements EnumInterface {
    REFUSED("отказался сам"),
    INTERVIEW("приглашен на интервью"),
    SPECIFICITY_OF_WORK("специфика работы"),
    MONEY("деньги"),
    SCHEDULE("график работы"),
    LOCATION("расположение"),
    FOUND_ANOTHER_JOB("нашел другую работу"),
    NO_EXPLANATION("нет объяснения");
    private String name;

    AnswerOfCallConversation(String name) {
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
        for (AnswerOfCallConversation one : values()) {
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
