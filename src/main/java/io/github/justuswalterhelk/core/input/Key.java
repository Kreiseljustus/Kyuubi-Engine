package io.github.justuswalterhelk.core.input;

public enum Key
{
    SPACE(32),
    APOSTROPHE(39),
    COMMA(44),
    MINUS(45),
    PERIOD(46),
    SLASH(47),
    ZERO(48),
    ONE(49),
    TWO(50),
    THREE(51),
    FOUR(52),
    FIVE(53),
    SIX(54),
    SEVEN(55),
    EIGHT(56),
    NINE(57),
    SEMICOLON(59),
    EQUAL(61),
    A(65),
    B(66);

    private int value;

    private Key(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

}
