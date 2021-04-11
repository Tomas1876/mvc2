package kr.or.bit.action;

//servlet 요청 받는다
//1. 화면보여주세요
//2. 처리해주세요 >> 로직제어 해주세요

//화면이냐 로직이냐
//뷰의 경로는 어디냐
// >> 이 두가지를 담을 수 있는 class
public class ActionForward {
	private boolean isRedirect = false; //화면 전환 여부
	private String path=null; // 이동경로(뷰의 주소)
	
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
