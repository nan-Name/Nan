package com.dao.appcategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.pojo.AppCategory;

public interface AppCategoryMapper {
	
	public List<AppCategory> getAppCategoryListByParentId(@Param("parentId")Integer parentId)throws Exception;
}
