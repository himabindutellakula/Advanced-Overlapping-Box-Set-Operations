package box;

import java.util.ArrayList;
import java.util.List;

/**
 * SingleBox represented as x, y, width and height.
 */
public class SingleBox {

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs a SingleBox in terms of its x and y co-ordinates which represent the lower left
   * corner of the rectangle and also its width and height.
   *
   * @param x      the x-coordinate of the rectangle to be added
   * @param y      the y-coordinate of the rectangle to be added
   * @param width  the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  public SingleBox(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Check whether the box is overlapping with the present one.
   *
   * @param tempBox the SingleBox to check whether it is overlapping with the current box
   * @return true when the boxes are overlapping and false when the boxes are not overlapping
   */
  public boolean isOverlapping(SingleBox tempBox) {
    return Math.min(this.x + this.width, tempBox.x + tempBox.width) > Math.max(this.x, tempBox.x)
        && Math.min(this.y + this.height, tempBox.y + tempBox.height) > Math.max(this.y, tempBox.y);
  }

  /**
   * Calculate the contained difference.
   *
   * @param tempBox the SingleBox to subtract from the current box
   * @return List of SingleBox which represents the subtraction of tempBox from current SingleBox
   */
  public List<SingleBox> getContainedDifference(SingleBox tempBox) {
    List<SingleBox> result = new ArrayList<>();
    if (!this.isOverlapping(tempBox)) {
      result.add(this);
      return result;
    }

    //Adding the vertical boxes first, which are left and right boxes

    //Add Left SingleBox
    if (this.x < tempBox.x) {
      result.add(new SingleBox(this.x, this.y, tempBox.x - this.x, this.height));
    }

    //Add Right SingleBox
    if (this.x + this.width > tempBox.x + tempBox.width) {
      result.add(new SingleBox(tempBox.x + tempBox.width, this.y,
          (this.x + this.width) - (tempBox.x + tempBox.width), this.height));
    }

    // Add the horizontal boxes, by excluding the overlapping parts from vertical boxes
    int newX = Math.max(this.x, tempBox.x);
    int newWidth =
        Math.min(this.x + this.width, tempBox.x + tempBox.width) - Math.max(this.x, tempBox.x);

    //Add Bottom SingleBox
    if (this.y < tempBox.y) {
      result.add(new SingleBox(newX, this.y, newWidth, tempBox.y - this.y));
    }

    //Add Top SingleBox
    if (this.y + this.height > tempBox.y + tempBox.height) {
      result.add(new SingleBox(newX, tempBox.y + tempBox.height, newWidth,
          (this.y + this.height) - (tempBox.y + tempBox.height)));
    }

    return result;
  }


  /**
   * Getter to get the value of x, which is the x co-ordinate of lower left corner of SingleBox.
   *
   * @return x value of the SingleBox
   */
  public int getX() {
    return this.x;
  }

  /**
   * Getter to get the value of y, which is the y co-ordinate of lower left corner of SingleBox.
   *
   * @return y value of the SingleBox
   */
  public int getY() {
    return this.y;
  }

  /**
   * Getter to get the value of width, which is the width of SingleBox.
   *
   * @return width value of the SingleBox
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Getter to get the value of height, which is the height of SingleBox.
   *
   * @return height value of the SingleBox
   */
  public int getHeight() {
    return this.height;
  }
}
