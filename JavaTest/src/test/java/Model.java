public class Model {



  private double minScore;
  private double maxScore;

  private int strategy;
  private String message;


  public Model(double minScore, double maxScore, int strategy, String message) {
    this.minScore = minScore;
    this.maxScore = maxScore;
    this.strategy = strategy;
    this.message = message;
  }

  public double getMinScore() {
    return minScore;
  }

  public void setMinScore(double minScore) {
    this.minScore = minScore;
  }

  public double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(double maxScore) {
    this.maxScore = maxScore;
  }

  public int getStrategy() {
    return strategy;
  }

  public void setStrategy(int strategy) {
    this.strategy = strategy;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
