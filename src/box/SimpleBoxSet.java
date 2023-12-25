package box;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleBoxSet represented as boxSet,  which represents the set of non-overlapping SingleBoxes.
 */
public class SimpleBoxSet implements BoxSet {

  private List<SingleBox> boxSet;

  /**
   * Constructs a BoxSet with an empty set of SingleBoxes.
   */
  public SimpleBoxSet() {
    this.boxSet = new ArrayList<>();
  }

  /**
   * Getter to get the boxSet which is the List of SingleBox.
   *
   * @return boxSet
   */
  public List<SingleBox> getBoxSet() {
    return this.boxSet;
  }

  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }

    List<SingleBox> boxSetAfterAdding = new ArrayList<>();
    SingleBox givenBox = new SingleBox(x, y, width, height);

    if (!boxSet.isEmpty()) {
      boxSetAfterAdding = calculateContainedDifference(givenBox);
    }
    boxSetAfterAdding.add(givenBox);
    boxSet = boxSetAfterAdding;
  }

  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }

    List<SingleBox> boxSetAfterSubtracting = new ArrayList<>();
    SingleBox givenBox = new SingleBox(x, y, width, height);

    if (!boxSet.isEmpty()) {
      boxSetAfterSubtracting = calculateContainedDifference(givenBox);
      boxSet = boxSetAfterSubtracting;
    }
  }

  /**
   * Calculate the contained difference.
   *
   * @param givenBox the SingleBox to subtract from the current BoxSet
   * @return List of SingleBox which represents the subtraction of givenBox from current BoxSet
   */
  private List<SingleBox> calculateContainedDifference(SingleBox givenBox) {
    List<SingleBox> containedDifferenceBoxSet = new ArrayList<>();
    for (SingleBox eachBox : boxSet) {
      if (!(eachBox.getX() == givenBox.getX() && eachBox.getY() == givenBox.getY()
          && eachBox.getWidth() == givenBox.getWidth()
          && eachBox.getHeight() == givenBox.getHeight())) {
        List<SingleBox> differenceBoxes = eachBox.getContainedDifference(givenBox);
        containedDifferenceBoxSet.addAll(differenceBoxes);
      }
    }
    return containedDifferenceBoxSet;
  }

  @Override
  public int[][] getBoxes() {
    if (!boxSet.isEmpty()) {
      int[][] allBoxes = new int[boxSet.size()][4];
      int i = 0;
      for (SingleBox eachBox : boxSet) {
        allBoxes[i] = new int[]{eachBox.getX(), eachBox.getY(), eachBox.getWidth(),
            eachBox.getHeight()};
        i++;
      }
      return allBoxes;
    } else {
      return new int[0][];
    }
  }

  @Override
  public int size() {
    return boxSet.size();
  }
}
