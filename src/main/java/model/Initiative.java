package model;

public enum Initiative implements EnumInterface {
    //   TEMPERATURE
    WARM("сам нашел нас"),
    COLD("мы нашли");

    private String name;

    Initiative(String name) {
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
        for (Initiative one : values()) {
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
