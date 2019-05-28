package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を操作するサービス.
 * 
 * @author momoyo kanie
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	/**
	 * 従業員情報一覧を取得する.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	
	/**
	 * IDから従業員情報を取得する.
	 * 
	 * @param id 検索するID
	 * @return 取得した従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
}
