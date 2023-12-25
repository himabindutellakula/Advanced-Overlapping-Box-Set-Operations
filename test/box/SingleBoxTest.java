package box;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SingleBox class.
 */

public class SingleBoxTest {

  private SingleBox sampleBox;

  @Before
  public void setUp() {
    sampleBox = new SingleBox(8, 9, 11, 30);
  }

  @Test
  public void testGetX() {
    assertEquals(8, sampleBox.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(9, sampleBox.getY());
  }

  @Test
  public void testGetWidth() {
    assertEquals(11, sampleBox.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(30, sampleBox.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    SingleBox b1 = new SingleBox(0, 0, 5, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight2() {
    SingleBox b2 = new SingleBox(-20, 30, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight3() {
    SingleBox b3 = new SingleBox(-20, -30, 5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    SingleBox b1 = new SingleBox(-9, 7, -15, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth2() {
    SingleBox b2 = new SingleBox(10, -1, -5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth3() {
    SingleBox b3 = new SingleBox(-80, -91, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidth() {
    SingleBox b1 = new SingleBox(0, 90, -500, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidth2() {
    SingleBox b2 = new SingleBox(-20, -30, -5, -91);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidth3() {
    SingleBox b3 = new SingleBox(-23, 50, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidth4() {
    SingleBox b3 = new SingleBox(-23, -50, 0, 0);
  }

  @Test
  public void testIsOverLapping() {
    SingleBox b = new SingleBox(0, 0, 4, 4);
    assertTrue(b.isOverlapping(new SingleBox(2, 2, 3, 3)));
    assertFalse(b.isOverlapping(new SingleBox(-1, -1, 1, 1)));

    SingleBox c = new SingleBox(-10, -1, 14, 40);
    assertTrue(c.isOverlapping(new SingleBox(-1, -1, 1, 1)));
    assertFalse(c.isOverlapping(new SingleBox(23, 1, 1, 1)));

    SingleBox a = new SingleBox(1, 1, 14, 40);
    assertFalse(a.isOverlapping(new SingleBox(-8, -1, 1, 1)));
    assertFalse(a.isOverlapping(new SingleBox(-8, -1, 2, 2)));
    assertTrue(a.isOverlapping(new SingleBox(-8, -1, 10, 9)));
  }

  @Test
  public void testIsOverLapping2() {
    //rectangles edges are touching, but not overlapping, with positive co-ordinates
    SingleBox b = new SingleBox(0, 0, 4, 4);
    assertFalse(b.isOverlapping(new SingleBox(4, 4, 4, 4)));

    //rectangles edges are touching at a corner, but not overlapping, with negative co-ordinates
    SingleBox b2 = new SingleBox(-4, -4, 4, 4);
    assertFalse(b2.isOverlapping(new SingleBox(-8, -8, 4, 4)));

    //rectangles are sharing an edge, but not overlapping, with negative co-ordinates
    SingleBox b3 = new SingleBox(-4, -4, 4, 4);
    assertFalse(b3.isOverlapping(new SingleBox(-8, -4, 4, 4)));

    //Both rectangles are same
    SingleBox b1 = new SingleBox(0, 0, 4, 4);
    assertTrue(b1.isOverlapping(new SingleBox(0, 0, 4, 4)));
  }

  @Test
  public void testGetContainedDifference() {
    SingleBox b = new SingleBox(4, 5, 5, 5);
    SingleBox b1 = new SingleBox(6, 7, 4, 5);
    List<SingleBox> results = b.getContainedDifference(b1);

    assertTrue(
        results.get(0).getX() == 4 && results.get(0).getY() == 5
            && results.get(0).getWidth() == 2 && results.get(0).getHeight() == 5);
    assertTrue(
        results.get(1).getX() == 6 && results.get(1).getY() == 5
            && results.get(1).getWidth() == 3 && results.get(1).getHeight() == 2);
  }

  @Test
  public void testGetContainedDifferenceOfSameSingleBoxes() {
    SingleBox b = new SingleBox(11, -2, 2, 3);
    SingleBox b1 = new SingleBox(11, -2, 2, 3);
    List<SingleBox> results = b.getContainedDifference(b1);
    assertEquals(0, results.size());
  }

  @Test
  public void testGetContainedDifferenceOfCompletelyOverlappingSingleBoxes() {
    SingleBox b = new SingleBox(-8, -8, 8, 8);
    SingleBox b1 = new SingleBox(-6, -6, 4, 4);
    List<SingleBox> results = b.getContainedDifference(b1);
    assertEquals(4, results.size());
    assertTrue(
        results.get(0).getX() == -8 && results.get(0).getY() == -8
            && results.get(0).getWidth() == 2 && results.get(0).getHeight() == 8);
    assertTrue(
        results.get(1).getX() == -2 && results.get(1).getY() == -8
            && results.get(1).getWidth() == 2 && results.get(1).getHeight() == 8);
    assertTrue(
        results.get(2).getX() == -6 && results.get(2).getY() == -8
            && results.get(2).getWidth() == 4 && results.get(2).getHeight() == 2);
    assertTrue(
        results.get(3).getX() == -6 && results.get(3).getY() == -2
            && results.get(3).getWidth() == 4 && results.get(3).getHeight() == 2);
  }

  @Test
  public void testGetContainedDifferenceOfNonOverlappingSingleBoxes() {
    SingleBox b = new SingleBox(-8, -8, 8, 8);
    SingleBox b1 = new SingleBox(0, 0, 4, 4);
    List<SingleBox> results = b.getContainedDifference(b1);
    assertEquals(1, results.size());
    assertTrue(
        results.get(0).getX() == -8 && results.get(0).getY() == -8
            && results.get(0).getWidth() == 8 && results.get(0).getHeight() == 8);
  }
}