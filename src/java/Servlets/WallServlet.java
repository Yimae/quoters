/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author Kva
 */
import KQT.QuoteKQT;
import Entity.Quote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "WallServlet", urlPatterns = ("/wall"))
public class WallServlet extends HttpServlet{
    @EJB
    private QuoteKQT quoteKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        ServletContext ctx = getServletConfig().getServletContext();
        Integer personId = (Integer)sess.getAttribute("personId");
        
        Integer ownerId = personId;
        if(hsrqst.getParameter("ownerId") != null){
            ownerId = Integer.parseInt(hsrqst.getParameter("ownerId"));
        }
        List<Quote> quotes = null;
        if(hsrqst.getParameter("filter") != null && hsrqst.getParameter("filter").equals("popular")){ 
            quotes = quoteKQT.topTenFor(ownerId);
        }else{
            quotes = quoteKQT.wallFor(ownerId);
        }
            hsrqst.setAttribute("quotess", quotes);
            hsrqst.setAttribute("wallOnwerId", ownerId);
            ctx.getRequestDispatcher("/wall.jsp").forward(hsrqst, hsrsp);
    }
    
    @Override
    protected void doGet(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    @Override
    public String getServletInfo(){
        return "Short description";
    }
}
