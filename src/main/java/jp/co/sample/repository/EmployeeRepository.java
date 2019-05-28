package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) -> {
		/** 従業員オブジェクトを生成するRowMapper. */
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
	
	
	public List<Employee> findAll(){
		
	})

}
