package box;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A JUnit test class for the SimpleBoxSet class.
 */

public class SimpleBoxSetTest {

  @Test
  public void testSimpleBoxSetConstructor() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    assertEquals(0, sampleSet.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightInAdd() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(20, 3, 50, -90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightInSubtract() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.subtract(4, 13, 59, -9000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthInAdd() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(20, 3, -50, 90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthInSubtract() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.subtract(4, 13, -59, 9000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidthInAdd() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 90, -500, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidthINSubtract() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.subtract(0, 90, -500, -1);
  }

  @Test
  public void testAdd() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 4, 4);
    sampleSet.add(5, 5, 3, 3);
    sampleSet.add(2, 2, 3, 3);

    //Testing all non-overlapping rectangles addition
    SimpleBoxSet sampleSet2 = new SimpleBoxSet();
    sampleSet2.add(0, 0, 4, 4);
    sampleSet2.add(4, 4, 3, 3);
    sampleSet2.add(-2, -20, 3, 3);

    assertEquals(3, sampleSet2.size());
    assertArrayEquals(sampleSet2.getBoxes(),
        new int[][]{{0, 0, 4, 4}, {4, 4, 3, 3}, {-2, -20, 3, 3}});

  }

  @Test
  public void testAddAndSubtractCompleteOverlappingRectangles() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 4, 4);
    sampleSet.add(0, 0, 4, 4);
    assertEquals(1, sampleSet.size());
    assertArrayEquals(sampleSet.getBoxes(), new int[][]{{0, 0, 4, 4}});
    sampleSet.subtract(0, 0, 4, 4);
    assertEquals(0, sampleSet.size());
  }

  @Test
  public void testOneRectangleSubtractAnother() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 20, 10);
    sampleSet.subtract(-22, -22, 23, 23);
    assertEquals(2, sampleSet.size());
    assertArrayEquals(sampleSet.getBoxes(), new int[][]{{1, 0, 19, 10}, {0, 1, 1, 9}});
  }

  @Test
  public void testSmallRectangleFromBig() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 4, 4);
    sampleSet.subtract(1, 1, 2, 2);
    assertEquals(4, sampleSet.size());
    assertArrayEquals(sampleSet.getBoxes(),
        new int[][]{{0, 0, 1, 4}, {3, 0, 1, 4}, {1, 0, 2, 1}, {1, 3, 2, 1}});
  }

  @Test
  public void testSubtractMultipleRectanglesFromOne() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 10, 10);
    sampleSet.subtract(3, 3, 3, 3);
    assertEquals(4, sampleSet.size());
    int[][] sampleSetBoxes = {{0, 0, 3, 10}, {6, 0, 4, 10}, {3, 0, 3, 3}, {3, 6, 3, 4}};
    assertArrayEquals(sampleSet.getBoxes(), sampleSetBoxes);

    sampleSet.subtract(0, 0, 4, 2);
    assertEquals(5, sampleSet.size());
    sampleSetBoxes = new int[][]{{0, 2, 3, 8}, {6, 0, 4, 10}, {4, 0, 2, 3}, {3, 2, 1, 1},
        {3, 6, 3, 4}};
    assertArrayEquals(sampleSet.getBoxes(), sampleSetBoxes);

    sampleSet.subtract(0, 0, 6, 10);
    assertEquals(1, sampleSet.size());
    sampleSetBoxes = new int[][]{{6, 0, 4, 10}};
    assertArrayEquals(sampleSet.getBoxes(), sampleSetBoxes);
  }

  @Test
  public void testAddMultipleRectanglesToOne() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(-10, -10, 4, 4);
    sampleSet.add(-7, -10, 3, 8);
    assertEquals(2, sampleSet.size());
    assertArrayEquals(sampleSet.getBoxes(), new int[][]{{-10, -10, 3, 4}, {-7, -10, 3, 8}});
  }

  @Test
  public void testMultipleOverLappingRectangles() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(0, 0, 4, 4);
    sampleSet.add(5, 5, 3, 3);
    sampleSet.add(10, 10, 2, 2);
    sampleSet.subtract(2, 2, 4, 4);
    sampleSet.add(8, 8, 3, 3);
    sampleSet.subtract(1, 1, 10, 10);
    assertEquals(5, sampleSet.size());
  }

  @Test
  public void testSubtractNonOverLappingRectangles() {
    SimpleBoxSet sampleSet = new SimpleBoxSet();
    sampleSet.add(10, 10, 4, 4);
    sampleSet.add(5, 5, 3, 3);
    sampleSet.subtract(-2, -2, 1, 1);
    sampleSet.subtract(-9, -76, 3, 3);
    assertEquals(2, sampleSet.size());
    assertArrayEquals(sampleSet.getBoxes(), new int[][]{{10, 10, 4, 4}, {5, 5, 3, 3}});
  }
}








