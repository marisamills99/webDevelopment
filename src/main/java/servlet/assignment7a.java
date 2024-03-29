import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
 
//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
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
        name = "assignment7a",
        urlPatterns = {"/assignment7a"}
    )
public class assignment7a extends HttpServlet
{
 
// Location of servlet.
static String Domain  = "cs.gmu.edu:8443";
static String Path    = "/offutt/servlet/";
static String Servlet = "assignment7a";
 
// Button labels
static String Operation1 = "Add to List";
static String Operation2 = "Print list from file";
static String ResultSorted = "Sorted List";
static String ResultRevSorted = "Reverse Sorted List";
static String Random = "Random without replacement";
static String Randomreplace = "Random with replacement";
//required element 3
static String RemoveDuplicate = "Remove Duplicate Item";
//required element 1
//removes any string that contains < or > sign
static String VandS = "Validate and Sanitize Items that contain < or > signs";
static String Reset = "Reset Result";
 
// Other strings.
static String Style ="https://mason.gmu.edu/~mmills20/style.css";
//file
static String RESOURCE_FILE = "entries.txt";
// my list
List<String> array = new ArrayList<String>();
/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts the values in the form, performs the operation
 *  indicated by the submit button, and sends the results
 *  back to the client.
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
    RequestDispatcher rd=request.getRequestDispatcher("/assignment7b");  
    rd.forward(request, response);  
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
   out.println("<title>Assignment 7</title>");
   out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
   out.println("</head>");
   out.println("");
} // End PrintHead
 
/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String newStr, String rhs, String rslt)
{
   out.println("<body>");
   out.println("<div class=\"header\"><h1>SWE 432 ASSIGNMENT 7 </h1></div>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"/assignment7a\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>Add word here:");
   out.println("   <td><input type=\"text\" name=\"newstr\" value=\"" + newStr + "\" size=5>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=100>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + Operation1 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Operation2 + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + ResultSorted + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + ResultRevSorted + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Random + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Randomreplace + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + RemoveDuplicate + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + VandS + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + Reset + "\" name=\"Operation\">");
   out.println("</form>");
   out.println("");
   //collab summary
   out.println("<p>");
   out.println("Group Members: Marisa Mills, Ximena Perez, Saman Fatima");
   out.println("</p>");
   out.println("<p>");   
 
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
 
}  // End assignment7a