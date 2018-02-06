package test.enmu;

public enum TestEnum {

  INSERT(1),UPDATE(2),REPLACE(3);


  private final int value;


  TestEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
