package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員テーブルのリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
@Repository
public class EmployeeRepository {

	/** 従業員オブジェクトを生成するRowMapper. */
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee emoployee = new Employee();
		emoployee.setId(rs.getInt("id"));
		emoployee.setName(rs.getString("name"));
		emoployee.setImage(rs.getString("image"));
		emoployee.setGender(rs.getString("gender"));
		emoployee.setHireDate(rs.getDate("hire_date"));
		emoployee.setMailAddress(rs.getString("mail_address"));
		emoployee.setZipCode(rs.getString("zip_code"));
		emoployee.setTelephone(rs.getString("telephone"));
		emoployee.setSalary(rs.getInt("salary"));
		emoployee.setCharacteristics(rs.getString("characteristics"));
		emoployee.setDependentsCount(rs.getInt("dependentsCount"));

		return emoployee;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日順で取得する.
	 * 
	 * @return 従業員一覧情報
	 */
	public List<Employee> findAll() {
		
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees;";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		return employeeList;
	}
	
	
	/**
	 * 主キーから従業員情報を取得する.
	 * 
	 * @param id 検索するID
	 * @return 取得した従業員情報
	 */
	public Employee load(Integer id) {
		
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
				+ " FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	
	/**
	 * 従業員情報を変更する.<br>
	 * 従業員情報のうち、扶養人数だけを更新する.
	 * 
	 * @param employee 変更する従業員情報
	 */
	public void update(Employee employee) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id;";
		template.update(sql, param);
		
	}

}
