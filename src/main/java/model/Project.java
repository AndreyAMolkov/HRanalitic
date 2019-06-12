package model;

public enum Project implements EnumInterface {
    //NAME_OF_PROJECT
    TNS("ТНС"),
    YANDEX("Яндекс"),
    HARTIY("Хартия");

    private String name;

    Project(String name) {
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
        for (Project one : values()) {
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
