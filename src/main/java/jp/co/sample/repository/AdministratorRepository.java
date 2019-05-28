package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者テーブルのリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
@Repository
public class AdministratorRepository {

	/** 管理者オブジェクトを生成するRowMapper. */
	private final static RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 管理者の情報を登録する.
	 * 
	 * @param administrator 登録する管理者情報
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "INSERT INTO administrators(name, mail_address, password) VALUES(:name, :mailAddress, :password)";
		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を検索する.
	 * 
	 * @param mailAddress 検索するメールアドレス
	 * @param password    検索するパスワード
	 * @return 取得した管理者情報（存在しない場合はnull）
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address = :mailAddress AND password = :password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);
		try {
			Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		} catch (DataAccessException e) {
			return null;
		}
	}

}
