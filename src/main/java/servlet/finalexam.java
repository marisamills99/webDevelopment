/** *****************************************************************
    twoButtons.java   servlet example

        @author Jeff Offutt
********************************************************************* */

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
//import org.apache.commons.math;
 
//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
// twoButtons class
// twoButtons class
// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
// private void PrintHead (PrintWriter out) --> Prints the HTML head section
// private void PrintBody (PrintWriter out) --> Prints the HTML body with
//              the form. Fields are blank.
// private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
//              Prints the HTML body with the form.
//              Fields are filled from the parameters.
// private void PrintTail (PrintWriter out) --> Prints the HTML bottom
//***********************************************************************
@WebServlet(
        name = "finalexam",
        urlPatterns = {"/finalexam"}
    )

public class finalexam extends HttpServlet
{
List<Double> array = new ArrayList<Double>();
// Location of servlet.
//static String Domain  = "cs.gmu.edu:8443";
//static String Path    = "/offutt/servlet/";
//static String Servlet = "finalexam";

// Button labels
static String OperationAdd = "Add";
static String OperationStd = "Print Standard Deviation";
static String OperationMean = "Print Mean";
static String OperationMode = "Print Mode";
static String OperationMedian = "Print Median";
static String Operation1 = "Add to List";
static String Reset = "Reset";
// Other strings.
static String Style ="https://mason.gmu.edu/~mmills20/style.css";

/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts the values in the form, performs the operation
 *  indicated by the submit button, and sends the results
 *  back to the client.
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
   Double rslt   = new Double(0.0);
   Double inputVal = new Double(0.0);
   Double rhsVal = new Double(0.0);
   String operation = request.getParameter("Operation");
   String lhsStr = request.getParameter("LHS");
   String rhsStr = request.getParameter("RHS");
   String rsltlist = new String("");

   /*DescriptiveStatistics stats = new DescriptiveStatistics();

   // Add the data from the array
   for( int i = 0; i < array.size(); i++) {
         stats.addValue(array.get(i));
   }*/

   if ((lhsStr != null) && (lhsStr.length() > 0))
      inputVal = new Double(lhsStr);
   if ((rhsStr != null) && (rhsStr.length() > 0))
      rhsVal = new Double(rhsStr);
   if (operation.equals(Operation1))
   {
         double x = new Double(inputVal.doubleValue());
         array.add(x);

         StringBuffer sb = new StringBuffer();
      
         for (Double s : array) {
            sb.append(s);
            sb.append(", ");
         }
         //print the current list
         rsltlist = sb.toString();
        // rsltlist= Arrays.toString(array);

   
   }
   else if (operation.equals(Reset))
   {
      
      array.clear();
   }
   else if (operation.equals(OperationMean))
   {
      //rslt = new Float(inputVal.floatValue() + rhsVal.floatValue());
      double sum = 0;
      for (int i = 0; i < array.size(); i++) {
         sum += array.get(i);
      }
      //sum over size = mean
      rslt= sum / array.size();
   }
   else if (operation.equals(OperationMode))
   {
      double mode=0.0;
      int maxCount = 0;
      int length = array.size();
      //iterate through and check how many times each num appears
      for (int i = 0; i < length; ++i) 
      {
         //curr count
          int count = 0;
          for (int j = 0; j < length; ++j) 
          {
              if (Double.compare(array.get(i), array.get(j))==0) {
                  ++count;
              }
              //if the current is greater then the max we have seen 
              if (count > maxCount) 
              {
              maxCount = count;
              mode = array.get(i);
              }
          }           
      }
      //set our mode
      rslt= mode;
   }
   else if (operation.equals(OperationMedian))
   {
      Collections.sort(array);
      double median;
      //check if length is even or odd
      if (array.size() % 2 == 0){
         median = (array.get(array.size()/2) + (double)array.get(array.size()/2 - 1))/2;
      }
      else{
         median =  array.get(array.size()/2);
      }
         rslt= median;
   }
   else if (operation.equals(OperationStd))
   {
      //rslt = new Float(inputVal.floatValue() - rhsVal.floatValue());
      //rslt=stats.getStandardDeviation();

      double sum = 0.0;
      for (int i = 0; i < array.size(); i++) {
         sum += array.get(i);
      }
      //calculate mean
      double mean = sum / array.size();
      double cursum = 0;
      //calclate the differences 
      for (int i = 0; i < array.size(); i++)
      {
         double val = array.get(i);
         double sqred = Math.pow(val - mean, 2);
         cursum += sqred;
      }
      //calculatle the diffs 
      double mdiffs = (double) cursum / (double) (array.size());
      rslt= Math.sqrt(mdiffs);
   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out, lhsStr, rsltlist, rslt.toString());
   PrintTail(out);
}  // End doPost

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out);
   PrintTail(out);
} // End doGet

/** *****************************************************
 *  Prints the <head> of the HTML page, no <body>.
********************************************************* */
private void PrintHead (PrintWriter out)
{
   out.println("<html>");
   out.println("");

   out.println("<head>");
   out.println("<title>Final exam</title>");
   out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String lhs, String rsltlist, String rslt)
{
   out.println("<body>");
   out.println("<p>");
   out.println("A simple example that demonstrates how to operate with");
   out.println("multiple submit buttons.");
   out.println("</p>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"/finalexam\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>Input number:");
   out.println("   <td><input type=\"text\" name=\"LHS\" value=\"" + lhs + "\" size=5>");
   out.println("  </tr>");
   //out.println("  <tr>");
   //out.println("   <td>Second value:");
  // out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rhs + "\" size=5>");
  // out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=6>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td> Current List:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rsltlist + "\" size=6>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   
   out.println(" <input type=\"submit\" value=\"" + Operation1 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationMean + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationMode + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationMedian + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + OperationStd + "\" name=\"Operation\">");
   out.println(" <input type=\"reset\" value=\"" + Reset + "\" name=\"Operation\">");
   out.println("</form>");
   out.println("");
   out.println("</body>");
} // End PrintBody

/** *****************************************************
 *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "", "", "");
}

/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail

}  // End twoButtons