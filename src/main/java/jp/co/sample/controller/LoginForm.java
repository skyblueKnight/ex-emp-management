package jp.co.sample.controller;

/**
 * 管理者ログインフォーム.
 * 
 * @author momoyo kanie
 *
 */
public class LoginForm {

	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	
	
	// ----- setter getter ----- //
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	
}
