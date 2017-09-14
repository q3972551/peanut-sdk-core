package com.mail;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.common.ModuleCenter;
import com.common.ModuleDTO;
import net.sf.json.JSONObject;

public class MailDataDTO implements ModuleDTO{
	private String         m_roleid     = null;
	private MailTitle      m_title      = null;
	private MailContent    m_content    = null;
	private MailAttachment m_attachment = null;
	private ModuleCenter   m_center     = ModuleCenter.Mail;

	public String getRoleid() {
		return m_roleid;
	}

	public void setRoleid(String roleid) {
		this.m_roleid = roleid;
	}

	public MailTitle getTitle() {
		return m_title;
	}

	public void setTitle(MailTitle title) {
		this.m_title = title;
	}

	public MailContent getContent() {
		return m_content;
	}

	public void setContent(MailContent content) {
		this.m_content = content;
	}

	public MailAttachment getAttachment() {
		return m_attachment;
	}

	public void setAttachment(MailAttachment attachment) {
		this.m_attachment = attachment;
	}

	public MailDataDTO() {

	}

	public MailDataDTO(MailTitle title, MailContent content, String roleId) {
		m_title   = title;
		m_content = content;
		m_roleid  = roleId;
		this.init();
	}

	public MailDataDTO(MailTitle title, MailContent content,
			MailAttachment attachment, String roleId) {
		m_title      = title;
		m_content    = content;
		m_attachment = attachment;
		m_roleid     = roleId;
	}

	private void init() {

	}

	@Override
	public JSONObject toJSON() {
		JSONObject js = new JSONObject();
		try {

			Field[] fields = this.getClass().getDeclaredFields(); // 鑾峰彇瀹炰綋绫荤殑鎵�鏈夊睘鎬э紝杩斿洖Field鏁扮粍聽

			for (Field field : fields) {
				String name = field.getName();

				if (!name.equals("m_center"))
				{
					name = name.substring(2, 3).toUpperCase() + name.substring(3);
					Method m     = (Method) this.getClass().getMethod("get" + name);

					Object value = m.invoke(this);
					if (value != null) {
						js.put(name.substring(0, 1).toLowerCase()+ name.substring(1), value.toString());
					}
				}

			}

			return js;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {

		return this.toJSON().toString();
	}

	@Override
	public ModuleCenter getCenter()
	{
		// TODO Auto-generated method stub
		return m_center;
	}

}
