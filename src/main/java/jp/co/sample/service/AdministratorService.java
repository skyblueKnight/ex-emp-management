package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.repository.AdministratorRepository;

/**
 * 従業員登録情報を操作するサービス.
 * 
 * @author momoyo kanie
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository; 
	
}
