package my.pro.job.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@AllArgsConstructor
public class MailDTO {

	private String from;
	private String to;
	private String subject;
	private String text;
	private String img;
	private String template;
	private Map<String, Object> props;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFrom() {
		return from;
	}
	
	public MailDTO() {
		super();
	}
	public Map<String, Object> getProps() {
		return props;
	}
	public void setProps(Map<String, Object> props) {
		this.props = props;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
}
