package com.dao.backenduser;
import org.apache.ibatis.annotations.Param;

import com.pojo.BackendUser;

public interface BackendUserMapper {

	/**
	 * ͨ��userCode��ȡUser
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public BackendUser getLoginUser(@Param("userCode")String userCode)throws Exception;

}
