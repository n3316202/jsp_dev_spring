package edu.bit.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispacherServlet
 */
@WebServlet("/")
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public DispacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException{
    	handlerMapping = new HandlerMapping();
    	viewResolver = new ViewResolver();
    	viewResolver.setPrefix("./");
    	viewResolver.setSuffix(".jsp");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ECU-KR");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//1. 클라이언트 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		//2.HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);
		
		//3.검색된 Controller를 실행한다.
		String viewName = ctrl.handleRequest(request, response);
		
		//4.ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		}else {
			view = viewName;
		}
		
		//검색된 화면으로 이동한다.	
		
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
