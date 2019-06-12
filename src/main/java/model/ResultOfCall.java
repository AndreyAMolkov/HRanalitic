package model;

public enum ResultOfCall implements EnumInterface {
    HUNG_UP("повесил трубку"),
    UNAVAILABLE("недоступен"),
    NO_ANSWER("не берет трубку");

    private String name;

    ResultOfCall(String name) {
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
        for (ResultOfCall one : values()) {
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
