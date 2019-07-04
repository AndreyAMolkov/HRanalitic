package model;

public enum EstimateOfHR implements EnumInterface {
    ONE("1"),
    TWO("2"),
    THREE("3");

    private String name;

    EstimateOfHR(String name) {
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
        for (EstimateOfHR one : values()) {
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
