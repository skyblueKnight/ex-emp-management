package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 従業員情報更新時に使用するフォーム.
 * 
 * @author momoyo kanie
 *
 */
public class UpdateEmployeeForm {
	
	/** 従業員ID */
	private String id;
	/** 扶養人数	*/
	@Pattern(regexp = "[1-9]+", message="正しい値を入力してください")
	@NotBlank(message="扶養人数を入力してください")
	private String dependentsCount;
	
	// ----- setter getter ----- //
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	
	
	
	
}
