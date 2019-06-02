package assignmentPackage;

import static assignmentPackage.VecCommand.VecCommandType.FILL;
import static org.junit.jupiter.api.Assertions.*;

import assignmentPackage.VecCommand.*;
import org.junit.jupiter.api.*;
import assignmentPackage.VecFile.VecFile;
import java.awt.Point;
import javax.swing.*;
import java.awt.*;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class testComponent{
    /*
     * Test 0: Declaring Vecfile objects.
     */
    assignmentPackage.VecFile.VecFile VecFile;
    private JButton fill;
    private JButton colour;
    private ArrayList<Point2D.Double> fakeCoord;
    private File testFile;
    private File testImportFile;
    private String importFileComparison = "POLYGON 0.245000 0.125000 0.065000 0.228923 0.065000 0.021077\n" +
                 "POLYGON 0.495000 0.125000 0.375000 0.245000 0.255000 0.125000 0.375000 0.005000\n" +
                 "POLYGON 0.745000 0.125000 0.662082 0.239127 0.527918 0.195534 0.527918 0.054466 0.662082 0.010873\n" +
                 "POLYGON 0.995000 0.125000 0.935000 0.228923 0.815000 0.228923 0.755000 0.125000 0.815000 0.021077 0.935000 0.021077\n" +
                 "POLYGON 0.245000 0.375000 0.199819 0.468820 0.098297 0.491991 0.016884 0.427066 0.016884 0.322934 0.098297 0.258009 0.199819 0.281180\n" +
                 "POLYGON 0.495000 0.375000 0.459853 0.459853 0.375000 0.495000 0.290147 0.459853 0.255000 0.375000 0.290147 0.290147 0.375000 0.255000 0.459853 0.290147\n" +
                 "POLYGON 0.745000 0.375000 0.716925 0.452135 0.645838 0.493177 0.565000 0.478923 0.512237 0.416042 0.512237 0.333958 0.565000 0.271077 0.645838 0.256823 0.716925 0.297865\n" +
                 "POLYGON 0.995000 0.375000 0.972082 0.445534 0.912082 0.489127 0.837918 0.489127 0.777918 0.445534 0.755000 0.375000 0.777918 0.304466 0.837918 0.260873 0.912082 0.260873 0.972082 0.304466\n" +
                 "POLYGON 0.245000 0.625000 0.225950 0.689877 0.174850 0.734156 0.107922 0.743779 0.046417 0.715690 0.009861 0.658808 0.009861 0.591192 0.046417 0.534310 0.107922 0.506221 0.174850 0.515844 0.225950 0.560123\n" +
                 "POLYGON 0.495000 0.625000 0.478923 0.685000 0.435000 0.728923 0.375000 0.745000 0.315000 0.728923 0.271077 0.685000 0.255000 0.625000 0.271077 0.565000 0.315000 0.521077 0.375000 0.505000 0.435000 0.521077 0.478923 0.565000\n" +
                 "POLYGON 0.745000 0.625000 0.731255 0.680767 0.693168 0.723758 0.639464 0.744125 0.582447 0.737202 0.535179 0.704575 0.508487 0.653718 0.508487 0.596282 0.535179 0.545425 0.582447 0.512798 0.639464 0.505875 0.693168 0.526242 0.731255 0.569233\n" +
                 "POLYGON 0.995000 0.625000 0.983116 0.677066 0.949819 0.718820 0.901703 0.741991 0.848297 0.741991 0.800181 0.718820 0.766884 0.677066 0.755000 0.625000 0.766884 0.572934 0.800181 0.531180 0.848297 0.508009 0.901703 0.508009 0.949819 0.531180 0.983116 0.572934\n" +
                 "POLYGON 0.245000 0.875000 0.234625 0.923808 0.205296 0.964177 0.162082 0.989127 0.112457 0.994343 0.065000 0.978923 0.027918 0.945534 0.007622 0.899949 0.007622 0.850051 0.027918 0.804466 0.065000 0.771077 0.112457 0.755657 0.162082 0.760873 0.205296 0.785823 0.234625 0.826192\n" +
                 "POLYGON 0.495000 0.875000 0.485866 0.920922 0.459853 0.959853 0.420922 0.985866 0.375000 0.995000 0.329078 0.985866 0.290147 0.959853 0.264134 0.920922 0.255000 0.875000 0.264134 0.829078 0.290147 0.790147 0.329078 0.764134 0.375000 0.755000 0.420922 0.764134 0.459853 0.790147 0.485866 0.829078\n" +
                 "POLYGON 0.745000 0.875000 0.736897 0.918349 0.713681 0.955843 0.678489 0.982420 0.636072 0.994488 0.592160 0.990419 0.552684 0.970762 0.522974 0.938172 0.507043 0.897050 0.507043 0.852950 0.522974 0.811828 0.552684 0.779238 0.592160 0.759581 0.636072 0.755512 0.678489 0.767580 0.713681 0.794157 0.736897 0.831651\n" +
                 "POLYGON 0.995000 0.875000 0.987763 0.916042 0.966925 0.952135 0.935000 0.978923 0.895838 0.993177 0.854162 0.993177 0.815000 0.978923 0.783075 0.952135 0.762237 0.916042 0.755000 0.875000 0.762237 0.833958 0.783075 0.797865 0.815000 0.771077 0.854162 0.756823 0.895838 0.756823 0.935000 0.771077 0.966925 0.797865 0.987763 0.833958";
    Stack<VecCommand> VecCommandStack = new Stack<VecCommand>();
    
    @BeforeEach
    public void setUpCanvas() {
        VecFile = null;
        fakeCoord = new ArrayList<>();
        testFile = new File(System.getProperty("user.dir")+"\\"+"assignmentPackage/VecFile/testFile.vec");
        testImportFile = new File(System.getProperty("user.dir")+"\\"+"assignmentPackage/example2.vec");
        VecFile = new assignmentPackage.VecFile.VecFile(testImportFile, colour, fill);
        VecCommandStack = VecFile.getVecStack();
    }

    /*
     * Test 1: Constructing a basic CanvasComponent object.
     */
    @Test
    public void testConstruction() {
        VecFile = new assignmentPackage.VecFile.VecFile(testFile, colour, fill);
    }

    /*
     * Test 2: Testing the adding function of a file
     * @Return The file's exported co-ordinates
     */
    @Test
    public void testAdding(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.RECTANGLE, fakeCoord));
        assertEquals(VecCommandType.RECTANGLE, VecCommandStack.peek().GetType());
    }

    /*
     * Test 2: Testing the print to file function of a file (Rectangle)
     * @Return The file's exported co-ordinates
     */

    @Test
    public void testRectangleExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.RECTANGLE, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("RECTANGLE 0.1 0.2 \n", print);
    }

    /*
     * Test 3: Testing the print to file and drawing function of a file (Ellipse)
     * @Return The file's exported co-ordinates
     */
    @Test
    public void testEllipseExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.ELLIPSE, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("ELLIPSE 0.1 0.2 \n", print);
    }

    /*
     * Test 4: Testing the print to file function of a file (Polygon)
     * @Return The file's exported co-ordinates
     */
    @Test
    public void testPolygonExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.POLYGON, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("POLYGON 0.1 0.2 \n", print);
    }

    /*
     * Test 5: Testing the print to file function of a file (Line)
     * @Return The file's exported co-ordinates
     */

    @Test
    public void testLineExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.LINE, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("LINE 0.1 0.2 \n", print);
    }
    /*
     * Test 6: Testing the print to file function of a file (Plot)
     * @Return The file's exported co-ordinates
     */

    @Test
    public void testPlotExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.PLOT, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("PLOT 0.1 0.2 \n", print);
    }
    /*
     * Test 7: Testing the print to file function of a file (Pen)
     * @Return The file's exported co-ordinates
     */

    @Test
    public void testPenExport(){
        fakeCoord.add(new Point2D.Double(0.10,0.20));
        VecCommandStack.push(VecCommandFactory.GetShapeCommand(VecCommandType.PEN, fakeCoord));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("PEN 0.1 0.2 \n", print);
    }

    /*
     * Test 8: Testing the print to file function of a file (Fill ON)
     * @Return The file's exported co-ordinates
     */
    @Test
    public void testFillOnExport(){
        VecCommandStack.push(VecCommandFactory.GetColorCommand(FILL, Color.BLUE));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("FILL #0000ff\n", print);
    }
    /*
     * Test 9: Testing the print to file function of a file (Fill OFF)
     * @Return The file's exported co-ordinates
     */
    @Test
    public void testFillOffExport(){
        VecCommandStack.push(VecCommandFactory.GetColorCommand(FILL, "OFF"));
        String print = VecCommandStack.peek().PrintToFile();
        assertEquals("FILL OFF\n", print);
    }

    /*
     * Test 10: Testing the drawing functions of a file
     * @Return The file's co-ordinates
     */
    @Test
    public void testUndo(){
        //TODO
    }
}
