package model;

public enum Source implements EnumInterface {
    //SOURCE
    HEAD_HUNTER("hh.ru"),
    AVITO("avito.ru"),
    BK("в контакте"),
    RABOTA_RU("работа.ру"),
    MORE("другое"),
    BRING_A_FRIEND("привел друг");

    private String name;

    Source(String name) {
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
        for (Source one : values()) {
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
