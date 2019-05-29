package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員関係のコントローラ.
 * 
 * @author momoyo kanie
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員情報更新時に使用するフォームをインスタンス化する.
	 * 
	 * @return 従業員情報更新時に使用するフォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * 従業員一覧を出力する.
	 * 
	 * @param model モデル
	 * @return 従業員一覧表示画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);

		return "employee/list";
	}

	/**
	 * 選択されたIDから従業員情報を取得して出力する.
	 * 
	 * @param id    選択されたID
	 * @param model モデル
	 * @return 従業員情報詳細表示画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);

		return "employee/detail";
	}

	/**
	 * 従業員情報を更新する.<br>
	 * ここでは扶養人数のみを更新する。 数字以外の数値が渡された場合は、詳細画面に戻りエラーメッセージを表示。
	 * 
	 * @param form  更新する従業員情報
	 * @param model モデル
	 * @return 通常時：従業員一覧画面（リダイレクト）/ エラー時：従業員詳細画面
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}

		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);

		return "redirect:/employee/showList";
	}

}
