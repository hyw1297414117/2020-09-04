package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.text.Convert;
import com.project.app.imperial.domain.ImpDicItem;
import com.project.app.imperial.domain.ImpOrdercheckHis;
import com.project.app.imperial.mapper.ImpDicItemMapper;
import com.project.app.imperial.mapper.ImpOrdercheckHisMapper;
import com.project.app.imperial.service.IImpDicItemService;
import com.project.app.imperial.service.IImpOrdercheckHisService;

/**
 * 字典条目操作Service业务层处理
 * 
 * @author fanke
 * @date 2020-07-15
 */
@Service
public class ImpOrdercheckHisServiceImpl implements IImpOrdercheckHisService 
{
    @Autowired
    private ImpOrdercheckHisMapper impOrdercheckHisMapper;

	@Override
	public ImpOrdercheckHis selectImpDicItemById(Long id) {
		// TODO Auto-generated method stub
		return impOrdercheckHisMapper.selectImpDicItemById(id);
	}

	@Override
	public List<ImpOrdercheckHis> selectImpOrdercheckHisList(ImpOrdercheckHis impOrdercheckHis) {
		// TODO Auto-generated method stub
		return impOrdercheckHisMapper.selectImpOrdercheckHisList(impOrdercheckHis);
	}

	@Override
	public int insertOrdercheckHis(ImpOrdercheckHis impOrdercheckHis) {
		// TODO Auto-generated method stub
		return impOrdercheckHisMapper.insertOrdercheckHis(impOrdercheckHis);
	}

   
}
