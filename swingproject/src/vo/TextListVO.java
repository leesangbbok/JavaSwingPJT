package vo;

public class TextListVO {
	//TITLE CONTENTS REGTEXT USERID
	private int textIndex ;
	private String title;
	private String contents;
	private String regText;
	private String userId;
	
	public TextListVO() {}
	
	public TextListVO(String contents) {
		this.contents = contents;
	}
	
	public TextListVO(int textIndex, String title, String contents, String regText, String userId) {
		this.textIndex = textIndex;
		this.title = title;
		this.contents = contents;
		this.regText = regText;
		this.userId = userId;
	}

	public int getTextIndex() {
		return textIndex;
	}

	public void setTextIndex(int textIndex) {
		this.textIndex = textIndex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegText() {
		return regText;
	}

	public void setRegText(String regText) {
		this.regText = regText;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
