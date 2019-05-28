package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関係のコントローラ.
 * 
 * @author momoyo kanie
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;

	/**
	 * 管理者登録フォームをインスタンス化する.
	 * 
	 * @return 管理者登録フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 管理者ログインフォームをインスタンス化する.
	 * 
	 * @return 管理者ログインフォーム
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面に遷移する.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * 管理者登録画面に遷移する.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 入力された管理者情報
	 * @return ログイン画面（リダイレクト）
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);

		return "redirect:/";
	}
	
	
	@RequestMapping("/login")
	public String login(LoginForm loginForm, Model model) {
		
		Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
		
		if(administrator != null) {			
			session.setAttribute("administratorName", administrator.getName());			
		}else {
			model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です。");
		}

		return "forward:/employee/showList";
		
	}
	
	

}
