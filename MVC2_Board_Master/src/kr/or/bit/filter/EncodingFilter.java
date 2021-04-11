package kr.or.bit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


@WebFilter(
		description = "어노테이션 활용 필터적용하기", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8", description = "모든페이지 한글 처리")
		})
public class EncodingFilter implements Filter {
	
	private String encoding;
	
    
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
    	this.encoding = fConfig.getInitParameter("encoding");
    	System.out.println("Filter init");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//요청
//		if(request.getCharacterEncoding()==null) {
//			request.setCharacterEncoding(this.encoding);
//		}
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		
		//응답
	}

	

}