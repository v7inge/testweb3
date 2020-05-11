package testweb3.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import testweb3.beans.Message;
import testweb3.beans.UserAccount;
import testweb3.utils.DBUtils;
import testweb3.utils.MyUtils;
 
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ChatServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        HttpSession session = request.getSession();
 
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Handle contact history request
        if (request.getAttribute("contact") != null) {
        	JSONObject jsonEnt = new JSONObject();
            
        	ArrayList<Message> history = new ArrayList();
            String errorString = null;
            Connection conn = MyUtils.getStoredConnection(request);
            try {
            	history = DBUtils.getHistory(conn, loginedUser.getUserName(), request.getAttribute("contact").toString());
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            jsonEnt.put("contactHistory", history);
            PrintWriter out = response.getWriter();
        	out.write(jsonEnt.toString());
        	return;
        }   

        request.setAttribute("user", loginedUser);
 
        String errorString = null;
        Connection conn = MyUtils.getStoredConnection(request);
        
        // Not used: Fill message history
        /*
        ArrayList<Message> history = new ArrayList();
        try {
        	history = DBUtils.getHistory(conn, loginedUser.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("history", history);
        */
        
        // Fill user list
        ArrayList<UserAccount> userList = new ArrayList();
        try {
        	userList = DBUtils.userList(conn, loginedUser.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
        request.setAttribute("userList", userList);
        
        request.setAttribute("errorString", errorString);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String contact = "";
        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            sb.append(line);

        try {
            
        	String jsonString = sb.toString();
        	JSONObject jsonObject =  new JSONObject(jsonString);
        	
        	switch (jsonObject.getString("queryType")) {
        		case "contact_clicked":
        			contact = jsonObject.getString("contact");
        			request.setAttribute("contact", contact);            	
        	        doGet(request, response);
        	        break;
        		case "message_sent":
        			String messageString = jsonString.replace("\"queryType\":\"message_sent\",", "");
        			jsonObject.remove("queryType"); 
        			Connection conn = MyUtils.getStoredConnection(request);
        			StringReader stringReader = new StringReader(messageString);
        			ObjectMapper mapper = new ObjectMapper();
        			Message message = mapper.readValue(stringReader, Message.class);
        			try {
        				DBUtils.saveMessage(conn, message);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        			break;
        	}
        } catch (JSONException e) {
        }

        
    }
 
}